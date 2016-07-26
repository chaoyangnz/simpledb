package simpledb.index.btree;

import static java.sql.Types.INTEGER;
import static simpledb.file.Page.*;
import simpledb.file.Block;
import simpledb.record.*;
import simpledb.query.*;
import simpledb.tx.Transaction;

/**
 * B-tree directory and leaf pages have many commonalities:
 * in particular, their records are stored in sorted order, 
 * and pages split when full.
 * A BTreePage object contains this common functionality.
 * @author Edward Sciore
 */
public class BTreePage {
   private Block currentblk;
   private TableInfo ti;
   private Transaction tx;
   private int slotsize;
   
   /**
    * Opens a page for the specified B-tree block.
    * @param currentblk a reference to the B-tree block
    * @param ti the metadata for the particular B-tree file
    * @param tx the calling transaction
    */
   public BTreePage(Block currentblk, TableInfo ti, Transaction tx) {
      this.currentblk = currentblk;
      this.ti = ti;
      this.tx = tx;
      slotsize = ti.recordLength();
      tx.pin(currentblk);
   }
   
   /**
    * Calculates the position where the first record having
    * the specified search key should be, then returns
    * the position before it.
    * @param searchkey the search key
    * @return the position before where the search key goes
    */
   public int findSlotBefore(Constant searchkey) {
      int slot = 0;
      while (slot < getNumRecs() && getDataVal(slot).compareTo(searchkey) < 0)
         slot++;
      return slot-1;
   }
   
   /**
    * Closes the page by unpinning its buffer.
    */
   public void close() {
      if (currentblk != null)
         tx.unpin(currentblk);
      currentblk = null;
   }
   
   /**
    * Returns true if the block is full.
    * @return true if the block is full
    */
   public boolean isFull() {
      return slotpos(getNumRecs()+1) >= BLOCK_SIZE;
   }
   
   /**
    * Splits the page at the specified position.
    * A new page is created, and the records of the page
    * starting at the split position are transferred to the new page.
    * @param splitpos the split position
    * @param flag the initial value of the flag field
    * @return the reference to the new block
    */
   public Block split(int splitpos, int flag) {
      Block newblk = appendNew(flag);
      BTreePage newpage = new BTreePage(newblk, ti, tx);
      transferRecs(splitpos, newpage);
      newpage.setFlag(flag);
      newpage.close();
      return newblk;
   }
   
   /**
    * Returns the dataval of the record at the specified slot.
    * @param slot the integer slot of an index record
    * @return the dataval of the record at that slot
    */
   public Constant getDataVal(int slot) {
      return getVal(slot, "dataval");
   }
   
   /**
    * Returns the value of the page's flag field
    * @return the value of the page's flag field
    */
   public int getFlag() {
      return tx.getInt(currentblk, 0);
   }
   
   /**
    * Sets the page's flag field to the specified value
    * @param val the new value of the page flag
    */
   public void setFlag(int val) {
      tx.setInt(currentblk, 0, val);
   }
   
   /**
    * Appends a new block to the end of the specified B-tree file,
    * having the specified flag value.
    * @param flag the initial value of the flag
    * @return a reference to the newly-created block
    */
   public Block appendNew(int flag) {
      return tx.append(ti.fileName(), new BTPageFormatter(ti, flag));
   }
   
   // Methods called only by BTreeDir
   
   /**
    * Returns the block number stored in the index record 
    * at the specified slot.
    * @param slot the slot of an index record
    * @return the block number stored in that record
    */
   public int getChildNum(int slot) {
      return getInt(slot, "block");
   }
   
   /**
    * Inserts a directory entry at the specified slot.
    * @param slot the slot of an index record
    * @param val the dataval to be stored
    * @param blknum the block number to be stored
    */
   public void insertDir(int slot, Constant val, int blknum) {
      insert(slot);
      setVal(slot, "dataval", val);
      setInt(slot, "block", blknum);
   }
   
   // Methods called only by BTreeLeaf
   
   /**
    * Returns the dataRID value stored in the specified leaf index record.
    * @param slot the slot of the desired index record
    * @return the dataRID value store at that slot
    */
   public RID getDataRid(int slot) {
      return new RID(getInt(slot, "block"), getInt(slot, "id"));
   }
   
   /**
    * Inserts a leaf index record at the specified slot.
    * @param slot the slot of the desired index record
    * @param val the new dataval
    * @param rid the new dataRID
    */
   public void insertLeaf(int slot, Constant val, RID rid) {
      insert(slot);
      setVal(slot, "dataval", val);
      setInt(slot, "block", rid.blockNumber());
      setInt(slot, "id", rid.id());
   }
   
   /**
    * Deletes the index record at the specified slot.
    * @param slot the slot of the deleted index record
    */
   public void delete(int slot) {
      for (int i=slot+1; i<getNumRecs(); i++)
         copyRecord(i, i-1);
      setNumRecs(getNumRecs()-1);
      return;
   }
   
   /**
    * Returns the number of index records in this page.
    * @return the number of index records in this page
    */
   public int getNumRecs() {
      return tx.getInt(currentblk, INT_SIZE);
   }
   
   // Private methods
   
   private int getInt(int slot, String fldname) {
      int pos = fldpos(slot, fldname);
      return tx.getInt(currentblk, pos);
   }
   
   private String getString(int slot, String fldname) {
      int pos = fldpos(slot, fldname);
      return tx.getString(currentblk, pos);
   }
   
   private Constant getVal(int slot, String fldname) {
      int type = ti.schema().type(fldname);
      if (type == INTEGER)
         return new IntConstant(getInt(slot, fldname));
      else
         return new StringConstant(getString(slot, fldname));
   }
   
   private void setInt(int slot, String fldname, int val) {
      int pos = fldpos(slot, fldname);
      tx.setInt(currentblk, pos, val);
   }
   
   private void setString(int slot, String fldname, String val) {
      int pos = fldpos(slot, fldname);
      tx.setString(currentblk, pos, val);
   }
   
   private void setVal(int slot, String fldname, Constant val) {
      int type = ti.schema().type(fldname);
      if (type == INTEGER)
         setInt(slot, fldname, (Integer)val.asJavaVal());
      else
         setString(slot, fldname, (String)val.asJavaVal());
   }
   
   private void setNumRecs(int n) {
      tx.setInt(currentblk, INT_SIZE, n);
   }
   
   private void insert(int slot) {
      for (int i=getNumRecs(); i>slot; i--)
         copyRecord(i-1, i);
      setNumRecs(getNumRecs()+1);
   }
   
   private void copyRecord(int from, int to) {
      Schema sch = ti.schema();
      for (String fldname : sch.fields())
         setVal(to, fldname, getVal(from, fldname));
   }
   
   private void transferRecs(int slot, BTreePage dest) {
      int destslot = 0;
      while (slot < getNumRecs()) {
         dest.insert(destslot);
         Schema sch = ti.schema();
         for (String fldname : sch.fields())
            dest.setVal(destslot, fldname, getVal(slot, fldname));
         delete(slot);
         destslot++;
      }
   }
   
   private int fldpos(int slot, String fldname) {
      int offset = ti.offset(fldname);
      return slotpos(slot) + offset;
   }
   
   private int slotpos(int slot) {
      return INT_SIZE + INT_SIZE + (slot * slotsize);
   }
}
