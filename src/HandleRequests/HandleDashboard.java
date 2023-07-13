//to this request will be forwarded and it will check the attribute of the request setted by our two authenticate servelets and will return the servlet else it will return nothing means no access!

package HandleRequests;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class HandleDashboard extends HttpServlet{
	
	@Override
	public void service( HttpServletRequest req , HttpServletResponse res ) {
		System.out.println("i am logged in servlet :)");
		
		boolean auth = (boolean) req.getAttribute("auth");
		if( auth ) {
			System.out.println("auth user");
			//here means our user is authenticated 
			//noe fetch the data from db...
			
			Connection conn = ConnectToDb.getConnection();
			if(conn == null) {
				//then also return the error page ... with appropiate message 
				req.setAttribute("err_msg", "Something Went Wrong :( Try Sometime later");
				RequestDispatcher request = req.getRequestDispatcher("/pages/eroorPage.jsp");
				try {
					request.forward(req, res);
				} catch (ServletException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return;
			}
			
			String ourQry = "SELECT name , email FROM students;";
			PreparedStatement st = ConnectToDb.getStatement(ourQry);
			
			if(st == null) {
				//add appropiate message and display the error...
				req.setAttribute("err_msg", "Something Went Wrong :( Try Sometime later");
				RequestDispatcher request = req.getRequestDispatcher("/pages/eroorPage.jsp");
				try {
					request.forward(req, res);
				} catch (ServletException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return;
			}
			
			ResultSet studentData = null;
			try {
				studentData = st.executeQuery();
			} catch(SQLException err) {
				err.printStackTrace();
				req.setAttribute("err_msg", "Something Went Wrong :( Try Sometime later");
				RequestDispatcher request = req.getRequestDispatcher("/pages/eroorPage.jsp");
				try {
					request.forward(req, res);
				} catch (ServletException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return;
			}
			
			//now the student data will have all the student records...
//			ArrayList<Student> dataSet = new ArrayList<Student>();
//			try {
//				while(studentData.next()) {
//					String name = studentData.getString("name");
//					String email = studentData.getString("email");
//					
//					Student student = new Student();
//					student.student_email = email;
//					student.student_name = name;
//					
//					dataSet.add(student);
//					
//					System.out.println(name +  " " + email);
//				}
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			//till here we will have the data  in studentsData...
			
			System.out.println("here");
			req.setAttribute("studentData", studentData);
			RequestDispatcher frwd = req.getRequestDispatcher("/pages/home.jsp");
			
			try {
				frwd.forward(req, res);
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
			
		} else {
			//user not authenticated ...
			req.setAttribute("err_msg", "Un-Auth user dont be to inspired by theme :D ");
			RequestDispatcher request = req.getRequestDispatcher("/pages/eroorPage.jsp");
			try {
				request.forward(req, res);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return;
		}
		 
	}
}
