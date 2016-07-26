package simpledb.remote;

import java.rmi.*;

/**
 * The RMI remote interface corresponding to Connection.
 * The methods are identical to those of Connection, 
 * except that they throw RemoteExceptions instead of SQLExceptions.
 * @author Edward Sciore
 */
public interface RemoteConnection extends Remote {
   public RemoteStatement createStatement() throws RemoteException;
   public void close() throws RemoteException;
}

