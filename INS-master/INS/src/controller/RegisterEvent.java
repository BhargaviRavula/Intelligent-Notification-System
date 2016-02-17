package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.User;
import domain.CategoryService;
import domain.EventService;

/**
 * Servlet implementation class RegisterEvent
 */
@WebServlet("/RegisterEvent")
public class RegisterEvent extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterEvent() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User)request.getSession().getAttribute("user");
		String eventid = (String) request.getParameter("eventID");
		int eventID = Integer.parseInt(eventid);
		String categoryName = (String)request.getParameter("categoryName");
		CategoryService categoryserv = new CategoryService();
		int categoryID = 0;
		EventService eventservice = new EventService();
		try {
			categoryID = categoryserv.getCategoryID(categoryName);
			eventservice.registerEvent(eventID, user,categoryID);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String url = (String) request.getParameter("url");
		response.sendRedirect(response.encodeRedirectURL(url));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
