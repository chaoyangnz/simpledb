package simpledb.parse;

import simpledb.query.*;

/**
 * Data for the SQL <i>delete</i> statement.
 * @author Edward Sciore
 */
public class DeleteData {
   private String tblname;
   private Predicate pred;
   
   /**
    * Saves the table name and predicate.
    */
   public DeleteData(String tblname, Predicate pred) {
      this.tblname = tblname;
      this.pred = pred;
   }
   
   /**
    * Returns the name of the affected table.
    * @return the name of the affected table
    */
   public String tableName() {
      return tblname;
   }
   
   /**
    * Returns the predicate that describes which
    * records should be deleted.
    * @return the deletion predicate
    */
   public Predicate pred() {
      return pred;
   }
}

