package university;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class db {

private String url = "jdbc:postgresql://hattie.db.elephantsql.com:5432/hsbalsct" ;
private String username;
private String password;
Connection db = null;


public db (String Username, String Password) throws SQLException{
	this.username = Username;
	this.password = Password;
	
	try{
	Class.forName("org.postgresql.Driver");
	db = DriverManager.getConnection(url, Username, Password);
 	}
	catch (java.lang.ClassNotFoundException e) {
            System.out.println(e.getMessage());
     }
}
 


public void addPerson(String email, String name, String role, String adress, int age){
	try {
	      Statement st = db.createStatement();
	      DatabaseMetaData dbm = db.getMetaData();
	      ResultSet tables = dbm.getTables(null, null, "Person", null);
	       if(tables.next()){
	         
	        }else{
	          ResultSet rs = st.executeQuery("CREATE TABLE Person(email varchar(255), name varchar(255), role varchar(255), adress varchar(255), age int, PRIMARY KEY(email));");
	            }
	            st.close();
	            //st2.close();
	            }
	        catch (java.sql.SQLException e) {
	            System.out.println(e.getMessage());
	        }try {
	            Statement st = db.createStatement();
	            ResultSet rs2 = st.executeQuery("INSERT INTO Person(email, name, role, adress, age) VALUES('"+email+"', '"+name+"', '"+role+"', '"+adress+"', '"+age+"');");
	            rs2.close();
	            st.close();
	            }
	        catch (java.sql.SQLException e) {
	            System.out.println(e.getMessage());
	        } 
	
}

public void addCourse(int CourseID, String faculty){
	try {
	      Statement st = db.createStatement();
	      DatabaseMetaData dbm = db.getMetaData();
	      ResultSet tables = dbm.getTables(null, null, "Course", null);
	       if(tables.next()){
	         
	        }else{
	          ResultSet rs = st.executeQuery("CREATE TABLE Course(CourseID int, faculty varchar(255), PRIMARY KEY(CourseID));");
	            }
	            st.close();
	            //st2.close();
	            }
	        catch (java.sql.SQLException e) {
	            System.out.println(e.getMessage());
	        }try {
	            Statement st = db.createStatement();
	            ResultSet rs2 = st.executeQuery("INSERT INTO Course(CourseID, faculty) VALUES( '"+CourseID+"', '"+faculty+"');");
	            rs2.close();
	            st.close();
	            }
	        catch (java.sql.SQLException e) {
	            System.out.println(e.getMessage());
	        } 
	
}

 
public void selectStudent(){
       try {
           Statement st = db.createStatement();
           ResultSet rs = st.executeQuery("SELECT * FROM Person WHERE CourseID > 4000 and email = 'patrick@live.dk' and name = 'Patrick' and adress = 'Fredericiavej 2, 5500 Middelfart'");
	
           while (rs.next()) {
            System.out.print("CourseID returned");
            System.out.println(rs.getString(1));
            //2
            System.out.print("email returned");
            System.out.println(rs.getString(2));
            //3
            System.out.print("name returned");
            System.out.println(rs.getString(3));
            //4
            System.out.print("adress returned");
            System.out.println(rs.getString(4));
            //5
            
           }
           rs.close();
           st.close();
           }
       catch (java.sql.SQLException e) {
           System.out.println(e.getMessage());
       }
   }
   
public void selectTeacher(){
       try {
           Statement st = db.createStatement();
           ResultSet rs = st.executeQuery("SELECT * FROM Person WHERE role = 'Teacher'");
	
           while (rs.next()) {
            System.out.print("role returned");
            System.out.println(rs.getString(1));
            //2
            
           }
           rs.close();
           st.close();
           }
       catch (java.sql.SQLException e) {
           System.out.println(e.getMessage());
       }
   }
   
public void selectstudentFromCourse(){
       try {
           Statement st = db.createStatement();
           ResultSet rs = st.executeQuery("SELECT * FROM Course WHERE courseID > 3000");
	
           while (rs.next()) {
            System.out.print("courseID returned");
            System.out.println(rs.getString(1));
            //2
            
           }
           rs.close();
           st.close();
           }
       catch (java.sql.SQLException e) {
           System.out.println(e.getMessage());
       }
   }
   


 public void removePerson(String name){
        try {
            
            Statement st = db.createStatement();
            ResultSet rs = st.executeQuery("Delete FROM Person WHERE name ='"+name+"' and adress = 'Holmegade 22, 5500 Middelfart' and age > 18");
          
            rs.close();
            st.close();
            }
        catch (java.sql.SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    

 public void removeOldPeople(){
        try {
            
            Statement st = db.createStatement();
            ResultSet rs = st.executeQuery("Delete FROM Person WHERE age > 50 and name = 'Heino'");
          
            rs.close();
            st.close();
            }
        catch (java.sql.SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    


 public void relationshipPeco(String email, int courseID){
         try {
             Statement st = db.createStatement();
             DatabaseMetaData dbm = db.getMetaData();
             ResultSet tables = dbm.getTables(null, null,"Peco" , null);
             if(tables.next()){
                 
             }else{
              ResultSet rs = st.executeQuery("CREATE TABLE Peco(
              email varchar(255), 
              courseID int ,
               FOREIGN KEY(email) REFERENCES Person (email),  FOREIGN KEY(courseID) REFERENCES Course (courseID));");
                        
             }
             
             st.close();
             //st2.close();
             }
         catch (java.sql.SQLException e) {
             System.out.println(e.getMessage());
         }try {
             Statement st = db.createStatement();
             ResultSet rs2 = st.executeQuery("INSERT INTO Peco(email, courseID) VALUES('"+email+"', '"+courseID+"');");
             rs2.close();
             st.close();
             //st2.close();
             }
         catch (java.sql.SQLException e) {
             System.out.println(e.getMessage());
         }
     }
 
}
