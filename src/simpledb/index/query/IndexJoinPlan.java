package simpledb.index.query;

import simpledb.tx.Transaction;
import simpledb.record.Schema;
import simpledb.metadata.IndexInfo;
import simpledb.query.*;
import simpledb.index.Index;

/** The Plan class corresponding to the <i>indexjoin</i>
  * relational algebra operator.
  * @author Edward Sciore
  */
public class IndexJoinPlan implements Plan {
   private Plan p1, p2;
   private IndexInfo ii;
   private String joinfield;
   private Schema sch = new Schema();
   
   /**
    * Implements the join operator,
    * using the specified LHS and RHS plans.
    * @param p1 the left-hand plan
    * @param p2 the right-hand plan
    * @param ii information about the right-hand index
    * @param joinfield the left-hand field used for joining
    * @param tx the calling transaction
    */
   public IndexJoinPlan(Plan p1, Plan p2, IndexInfo ii, String joinfield, Transaction tx) {
      this.p1 = p1;
      this.p2 = p2;
      this.ii = ii;
      this.joinfield = joinfield;
      sch.addAll(p1.schema());
      sch.addAll(p2.schema());
   }
   
   /**
    * Opens an indexjoin scan for this query
    * @see simpledb.query.Plan#open()
    */
   public Scan open() {
      Scan s = p1.open();
      // throws an exception if p2 is not a tableplan
      TableScan ts = (TableScan) p2.open();
      Index idx = ii.open();
      return new IndexJoinScan(s, idx, joinfield, ts);
   }
   
   /**
    * Estimates the number of block accesses to compute the join.
    * The formula is:
    * <pre> B(indexjoin(p1,p2,idx)) = B(p1) + R(p1)*B(idx)
    *       + R(indexjoin(p1,p2,idx) </pre>
    * @see simpledb.query.Plan#blocksAccessed()
    */
   public int blocksAccessed() {
      return p1.blocksAccessed() 
         + (p1.recordsOutput() * ii.blocksAccessed())
         + recordsOutput();
   }
   
   /**
    * Estimates the number of output records in the join.
    * The formula is:
    * <pre> R(indexjoin(p1,p2,idx)) = R(p1)*R(idx) </pre>
    * @see simpledb.query.Plan#recordsOutput()
    */
   public int recordsOutput() {
      return p1.recordsOutput() * ii.recordsOutput();
   }
   
   /**
    * Estimates the number of distinct values for the 
    * specified field.  
    * @see simpledb.query.Plan#distinctValues(java.lang.String)
    */
   public int distinctValues(String fldname) {
      if (p1.schema().hasField(fldname))
         return p1.distinctValues(fldname);
      else
         return p2.distinctValues(fldname);
   }
   
   /**
    * Returns the schema of the index join.
    * @see simpledb.query.Plan#schema()
    */
   public Schema schema() {
      return sch;
   }
}
