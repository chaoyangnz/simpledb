package simpledb.query;

import simpledb.record.Schema;

/**
 * The interface corresponding to SQL expressions.
 * @author Edward Sciore
 *
 */
public interface Expression {
   
   /**
    * Returns true if the expression is a constant.
    * @return true if the expression is a constant
    */
   public boolean  isConstant();
   
   /**
    * Returns true if the expression is a field reference.
    * @return true if the expression denotes a field
    */
   public boolean  isFieldName();
   
   /**
    * Returns the constant corresponding to a constant expression.
    * Throws an exception if the expression does not
    * denote a constant.
    * @return the expression as a constant
    */
   public Constant asConstant();
   
   /**
    * Returns the field name corresponding to a constant expression.
    * Throws an exception if the expression does not
    * denote a field.
    * @return the expression as a field name
    */
   public String   asFieldName();
   
   /**
    * Evaluates the expression with respect to the
    * current record of the specified scan.
    * @param s the scan
    * @return the value of the expression, as a Constant
    */
   public Constant evaluate(Scan s);
   
   /**
    * Determines if all of the fields mentioned in this expression
    * are contained in the specified schema.
    * @param sch the schema
    * @return true if all fields in the expression are in the schema
    */
   public boolean  appliesTo(Schema sch);
}
