package simpledb.index.query;

import simpledb.query.*;
import simpledb.index.Index;

/**
 * The scan class corresponding to the indexjoin relational
 * algebra operator.
 * The code is very similar to that of ProductScan, 
 * which makes sense because an index join is essentially
 * the product of each LHS record with the matching RHS index records.
 * @author Edward Sciore
 */
public class IndexJoinScan implements Scan {
   private Scan s;
   private TableScan ts;  // the data table
   private Index idx;
   private String joinfield;
   
   /**
    * Creates an index join scan for the specified LHS scan and 
    * RHS index.
    * @param s the LHS scan
    * @param idx the RHS index
    * @param joinfield the LHS field used for joining
    */
   public IndexJoinScan(Scan s, Index idx, String joinfield, TableScan ts) {
      this.s = s;
      this.idx  = idx;
      this.joinfield = joinfield;
      this.ts = ts;
      beforeFirst();
   }
   
   /**
    * Positions the scan before the first record.
    * That is, the LHS scan will be positioned at its
    * first record, and the index will be positioned
    * before the first record for the join value.
    * @see simpledb.query.Scan#beforeFirst()
    */
   public void beforeFirst() {
      s.beforeFirst();
      s.next();
      resetIndex();
   }
   
   /**
    * Moves the scan to the next record.
    * The method moves to the next index record, if possible.
    * Otherwise, it moves to the next LHS record and the
    * first index record.
    * If there are no more LHS records, the method returns false.
    * @see simpledb.query.Scan#next()
    */
   public boolean next() {
      while (true) {
         if (idx.next()) {
            ts.moveToRid(idx.getDataRid());
            return true;
         }
         if (!s.next())
            return false;
         resetIndex();
      }
   }
   
   /**
    * Closes the scan by closing its LHS scan and its RHS index.
    * @see simpledb.query.Scan#close()
    */
   public void close() {
      s.close();
      idx.close();
      ts.close();
   }
   
   /**
    * Returns the Constant value of the specified field.
    * @see simpledb.query.Scan#getVal(java.lang.String)
    */
   public Constant getVal(String fldname) {
      if (ts.hasField(fldname))
         return ts.getVal(fldname);
      else
         return s.getVal(fldname);
   }
   
   /**
    * Returns the integer value of the specified field.
    * @see simpledb.query.Scan#getVal(java.lang.String)
    */
   public int getInt(String fldname) {
      if (ts.hasField(fldname))
         return ts.getInt(fldname);
      else  
         return s.getInt(fldname);
   }
   
   /**
    * Returns the string value of the specified field.
    * @see simpledb.query.Scan#getVal(java.lang.String)
    */
   public String getString(String fldname) {
      if (ts.hasField(fldname))
         return ts.getString(fldname);
      else
         return s.getString(fldname);
   }
   
   /** Returns true if the field is in the schema.
     * @see simpledb.query.Scan#hasField(java.lang.String)
     */
   public boolean hasField(String fldname) {
      return ts.hasField(fldname) || s.hasField(fldname);
   }
   
   private void resetIndex() {
      Constant searchkey = s.getVal(joinfield);
      idx.beforeFirst(searchkey);
   }
}
