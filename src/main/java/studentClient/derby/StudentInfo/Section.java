import java.sql.*;
import java.util.Collection;

public class Section {
	private SectionDAO dao;
	private int sectid, year;
	private String prof;
	private Course course;
	private Collection<Enroll> enrollments = null;

	public Section(SectionDAO dao, int sectid, String prof, int year, Course course) {
		this.dao     = dao;
		this.sectid  = sectid;
		this.prof    = prof;
		this.year    = year;
		this.course  = course;
	}

	public int getId() {
		return sectid;
	}

	public String getProf() {
		return prof;
	}

	public int getYearOffered() {
		return year;
	}

	public Course getCourse() {
		return course;
	}

	public Collection<Enroll> getEnrollments() {
		if (enrollments == null)
			enrollments = dao.getEnrollments(sectid);
		return enrollments;
	}

	public void changeProf(String newprof) {
		prof = newprof;
		dao.changeProf(sectid, newprof);
	}
}
