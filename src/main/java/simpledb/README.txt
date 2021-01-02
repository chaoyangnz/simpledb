                      THE SIMPLEDB DATABASE SYSTEM
                  General Information and Instructions


This document contains the following sections:
    * Release Notes
    * Server Installation
    * Running the Server
    * Running Client Programs
    * SimpleDB Limitations
    * The Organization of the Server Code


I. Release Notes:

  This release of the SimpleDB system is Version 2.10, which was
  uploaded on January 16, 2013.  This release provides the following
  fixes to Version 2.10:

    * The files simpledb.Startup and remote.SimpleDriver have been changed 
      to use a server-specific registry, instead of forcing the user to 
      run rmiregistry as a separate process.
    * The files ConnectionAdapter, DriverAdapter, StatementAdapter, and 
      ResultSetAdapter in simpledb.remote have been changed to handle
      the new Java 7 JDBC methods.
    * A bug was fixed in the file SortScan.java.
    * The new client file StudentMajorNoServer was added.

  SimpleDB is distributed in a WinZip-formatted file. This file contains
  four items:

    * The folder simpledb, which contains the server-side Java code.
    * The folder javadoc, which contains the JavaDoc documentation 
      of the above code.
    * The folder studentClient, which contains some client-side code 
      for an example database.
    * This document.

  The author welcomes all comments, including bug reports, suggestions
  for improvement, and anectodal experiences.  His email address is 
  sciore@bc.edu
  

II. Installation Instructions:

  1)  Install the Java SDK, level 1.5 or higher.

  2)  If you do install Java 1.5, you need to make some minor changes 
      to the package simpledb.remote:
    
      * The classes named xxxAdapter provide default implementations of 
        the interfaces in java.sql. Java 1.6 added several extra methods 
        to these interfaces. If you are using Java 1.5, just comment out 
        those methods. (You can tell which ones they are because you'll 
        get an error when you try to compile them.)
      
      * The classes named SimpleXXX call the SQLException constructor 
        with a Throwable argument.  This constructor is new to version
        1.6. To use in 1.5, rewrite the code "throw new SQLException(e)"
        to be "throw new SQLException(e.getMessage())".

  3)  Decide where you want the server-side software to go. Let's assume 
      that the code will go in the folder C:\javalib in Windows, or the 
      folder ~/javalib in UNIX or MacOS.

  4)  Add that folder to your classpath. In other words, the javalib 
      folder must be mentioned in your CLASSPATH environment variable.
    
      * In UNIX, your home directory has an initialization file, 
        typically called .bashrc.  If the file does not set CLASSPATH,
        add the following line to the file:  
               CLASSPATH =.:~/javalib     
               
        Here, the ‘:’ character separates folder names.  The command 
        therefore says that the folder "." (i.e., your current diretory) 
        and "~/javalib" are to be searched whenever Java needs to find a 
        class.  If the file already contains a CLASSPATH setting, modify 
        it to include the javalib directory.
 
      * In Windows, you must set the CLASSPATH variable via the System 
        control panel.  From that control panel, choose the advanced tab 
        and click on the environment variables button.  You want to have 
        a user variable named CLASSPATH that looks like this:
               .;C:\javalib
               
        Here, the ‘;’ character separates the two folder names.

  5)  Copy the simpledb folder from the distribution file to that
      folder. Within the simpledb folder should be subfolders 
      containing all of the code for SimpleDB.


III. Running the Server:

  SimpleDB has a client-server architecture. You run the server code on 
  a host machine, where it will sit and wait for connections from clients.
  It is able to handle multiple simultaneous requests from clients, 
  each on possibly different machines. You can then run a client program
  from any machine that is able to connect to the host machine.

  To run the SimpleDB server, run Java on the simpledb.server.Startup class.  
  You must pass in the name of a folder that SimpleDB will use to hold the 
  database. For example in Windows, if you execute the command:
      
         > start java simpledb.server.Startup studentdb
             
  then the server will run in a new window, using studentdb as the
  database folder.  You can execute this command from any directory;
  the server will always use the studentdb folder that exists in your
  home directory.  If a folder with that name does not exist, then
  one will be created automatically.
 
  If everything is working correctly, when you run the server with a
  new database folder the following will be printed in the server’s 
  window:

      creating new database
      new transaction: 1
      transaction 1 committed
      database server ready

  If you run the server with an existing database folder, the following
  will be printed instead:

      recovering existing database
      database server ready

  In either case, the server will then sit awaiting connections from
  clients.  As connections arrive, the server will print additional
  messages in its window.

  The server is implemented using RMI, and requires that an RMI registry
  be running on port 1099. If a registry is running when the server is 
  started, it will use that registry; otherwise, it will run the registry
  itself.


IV. Running Client Programs 

  The SimpleDB server accepts connections from any JDBC client. The client
  program makes its connection via the following code:
            Driver d = new SimpleDriver();
            String host = "mymachine.com"; //any DNS name or IP address
            String url = "jdbc:simpledb://" + host;
            Connection conn = d.connect(url, null);

  Note that SimpleDB does not require a username and password, although
  it is easy enough to modify the server code to do so.

  The driver class SimpleDriver is contained in the package 
  simpledb.remote, along with the other classes that it needs. A client
  program will not run unless this package in its classpath. Note that
  you could install the entire SimpleDB server code on a client machine,
  but that is overkill.  All you need is simpledb.remote.

  The studentClient folder contains client code for a simple university
  student-course database.  The folder contains two subfolders, named 
  simpledb and derby.  The simpledb subfolder contains programs that 
  run with the SimpleDB database server. The derby subfolder is not
  relevant here. (It contains programs for the Derby database server,
  which can be downloaded from db.apache.org. That code is used to
  illustrate some examples from my text "Database Design and
  Implementation", published by John WIley.) 

  The following list briefly describes the SimpleDB clients.

    * CreateStudentDB creates and populates the student database used
      by the other clients.  It therefore must be the first client run 
      on a new database. 
    * StudentMajors prints a table listing the names of students and 
      their majors.
    * FindMajors requires a command-line argument denoting the name of 
      a department.  The program then prints the name and graduation
      year of all students having that major.
    * SQLInterpreter repeatedly prints a prompt asking you to enter a 
      single line of text containing an SQL statement.  The program then 
      executes that statement.  If the statement is a query, the output 
      table is displayed.  If the statement is an update command, then
      the number of affected records is printed.  If the statement is ill
      formed, and error message will be printed.  SimpleDB understands 
      only a limited subset of SQL, which is described below.
    * ChangeMajor changes the student named Amy to be a drama major.  
      It is the only client that updates the database (although you can 
      use SQLInterpreter to run update commands).

  These clients connect to the server at "localhost".  If the client is  
  to be run from a different machine than the server, then its source code 
  must be modified so that localhost is replaced by the domain name (or IP 
  address) of the server machine. 
  
  Unlike the server classes, the client classes are not part of an 
  explicit package, and thus they need to be run from the directory that
  they are stored in. For example, suppose we copy the studentClient 
  folder from the distribution file to our home directory.  In Windows
  we could execute the client programs as follows:

             > cd C:\studentClient\simpledb
             > java CreateStudentDB


V. Running SimpleDB as a Standalone Program

  It is possible to write a program that calls the SimpleDB source code 
  directly, instead of calling server.Startup. The demo program 
  StudentMajorNoServer is an example. In this case, the entire database 
  source code is available to the program. Such programs are very useful
  for testing changes to the source code without having to run the server 
  and a client.  


VI. SimpleDB Limitations

  SimpleDB is a teaching tool. It deliberately implements a tiny subset
  of SQL and JDBC, and (for simplicity) imposes restrictions not present
  in the SQL standard.  Here we briefly indicate these restrictions.


  SimpleDB SQL
  
  A query in SimpleDB consists only of select-from-where clauses in which
  the select clause contains a list of fieldnames (without the AS 
  keyword), and the from clause contains a list of tablenames (without
  range variables).
 
  The where clause is optional.  The only Boolean operator is and.  The
  only comparison operator is equality.  Unlike standard SQL, there are
  no other comparison operators, no other Boolean operators, no arithmetic
  operators or built-in functions, and no parentheses.  Consequently,
  nested queries, aggregation, and computed values are not supported.

  Views can be created, but a view definition can be at most 100 
  characters.
 
  Because there are no range variables and no renaming, all field names in
  a query must be disjoint.  And because there are no group by or order by
  clauses, grouping and sorting are not supported.  Other restrictions:

    * The "*" abbreviation in the select clause is not supported.
    * There are no null values.
    * There are no explicit joins or outer joins in the from clause.
    * The union and except keywords are not supported.
    * Insert statements take explicit values only, not queries.
    * Update statements can have only one assignment in the set clause.


  SimpleDB JDBC
  
  SimpleDB implements only the following JDBC methods:

   Driver

      public Connection connect(String url, Properties prop);
      // The method ignores the contents of variable prop.

   Connection

      public Statement createStatement();
      public void      close();

   Statement

      public ResultSet executeQuery(String qry);
      public int       executeUpdate(String cmd);

   ResultSet

      public boolean   next();
      public int       getInt();
      public String    getString();
      public void      close();
      public ResultSetMetaData getMetaData();

   ResultSetMetaData

      public int        getColumnCount();
      public String     getColumnName(int column);
      public int        getColumnType(int column);
      public int getColumnDisplaySize(int column);


VII. The Organization of the Server Code

  SimpleDB is usable without knowing anything about what the code looks
  like. However, the entire point of the system is to make the code
  easy to read and modify.  The basic packages in SimpleDB are structured
  hierarchically, in the following order:

    * file (Manages OS files as a virtual disk.)
    * log (Manages the log.)
    * buffer (Manages a buffer pool of pages in memory that acts as a
              cache of disk blocks.)
    * tx (Implements transactions at the page level.  Does locking
          and logging.)
    * record (Implements fixed-length records inside of pages.)
    * metadata (Maintains metadata in the system catalog.)
    * query (Implements relational algebra operations.  Each operation 
             has a plan class, used by the planner, and a scan class,
             used at runtime.)
    * parse (Implements the parser.)
    * planner (Implements a naive planner for SQL statements.)
    * remote (Implements the server using RMI.)
    * server (The place where the startup and initialization code live. 
              The class Startup contains the main method.)

  The basic server is exceptionally inefficient.  The following packages
  enable more efficient query processing:

    * index (Implements static hash and btree indexes, as well as 
             extensions to the parser and planner to take advantage
             of them.)
    * materialize (Implements implementations of the relational 
                   operators materialize, sort, groupby, and mergejoin.)
    * multibuffer (Implements modifications to the sort and product 
                   operators, in order to make optimum use of available
                   buffers.)
    * opt (Implements a heuristic query optimizer)
 
   The textbook "Database Design and Implementation" describes these
   packages in considerably more detail. For further information, go
   to the URL www.wiley.com/college/sciore
   