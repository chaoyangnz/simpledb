package simpledb.query;

/**
 * The class that wraps Java ints as database constants.
 * @author Edward Sciore
 */
public class IntConstant implements Constant {
   private Integer val;
   
   /**
    * Create a constant by wrapping the specified int.
    * @param n the int value
    */
   public IntConstant(int n) {
      val = new Integer(n);
   }
   
   /**
    * Unwraps the Integer and returns it.
    * @see simpledb.query.Constant#asJavaVal()
    */
   public Object asJavaVal() {
      return val;
   }
   
   public boolean equals(Object obj) {
      IntConstant ic = (IntConstant) obj;
      return ic != null && val.equals(ic.val);
   }
   
   public int compareTo(Constant c) {
      IntConstant ic = (IntConstant) c;
      return val.compareTo(ic.val);
   }
   
   public int hashCode() {
      return val.hashCode();
   }
   
   public String toString() {
      return val.toString();
   }
}
