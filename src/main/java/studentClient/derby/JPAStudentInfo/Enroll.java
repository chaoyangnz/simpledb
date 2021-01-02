import javax.persistence.*;
import java.util.*;

@Entity
public class Enroll {

    @Id private int eid;
    private String grade = null;

    @ManyToOne
    @JoinColumn(name="StudentId")
    private Student student;

    @ManyToOne
    @JoinColumn(name="SectionId")
    private Section section;

	public Enroll() {}

	public Enroll(int eid, Student student, Section section) {
		this.eid = eid;
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

	public void changeGrade(String grade) {
		this.grade = grade;
	}
}
