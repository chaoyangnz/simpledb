package studentClient.simpledb;

import java.sql.*;
import simpledb.remote.SimpleDriver;

public class FindMajors {
    public static void main(String[] args) {
		String major = args[0];
		System.out.println("Here are the " + major + " majors");
		System.out.println("Name\tGradYear");

		Connection conn = null;
		try {
			// Step 1: connect to database server
			Driver d = new SimpleDriver();
			conn = d.connect("jdbc:simpledb://localhost", null);

			// Step 2: execute the query
			Statement stmt = conn.createStatement();
			String qry = "select sname, gradyear "
			           + "from student, dept "
			           + "where did = majorid "
			           + "and dname = '" + major + "'";
			ResultSet rs = stmt.executeQuery(qry);

			// Step 3: loop through the result set
			while (rs.next()) {
				String sname = rs.getString("sname");
				int gradyear = rs.getInt("gradyear");
				System.out.println(sname + "\t" + gradyear);
			}
			rs.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			// Step 4: close the connection
			try {
				if (conn != null)
					conn.close();
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
