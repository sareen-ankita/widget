package test;
import java.io.BufferedReader;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Testing {

	public static void main(String[] args) {
			
       createNewDatabase();                   // Creates a new SQL-Lite Database connection
       createNewDepartmentAndEmployeesTable();
       Testing t = new Testing();
       
       // Inserting Test Data in Departments Table
       t.insertIntoDepartmentsTable(1, "Management", "LONDON");
       t.insertIntoDepartmentsTable(2, "Engineering", "CARDIFF");
       t.insertIntoDepartmentsTable(3, "R&D", "EDINBURGH");
       t.insertIntoDepartmentsTable(4, "Sales", "BELFAST");
       
       //Inserting Test Data in Employees Table
       t.insertIntoEmployeesTable(90001, "John", "CEO", 1, "01/01/95", 100000, 1);
       t.insertIntoEmployeesTable(90002, "Jimmy", "Manager", 90001, "23/09/03", 52500, 4);
       t.insertIntoEmployeesTable(90003, "Roxy", "Sales", 90002, "11/02/17", 35000, 4);
       t.insertIntoEmployeesTable(90004, "Selwyn", "Sales", 90003, "20/05/15", 32000, 4);
       t.insertIntoEmployeesTable(90005, "David", "Engineer", 90006, "17/04/18", 40000, 2);
       t.insertIntoEmployeesTable(90008, "Tully", "Sales", 90005, "28/07/11", 55000, 4);
       t.insertIntoEmployeesTable(90006, "Andrew", "Engineer", 90002, "12/12/12", 50000, 2);
       t.insertIntoEmployeesTable(90007, "Leena", "R&D", 90003, "11/11/11", 45000, 3);
       t.insertIntoEmployeesTable(90009, "Maggie", "Manager", 90004, "08/07/08", 45000, 1);
       t.insertIntoEmployeesTable(90010, "Summer", "R&D", 90005, "06/06/18", 45000, 3);
       
       
       try {
           BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));   
           System.out.println("\n Please choose one of the following locations");
           System.out.println("London");
           System.out.println("Cardiff");
           System.out.println("Edinburgh");
           System.out.println("Belfast");
           
           System.out.print("\n Enter Location: ");

           String name = reader.readLine();
           System.out.println("\n Your loaction is: " + name);
           
           t.fetchEmployeeDataByLocation(name);
           
           System.out.print("\n Do you need Employee Data by Location Extract Y/N: ");

           String location = reader.readLine();
           if ((location.equalsIgnoreCase("y"))   || (location.equalsIgnoreCase("yes")) ) {
        	   t.employeeDataByLocationExtract(name);  
           }
           
           
           
           System.out.println("\n Please choose one of the following Department ID");
           System.out.println("1");
           System.out.println("2");
           System.out.println("3");
           System.out.println("4");
           System.out.print("\n Enter Department ID: ");
           try {
           int id = Integer.parseInt(reader.readLine());
           System.out.println("Department id is: " + id);
           
           t.fetchEmployeeDataByDepartmentId(id); 
           }catch(NumberFormatException e){
             System.out.println("\n Incorrect Department id value");
           }
           
           
           
           t.deleteEmployeesTableRecords();
           t.deleteDepartmentTableRecords();
           

       } catch (IOException ioe) {
           ioe.printStackTrace();
       }
       
          
	}

	
	public static void createNewDatabase() {

        
		String url = "jdbc:sqlite:C://sqlite/db/Records.db";
		

        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database Records.db has been created.");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
	
    public static void createNewDepartmentAndEmployeesTable() {
        
    	 String url = "jdbc:sqlite:C://sqlite/db/Records.db";
        
        // Create Departments Table
        String sql = "CREATE TABLE IF NOT EXISTS Departments (\n"
                + "	id integer PRIMARY KEY,\n"
                + "	name text NOT NULL,\n"
                + "	location text NOT NULL\n"
                + ");";
        
        String sqlInsert = "CREATE TABLE IF NOT EXISTS Employees (\n"
                + "	empid integer PRIMARY KEY,\n"
                + "	empname text NOT NULL,\n"
                + "	jobtitle text NOT NULL,\n"
                + "	managerid Integer ,\n"
                + "	datehired text NOT NULL,\n"
                + "	salary integer NOT NULL,\n"
                + "	departmentid integer NOT NULL\n"
                + ");";
        try (Connection conn = DriverManager.getConnection(url);
                Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
            stmt.execute(sqlInsert);
               
            System.out.println("\n Employee and Department Table created successfully");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    private Connection connect() {
        // SQLite connection string
        String url = "jdbc:sqlite:C://sqlite/db/Records.db";
    	Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
    
    public void insertIntoEmployeesTable(int empid, String empname, String jobtitle, int managerid, String datehired, int salary, int departmentid) {
    	 String sql = "INSERT INTO Employees(empid,empname,jobtitle,managerid,datehired,salary,departmentid) VALUES(?,?,?,?,?,?,?)";
    	 try (Connection conn = this.connect();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
             pstmt.setInt(1, empid);
             pstmt.setString(2, empname);
             pstmt.setString(3, jobtitle);
             pstmt.setInt(4, managerid);
             pstmt.setString(5, datehired);
             pstmt.setInt(6, salary);
             pstmt.setInt(7, departmentid);
             pstmt.executeUpdate();
         }
    	 catch (SQLException e) {
             System.out.println(e.getMessage());
         }
    }
    
    public void insertIntoDepartmentsTable(int id, String name, String location) {
   	 String sql = "INSERT INTO Departments(id,name,location) VALUES(?,?,?)";
   	 try (Connection conn = this.connect();
        PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.setString(2, name);
            pstmt.setString(3, location);
            pstmt.executeUpdate();
        }
   	 catch (SQLException e) {
            System.out.println(e.getMessage());
        }
   }
    
    
    public void fetchEmployeeDataByDepartment(){
    	
    	System.out.println("Employee Data by Department");
        String sql = "SELECT Employees.empid,\r\n"
        		+ "       Employees.empname,\r\n"
        		+ "       Employees.jobtitle,\r\n"
        		+ "       Employees.salary,\r\n"
        		+ "       Departments.name\r\n"
        		+ "  FROM Departments\r\n"
        		+ "  Inner Join Employees\r\n"
        		+ "  ON Departments.id = Employees.departmentid;";
        
        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            
            
            while (rs.next()) {
                System.out.println(rs.getInt("empid") +  "\t" + 
                                   rs.getString("empname") + "\t" +
                                   rs.getString("jobtitle") + "\t" +
                                   + rs.getInt("salary") + "\t" +
                                   rs.getString("name")  );
                
            }
            System.out.println("*********************************************");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void fetchEmployeeDataByLocation(String locationa) throws IOException{
    	ResultSet rs = null;
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        int i=0;
        
    	System.out.println("\n Employee Data by Location \n");
        String sql = "SELECT Employees.empid,\r\n"
        		+ "       Employees.empname,\r\n"
        		+ "       Employees.jobtitle,\r\n"
        		+ "       Employees.salary,\r\n"
        		+ "       Departments.name,\r\n"
        		+ "       Departments.location\r\n"
        		+ "  FROM Departments\r\n"
        		+ "  Inner Join Employees\r\n"
        		+ "  ON Departments.id = Employees.departmentid WHERE Departments.location = ?;";
        
        try {
            conn = connect();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, locationa.toUpperCase());
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
            	
            	
            	
              System.out.println(rs.getInt("empid") +  "\t" + 
                                 rs.getString("empname") + "\t" +
                                 rs.getString("jobtitle") + "\t" +
                                 rs.getInt("salary") + "\t" +
                                 rs.getString("name") + "\t" +
                                 rs.getString("location")  );
              i++;
          }
            
            if (i == 0) {
            	System.out.println("\n Location does not exist in our system");
            }
           
        }
   

         catch (SQLException e) {
                  System.out.println(e.getMessage());
              }
        
       
    }
    
    public void fetchEmployeeDataByDepartmentId(int departmentId){
    	ResultSet rs = null;
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        if(departmentId< 0 && departmentId>4 ) {
        	System.out.println("Records does not exist in DB");
        	
        	
        }else {
    	System.out.println("\n Employee Data by DepartmentID \n");
    	String sql = "SELECT Employees.empid,\r\n"
        		+ "       Employees.empname,\r\n"
        		+ "       Employees.jobtitle,\r\n"
        		+ "       Employees.salary,\r\n"
        		+ "       Departments.name\r\n"
        		+ "  FROM Departments\r\n"
        		+ "  Inner Join Employees\r\n"
        		+ "  ON Departments.id = Employees.departmentid WHERE Departments.id = ?;";
        
        try {
            conn = connect();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, departmentId);
            rs = pstmt.executeQuery();
            while (rs.next()) {
            	System.out.println(rs.getInt("empid") +  "\t" + 
                        rs.getString("empname") + "\t" +
                        rs.getString("jobtitle") + "\t" +
                        + rs.getInt("salary") + "\t" +
                        rs.getString("name")  );
          }
        }
   

         catch (SQLException e) {
                  System.out.println(e.getMessage());
              }
        } 
    }
        
        public void deleteDepartmentTableRecords(){
        	
            Connection conn = null;
            PreparedStatement pstmt = null;
            System.out.println("\n *********Deleting Department table data*********");
        	 	String sql = "Delete from Departments;";
            
            try {
                conn = connect();
                pstmt = conn.prepareStatement(sql);
              
                pstmt.executeUpdate();
                }
            
             catch (SQLException e) {
                      System.out.println(e.getMessage());
                  }
            
    }

        
        public void deleteEmployeesTableRecords(){
        
            Connection conn = null;
            PreparedStatement pstmt = null;
            System.out.println("\n ********Deleting Employee table data**********");
        	String sql = "Delete from Employees;";
            
            try {
                conn = connect();
                pstmt = conn.prepareStatement(sql);
               
                pstmt.executeUpdate();
                }
            
             catch (SQLException e) {
                      System.out.println(e.getMessage());
                  }
            
    }
        
        public void employeeDataByLocationExtract(String locationa) throws IOException{
        	ResultSet rs = null;
            
            Connection conn = null;
            PreparedStatement pstmt = null;
            int i=0;
            
        	System.out.println("\n Creating Extract for Employee Data by Location at D://output.csv ");
            String sql = "SELECT Employees.empid,\r\n"
            		+ "       Employees.empname,\r\n"
            		+ "       Employees.jobtitle,\r\n"
            		+ "       Employees.salary,\r\n"
            		+ "       Departments.name,\r\n"
            		+ "       Departments.location\r\n"
            		+ "  FROM Departments\r\n"
            		+ "  Inner Join Employees\r\n"
            		+ "  ON Departments.id = Employees.departmentid WHERE Departments.location = ?;";
            
            try {
                conn = connect();
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, locationa.toUpperCase());
                rs = pstmt.executeQuery();
                
                FileWriter writer = new FileWriter("D:\\output.csv");
                while (rs.next()) {
                	
                	String record = "Employee ID: " + rs.getInt("empid") + ","+ " Employee Name: " + rs.getString("empname") + "," + "Job Title: "  + rs.getString("jobtitle") + "," + "Salary: " + rs.getInt("salary") 
                	+ "," + " Department Name: " + rs.getString("name") + "," + " Department Location: " + rs.getString("location");
                	
                	writer.append(record);
                	writer.append(",");
                	writer.append("\n");
                	

                  i++;
              }
                
                if (i == 0) {

                	writer.append("\n Location does not exist in our system");
                }
                writer.flush();
                writer.close();
            }
       

             catch (SQLException e) {
                      System.out.println(e.getMessage());
                  }
            
           
        }
    
}
