package simpledb;

import org.junit.jupiter.api.Test;
import simpledb.remote.SimpleDriver;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.SQLException;

public class Client {

	@Test
	public void connect() throws SQLException {
		Driver d = new SimpleDriver();
		String host = "mymachine.com"; //any DNS name or IP address
		String url = "jdbc:simpledb://" + host;
		Connection conn = d.connect(url, null);
	}
}
