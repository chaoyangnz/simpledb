package simpledb.materialize;

import simpledb.query.*;

/**
 * The <i>count</i> aggregation function.
 * @author Edward Sciore
 */
public class CountFn implements AggregationFn {
   private String fldname;
   private int count;
   
   /**
    * Creates a count aggregation function for the specified field.
    * @param fldname the name of the aggregated field
    */
   public CountFn(String fldname) {
      this.fldname = fldname;
   }
   
   /**
    * Starts a new count.
    * Since SimpleDB does not support null values,
    * every record will be counted,
    * regardless of the field.
    * The current count is thus set to 1.
    * @see simpledb.materialize.AggregationFn#processFirst(simpledb.query.Scan)
    */
   public void processFirst(Scan s) {
      count = 1;
   }
   
   /**
    * Since SimpleDB does not support null values,
    * this method always increments the count,
    * regardless of the field.
    * @see simpledb.materialize.AggregationFn#processNext(simpledb.query.Scan)
    */
   public void processNext(Scan s) {
      count++;
   }
   
   /**
    * Returns the field's name, prepended by "countof".
    * @see simpledb.materialize.AggregationFn#fieldName()
    */
   public String fieldName() {
      return "countof" + fldname;
   }
   
   /**
    * Returns the current count.
    * @see simpledb.materialize.AggregationFn#value()
    */
   public Constant value() {
      return new IntConstant(count);
   }
}
