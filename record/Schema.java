package simpledb.record;

import static java.sql.Types.*;
import java.util.*;

/**
 * The record schema of a table.
 * A schema contains the name and type of
 * each field of the table, as well as the length
 * of each varchar field.
 * @author Edward Sciore
 *
 */
public class Schema {
   private Map<String,FieldInfo> info = new HashMap<String,FieldInfo>();
   
   /**
    * Creates an empty schema.
    * Field information can be added to a schema
    * via the five addXXX methods. 
    */
   public Schema() {}
   
   /**
    * Adds a field to the schema having a specified
    * name, type, and length.
    * If the field type is "integer", then the length
    * value is irrelevant.
    * @param fldname the name of the field
    * @param type the type of the field, according to the constants in simpledb.sql.types
    * @param length the conceptual length of a string field.
    */
   public void addField(String fldname, int type, int length) {
      info.put(fldname, new FieldInfo(type, length));
   }
   
   /**
    * Adds an integer field to the schema.
    * @param fldname the name of the field
    */
   public void addIntField(String fldname) {
      addField(fldname, INTEGER, 0);
   }
   
   /**
    * Adds a string field to the schema.
    * The length is the conceptual length of the field.
    * For example, if the field is defined as varchar(8),
    * then its length is 8.
    * @param fldname the name of the field
    * @param length the number of chars in the varchar definition
    */
   public void addStringField(String fldname, int length) {
      addField(fldname, VARCHAR, length);
   }
   
   /**
    * Adds a field to the schema having the same
    * type and length as the corresponding field
    * in another schema.
    * @param fldname the name of the field
    * @param sch the other schema
    */
   public void add(String fldname, Schema sch) {
      int type   = sch.type(fldname);
      int length = sch.length(fldname);
      addField(fldname, type, length);
   }
   
   /**
    * Adds all of the fields in the specified schema
    * to the current schema.
    * @param sch the other schema
    */
   public void addAll(Schema sch) {
      info.putAll(sch.info);
   }
   
   /**
    * Returns a collection containing the name of
    * each field in the schema.
    * @return the collection of the schema's field names
    */
   public Collection<String> fields() {
      return info.keySet();
   }
   
   /**
    * Returns true if the specified field
    * is in the schema
    * @param fldname the name of the field
    * @return true if the field is in the schema
    */
   public boolean hasField(String fldname) {
      return fields().contains(fldname);
   }
   
   /**
    * Returns the type of the specified field, using the
    * constants in {@link java.sql.Types}.
    * @param fldname the name of the field
    * @return the integer type of the field
    */
   public int type(String fldname) {
      return info.get(fldname).type;
   }
   
   /**
    * Returns the conceptual length of the specified field.
    * If the field is not a string field, then
    * the return value is undefined.
    * @param fldname the name of the field
    * @return the conceptual length of the field
    */
   public int length(String fldname) {
      return info.get(fldname).length;
   }
   
   class FieldInfo {
      int type, length;
      public FieldInfo(int type, int length) {
         this.type = type;
         this.length = length;
      }
   }
}
