package simpledb.file;

import static simpledb.file.Page.BLOCK_SIZE;
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.*;

/**
 * The SimpleDB file manager.
 * The database system stores its data as files within a specified directory.
 * The file manager provides methods for reading the contents of
 * a file block to a Java byte buffer,
 * writing the contents of a byte buffer to a file block,
 * and appending the contents of a byte buffer to the end of a file.
 * These methods are called exclusively by the class {@link simpledb.file.Page Page},
 * and are thus package-private.
 * The class also contains two public methods:
 * Method {@link #isNew() isNew} is called during system initialization by {@link simpledb.server.SimpleDB#init}.
 * Method {@link #size(String) size} is called by the log manager and transaction manager to
 * determine the end of the file.
 * @author Edward Sciore
 */
public class FileMgr {
   private File dbDirectory;
   private boolean isNew;
   private Map<String,FileChannel> openFiles = new HashMap<String,FileChannel>();

   /**
    * Creates a file manager for the specified database.
    * The database will be stored in a folder of that name
    * in the user's home directory.
    * If the folder does not exist, then a folder containing
    * an empty database is created automatically.
    * Files for all temporary tables (i.e. tables beginning with "temp") are deleted.
    * @param dbname the name of the directory that holds the database
    */
   public FileMgr(String dbname) {
      String homedir = System.getProperty("user.home");
      dbDirectory = new File(homedir, dbname);
      isNew = !dbDirectory.exists();

      // create the directory if the database is new
      if (isNew && !dbDirectory.mkdir())
         throw new RuntimeException("cannot create " + dbname);

      // remove any leftover temporary tables
      for (String filename : dbDirectory.list())
         if (filename.startsWith("temp"))
         new File(dbDirectory, filename).delete();
   }

   /**
    * Reads the contents of a disk block into a bytebuffer.
    * @param blk a reference to a disk block
    * @param bb  the bytebuffer
    */
   synchronized void read(Block blk, ByteBuffer bb) {
      try {
         bb.clear();
         FileChannel fc = getFile(blk.fileName());
         fc.read(bb, blk.number() * BLOCK_SIZE);
      }
      catch (IOException e) {
         throw new RuntimeException("cannot read block " + blk);
      }
   }

   /**
    * Writes the contents of a bytebuffer into a disk block.
    * @param blk a reference to a disk block
    * @param bb  the bytebuffer
    */
   synchronized void write(Block blk, ByteBuffer bb) {
      try {
         bb.rewind();
         FileChannel fc = getFile(blk.fileName());
         fc.write(bb, blk.number() * BLOCK_SIZE);
      }
      catch (IOException e) {
         throw new RuntimeException("cannot write block" + blk);
      }
   }

   /**
    * Appends the contents of a bytebuffer to the end
    * of the specified file.
    * @param filename the name of the file
    * @param bb  the bytebuffer
    * @return a reference to the newly-created block.
    */
   synchronized Block append(String filename, ByteBuffer bb) {
      int newblknum = size(filename);
      Block blk = new Block(filename, newblknum);
      write(blk, bb);
      return blk;
   }

   /**
    * Returns the number of blocks in the specified file.
    * @param filename the name of the file
    * @return the number of blocks in the file
    */
   public synchronized int size(String filename) {
      try {
         FileChannel fc = getFile(filename);
         return (int)(fc.size() / BLOCK_SIZE);
      }
      catch (IOException e) {
         throw new RuntimeException("cannot access " + filename);
      }
   }

   /**
    * Returns a boolean indicating whether the file manager
    * had to create a new database directory.
    * @return true if the database is new
    */
   public boolean isNew() {
      return isNew;
   }

   /**
    * Returns the file channel for the specified filename.
    * The file channel is stored in a map keyed on the filename.
    * If the file is not open, then it is opened and the file channel
    * is added to the map.
    * @param filename the specified filename
    * @return the file channel associated with the open file.
    * @throws IOException
    */
   private FileChannel getFile(String filename) throws IOException {
      FileChannel fc = openFiles.get(filename);
      if (fc == null) {
         File dbTable = new File(dbDirectory, filename);
         RandomAccessFile f = new RandomAccessFile(dbTable, "rws");
         fc = f.getChannel();
         openFiles.put(filename, fc);
      }
      return fc;
   }
}
