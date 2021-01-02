import java.sql.*;
import javax.sql.rowset.*;
import org.apache.derby.jdbc.ClientDriver;
import com.sun.rowset.*;
import java.io.*;

public class WrsStudentsToXML {
   public static final String OUTFILE = "students2005.xml";

   public static void main(String[] args) {
		Connection conn = null;
		try {
			// Step 1: connect to database server
			Driver d = new ClientDriver();
			String url = "jdbc:derby://localhost/studentdb";
			conn = d.connect(url, null);

			// Step 2: execute the query
			Statement stmt = conn.createStatement();
			String qry  = "select s.SName, s.GradYear, c.Title, "
						+        "k.YearOffered, e.Grade "
						+ "from STUDENT s, ENROLL e, SECTION k, COURSE c "
						+ "where s.SId=e.StudentId and e.SectionId=k.SectId "
						+ "and k.CourseId=c.CId and s.GradYear=2005";

			ResultSet rs = stmt.executeQuery(qry);

			Writer w = new FileWriter(OUTFILE);
			WebRowSet wrs = new WebRowSetImpl();
			wrs.populate(rs);
			wrs.writeXml(w);
			rs.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			// Step 4: close the connection
			try {
				if (conn != null)
					conn.close();
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
