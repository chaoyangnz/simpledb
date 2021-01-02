import java.sql.*;
import org.apache.derby.jdbc.ClientDriver;
import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;


public class ServletFindMajorsInputForm extends HttpServlet {
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
        throws IOException, ServletException
    {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
	    out.println("<title> Find Majors </title>");
        out.println("</head>");
        out.println("<body>");
		out.println("<form method=\"get\" "
				        + "action=\"/studentdb/ServletFindMajors\">");
		out.println("Enter major:");
		out.println("<select name=\"major\">");

		Connection conn = null;
		try {
			// Step 1: connect to database server
			Driver d = new ClientDriver();
			String url = "jdbc:derby://localhost/studentdb";
			conn = d.connect(url, null);

			// Step 2: execute the query
			Statement stmt = conn.createStatement();
			String qry = "select dname from dept";
			ResultSet rs = stmt.executeQuery(qry);

			// Step 3: loop through the result set
			while (rs.next()) {
				String dname = rs.getString("DName");
				out.println("<option value=\"" + dname + "\">"
				            + dname + "</option>");
			}
			rs.close();
			out.println("</select>");
			out.println("<p><input type=\"submit\" value=\"Find Majors\">");
			out.println("</form>");
		}
		catch(Exception e) {
			e.printStackTrace();
			out.println("SQL Exception. Execution aborted");
		}
		finally {
	        out.println("</body>");
	        out.println("</html>");
			try {
				if (conn != null)
					conn.close();
			}
			catch (SQLException e) {
				e.printStackTrace();
				out.println("Could not close database");
			}
		}
    }
}



