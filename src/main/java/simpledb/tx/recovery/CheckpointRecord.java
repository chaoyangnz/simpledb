package simpledb.tx.recovery;

import simpledb.log.BasicLogRecord;

/**
 * The CHECKPOINT log record.
 * @author Edward Sciore
 */
class CheckpointRecord implements LogRecord {
   
   /**
    * Creates a quiescent checkpoint record.
    */
   public CheckpointRecord() {}
   
   /**
    * Creates a log record by reading no other values 
    * from the basic log record.
    * @param rec the basic log record
    */
   public CheckpointRecord(BasicLogRecord rec) {}
   
   /** 
    * Writes a checkpoint record to the log.
    * This log record contains the CHECKPOINT operator,
    * and nothing else.
    * @return the LSN of the last log value
    */
   public int writeToLog() {
      Object[] rec = new Object[] {CHECKPOINT};
      return logMgr.append(rec);
   }
   
   public int op() {
      return CHECKPOINT;
   }
   
   /**
    * Checkpoint records have no associated transaction,
    * and so the method returns a "dummy", negative txid.
    */
   public int txNumber() {
      return -1; // dummy value
   }
   
   /**
    * Does nothing, because a checkpoint record
    * contains no undo information.
    */
   public void undo(int txnum) {}
   
   public String toString() {
      return "<CHECKPOINT>";
   }
}
