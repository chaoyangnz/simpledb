import java.sql.*;
import java.util.*;
import org.apache.derby.jdbc.ClientDriver;

public class SectionDAO {
	private Connection conn;
	private DatabaseManager dbm;

	public SectionDAO(Connection conn, DatabaseManager dbm) {
		this.conn = conn;
		this.dbm  = dbm;
	}

	public Section find(int sectid) {
		try {
			String qry = "select Prof, YearOffered, CourseId "
					   + "from SECTION where SectId = ?";
			PreparedStatement pstmt = conn.prepareStatement(qry);
			pstmt.setInt(1, sectid);
			ResultSet rs = pstmt.executeQuery();

			// return null if section doesn't exist
			if (!rs.next())
				return null;

			String prof = rs.getString("Prof");
			int year = rs.getInt("YearOffered");
			int courseid  = rs.getInt("CourseId");
			rs.close();
			Course course = dbm.findCourse(courseid);
			return new Section(this, sectid, prof, year, course);
		}
		catch(SQLException e) {
			dbm.cleanup();
			throw new RuntimeException("error finding section", e);
		}
	}

	public Section insert(int sectid, String prof, int year, Course course) {
		try {
			// make sure that the sectid is currently unused
			if (find(sectid) != null)
				return null;

			String cmd = "insert into SECTION(SectId, Prof, YearOffered, CourseId) "
			           + "values(?, ?, ?, ?)";
			PreparedStatement pstmt = conn.prepareStatement(cmd);
			pstmt.setInt(1, sectid);
			pstmt.setString(2, prof);
			pstmt.setInt(3, year);
			pstmt.setInt(4, course.getId());
			pstmt.executeUpdate();
			return new Section(this, sectid, prof, year, course);
		}
		catch(SQLException e) {
			dbm.cleanup();
			throw new RuntimeException("error inserting new section", e);
		}
	}

	public Collection<Enroll> getEnrollments(int sectid) {
		try {
			Collection<Enroll> enrollments = new ArrayList<Enroll>();
			String qry = "select EId from ENROLL where SectionId = ?";
			PreparedStatement pstmt = conn.prepareStatement(qry);
			pstmt.setInt(1, sectid);
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
			throw new RuntimeException("error getting section enrollments", e);
		}
	}

	public void changeProf(int sectid, String newprof) {
		try {
			String cmd = "update SECTION set Prof = ? where SectId = ?";
			PreparedStatement pstmt = conn.prepareStatement(cmd);
			pstmt.setString(1, newprof);
			pstmt.setInt(1, sectid);
			pstmt.executeUpdate();
		}
		catch(SQLException e) {
			dbm.cleanup();
			throw new RuntimeException("error changing prof", e);
		}
	}
}
