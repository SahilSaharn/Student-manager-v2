
package HandleRequests;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class HandleLoginAction extends HttpServlet {
	
	@Override
	public void doPost(HttpServletRequest req , HttpServletResponse res ) {
		System.out.println(" i am the login handler");
		String email = req.getParameter("userEmail");
		String password = req.getParameter("userPass");
		
		System.out.println(email + " " + password);
		
		//get the user mail real pass if not existing then send error jsp 
		
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
		
		String ourQry = "SELECT email , pass FROM students WHERE email = ?;";
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
		
		ResultSet user_creds = null;
		//this will get us the results...
		try {
			
			st.setString(1, email);
			user_creds = st.executeQuery();
			
		} catch (SQLException err) {
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
		
		//else our user_creds have the data of the user ... now check it...
		try {
			if(user_creds.next()) {
				//means user found here we need to authenticate it...
				String realPass = user_creds.getString("pass");
				//this is our real pass
				System.out.println(realPass);
				
				if(!password.equals(realPass)) {
					//invalid mail and pass
					req.setAttribute("err_msg", "Invalid mail and password! :(");
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
				} else {
					//valid mail and pass and authenticate success...
					System.out.println("authenticate success :)" + " pass " + password + " real " + realPass);
					
					//for warding our request to another servlet
					//setting auth to true 
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
				
			} else {
				//no user found 
				req.setAttribute("err_msg", "No user Found try SignUp! :(");
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
		} catch (SQLException err) {
			// TODO Auto-generated catch block
			err.printStackTrace();
			req.setAttribute("err_msg", "Something Went Wrong :(");
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
		
//		.next returns false if there id no next row 
		
	}
}
