package simpledb.index.btree;

import static simpledb.file.Page.*;
import static java.sql.Types.INTEGER;
import simpledb.file.Page;
import simpledb.buffer.PageFormatter;
import simpledb.record.TableInfo;

/**
 * An object that can format a page to look like an
 * empty B-tree block.
 * @author Edward Sciore
 */
public class BTPageFormatter implements PageFormatter {
   private TableInfo ti;
   private int flag;
   
   /**
    * Creates a formatter for a new page of the
    * specified B-tree index.
    * @param ti the index's metadata
    * @param flag the page's initial flag value
    */
   public BTPageFormatter(TableInfo ti, int flag) {
      this.ti = ti;
      this.flag = flag;
   }
   
   /** 
    * Formats the page by initializing as many index-record slots
    * as possible to have default values.
    * Each integer field is given a value of 0, and
    * each string field is given a value of "".
    * The location that indicates the number of records
    * in the page is also set to 0.
    * @see simpledb.buffer.PageFormatter#format(simpledb.file.Page)
    */
   public void format(Page page) {
      page.setInt(0, flag);
      page.setInt(INT_SIZE, 0);  // #records = 0
      int recsize = ti.recordLength();
      for (int pos=2*INT_SIZE; pos+recsize<=BLOCK_SIZE; pos += recsize)
         makeDefaultRecord(page, pos);
   }
   
   private void makeDefaultRecord(Page page, int pos) {
      for (String fldname : ti.schema().fields()) {
         int offset = ti.offset(fldname);
         if (ti.schema().type(fldname) == INTEGER)
            page.setInt(pos + offset, 0);
         else
            page.setString(pos + offset, "");
      }
   }
}
