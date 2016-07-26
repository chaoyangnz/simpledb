package simpledb.remote;

import simpledb.record.Schema;
import static java.sql.Types.INTEGER;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

/**
 * The RMI server-side implementation of RemoteMetaData.
 * @author Edward Sciore
 */
@SuppressWarnings("serial")
public class RemoteMetaDataImpl extends UnicastRemoteObject implements RemoteMetaData {
   private Schema sch;
   private List<String> fields = new ArrayList<String>();
   
   /**
    * Creates a metadata object that wraps the specified schema.
    * The method also creates a list to hold the schema's
    * collection of field names,
    * so that the fields can be accessed by position.
    * @param sch the schema
    * @throws RemoteException
    */
   public RemoteMetaDataImpl(Schema sch) throws RemoteException {
      this.sch = sch;
      fields.addAll(sch.fields());
   }
   
   /**
    * Returns the size of the field list.
    * @see simpledb.remote.RemoteMetaData#getColumnCount()
    */
   public int getColumnCount() throws RemoteException {
      return fields.size();
   }
   
   /**
    * Returns the field name for the specified column number.
    * In JDBC, column numbers start with 1, so the field
    * is taken from position (column-1) in the list.
    * @see simpledb.remote.RemoteMetaData#getColumnName(int)
    */
   public String getColumnName(int column) throws RemoteException {
      return fields.get(column-1);
   }
   
   /**
    * Returns the type of the specified column.
    * The method first finds the name of the field in that column,
    * and then looks up its type in the schema.
    * @see simpledb.remote.RemoteMetaData#getColumnType(int)
    */
   public int getColumnType(int column) throws RemoteException {
      String fldname = getColumnName(column);
      return sch.type(fldname);
   }
   
   /**
    * Returns the number of characters required to display the
    * specified column.
    * For a string-type field, the method simply looks up the 
    * field's length in the schema and returns that.
    * For an int-type field, the method needs to decide how
    * large integers can be.
    * Here, the method arbitrarily chooses 6 characters,
    * which means that integers over 999,999 will  
    * probably get displayed improperly.
    * @see simpledb.remote.RemoteMetaData#getColumnDisplaySize(int)
    */
   public int getColumnDisplaySize(int column) throws RemoteException {
      String fldname = getColumnName(column);
      int fldtype = sch.type(fldname);
      int fldlength = sch.length(fldname);
      if (fldtype == INTEGER)
         return 6;  // accommodate 6-digit integers
      else
         return fldlength;
   }
}
