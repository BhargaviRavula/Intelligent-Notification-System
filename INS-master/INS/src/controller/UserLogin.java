package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.User;
import domain.UserService;

/**
 * Servlet implementation class Login
 */
@WebServlet(description = "Checks the login credentials of the user", urlPatterns = { "/Login" })
public class UserLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.print("Inside Login");
		UserService userService = new UserService();
		User student = new User();
		String emailID = (String)request.getParameter("username");
		if(emailID.lastIndexOf("@")!=-1)
		{
			student.setUserName(emailID.substring(0,emailID.lastIndexOf("@")));
		}
		
		student.setPassword(request.getParameter("password"));
		
		try {
			student = userService.checkLoginDetails(student);
			if(student != null)
			{
				request.getSession().setAttribute("user", student);
				response.getWriter().write("success");
				return;
			}
			else
			{
				request.getSession().removeAttribute("user");
				response.getWriter().write("fail");
				return;
			}
		} 
		catch (Exception e) {
			throw new IOException("DBError");
		}
	}

}
