import java.sql.*;
import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import org.apache.derby.jdbc.ClientDriver;

public class ServletFindMajors extends HttpServlet {
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
        throws IOException, ServletException
    {
		String major = request.getParameter("major");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
	    out.println("<title> Student Majors </title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<P>Here are the " + major + " majors:");

		Connection conn = null;
		try {
			// Step 1: connect to database server
			Driver d = new ClientDriver();
			String url = "jdbc:derby://localhost/studentdb";
			conn = d.connect(url, null);

			// Step 2: execute the query
			String qry = "select SName, GradYear "
			           + "from STUDENT, DEPT "
			           + "where DId = MajorId and DName = ?";
			PreparedStatement pstmt = conn.prepareStatement(qry);
			pstmt.setString(1, major);
			ResultSet rs = pstmt.executeQuery();

			// Step 3: loop through the result set
			out.println("<P><table border=1 cellpadding=2>");
			out.println("<tr><th>Name</th><th>GradYear</th></tr>");
			while (rs.next()) {
				String name = rs.getString("SName");
				int    year = rs.getInt("GradYear");
				out.println("<tr><td>" + name + "</td><td>" + year + "</td></tr>");
			}
			out.println("</table>");
			rs.close();
		}
		catch(Exception e) {
			e.printStackTrace();
			out.println("SQL Exception. Execution aborted");
		}
		finally {
			try {
				if (conn != null)
					conn.close();
			}
			catch (SQLException e) {
				e.printStackTrace();
				out.println("Could not close database");
			}
       	 	out.println("</body>");
      	 	out.println("</html>");
		}
    }
}



