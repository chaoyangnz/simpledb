package simpledb.query;

/**
 * The class that wraps Java strings as database constants.
 * @author Edward Sciore
 */
public class StringConstant implements Constant {
   private String val;
   
   /**
    * Create a constant by wrapping the specified string.
    * @param s the string value
    */
   public StringConstant(String s) {
      val = s;
   }
   
   /**
    * Unwraps the string and returns it.
    * @see simpledb.query.Constant#asJavaVal()
    */
   public String asJavaVal() {
      return val;
   }
   
   public boolean equals(Object obj) {
      StringConstant sc = (StringConstant) obj;
      return sc != null && val.equals(sc.val);
   }
   
   public int compareTo(Constant c) {
      StringConstant sc = (StringConstant) c;
      return val.compareTo(sc.val);
   }
   
   public int hashCode() {
      return val.hashCode();
   }
   
   public String toString() {
      return val;
   }
}
