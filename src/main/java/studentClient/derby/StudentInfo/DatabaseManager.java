import java.sql.*;
import org.apache.derby.jdbc.ClientDriver;

public class DatabaseManager {
	private Connection conn;
	private StudentDAO studentDAO;
	private DeptDAO    deptDAO;
	private EnrollDAO  enrollDAO;
	private SectionDAO sectionDAO;
	private CourseDAO  courseDAO;

	public DatabaseManager() {
    	try {
			Driver d = new ClientDriver();
			String url = "jdbc:derby://localhost/studentdb";
			conn = d.connect(url, null);
			conn.setAutoCommit(false);

			studentDAO = new StudentDAO(conn, this);
			deptDAO    = new DeptDAO(conn, this);
			enrollDAO  = new EnrollDAO(conn, this);
			sectionDAO = new SectionDAO(conn, this);
			courseDAO  = new CourseDAO(conn, this);
		}
		catch(SQLException e) {
			throw new RuntimeException("cannot connect to database", e);
		}
	}

	public void commit() {
		try {
			conn.commit();
		}
		catch(SQLException e) {
			throw new RuntimeException("cannot commit database", e);
		}
	}

	public void close() {
		try {
			conn.close();
		}
		catch(SQLException e) {
			throw new RuntimeException("cannot close database", e);
		}
	}

	public Student findStudent(int sid) {
		return studentDAO.find(sid);
	}

	public Dept findDept(int did) {
		return deptDAO.find(did);
	}

	public Enroll findEnroll(int eid) {
		return enrollDAO.find(eid);
	}

	public Course findCourse(int cid) {
		return courseDAO.find(cid);
	}

	public Section findSection(int sectid) {
		return sectionDAO.find(sectid);
	}

	public Student insertStudent(int sid, String sname, int gradyear, Dept major) {
		return studentDAO.insert(sid, sname, gradyear, major);
	}

	public Dept insertDept(int did, String dname) {
		return deptDAO.insert(did, dname);
	}

	public Enroll insertEnroll(int eid, Student student, Section section) {
		return enrollDAO.insert(eid, student, section);
	}

	public Section insertSection(int sectid, String prof, int year, Course course) {
		return sectionDAO.insert(sectid, prof, year, course);
	}

	public Course insertCourse(int cid, String title, Dept dept) {
		return courseDAO.insert(cid, title, dept);
	}

	public void cleanup() {
		try {
			conn.rollback();
			conn.close();
		}
		catch(SQLException e) {
			System.out.println("fatal error: cannot cleanup connection");
		}
	}
}
