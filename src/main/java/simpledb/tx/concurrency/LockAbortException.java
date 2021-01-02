package simpledb.tx.concurrency;

/**
 * A runtime exception indicating that the transaction
 * needs to abort because a lock could not be obtained.
 * @author Edward Sciore
 */
@SuppressWarnings("serial")
public class LockAbortException extends RuntimeException {
   public LockAbortException() {
   }
}
