package simpledb.index.query;

import simpledb.record.RID;
import simpledb.query.*;
import simpledb.index.Index;

/**
 * The scan class corresponding to the select relational
 * algebra operator.
 * @author Edward Sciore
 */
public class IndexSelectScan implements Scan {
   private Index idx;
   private Constant val;
   private TableScan ts;
   
   /**
    * Creates an index select scan for the specified
    * index and selection constant.
    * @param idx the index
    * @param val the selection constant
    */
   public IndexSelectScan(Index idx, Constant val, TableScan ts) {
      this.idx = idx;
      this.val = val;
      this.ts  = ts;
      beforeFirst();
   }
   
   /**
    * Positions the scan before the first record,
    * which in this case means positioning the index
    * before the first instance of the selection constant.
    * @see simpledb.query.Scan#beforeFirst()
    */
   public void beforeFirst() {
      idx.beforeFirst(val);
   }
   
   /**
    * Moves to the next record, which in this case means
    * moving the index to the next record satisfying the
    * selection constant, and returning false if there are
    * no more such index records.
    * If there is a next record, the method moves the 
    * tablescan to the corresponding data record.
    * @see simpledb.query.Scan#next()
    */
   public boolean next() {
      boolean ok = idx.next();
      if (ok) {
         RID rid = idx.getDataRid();
         ts.moveToRid(rid);
      }
      return ok;
   }
   
   /**
    * Closes the scan by closing the index and the tablescan.
    * @see simpledb.query.Scan#close()
    */
   public void close() {
      idx.close();
      ts.close();
   }
   
   /**
    * Returns the value of the field of the current data record.
    * @see simpledb.query.Scan#getVal(java.lang.String)
    */
   public Constant getVal(String fldname) {
      return ts.getVal(fldname);
   }
   
   /**
    * Returns the value of the field of the current data record.
    * @see simpledb.query.Scan#getInt(java.lang.String)
    */
   public int getInt(String fldname) {
      return ts.getInt(fldname);
   }
   
   /**
    * Returns the value of the field of the current data record.
    * @see simpledb.query.Scan#getString(java.lang.String)
    */
   public String getString(String fldname) {
      return ts.getString(fldname);
   }
   
   /**
    * Returns whether the data record has the specified field.
    * @see simpledb.query.Scan#hasField(java.lang.String)
    */
   public boolean hasField(String fldname) {
      return ts.hasField(fldname);
   }
}
