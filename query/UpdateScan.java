package simpledb.query;

import simpledb.record.RID;

/**
 * The interface implemented by all updateable scans.
 * @author Edward Sciore
 */
public interface UpdateScan extends Scan {
   /**
    * Modifies the field value of the current record.
    * @param fldname the name of the field
    * @param val the new value, expressed as a Constant
    */
   public void setVal(String fldname, Constant val);
   
   /**
    * Modifies the field value of the current record.
    * @param fldname the name of the field
    * @param val the new integer value
    */
   public void setInt(String fldname, int val);
   
   /**
    * Modifies the field value of the current record.
    * @param fldname the name of the field
    * @param val the new string value
    */
   public void setString(String fldname, String val);
   
   /**
    * Inserts a new record somewhere in the scan.
    */
   public void insert();
   
   /**
    * Deletes the current record from the scan.
    */
   public void delete();
   
   /**
    * Returns the RID of the current record.
    * @return the RID of the current record
    */
   public RID  getRid();
   
   /**
    * Positions the scan so that the current record has
    * the specified RID.
    * @param rid the RID of the desired record
    */
   public void moveToRid(RID rid);
}
