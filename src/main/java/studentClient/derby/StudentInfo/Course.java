import java.sql.*;
import java.util.Collection;

public class Course {
	private CourseDAO dao;
	private int cid;
	private String title;
	private Dept dept;
	private Collection<Section> sections = null;

	public Course(CourseDAO dao, int cid, String title, Dept dept) {
		this.dao   = dao;
		this.cid   = cid;
		this.title = title;
		this.dept  = dept;
	}

	public int getId() {
		return cid;
	}

	public String getTitle() {
		return title;
	}

	public Dept getDept() {
		return dept;
	}

	public Collection<Section> getSections() {
		if (sections == null)
			sections = dao.getSections(cid);
		return sections;
	}

	public void changeTitle(String newtitle) {
		title = newtitle;
		dao.changeTitle(cid, newtitle);
	}
}
