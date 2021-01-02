import java.sql.*;
import java.util.*;
import org.apache.derby.jdbc.ClientDriver;

public class StudentDAO {
	private Connection conn;
	private DatabaseManager dbm;

	public StudentDAO(Connection conn, DatabaseManager dbm) {
		this.conn = conn;
		this.dbm  = dbm;
	}

	public Student find(int sid) {
		try {
			String qry = "select SName, GradYear, MajorId from STUDENT where SId = ? ";
			PreparedStatement pstmt = conn.prepareStatement(qry);
			pstmt.setInt(1, sid);
			ResultSet rs = pstmt.executeQuery();

			// return null if student doesn't exist
			if (!rs.next())
				return null;

			String sname = rs.getString("SName");
			int gradyear = rs.getInt("GradYear");
			int majorid  = rs.getInt("MajorId");
			rs.close();

			Dept major = dbm.findDept(majorid);
			return new Student(this, sid, sname, gradyear, major);
		}
		catch(SQLException e) {
			dbm.cleanup();
			throw new RuntimeException("error finding student", e);
		}
	}

	public Student insert(int sid, String sname, int gradyear, Dept major) {
		try {
			// make sure that the sid is currently unused
			if (find(sid) != null)
				return null;

			String cmd = "insert into STUDENT(SId, SName, GradYear, MajorId) "
			           + "values(?, ?, ?, ?)";
			PreparedStatement pstmt = conn.prepareStatement(cmd);
			pstmt.setInt(1, sid);
			pstmt.setString(2, sname);
			pstmt.setInt(3, gradyear);
			pstmt.setInt(4, major.getId());
			pstmt.executeUpdate();
			return new Student(this, sid, sname, gradyear, major);
		}
		catch(SQLException e) {
			dbm.cleanup();
			throw new RuntimeException("error inserting new student", e);
		}
	}

	public Collection<Enroll> getEnrollments(int sid) {
		try {
			Collection<Enroll> enrollments = new ArrayList<Enroll>();
			String qry = "select EId from ENROLL where StudentId = ?";
			PreparedStatement pstmt = conn.prepareStatement(qry);
			pstmt.setInt(1, sid);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int eid = rs.getInt("EId");
				enrollments.add(dbm.findEnroll(eid));
			}
			rs.close();
			return enrollments;
		}
		catch(SQLException e) {
			dbm.cleanup();
			throw new RuntimeException("error getting student enrollments", e);
		}
	}

	public void changeGradYear(int sid, int newyear) {
		try {
			String cmd = "update STUDENT set GradYear = ? where SId = ?";
			PreparedStatement pstmt = conn.prepareStatement(cmd);
			pstmt.setInt(1, newyear);
			pstmt.setInt(2, sid);
			pstmt.executeUpdate();
		}
		catch(SQLException e) {
			dbm.cleanup();
			throw new RuntimeException("error changing grad year", e);
		}
	}

	public void changeMajor(int sid, Dept newmajor) {
		try {
			String cmd = "update STUDENT set MajorId = ? where SId = ?";
			PreparedStatement pstmt = conn.prepareStatement(cmd);
			pstmt.setInt(1, newmajor.getId());
			pstmt.setInt(2, sid);
			pstmt.executeUpdate();
		}
		catch(SQLException e) {
			dbm.cleanup();
			throw new RuntimeException("error changing major", e);
		}
	}
}
