import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import javax.persistence.*;
import java.util.Collection;

public class JPAStudentInfo {
	public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("studentdb");
        EntityManager em = emf.createEntityManager();
   		JFrame frame = new TSFrame(em);
    	frame.setVisible(true);
    }
}

class TSFrame extends JFrame {
	public TSFrame(EntityManager em) {
		setTitle("Student Transcript Info");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(550,150);
        setLocation(200,200);
        getContentPane().add(new TSPanel(em));
    }
}

class TSPanel extends JPanel {
	private JLabel inputLbl  = new JLabel("Enter Student ID: ");
	private JTextField txt   = new JTextField(4);
	private JButton btn1     = new JButton("SHOW TRANSCRIPT");
	private JButton btn2     = new JButton("CHANGE GRADYEAR");
	private JButton btn3     = new JButton("CLOSE");
	private JLabel outputLbl = new JLabel("");
	private DefaultTableModel courses;
	private EntityManager em;

	public TSPanel(final EntityManager em) {
		this.em = em;
		Object[] columnNames = {"Title", "Year", "Grade"};
		courses = new DefaultTableModel(columnNames, 0);
		JTable tbl = new JTable(courses);
		JScrollPane sp = new JScrollPane(tbl);
		add(inputLbl); add(txt); add(btn1); add(btn2); add(btn3);
		add(outputLbl); add(sp);

		btn1.addActionListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int sid = Integer.parseInt(txt.getText());
					em.getTransaction().begin();
					Student s = em.find(Student.class, sid);
					display(s);
					em.getTransaction().commit();
				}
			});

		btn2.addActionListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String yearstring = JOptionPane.showInputDialog("Enter new grad year");
					int sid = Integer.parseInt(txt.getText());
					int newyear = Integer.parseInt(yearstring);
					em.getTransaction().begin();
					Student s = em.find(Student.class, sid);
					s.changeGradYear(newyear);
					display(s);
					em.getTransaction().commit();
				}
			});

		btn3.addActionListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					em.close();
					setVisible(false);
					System.exit(0);
				}
			});
	}

	private void display(Student s) {
		courses.setRowCount(0);
		if (s == null)
			outputLbl.setText("            No such student!");
		else {
			outputLbl.setText("Name: " + s.getName()
			            + "    Graduation Year: " + s.getGradYear());
			for (Enroll e : s.getEnrollments()) {
				Section k = e.getSection();
				Course  c = k.getCourse();
				Object[] row = {c.getTitle(), k.getYearOffered(), e.getGrade()};
				courses.addRow(row);
			}
		}
	}
}
