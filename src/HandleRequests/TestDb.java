package HandleRequests;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;


public class TestDb {

	public static void main(String[] args) {
		Connection conn = ConnectToDb.getConnection();
		PreparedStatement x;
//		try {
//			x = conn.prepareStatement("select * from students where email = ?");
//			x.setString(1, "sahil@gmail.com");
//			x.executeQuery();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
		System.out.println("conn success");
		
		try {
			
			PreparedStatement st = conn.prepareStatement("Select * from students where email = ? ;");
			st.setString(1, "sahil@gmail.com");
			
			ResultSet res = st.executeQuery();
			System.out.println(res.getRow());
			//by this we can get the very first user form the db 
			if(res.next()) {
				String name = res.getString("name");
				System.out.println(name);
			} else {
				System.out.println("no user found ");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
