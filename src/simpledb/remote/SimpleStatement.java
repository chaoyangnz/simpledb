package simpledb.remote;

import java.sql.*;

/**
 * An adapter class that wraps RemoteStatement.
 * Its methods do nothing except transform RemoteExceptions
 * into SQLExceptions.
 * @author Edward Sciore
 */
public class SimpleStatement extends StatementAdapter {
   private RemoteStatement rstmt;
   
   public SimpleStatement(RemoteStatement s) {
      rstmt = s;
   }
   
   public ResultSet executeQuery(String qry) throws SQLException {
      try {
         RemoteResultSet rrs = rstmt.executeQuery(qry);
         return new SimpleResultSet(rrs);
      }
      catch(Exception e) {
         throw new SQLException(e);
      }
   }
   
   public int executeUpdate(String cmd) throws SQLException {
      try {
         return rstmt.executeUpdate(cmd);
      }
      catch(Exception e) {
         throw new SQLException(e);
      }
   }
}

