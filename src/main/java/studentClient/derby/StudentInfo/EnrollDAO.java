import java.sql.*;
import org.apache.derby.jdbc.ClientDriver;

public class EnrollDAO {
	private Connection conn;
	private DatabaseManager dbm;

	public EnrollDAO(Connection conn, DatabaseManager dbm) {
		this.conn = conn;
		this.dbm  = dbm;
	}

	public Enroll find(int eid) {
		try {
			String qry = "select Grade, StudentId, SectionId from ENROLL where EId = ?";
			PreparedStatement pstmt = conn.prepareStatement(qry);
			pstmt.setInt(1, eid);
			ResultSet rs = pstmt.executeQuery();

			// return null if enrollment doesn't exist
			if (!rs.next())
				return null;

			String grade = rs.getString("Grade");
			int sid      = rs.getInt("StudentId");
			int sectid   = rs.getInt("SectionId");
			rs.close();
			Student student = dbm.findStudent(sid);
			Section section = dbm.findSection(sectid);
			return new Enroll(this, eid, grade, student, section);
		}
		catch(SQLException e) {
			dbm.cleanup();
			throw new RuntimeException("error finding enrollment", e);
		}
	}

	public Enroll insert(int eid, Student student, Section section) {
		try {
			// make sure that the eid is currently unused
			if (find(eid) != null)
				return null;

			// the grade for a new enrollment is ""
			String grade = "";

			String cmd = "insert into ENROLL(EId, Grade, StudentId, SectionId) "
			           + "values(?, ?, ?, ?)";
			PreparedStatement pstmt = conn.prepareStatement(cmd);
			pstmt.setInt(1, eid);
			pstmt.setString(2, grade);
			pstmt.setInt(3, student.getId());
			pstmt.setInt(4, section.getId());
			pstmt.executeUpdate();
			return new Enroll(this, eid, grade, student, section);
		}
		catch(SQLException e) {
			dbm.cleanup();
			throw new RuntimeException("error inserting new enrollment", e);
		}
	}

	public void changeGrade(int eid, String newgrade) {
		try {
			String cmd = "update ENROLL set Grade = ? where EId = ?";
			PreparedStatement pstmt = conn.prepareStatement(cmd);
			pstmt.setString(1, newgrade);
			pstmt.setInt(1, eid);
			pstmt.executeUpdate();
		}
		catch(SQLException e) {
			dbm.cleanup();
			throw new RuntimeException("error changing grade", e);
		}
	}
}
