package simpledb.remote;

import java.sql.*;
import java.rmi.registry.*;
import java.util.Properties;

/**
 * The SimpleDB database driver.
 * @author Edward Sciore
 */
public class SimpleDriver extends DriverAdapter {
   
   /**
    * Connects to the SimpleDB server on the specified host.
    * The method retrieves the RemoteDriver stub from
    * the RMI registry on the specified host.
    * It then calls the connect method on that stub,
    * which in turn creates a new connection and
    * returns the RemoteConnection stub for it.
    * This stub is wrapped in a SimpleConnection object
    * and is returned. 
    * <P>
    * The current implementation of this method ignores the 
    * properties argument.
    * @see java.sql.Driver#connect(java.lang.String, Properties)
    */
   public Connection connect(String url, Properties prop) throws SQLException {
      try {
         String host = url.replace("jdbc:simpledb://", "");  //assumes no port specified
         Registry reg = LocateRegistry.getRegistry(host);
         RemoteDriver rdvr = (RemoteDriver) reg.lookup("simpledb");
         RemoteConnection rconn = rdvr.connect();
         return new SimpleConnection(rconn);
      }
      catch (Exception e) {
         throw new SQLException(e);
      }
   }
}
