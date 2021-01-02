import javax.persistence.*;
import java.util.*;

@Entity
public class Course {

    @Id private int cid;
    private String title;

    @OneToMany(mappedBy="course")
    private Collection<Section> sections;

    @ManyToOne
    @JoinColumn(name="DeptId")
    private Dept dept;

	public Course() {}

	public Course(int cid, String title, Dept dept) {
		this.cid = cid;
		this.title = title;
		this.dept = dept;
	}

    public int getId() {
        return cid;
    }

    public String getTitle() {
        return title;
    }

    public void changeTitle(String title) {
		this.title = title;
	}

    public Dept getDept() {
		return dept;
	}

	public Collection<Section> getSections() {
		return sections;
	}
}
