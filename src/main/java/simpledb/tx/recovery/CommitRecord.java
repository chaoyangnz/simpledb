package simpledb.tx.recovery;

import simpledb.log.BasicLogRecord;

/**
 * The COMMIT log record
 * @author Edward Sciore
 */
class CommitRecord implements LogRecord {
   private int txnum;
   
   /**
    * Creates a new commit log record for the specified transaction.
    * @param txnum the ID of the specified transaction
    */
   public CommitRecord(int txnum) {
      this.txnum = txnum;
   }
   
   /**
    * Creates a log record by reading one other value from the log.
    * @param rec the basic log record
    */
   public CommitRecord(BasicLogRecord rec) {
      txnum = rec.nextInt();
   }
   
   /** 
    * Writes a commit record to the log.
    * This log record contains the COMMIT operator,
    * followed by the transaction id.
    * @return the LSN of the last log value
    */
   public int writeToLog() {
      Object[] rec = new Object[] {COMMIT, txnum};
      return logMgr.append(rec);
   }
   
   public int op() {
      return COMMIT;
   }
   
   public int txNumber() {
      return txnum;
   }
   
   /**
    * Does nothing, because a commit record
    * contains no undo information.
    */
   public void undo(int txnum) {}
   
   public String toString() {
      return "<COMMIT " + txnum + ">";
   }
}
