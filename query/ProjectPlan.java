package simpledb.query;

import simpledb.record.Schema;
import java.util.Collection;

/** The Plan class corresponding to the <i>project</i>
  * relational algebra operator.
  * @author Edward Sciore
  */
public class ProjectPlan implements Plan {
   private Plan p;
   private Schema schema = new Schema();
   
   /**
    * Creates a new project node in the query tree,
    * having the specified subquery and field list.
    * @param p the subquery
    * @param fieldlist the list of fields
    */
   public ProjectPlan(Plan p, Collection<String> fieldlist) {
      this.p = p;
      for (String fldname : fieldlist)
         schema.add(fldname, p.schema());
   }
   
   /**
    * Creates a project scan for this query.
    * @see simpledb.query.Plan#open()
    */
   public Scan open() {
      Scan s = p.open();
      return new ProjectScan(s, schema.fields());
   }
   
   /**
    * Estimates the number of block accesses in the projection,
    * which is the same as in the underlying query.
    * @see simpledb.query.Plan#blocksAccessed()
    */
   public int blocksAccessed() {
      return p.blocksAccessed();
   }
   
   /**
    * Estimates the number of output records in the projection,
    * which is the same as in the underlying query.
    * @see simpledb.query.Plan#recordsOutput()
    */
   public int recordsOutput() {
      return p.recordsOutput();
   }
   
   /**
    * Estimates the number of distinct field values
    * in the projection,
    * which is the same as in the underlying query.
    * @see simpledb.query.Plan#distinctValues(java.lang.String)
    */
   public int distinctValues(String fldname) {
      return p.distinctValues(fldname);
   }
   
   /**
    * Returns the schema of the projection,
    * which is taken from the field list.
    * @see simpledb.query.Plan#schema()
    */
   public Schema schema() {
      return schema;
   }
}
