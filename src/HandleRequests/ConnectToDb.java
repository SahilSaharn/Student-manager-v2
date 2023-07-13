package HandleRequests;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

//in this class we have to made a connection with the db

public class ConnectToDb {
	static private Connection conn = null;
	
	//db - credentials we can use env. variables  to setup them
	static private String db_user_name = "root";
	static private String db_pass = "";
	static private int db_port = 3306;
	static private String db_name = "studentInfo";
	
	static private String db_url= "jdbc:mysql://localhost:"+ db_port + "/" + db_name;
	
	//this willgive us the db connection -> 
	static public Connection getConnection() {
		
		//to avoid creating multiple connections...
		if(conn != null)
			return conn;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println("Connot Load driver :( ");
			
		}
		
		// else create a new connection and return it...
		try {
			
			conn = DriverManager.getConnection(db_url , db_user_name , db_pass);
			System.out.println("Connection Success!");
		} catch( SQLException err) {
			System.err.println("Connot Create Connection with db : returning a null connetcion! ");
			err.printStackTrace();
		}
		
		return conn;
	}
	
	static public void closeConnection() {
		if(conn == null)
			return;
		
		try {
			conn.close();
			System.out.println("Connection Closed Success");
		} catch(SQLException err) {
			System.err.println("Failed to close the connection");
			err.printStackTrace();
		}
	}
	
	//this will give us the statementby pasing our query orprepapred statement
	static public PreparedStatement getStatement(String your_query) {
		PreparedStatement st = null;
		if(conn == null){
			System.out.println("NO connection establish try calling {.getConnection()} to create a conn first and then get a statement");
			System.out.println("returning a null statement :(");
			return st;
		}
		
		try {
			st = conn.prepareStatement(your_query);
			System.out.println("statement creation success");
		} catch (SQLException err) {
			System.err.println("Cannot Get the Statement :( returning null statement");	
		}
		
		return st;
	}
	
	static public void closeStatement(PreparedStatement st) {
		if(st == null) return;
		
		try {
			st.close();
		} catch(SQLException err) {
			System.err.println("Cannot close statement :( ");
			err.printStackTrace();
		}
		return;
	}
	
}

