import java.sql.*;
import org.apache.derby.jdbc.ClientDriver;
import java.io.*;

public class StudentsToXML {
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
			String qry = "select s.SName, s.GradYear, c.Title, "
						+       "k.YearOffered, e.Grade "
						+ "from STUDENT s, ENROLL e, SECTION k, COURSE c "
						+ "where s.SId=e.StudentId and e.SectionId=k.SectId "
						+ "and k.CourseId=c.CId and s.GradYear=2005";
			ResultSet rs = stmt.executeQuery(qry);

			Writer w = new FileWriter(OUTFILE);
			writeXML(w, rs, "Student");
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

		public static void writeXML(Writer w, ResultSet rs, String elementname) throws Exception {
		w.write("<" + elementname + "s>\n");

		ResultSetMetaData md = rs.getMetaData();
		int colcount = md.getColumnCount();
		while(rs.next()) {
			w.write("\t<" + elementname + ">\n");
			for (int i=1; i<=colcount; i++) {
				String col = md.getColumnName(i);
				String val = rs.getString(i);
				w.write("\t\t<" + col + ">" + val + "</" + col + ">\n");
			}
			w.write("\t</" + elementname + ">\n");
		}
		w.write("</" + elementname + "s>\n");
	}
}
