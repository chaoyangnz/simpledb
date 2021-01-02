import java.sql.*;
import java.util.Collection;

public class Student {
	private StudentDAO dao;
	private int sid, gradyear;
	private String sname;
	private Dept major;
	private Collection<Enroll> enrollments = null;

	public Student(StudentDAO dao, int sid, String sname, int gradyear, Dept major) {
		this.dao      = dao;
		this.sid      = sid;
		this.sname    = sname;
		this.gradyear = gradyear;
		this.major    = major;
	}

	public int getId() {
		return sid;
	}

	public String getName() {
		return sname;
	}

	public int getGradYear() {
		return gradyear;
	}

	public Dept getMajor() {
		return major;
	}

	public Collection<Enroll> getEnrollments() {
		if (enrollments == null)
			enrollments = dao.getEnrollments(sid);
		return enrollments;
	}

	public void changeGradYear(int newyear) {
		gradyear = newyear;
		dao.changeGradYear(sid, newyear);
	}

	public void changeMajor(Dept newmajor) {
		major = newmajor;
		dao.changeMajor(sid, newmajor);
	}
}
