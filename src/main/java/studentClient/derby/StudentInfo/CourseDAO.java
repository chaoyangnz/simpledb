import java.sql.*;
import java.util.*;
import org.apache.derby.jdbc.ClientDriver;

public class CourseDAO {
	private Connection conn;
	private DatabaseManager dbm;

	public CourseDAO(Connection conn, DatabaseManager dbm) {
		this.conn = conn;
		this.dbm  = dbm;
	}

	public Course find(int cid) {
		try {
			String qry = "select Title, DeptId from COURSE where CId = ?";
			PreparedStatement pstmt = conn.prepareStatement(qry);
			pstmt.setInt(1, cid);
			ResultSet rs = pstmt.executeQuery();

			// return null if course doesn't exist
			if (!rs.next())
				return null;

			String title = rs.getString("Title");
			int deptid   = rs.getInt("DeptId");
			rs.close();
			Dept dept = dbm.findDept(deptid);
			return new Course(this, cid, title, dept);
		}
		catch(SQLException e) {
			dbm.cleanup();
			throw new RuntimeException("error finding course", e);
		}
	}

	public Course insert(int cid, String title, Dept dept) {
		try {
			// make sure that the cid is currently unused
			if (find(cid) != null)
				return null;

			String cmd = "insert into COURSE(CId, Title, DeptId) "
			           + "values(?, ?, ?, ?)";
			PreparedStatement pstmt = conn.prepareStatement(cmd);
			pstmt.setInt(1, cid);
			pstmt.setString(2, title);
			pstmt.setInt(3, dept.getId());
			pstmt.executeUpdate();
			return new Course(this, cid, title, dept);
		}
		catch(SQLException e) {
			dbm.cleanup();
			throw new RuntimeException("error inserting new course", e);
		}
	}

	public Collection<Section> getSections(int cid) {
		try {
			Collection<Section> sections = new ArrayList<Section>();
			String qry = "select SectId from SECTION where CourseId = ?";
			PreparedStatement pstmt = conn.prepareStatement(qry);
			pstmt.setInt(1, cid);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int sectid = rs.getInt("SectId");
				sections.add(dbm.findSection(sectid));
			}
			rs.close();
			return sections;
		}
		catch(SQLException e) {
			dbm.cleanup();
			throw new RuntimeException("error getting course sections", e);
		}
	}

	public void changeTitle(int cid, String newtitle) {
		try {
			String cmd = "update COURSE set Title = ? where CId = ?";
			PreparedStatement pstmt = conn.prepareStatement(cmd);
			pstmt.setString(1, newtitle);
			pstmt.setInt(1, cid);
			pstmt.executeUpdate();
		}
		catch(SQLException e) {
			dbm.cleanup();
			throw new RuntimeException("error changing title", e);
		}
	}
}
