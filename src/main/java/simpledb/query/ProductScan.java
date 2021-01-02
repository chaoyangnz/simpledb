package simpledb.query;

/**
 * The scan class corresponding to the <i>product</i> relational
 * algebra operator.
 * @author Edward Sciore
 */
public class ProductScan implements Scan {
   private Scan s1, s2;
   
   /**
    * Creates a product scan having the two underlying scans.
    * @param s1 the LHS scan
    * @param s2 the RHS scan
    */
   public ProductScan(Scan s1, Scan s2) {
      this.s1 = s1;
      this.s2 = s2;
      s1.next();
   }
   
   /**
    * Positions the scan before its first record.
    * In other words, the LHS scan is positioned at 
    * its first record, and the RHS scan
    * is positioned before its first record.
    * @see simpledb.query.Scan#beforeFirst()
    */
   public void beforeFirst() {
      s1.beforeFirst();
      s1.next();
      s2.beforeFirst();
   }
   
   /**
    * Moves the scan to the next record.
    * The method moves to the next RHS record, if possible.
    * Otherwise, it moves to the next LHS record and the
    * first RHS record.
    * If there are no more LHS records, the method returns false.
    * @see simpledb.query.Scan#next()
    */
   public boolean next() {
      if (s2.next())
         return true;
      else {
         s2.beforeFirst();
         return s2.next() && s1.next();
      }
   }
   
   /**
    * Closes both underlying scans.
    * @see simpledb.query.Scan#close()
    */
   public void close() {
      s1.close();
      s2.close();
   }
   
   /** 
    * Returns the value of the specified field.
    * The value is obtained from whichever scan
    * contains the field.
    * @see simpledb.query.Scan#getVal(java.lang.String)
    */
   public Constant getVal(String fldname) {
      if (s1.hasField(fldname))
         return s1.getVal(fldname);
      else
         return s2.getVal(fldname);
   }
   
   /** 
    * Returns the integer value of the specified field.
    * The value is obtained from whichever scan
    * contains the field.
    * @see simpledb.query.Scan#getInt(java.lang.String)
    */
   public int getInt(String fldname) {
      if (s1.hasField(fldname))
         return s1.getInt(fldname);
      else
         return s2.getInt(fldname);
   }
   
   /** 
    * Returns the string value of the specified field.
    * The value is obtained from whichever scan
    * contains the field.
    * @see simpledb.query.Scan#getString(java.lang.String)
    */
   public String getString(String fldname) {
      if (s1.hasField(fldname))
         return s1.getString(fldname);
      else
         return s2.getString(fldname);
   }
   
   /**
    * Returns true if the specified field is in
    * either of the underlying scans.
    * @see simpledb.query.Scan#hasField(java.lang.String)
    */
   public boolean hasField(String fldname) {
      return s1.hasField(fldname) || s2.hasField(fldname);
   }
}
