package HandleRequests;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class HandleRegisterAction extends HttpServlet{
	public void doPost(HttpServletRequest req ,HttpServletResponse res ) {
		System.out.println("i am the register servlet");
		
		String name = req.getParameter("userName");
		String email = req.getParameter("userEmail");
		String password = req.getParameter("userPass");
		String confirmPassword = req.getParameter("userConPass");
		
		//System.out.println(name + " " + email + " " + password + " " + confirmPassword );
		//System.out.println("i am values");
		
		if( !password.equals(confirmPassword) ) {
			System.out.println("pass didnt matched");
			req.setAttribute("err_msg", "Password and ConfirmPassword Must be SAME!");
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
		//else passwords matched ...
		// add user to db as email as primary key if user exists catch the error and send appropiate response...
		
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
		//else connection is success now execute query...
		String ourQry = "INSERT INTO students (name, email, pass) VALUES ( ? , ? , ? );";
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
		//else we got the statement and we can execute it...
		
		try {
			st.setString(1, name);
			st.setString(2, email);
			st.setString(3, password);
			st.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			
			///send error jsp if anything happens
			//make sure to check for duplicate entry with one email and send appropiate message here ...
			
			if(e.getErrorCode() == 1062) {
				System.out.println("user already exists");
				//send appropiate response to the user...
				req.setAttribute("err_msg", "Cannot SignUp user already exsists Try sign In");
				req.setAttribute("show_signin", true);
			}
			
			RequestDispatcher request = req.getRequestDispatcher("/pages/eroorPage.jsp");
			try {
				request.forward(req, res);
			} catch (ServletException err) {
				// TODO Auto-generated catch block
				err.printStackTrace();
			} catch (IOException err) {
				// TODO Auto-generated catch block
				err.printStackTrace();
			}
			return;
		}
		
		//till here signup is success then we need to redirect to the view students page...
		req.setAttribute("auth", true);
		RequestDispatcher request = req.getRequestDispatcher("/home");
		
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
