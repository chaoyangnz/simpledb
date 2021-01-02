import javax.persistence.*;
import java.util.*;

@Entity
public class Section {

    @Id private int sectid;
    private String prof;
    private int yearOffered;

    @OneToMany(mappedBy="section")
    private Collection<Enroll> students;

    @ManyToOne
    @JoinColumn(name="CourseId")
    private Course course;

	public Section() {}

	public Section(int sectid, String prof, int year, Course course) {
		this.sectid = sectid;
		this.prof = prof;
		this.yearOffered = year;
		this.course = course;
	}

    public int getId() {
        return sectid;
    }

    public String getProf() {
        return prof;
    }

    public void changeProf(String prof) {
		this.prof = prof;
	}

	public int getYearOffered() {
        return yearOffered;
    }

    public Course getCourse() {
		return course;
	}

	public Collection<Enroll> getStudents() {
		return students;
	}
}
