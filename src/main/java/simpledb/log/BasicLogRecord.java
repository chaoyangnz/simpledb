package simpledb.log;

import static simpledb.file.Page.*;
import simpledb.file.Page;

/**
 * A class that provides the ability to read the values of
 * a log record.
 * The class has no idea what values are there.
 * Instead, the methods {@link #nextInt() nextInt}
 * and {@link #nextString() nextString} read the values 
 * sequentially.
 * Thus the client is responsible for knowing how many values
 * are in the log record, and what their types are.
 * @author Edward Sciore
 */
public class BasicLogRecord {
   private Page pg;
   private int pos;
   
   /**
    * A log record located at the specified position of the specified page.
    * This constructor is called exclusively by
    * {@link LogIterator#next()}.
    * @param pg the page containing the log record
    * @param pos the position of the log record 
    */
   public BasicLogRecord(Page pg, int pos) {
      this.pg = pg;
      this.pos = pos;
   }
   
   /**
    * Returns the next value of the current log record, 
    * assuming it is an integer.
    * @return the next value of the current log record
    */
   public int nextInt() {
      int result = pg.getInt(pos);
      pos += INT_SIZE;
      return result;
   }
   
   /**
    * Returns the next value of the current log record, 
    * assuming it is a string.
    * @return the next value of the current log record
    */
   public String nextString() {
      String result = pg.getString(pos);
      pos += STR_SIZE(result.length());
      return result;
   }
}
