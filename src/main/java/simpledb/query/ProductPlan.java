package simpledb.query;

import simpledb.record.Schema;

/** The Plan class corresponding to the <i>product</i>
  * relational algebra operator.
  * @author Edward Sciore
  */
public class ProductPlan implements Plan {
   private Plan p1, p2;
   private Schema schema = new Schema();
   
   /**
    * Creates a new product node in the query tree,
    * having the two specified subqueries.
    * @param p1 the left-hand subquery
    * @param p2 the right-hand subquery
    */
   public ProductPlan(Plan p1, Plan p2) {
      this.p1 = p1;
      this.p2 = p2;
      schema.addAll(p1.schema());
      schema.addAll(p2.schema());
   }
   
   /**
    * Creates a product scan for this query.
    * @see simpledb.query.Plan#open()
    */
   public Scan open() {
      Scan s1 = p1.open();
      Scan s2 = p2.open();
      return new ProductScan(s1, s2);
   }
   
   /**
    * Estimates the number of block accesses in the product.
    * The formula is:
    * <pre> B(product(p1,p2)) = B(p1) + R(p1)*B(p2) </pre>
    * @see simpledb.query.Plan#blocksAccessed()
    */
   public int blocksAccessed() {
      return p1.blocksAccessed() + (p1.recordsOutput() * p2.blocksAccessed());
   }
   
   /**
    * Estimates the number of output records in the product.
    * The formula is:
    * <pre> R(product(p1,p2)) = R(p1)*R(p2) </pre>
    * @see simpledb.query.Plan#recordsOutput()
    */
   public int recordsOutput() {
      return p1.recordsOutput() * p2.recordsOutput();
   }
   
   /**
    * Estimates the distinct number of field values in the product.
    * Since the product does not increase or decrease field values,
    * the estimate is the same as in the appropriate underlying query.
    * @see simpledb.query.Plan#distinctValues(java.lang.String)
    */
   public int distinctValues(String fldname) {
      if (p1.schema().hasField(fldname))
         return p1.distinctValues(fldname);
      else
         return p2.distinctValues(fldname);
   }
   
   /**
    * Returns the schema of the product,
    * which is the union of the schemas of the underlying queries.
    * @see simpledb.query.Plan#schema()
    */
   public Schema schema() {
      return schema;
   }
}
