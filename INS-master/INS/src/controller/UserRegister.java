package controller;



import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.User;
import domain.EmailService;
import domain.UserService;
/**
 * Servlet implementation class SignUp
 */
@WebServlet(description = "Registers a new user", urlPatterns = { "/SignUp" })
public class UserRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserRegister() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @throws IOException 
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		System.out.println("Inside SignUp");
		String firstname=request.getParameter("firstName");
		String lastname=request.getParameter("lastName");
		String emailid=request.getParameter("emailID");
		String password=request.getParameter("pass");
		String phone = request.getParameter("phone");   
				//request.getParameter("phone");
		UserService userService = new UserService();
		User user = new User(-1,emailid,password,lastname,firstname,false,12345,phone);
		
		EmailService register = new EmailService();
		int code;
		try 
		{
			code = register.sendRegistrationEmail(emailid);
		} 
		catch (Exception e) 
		{
			throw new IOException("Email could not be sent");
		}
		
		user = new User(-1, emailid,password,lastname,firstname,false,code,phone);
		try {
			user = userService.registerUser(user);
		} catch (Exception e) {
			throw new IOException(e);
		}
		
		if(user == null)
		{
			throw new IOException("User details could not be saved");
		}
		else
		{
			request.getSession().setAttribute("user", user);
			response.getWriter().write("success");
			return;
		}
	}

	/**
	 * @throws IOException 
	 * @throws ServletException 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		service(request, response);
		
	}

}
