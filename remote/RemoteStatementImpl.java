package simpledb.remote;

import simpledb.tx.Transaction;
import simpledb.query.Plan;
import simpledb.server.SimpleDB;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * The RMI server-side implementation of RemoteStatement.
 * @author Edward Sciore
 */
@SuppressWarnings("serial")
class RemoteStatementImpl extends UnicastRemoteObject implements RemoteStatement {
   private RemoteConnectionImpl rconn;
   
   public RemoteStatementImpl(RemoteConnectionImpl rconn) throws RemoteException {
      this.rconn = rconn;
   }
   
   /**
    * Executes the specified SQL query string.
    * The method calls the query planner to create a plan
    * for the query. It then sends the plan to the
    * RemoteResultSetImpl constructor for processing.
    * @see simpledb.remote.RemoteStatement#executeQuery(java.lang.String)
    */
   public RemoteResultSet executeQuery(String qry) throws RemoteException {
      try {
         Transaction tx = rconn.getTransaction();
         Plan pln = SimpleDB.planner().createQueryPlan(qry, tx);
         return new RemoteResultSetImpl(pln, rconn);
      }
      catch(RuntimeException e) {
         rconn.rollback();
         throw e;
      }
   }
   
   /**
    * Executes the specified SQL update command.
    * The method sends the command to the update planner,
    * which executes it.
    * @see simpledb.remote.RemoteStatement#executeUpdate(java.lang.String)
    */
   public int executeUpdate(String cmd) throws RemoteException {
      try {
         Transaction tx = rconn.getTransaction();
         int result = SimpleDB.planner().executeUpdate(cmd, tx);
         rconn.commit();
         return result;
      }
      catch(RuntimeException e) {
         rconn.rollback();
         throw e;
      }
   }
}
