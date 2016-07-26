package simpledb.metadata;

import simpledb.record.*;
import simpledb.tx.Transaction;

class ViewMgr {
   private static final int MAX_VIEWDEF = 100;
   TableMgr tblMgr;
   
   public ViewMgr(boolean isNew, TableMgr tblMgr, Transaction tx) {
      this.tblMgr = tblMgr;
      if (isNew) {
         Schema sch = new Schema();
         sch.addStringField("viewname", TableMgr.MAX_NAME);
         sch.addStringField("viewdef", MAX_VIEWDEF);
         tblMgr.createTable("viewcat", sch, tx);
      }
   }
   
   public void createView(String vname, String vdef, Transaction tx) {
      TableInfo ti = tblMgr.getTableInfo("viewcat", tx);
      RecordFile rf = new RecordFile(ti, tx);
      rf.insert();
      rf.setString("viewname", vname);
      rf.setString("viewdef", vdef);
      rf.close();
   }
   
   public String getViewDef(String vname, Transaction tx) {
      String result = null;
      TableInfo ti = tblMgr.getTableInfo("viewcat", tx);
      RecordFile rf = new RecordFile(ti, tx);
      while (rf.next())
         if (rf.getString("viewname").equals(vname)) {
         result = rf.getString("viewdef");
         break;
      }
      rf.close();
      return result;
   }
}
