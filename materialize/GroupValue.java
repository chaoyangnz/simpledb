package simpledb.materialize;

import simpledb.query.*;

import java.util.*;

/**
 * An object that holds the values of the grouping fields
 * for the current record of a scan.
 * @author Edward Sciore
 */
public class GroupValue {
   private Map<String,Constant> vals;
   
   /**
    * Creates a new group value, given the specified scan
    * and list of fields.
    * The values in the current record of each field are 
    * stored.
    * @param s a scan
    * @param fields the list of fields
    */
   public GroupValue(Scan s, Collection<String> fields) {
      vals = new HashMap<String,Constant>();
      for (String fldname : fields)
         vals.put(fldname, s.getVal(fldname));
   }
   
   /**
    * Returns the Constant value of the specified field in the group.
    * @param fldname the name of a field
    * @return the value of the field in the group
    */
   public Constant getVal(String fldname) {
      return vals.get(fldname);
   }
   
   /** 
    * Two GroupValue objects are equal if they have the same values
    * for their grouping fields. 
    * @see java.lang.Object#equals(java.lang.Object)
    */
   public boolean equals(Object obj) {
      GroupValue gv = (GroupValue) obj;
      for (String fldname : vals.keySet()) {
         Constant v1 = vals.get(fldname);
         Constant v2 = gv.getVal(fldname);
         if (!v1.equals(v2))
            return false;
      }
      return true;
   }
   
   /**
    * The hashcode of a GroupValue object is the sum of the
    * hashcodes of its field values. 
    * @see java.lang.Object#hashCode()
    */
   public int hashCode() {
      int hashval = 0;
      for (Constant c: vals.values())
         hashval += c.hashCode();
      return hashval;
   }
}