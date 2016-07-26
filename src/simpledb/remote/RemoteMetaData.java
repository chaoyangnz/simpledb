package simpledb.remote;

import java.rmi.*;

/**
 * The RMI remote interface corresponding to ResultSetMetaData.
 * The methods are identical to those of ResultSetMetaData, 
 * except that they throw RemoteExceptions instead of SQLExceptions.
 * @author Edward Sciore
 */
public interface RemoteMetaData extends Remote {
   public int    getColumnCount()              throws RemoteException;
   public String getColumnName(int column)     throws RemoteException;
   public int    getColumnType(int column)     throws RemoteException;
   public int getColumnDisplaySize(int column) throws RemoteException;
}

