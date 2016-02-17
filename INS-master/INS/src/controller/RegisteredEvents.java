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
import domain.EventService;
import domain.UserPreferenceService;

/**
 * Servlet implementation class RegisteredEvents
 */
@WebServlet("/RegisteredEvents")
public class RegisteredEvents extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisteredEvents() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserPreferenceService userPreferenceService = new UserPreferenceService();
		UserPreferenceService userPreference = new UserPreferenceService();
		User user = (User)request.getSession().getAttribute("user");
		List<Category> userCategory = null;
		List<Event> eventList = null;
		List<UserPreferenceGroup> userpref = null;
		EventService eventservice = new EventService();
		try {
			userCategory = userPreference.getAllUserPreferencesByUserID(user.getUserID());
			eventList = eventservice.retrieveAllUserRegisteredEvents(user);
			userpref = userPreferenceService.getUserPreference(userCategory, eventList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("userpref", userpref);
		request.setAttribute("user", user);
		String nextJSP = "/WEB-INF/jsp/RegisteredEvent.jsp";
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
		dispatcher.forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
