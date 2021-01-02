import java.sql.*;
import simpledb.remote.SimpleDriver;

public class CreateStudentDB {
    public static void main(String[] args) {
		Connection conn = null;
		try {
			Driver d = new SimpleDriver();
			conn = d.connect("jdbc:simpledb://localhost", null);
			Statement stmt = conn.createStatement();

			String s = "create table STUDENT(SId int, SName varchar(10), MajorId int, GradYear int)";
			stmt.executeUpdate(s);
			System.out.println("Table STUDENT created.");

			s = "insert into STUDENT(SId, SName, MajorId, GradYear) values ";
			String[] studvals = {"(1, 'joe', 10, 2004)",
								 "(2, 'amy', 20, 2004)",
								 "(3, 'max', 10, 2005)",
								 "(4, 'sue', 20, 2005)",
								 "(5, 'bob', 30, 2003)",
								 "(6, 'kim', 20, 2001)",
								 "(7, 'art', 30, 2004)",
								 "(8, 'pat', 20, 2001)",
								 "(9, 'lee', 10, 2004)"};
			for (int i=0; i<studvals.length; i++)
				stmt.executeUpdate(s + studvals[i]);
			System.out.println("STUDENT records inserted.");

			s = "create table DEPT(DId int, DName varchar(8))";
			stmt.executeUpdate(s);
			System.out.println("Table DEPT created.");

			s = "insert into DEPT(DId, DName) values ";
			String[] deptvals = {"(10, 'compsci')",
								 "(20, 'math')",
								 "(30, 'drama')"};
			for (int i=0; i<deptvals.length; i++)
				stmt.executeUpdate(s + deptvals[i]);
			System.out.println("DEPT records inserted.");

			s = "create table COURSE(CId int, Title varchar(20), DeptId int)";
			stmt.executeUpdate(s);
			System.out.println("Table COURSE created.");

			s = "insert into COURSE(CId, Title, DeptId) values ";
			String[] coursevals = {"(12, 'db systems', 10)",
								   "(22, 'compilers', 10)",
								   "(32, 'calculus', 20)",
								   "(42, 'algebra', 20)",
								   "(52, 'acting', 30)",
								   "(62, 'elocution', 30)"};
			for (int i=0; i<coursevals.length; i++)
				stmt.executeUpdate(s + coursevals[i]);
			System.out.println("COURSE records inserted.");

			s = "create table SECTION(SectId int, CourseId int, Prof varchar(8), YearOffered int)";
			stmt.executeUpdate(s);
			System.out.println("Table SECTION created.");

			s = "insert into SECTION(SectId, CourseId, Prof, YearOffered) values ";
			String[] sectvals = {"(13, 12, 'turing', 2004)",
								 "(23, 12, 'turing', 2005)",
								 "(33, 32, 'newton', 2000)",
								 "(43, 32, 'einstein', 2001)",
								 "(53, 62, 'brando', 2001)"};
			for (int i=0; i<sectvals.length; i++)
				stmt.executeUpdate(s + sectvals[i]);
			System.out.println("SECTION records inserted.");

			s = "create table ENROLL(EId int, StudentId int, SectionId int, Grade varchar(2))";
			stmt.executeUpdate(s);
			System.out.println("Table ENROLL created.");

			s = "insert into ENROLL(EId, StudentId, SectionId, Grade) values ";
			String[] enrollvals = {"(14, 1, 13, 'A')",
								   "(24, 1, 43, 'C' )",
								   "(34, 2, 43, 'B+')",
								   "(44, 4, 33, 'B' )",
								   "(54, 4, 53, 'A' )",
								   "(64, 6, 53, 'A' )"};
			for (int i=0; i<enrollvals.length; i++)
				stmt.executeUpdate(s + enrollvals[i]);
			System.out.println("ENROLL records inserted.");

		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
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
