package simpledb.file;

/**
 * A reference to a disk block.
 * A Block object consists of a filename and a block number.
 * It does not hold the contents of the block;
 * instead, that is the job of a {@link Page} object.
 * @author Edward Sciore
 */
public class Block {
   private String filename;
   private int blknum;
   
   /**
    * Constructs a block reference 
    * for the specified filename and block number.
    * @param filename the name of the file
    * @param blknum the block number
    */
   public Block(String filename, int blknum) {
      this.filename = filename;
      this.blknum   = blknum;
   }
   
   /**
    * Returns the name of the file where the block lives.
    * @return the filename
    */
   public String fileName() {
      return filename;
   }
   
   /**
    * Returns the location of the block within the file.
    * @return the block number
    */
   public int number() {
      return blknum;
   }
   
   public boolean equals(Object obj) {
      Block blk = (Block) obj;
      return filename.equals(blk.filename) && blknum == blk.blknum;
   }
   
   public String toString() {
      return "[file " + filename + ", block " + blknum + "]";
   }
   
   public int hashCode() {
      return toString().hashCode();
   }
}
