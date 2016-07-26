package simpledb.remote;

import java.rmi.*;

/**
 * The RMI remote interface corresponding to Driver.
 * The method is similar to that of Driver, 
 * except that it takes no arguments and
 * throws RemoteExceptions instead of SQLExceptions.
 * @author Edward Sciore
 */
public interface RemoteDriver extends Remote {
   public RemoteConnection connect() throws RemoteException;
}

