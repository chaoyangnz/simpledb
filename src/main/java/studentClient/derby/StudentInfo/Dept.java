import java.sql.*;
import java.util.Collection;

public class Dept {
	private DeptDAO dao;
	private int did;
	private String dname;
	private Collection<Student> majors = null;
	private Collection<Course> courses = null;

	public Dept(DeptDAO dao, int did, String dname) {
		this.dao   = dao;
		this.did   = did;
		this.dname = dname;
	}

	public int getId() {
		return did;
	}

	public String getName() {
		return dname;
	}

	public void changeName(String newname) {
			dname = newname;
			dao.changeName(did, newname);
	}

	public Collection<Student> getMajors() {
		if (majors == null)
			majors = dao.getMajors(did);
		return majors;
	}

	public Collection<Course> getCourses() {
		if (courses == null)
			courses = dao.getCourses(did);
		return courses;
	}
}
