package simpledb.metadata;

import simpledb.tx.Transaction;
import simpledb.record.*;
import java.util.*;

/**
 * The table manager.
 * There are methods to create a table, save the metadata
 * in the catalog, and obtain the metadata of a
 * previously-created table.
 * @author Edward Sciore
 *
 */
public class TableMgr {
   /**
    * The maximum number of characters in any
    * tablename or fieldname.
    * Currently, this value is 16.
    */
   public static final int MAX_NAME = 16;
   
   private TableInfo tcatInfo, fcatInfo;
   
   /**
    * Creates a new catalog manager for the database system.
    * If the database is new, then the two catalog tables
    * are created.
    * @param isNew has the value true if the database is new
    * @param tx the startup transaction
    */
   public TableMgr(boolean isNew, Transaction tx) {
      Schema tcatSchema = new Schema();
      tcatSchema.addStringField("tblname", MAX_NAME);
      tcatSchema.addIntField("reclength");
      tcatInfo = new TableInfo("tblcat", tcatSchema);
      
      Schema fcatSchema = new Schema();
      fcatSchema.addStringField("tblname", MAX_NAME);
      fcatSchema.addStringField("fldname", MAX_NAME);
      fcatSchema.addIntField("type");
      fcatSchema.addIntField("length");
      fcatSchema.addIntField("offset");
      fcatInfo = new TableInfo("fldcat", fcatSchema);
      
      if (isNew) {
         createTable("tblcat", tcatSchema, tx);
         createTable("fldcat", fcatSchema, tx);
      }
   }
   
   /**
    * Creates a new table having the specified name and schema.
    * @param tblname the name of the new table
    * @param sch the table's schema
    * @param tx the transaction creating the table
    */
   public void createTable(String tblname, Schema sch, Transaction tx) {
      TableInfo ti = new TableInfo(tblname, sch);
      // insert one record into tblcat
      RecordFile tcatfile = new RecordFile(tcatInfo, tx);
      tcatfile.insert();
      tcatfile.setString("tblname", tblname);
      tcatfile.setInt("reclength", ti.recordLength());
      tcatfile.close();
      
      // insert a record into fldcat for each field
      RecordFile fcatfile = new RecordFile(fcatInfo, tx);
      for (String fldname : sch.fields()) {
         fcatfile.insert();
         fcatfile.setString("tblname", tblname);
         fcatfile.setString("fldname", fldname);
         fcatfile.setInt   ("type",   sch.type(fldname));
         fcatfile.setInt   ("length", sch.length(fldname));
         fcatfile.setInt   ("offset", ti.offset(fldname));
      }
      fcatfile.close();
   }
   
   /**
    * Retrieves the metadata for the specified table
    * out of the catalog.
    * @param tblname the name of the table
    * @param tx the transaction
    * @return the table's stored metadata
    */
   public TableInfo getTableInfo(String tblname, Transaction tx) {
      RecordFile tcatfile = new RecordFile(tcatInfo, tx);
      int reclen = -1;
      while (tcatfile.next())
         if(tcatfile.getString("tblname").equals(tblname)) {
         reclen = tcatfile.getInt("reclength");
         break;
      }
      tcatfile.close();
      
      RecordFile fcatfile = new RecordFile(fcatInfo, tx);
      Schema sch = new Schema();
      Map<String,Integer> offsets = new HashMap<String,Integer>();
      while (fcatfile.next())
         if (fcatfile.getString("tblname").equals(tblname)) {
         String fldname = fcatfile.getString("fldname");
         int fldtype    = fcatfile.getInt("type");
         int fldlen     = fcatfile.getInt("length");
         int offset     = fcatfile.getInt("offset");
         offsets.put(fldname, offset);
         sch.addField(fldname, fldtype, fldlen);
      }
      fcatfile.close();
      return new TableInfo(tblname, sch, offsets, reclen);
   }
}