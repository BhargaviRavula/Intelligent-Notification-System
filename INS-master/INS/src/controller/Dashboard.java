package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Category;
import beans.Event;
import beans.User;
import beans.UserPreferenceGroup;
import domain.UserPreferenceService;

/**
 * Servlet implementation class Dashboard
 */
@WebServlet(description = "Redirects to the main dashboard.jsp page", urlPatterns = { "/Dashboard" })
public class Dashboard extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Dashboard() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**    
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("INSIDE DASHBOARD");
		UserPreferenceService userPreferenceService = new UserPreferenceService();
		List<Category> userCategory = (List<Category>) request.getAttribute("userPreferences");
		List<Event> regEvents = (List<Event>) request.getAttribute("eventList");
		List<UserPreferenceGroup> userpref = null;
		try {
			userpref = userPreferenceService.getUserPreference(userCategory, regEvents);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("userpref", userpref);
		String nextJSP = "/WEB-INF/jsp/Dashboard.jsp";
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
		dispatcher.forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
