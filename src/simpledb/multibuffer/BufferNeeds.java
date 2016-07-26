package simpledb.multibuffer;

import simpledb.server.SimpleDB;

/**
 * A class containing static methods,
 * which estimate the optimal number of buffers
 * to allocate for a scan.
 * @author Edward Sciore
 */
public class BufferNeeds {
   
   /**
    * This method considers the various roots
    * of the specified output size (in blocks),
    * and returns the highest root that is less than
    * the number of available buffers.
    * @param size the size of the output file
    * @return the highest number less than the number of available buffers, that is a root of the plan's output size
    */
   public static int bestRoot(int size) {
      int avail = SimpleDB.bufferMgr().available();
      if (avail <= 1)
         return 1;
      int k = Integer.MAX_VALUE;
      double i = 1.0;
      while (k > avail) {
         i++;
         k = (int)Math.ceil(Math.pow(size, 1/i));
      }
      return k;
   }
   
   /**
    * This method considers the various factors
    * of the specified output size (in blocks),
    * and returns the highest factor that is less than
    * the number of available buffers.
    * @param size the size of the output file
    * @return the highest number less than the number of available buffers, that is a factor of the plan's output size
    */
   public static int bestFactor(int size) {
      int avail = SimpleDB.bufferMgr().available();
      if (avail <= 1)
         return 1;
      int k = size;
      double i = 1.0;
      while (k > avail) {
         i++;
         k = (int)Math.ceil(size / i);
      }
      return k;
   }
}
