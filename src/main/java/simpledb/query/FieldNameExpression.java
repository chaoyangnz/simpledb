package simpledb.query;

import simpledb.record.Schema;

/**
 * An expression consisting entirely of a single field.
 * @author Edward Sciore
 *
 */
public class FieldNameExpression implements Expression {
   private String fldname;
   
   /**
    * Creates a new expression by wrapping a field.
    * @param fldname the name of the wrapped field
    */
   public FieldNameExpression(String fldname) {
      this.fldname = fldname;
   }
   
   /**
    * Returns false.
    * @see simpledb.query.Expression#isConstant()
    */
   public boolean isConstant() {
      return false;
   }
   
   /**
    * Returns true.
    * @see simpledb.query.Expression#isFieldName()
    */
   public boolean isFieldName() {
      return true;
   }
   
   /**
    * This method should never be called.
    * Throws a ClassCastException.
    * @see simpledb.query.Expression#asConstant()
    */
   public Constant asConstant() {
      throw new ClassCastException();
   }
   
   /**
    * Unwraps the field name and returns it.
    * @see simpledb.query.Expression#asFieldName()
    */
   public String asFieldName() {
      return fldname;
   }
   
   /**
    * Evaluates the field by getting its value in the scan.
    * @see simpledb.query.Expression#evaluate(simpledb.query.Scan)
    */
   public Constant evaluate(Scan s) {
      return s.getVal(fldname);
   }
   
   /** 
    * Returns true if the field is in the specified schema.
    * @see simpledb.query.Expression#appliesTo(simpledb.record.Schema)
    */
   public boolean appliesTo(Schema sch) {
      return sch.hasField(fldname);
   }
   
   public String toString() {
      return fldname;
   }
}
