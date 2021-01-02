import java.sql.*;
import java.util.*;
import org.apache.derby.jdbc.ClientDriver;

public class DeptDAO {
	private Connection conn;
	private DatabaseManager dbm;

	public DeptDAO(Connection conn, DatabaseManager dbm) {
		this.conn = conn;
		this.dbm  = dbm;
	}

	public Dept find(int did) {
		try {
			String qry = "select DName from DEPT where DId = ?";
			PreparedStatement pstmt = conn.prepareStatement(qry);
			pstmt.setInt(1, did);
			ResultSet rs = pstmt.executeQuery();

			// return null if department doesn't exist
			if (!rs.next())
				return null;

			String dname = rs.getString("DName");
			rs.close();
			return new Dept(this, did, dname);
		}
		catch(SQLException e) {
			dbm.cleanup();
			throw new RuntimeException("error finding department", e);
		}
	}

	public Dept insert(int did, String dname) {
		try {
			// make sure that the did is currently unused
			if (find(did) != null)
				return null;

			String cmd = "insert into DEPT(DId, DName) values(?, ?)";
			PreparedStatement pstmt = conn.prepareStatement(cmd);
			pstmt.setInt(1, did);
			pstmt.setString(2, dname);
			pstmt.executeUpdate();
			return new Dept(this, did, dname);
		}
		catch(SQLException e) {
			dbm.cleanup();
			throw new RuntimeException("error inserting new department", e);
		}
	}

	public Collection<Student> getMajors(int did) {
		try {
			Collection<Student> majors = new ArrayList<Student>();
			String qry = "select SId from STUDENT where MajorId = ?";
			PreparedStatement pstmt = conn.prepareStatement(qry);
			pstmt.setInt(1, did);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int sid = rs.getInt("SId");
				majors.add(dbm.findStudent(sid));
			}
			rs.close();
			return majors;
		}
		catch(SQLException e) {
			dbm.cleanup();
			throw new RuntimeException("error getting student majors", e);
		}
	}

	public Collection<Course> getCourses(int did) {
		try {
			Collection<Course> courses = new ArrayList<Course>();
			String qry = "select CId from COURSE where CourseId = ?";
			PreparedStatement pstmt = conn.prepareStatement(qry);
			pstmt.setInt(1, did);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int cid = rs.getInt("CId");
				courses.add(dbm.findCourse(cid));
			}
			rs.close();
			return courses;
		}
		catch(SQLException e) {
			dbm.cleanup();
			throw new RuntimeException("error getting offered courses", e);
		}
	}

	public void changeName(int did, String newname) {
		try {
			String cmd = "update DEPT set SName = ? where DId = ?";
			PreparedStatement pstmt = conn.prepareStatement(cmd);
			pstmt.setString(1, newname);
			pstmt.setInt(1, did);
			pstmt.executeUpdate();
		}
		catch(SQLException e) {
			dbm.cleanup();
			throw new RuntimeException("error changing department name", e);
		}
	}
}
