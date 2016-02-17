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
 * Servlet implementation class CheckEmailAddress
 */
@WebServlet("/CheckEmailAddress")
public class UserSignUpFormCheckEmailAddress extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("INSIDE CHECK EMAIL ADDRESS");
		String emailid=request.getParameter("emailid");
		UserService userService = new UserService();
		User user = new User();
		if(emailid.lastIndexOf("@") != -1)
			user.setUserName(emailid.substring(0, emailid.lastIndexOf("@")));
		else
			user.setUserName(emailid);
			
			try 
			{
				if(userService.isUniqueUserName(user))
				{
				response.getWriter().write("success");
				}
				else
				{
				response.getWriter().write("fail");
				}
			} catch (Exception e) {
				throw new IOException(e);
			}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

}
