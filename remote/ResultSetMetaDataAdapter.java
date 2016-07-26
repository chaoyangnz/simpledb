package simpledb.remote;

import java.sql.*;

/**
 * This class implements all of the methods of the ResultSetMetaData interface,
 * by throwing an exception for each one.
 * Subclasses (such as SimpleMetaData) can override those methods that 
 * it want to implement.
 * @author Edward Sciore
 */
public abstract class ResultSetMetaDataAdapter implements ResultSetMetaData {
   public String getCatalogName(int column) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public String getColumnClassName(int column) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public int getColumnCount() throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public int getColumnDisplaySize(int column) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public String getColumnLabel(int column) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public String getColumnName(int column) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public int getColumnType(int column) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public String getColumnTypeName(int column) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public int getPrecision(int column) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public int getScale(int column) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public String getSchemaName(int column) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public String getTableName(int column) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public boolean isAutoIncrement(int column) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public boolean isCaseSensitive(int column) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public boolean isCurrency(int column) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public boolean isDefinitelyWritable(int column) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public int isNullable(int column) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public boolean isReadOnly(int column) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public boolean isSearchable(int column) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public boolean isSigned(int column) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public boolean isWritable(int column) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public boolean isWrapperFor(Class<?> iface) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public <T> T unwrap(Class<T> iface) throws SQLException {
      throw new SQLException("operation not implemented");
   }
}