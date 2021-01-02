package simpledb.parse;

/**
 * Data for the SQL <i>create view</i> statement.
 * @author Edward Sciore
 */
public class CreateViewData {
   private String viewname;
   private QueryData qrydata;
   
   /**
    * Saves the view name and its definition.
    */
   public CreateViewData(String viewname, QueryData qrydata) {
      this.viewname = viewname;
      this.qrydata = qrydata;
   }
   
   /**
    * Returns the name of the new view.
    * @return the name of the new view
    */
   public String viewName() {
      return viewname;
   }
   
   /**
    * Returns the definition of the new view.
    * @return the definition of the new view
    */
   public String viewDef() {
      return qrydata.toString();
   }
}
