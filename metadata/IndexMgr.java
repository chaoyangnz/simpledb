package simpledb.metadata;

import static simpledb.metadata.TableMgr.MAX_NAME;
import simpledb.tx.Transaction;
import simpledb.record.*;
import java.util.*;

/**
 * The index manager.
 * The index manager has similar functionalty to the table manager.
 * @author Edward Sciore
 */
public class IndexMgr {
   private TableInfo ti;
   
   /**
    * Creates the index manager.
    * This constructor is called during system startup.
    * If the database is new, then the <i>idxcat</i> table is created.
    * @param isnew indicates whether this is a new database
    * @param tx the system startup transaction
    */
   public IndexMgr(boolean isnew, TableMgr tblmgr, Transaction tx) {
      if (isnew) {
         Schema sch = new Schema();
         sch.addStringField("indexname", MAX_NAME);
         sch.addStringField("tablename", MAX_NAME);
         sch.addStringField("fieldname", MAX_NAME);
         tblmgr.createTable("idxcat", sch, tx);
      }
      ti = tblmgr.getTableInfo("idxcat", tx);
   }
   
   /**
    * Creates an index of the specified type for the specified field.
    * A unique ID is assigned to this index, and its information
    * is stored in the idxcat table.
    * @param idxname the name of the index
    * @param tblname the name of the indexed table
    * @param fldname the name of the indexed field
    * @param tx the calling transaction
    */
   public void createIndex(String idxname, String tblname, String fldname, Transaction tx) {
      RecordFile rf = new RecordFile(ti, tx);
      rf.insert();
      rf.setString("indexname", idxname);
      rf.setString("tablename", tblname);
      rf.setString("fieldname", fldname);
      rf.close();
   }
   
   /**
    * Returns a map containing the index info for all indexes
    * on the specified table.
    * @param tblname the name of the table
    * @param tx the calling transaction
    * @return a map of IndexInfo objects, keyed by their field names
    */
   public Map<String,IndexInfo> getIndexInfo(String tblname, Transaction tx) {
      Map<String,IndexInfo> result = new HashMap<String,IndexInfo>();
      RecordFile rf = new RecordFile(ti, tx);
      while (rf.next())
         if (rf.getString("tablename").equals(tblname)) {
         String idxname = rf.getString("indexname");
         String fldname = rf.getString("fieldname");
         IndexInfo ii = new IndexInfo(idxname, tblname, fldname, tx);
         result.put(fldname, ii);
      }
      rf.close();
      return result;
   }
}
