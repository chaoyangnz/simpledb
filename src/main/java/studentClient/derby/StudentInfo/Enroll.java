import java.sql.*;
import java.util.*;

public class Enroll {
	private EnrollDAO dao;
	private int eid;
	private String grade;
	private Student student;
	private Section section;

	public Enroll(EnrollDAO dao, int eid, String grade, Student student, Section section) {
		this.dao     = dao;
		this.eid     = eid;
		this.grade   = grade;
		this.student = student;
		this.section = section;
	}

	public int getId() {
		return eid;
	}

	public Student getStudent() {
		return student;
	}

	public Section getSection() {
		return section;
	}

	public String getGrade() {
		return grade;
	}

	public void changeGrade(String newgrade) {
		grade = newgrade;
		dao.changeGrade(eid, newgrade);
	}
}
