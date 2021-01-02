package simpledb.materialize;

import simpledb.tx.Transaction;
import simpledb.record.Schema;
import simpledb.query.*;
import java.util.*;

/**
 * The Plan class for the <i>groupby</i> operator.
 * @author Edward Sciore
 */
public class GroupByPlan implements Plan {
   private Plan p;
   private Collection<String> groupfields;
   private Collection<AggregationFn> aggfns;
   private Schema sch = new Schema();
   
   /**
    * Creates a groupby plan for the underlying query.
    * The grouping is determined by the specified
    * collection of group fields,
    * and the aggregation is computed by the
    * specified collection of aggregation functions.
    * @param p a plan for the underlying query
    * @param groupfields the group fields
    * @param aggfns the aggregation functions
    * @param tx the calling transaction
    */
   public GroupByPlan(Plan p, Collection<String> groupfields, Collection<AggregationFn> aggfns, Transaction tx) {
      List<String> grouplist = new ArrayList<String>();
      grouplist.addAll(groupfields);
      this.p = new SortPlan(p, grouplist, tx);
      this.groupfields = groupfields;
      this.aggfns = aggfns;
      for (String fldname : groupfields)
         sch.add(fldname, p.schema());
      for (AggregationFn fn : aggfns)
         sch.addIntField(fn.fieldName());
   }
   
   /**
    * This method opens a sort plan for the specified plan.
    * The sort plan ensures that the underlying records
    * will be appropriately grouped.
    * @see simpledb.query.Plan#open()
    */
   public Scan open() {
      Scan s = p.open();
      return new GroupByScan(s, groupfields, aggfns);
   }
   
   /**
    * Returns the number of blocks required to
    * compute the aggregation,
    * which is one pass through the sorted table.
    * It does <i>not</i> include the one-time cost
    * of materializing and sorting the records.
    * @see simpledb.query.Plan#blocksAccessed()
    */
   public int blocksAccessed() {
      return p.blocksAccessed();
   }
   
   /**
    * Returns the number of groups.  Assuming equal distribution,
    * this is the product of the distinct values
    * for each grouping field.
    * @see simpledb.query.Plan#recordsOutput()
    */
   public int recordsOutput() {
      int numgroups = 1;
      for (String fldname : groupfields)
         numgroups *= p.distinctValues(fldname);
      return numgroups;
   }
   
   /**
    * Returns the number of distinct values for the
    * specified field.  If the field is a grouping field,
    * then the number of distinct values is the same
    * as in the underlying query.
    * If the field is an aggregate field, then we
    * assume that all values are distinct.
    * @see simpledb.query.Plan#distinctValues(java.lang.String)
    */
   public int distinctValues(String fldname) {
      if (p.schema().hasField(fldname))
         return p.distinctValues(fldname);
      else
         return recordsOutput();
   }
   
   /**
    * Returns the schema of the output table.
    * The schema consists of the group fields,
    * plus one field for each aggregation function.
    * @see simpledb.query.Plan#schema()
    */
   public Schema schema() {
      return sch;
   }
}
