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
 * Servlet implementation class VerifyUser
 */
@WebServlet(description = "Checks the verification code of the user", urlPatterns = { "/VerifyUser" })
public class UserVerify extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserVerify() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.print("Inside VerifyUser");
		String code=request.getParameter("verificationCode");
		User user = (User)request.getSession().getAttribute("user");
		if(user==null)
		{
			response.getWriter().write("login");
			return;
		}
		int codeNumber = Integer.parseInt(code);
		user.setVerificationCode(codeNumber);
		UserService userService = new UserService();
		
		//Check the verification code and set the user as verified if true
		try {
			if(userService.checkVerificationCode(user))
			{	
				if(userService.markUserAsVerfied(user))
				{
					user.setVerified(true);
					request.getSession().setAttribute("user", user);
					response.getWriter().write("success");
				}
			}
			else
			{
				response.getWriter().write("fail");
			}
		} catch (Exception e) {
			throw new IOException(e);
		}
		
	}

}
