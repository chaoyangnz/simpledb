package simpledb.remote;

import java.sql.*;

/**
 * An adapter class that wraps RemoteConnection.
 * Its methods do nothing except transform RemoteExceptions
 * into SQLExceptions.
 * @author Edward Sciore
 */
public class SimpleConnection extends ConnectionAdapter {
   private RemoteConnection rconn;
   
   public SimpleConnection(RemoteConnection c) {
      rconn = c;
   }
   
   public Statement createStatement() throws SQLException {
      try {
         RemoteStatement rstmt = rconn.createStatement();
         return new SimpleStatement(rstmt);
      }
      catch(Exception e) {
         throw new SQLException(e);
      }
   }
   
   public void close() throws SQLException {
      try {
         rconn.close();
      }
      catch(Exception e) {
         throw new SQLException(e);
      }
   }
}

