import java.sql.*;
import org.apache.derby.jdbc.ClientDriver;

public class ChangeMajor {
    public static void main(String[] args) {
		Connection conn = null;
		try {
			Driver d = new ClientDriver();
			String url = "jdbc:derby://localhost/studentdb";
			conn = d.connect(url, null);
			Statement stmt = conn.createStatement();

			stmt.executeUpdate("update STUDENT set MajorId=30 where SName='amy'");
			System.out.println("Amy is now a drama major.");
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
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
