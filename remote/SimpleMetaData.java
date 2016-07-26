package simpledb.remote;

import java.sql.*;

/**
 * An adapter class that wraps RemoteMetaData.
 * Its methods do nothing except transform RemoteExceptions
 * into SQLExceptions.
 * @author Edward Sciore
 */
public class SimpleMetaData extends ResultSetMetaDataAdapter {
   private RemoteMetaData rmd;
   
   public SimpleMetaData(RemoteMetaData md) {
      rmd = md;
   }
   
   public int getColumnCount() throws SQLException {
      try {
         return rmd.getColumnCount();
      }
      catch(Exception e) {
         throw new SQLException(e);
      }
   }
   
   public String getColumnName(int column) throws SQLException {
      try {
         return rmd.getColumnName(column);
      }
      catch (Exception e) {
         throw new SQLException(e);
      }
   }
   
   public int getColumnType(int column) throws SQLException {
      try {
         return rmd.getColumnType(column);
      }
      catch (Exception e) {
         throw new SQLException(e);
      }
   }
   
   public int getColumnDisplaySize(int column) throws SQLException {
      try {
         return rmd.getColumnDisplaySize(column);
      }
      catch (Exception e) {
         throw new SQLException(e);
      }
   }
}

