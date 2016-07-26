package simpledb.remote;

import java.sql.*;
import java.util.Map;
import java.util.Calendar;
import java.io.*;
import java.math.BigDecimal;
import java.net.URL;

/**
 * This class implements all of the methods of the ResultSet interface,
 * by throwing an exception for each one.
 * Subclasses (such as SimpleResultSet) can override those methods that 
 * it want to implement.
 * @author Edward Sciore
 */
public abstract class ResultSetAdapter implements ResultSet {
   public boolean absolute(int row) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public void afterLast() throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public void beforeFirst() throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public void cancelRowUpdates() throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public void clearWarnings() throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public void close() throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public void deleteRow() throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public int findColumn(String columnLabel) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public boolean first() throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public Array getArray(int columnIndex) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public Array getArray(String columnLabel) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public InputStream getAsciiStream(int columnIndex) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public InputStream getAsciiStream(String columnLabel) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public BigDecimal getBigDecimal(int columnIndex) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public BigDecimal getBigDecimal(String columnLabel) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public BigDecimal getBigDecimal(int columnIndex, int scale) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public BigDecimal getBigDecimal(String columnLabel, int scale) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public InputStream getBinaryStream(int columnIndex) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public InputStream getBinaryStream(String columnLabel) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public Blob getBlob(int columnIndex) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public Blob getBlob(String columnLabel) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public boolean getBoolean(int columnIndex) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public boolean getBoolean(String columnLabel) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public byte getByte(int columnIndex) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public byte getByte(String columnLabel) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public byte[] getBytes(int columnIndex) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public byte[] getBytes(String columnLabel) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public Reader getCharacterStream(int columnIndex) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public Reader getCharacterStream(String columnLabel) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public Clob getClob(int columnIndex) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public Clob getClob(String columnLabel) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public int getConcurrency() throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public String getCursorName() throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public Date getDate(int columnIndex) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public Date getDate(String columnLabel) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public Date getDate(int columnIndex, Calendar cal) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public Date getDate(String columnLabel, Calendar cal) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public double getDouble(int columnIndex) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public double getDouble(String columnLabel) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public int getFetchDirection() throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public int getFetchSize() throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public float getFloat(int columnIndex) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public float getFloat(String columnLabel) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public int getHoldability() throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public int getInt(int columnIndex) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public int getInt(String columnLabel) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public long getLong(int columnIndex) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public long getLong(String columnLabel) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public ResultSetMetaData getMetaData() throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public Reader getNCharacterStream(int columnIndex) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public Reader getNCharacterStream(String columnLabel) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public NClob getNClob(int columnIndex) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public NClob getNClob(String columnLabel) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public String getNString(int columnIndex) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public String getNString(String columnLabel) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public Object getObject(int columnIndex) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public Object getObject(String columnLabel) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public Object getObject(int columnIndex, Map<String,Class<?>> map) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public Object getObject(String columnLabel, Map<String,Class<?>> map) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public Ref getRef(int columnIndex) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public Ref getRef(String columnLabel) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public int getRow() throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public RowId getRowId(int columnIndex) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public RowId getRowId(String columnLabel) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public short getShort(int columnIndex) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public short getShort(String columnLabel) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public SQLXML getSQLXML(int columnIndex) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public SQLXML getSQLXML(String columnLabel) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public Statement getStatement() throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public String getString(int columnIndex) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public String getString(String columnLabel) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public Time getTime(int columnIndex) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public Time getTime(String columnLabel) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public Time getTime(int columnIndex, Calendar cal) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public Time getTime(String columnLabel, Calendar cal) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public Timestamp getTimestamp(int columnIndex) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public Timestamp getTimestamp(String columnLabel) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public Timestamp getTimestamp(int columnIndex, Calendar cal) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public Timestamp getTimestamp(String columnLabel, Calendar cal) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public int getType() throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public InputStream getUnicodeStream(int columnIndex) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public InputStream getUnicodeStream(String columnLabel) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public URL getURL(int columnIndex) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public URL getURL(String columnLabel) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public SQLWarning getWarnings() throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public void insertRow() throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public boolean isAfterLast() throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public boolean isBeforeFirst() throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public boolean isClosed() throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public boolean isFirst() throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public boolean isLast() throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public boolean last() throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public void moveToCurrentRow() throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public void moveToInsertRow() throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public boolean next() throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public boolean previous() throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public void refreshRow() throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public boolean relative(int rows) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public boolean rowDeleted() throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public boolean rowInserted() throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public boolean rowUpdated() throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public void setFetchDirection(int direction) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public void setFetchSize(int rows) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public void updateArray(int columnIndex, Array x) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public void updateArray(String columnLabel, Array x) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public void updateAsciiStream(int columnIndex, InputStream x) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public void updateAsciiStream(String columnLabel, InputStream x) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public void updateAsciiStream(int columnIndex, InputStream x, int length) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public void updateAsciiStream(String columnLabel, InputStream x, int length) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public void updateAsciiStream(int columnIndex, InputStream x, long length) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public void updateAsciiStream(String columnLabel, InputStream x, long length) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public void updateBigDecimal(int columnIndex, BigDecimal x) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public void updateBigDecimal(String columnLabel, BigDecimal x) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public void updateBinaryStream(int columnIndex, InputStream x) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public void updateBinaryStream(String columnLabel, InputStream x) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public void updateBinaryStream(int columnIndex, InputStream x, int length) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public void updateBinaryStream(String columnLabel, InputStream x, int length) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public void updateBinaryStream(int columnIndex, InputStream x, long length) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public void updateBinaryStream(String columnLabel, InputStream x, long length) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public void updateBlob(int columnIndex, Blob x) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public void updateBlob(String columnLabel, Blob x) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public void updateBlob(int columnIndex, InputStream inputStream) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public void updateBlob(String columnLabel, InputStream inputStream) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public void updateBlob(int columnIndex, InputStream inputStream, long length) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public void updateBlob(String columnLabel, InputStream inputStream, long length) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public void updateBoolean(int columnIndex, boolean x) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public void updateBoolean(String columnLabel, boolean x) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public void updateByte(int columnIndex, byte x) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public void updateByte(String columnLabel, byte x) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public void updateBytes(int columnIndex, byte[] x) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public void updateBytes(String columnLabel, byte[] x) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public void updateCharacterStream(int columnIndex, Reader x) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public void updateCharacterStream(String columnLabel, Reader x) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public void updateCharacterStream(int columnIndex, Reader x, int length) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public void updateCharacterStream(String columnLabel, Reader x, int length) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public void updateCharacterStream(int columnIndex, Reader x, long length) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public void updateCharacterStream(String columnLabel, Reader x, long length) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public void updateClob(int columnIndex, Clob x) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public void updateClob(String columnLabel, Clob x) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public void updateClob(int columnIndex, Reader reader) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public void updateClob(String columnLabel, Reader reader) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public void updateClob(int columnIndex, Reader reader, long length) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public void updateClob(String columnLabel, Reader reader, long length) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public void updateDate(int columnIndex, Date x) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public void updateDate(String columnLabel, Date x) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public void updateDouble(int columnIndex, double x) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public void updateDouble(String columnLabel, double x) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public void updateFloat(int columnIndex, float x) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public void updateFloat(String columnLabel, float x) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public void updateInt(int columnIndex, int x) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public void updateInt(String columnLabel, int x) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public void updateLong(int columnIndex, long x) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public void updateLong(String columnLabel, long x) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public void updateNCharacterStream(int columnIndex, Reader x) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public void updateNCharacterStream(String columnLabel, Reader x) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public void updateNCharacterStream(int columnIndex, Reader x, long length) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public void updateNCharacterStream(String columnLabel, Reader x, long length) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public void updateNClob(int columnIndex, NClob nclob) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public void updateNClob(String columnLabel, NClob nclob) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public void updateNClob(int columnIndex, Reader reader) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public void updateNClob(String columnLabel, Reader reader) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public void updateNClob(int columnIndex, Reader reader, long length) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public void updateNClob(String columnLabel, Reader reader, long length) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public void updateNString(int columnIndex, String nstring) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public void updateNString(String columnLabel, String nstring) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public void updateNull(int columnIndex) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public void updateNull(String columnLabel) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public void updateObject(int columnIndex, Object x) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public void updateObject(String columnLabel, Object x) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public void updateObject(int columnIndex, Object x, int scale) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public void updateObject(String columnLabel, Object x, int scale) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public void updateRef(int columnIndex, Ref x) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public void updateRef(String columnLabel, Ref x) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public void updateRow() throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public void updateRowId(int columnIndex, RowId x) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public void updateRowId(String columnLabel, RowId x) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public void updateShort(int columnIndex, short x) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public void updateShort(String columnLabel, short x) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public void updateSQLXML(int columnIndex, SQLXML x) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public void updateSQLXML(String columnLabel, SQLXML x) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public void updateString(int columnIndex, String x) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public void updateString(String columnLabel, String x) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public void updateTime(int columnIndex, Time x) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public void updateTime(String columnLabel, Time x) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public void updateTimestamp(int columnIndex, Timestamp x) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public void updateTimestamp(String columnLabel, Timestamp x) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public boolean wasNull() throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public boolean isWrapperFor(Class<?> iface) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public <T> T unwrap(Class<T> iface) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public <T> T getObject(int columnIndex, Class<T> type) throws SQLException {
      throw new SQLException("operation not implemented");
   }
   
   public <T> T getObject(String columnLabel, Class<T> type) throws SQLException {
      throw new SQLException("operation not implemented");
   }
}