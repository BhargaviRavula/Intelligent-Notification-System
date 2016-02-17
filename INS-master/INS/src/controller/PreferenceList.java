package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Event;
import beans.EventType;
import beans.User;
import beans.UserPreference;
import beans.UserPreferenceGroup;
import domain.UserPreferenceService;

/**
 * Servlet implementation class PreferenceList
 */
@WebServlet( urlPatterns = {"/ViewPreferences"})
public class PreferenceList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PreferenceList() {
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
		
		System.out.println("Inside PreferenceList");
		User user = (User)request.getSession().getAttribute("user");
		UserPreferenceService userPreferencesService = new UserPreferenceService();
		UserPreferenceGroup userPreferenceGroup;
		try {
			userPreferenceGroup = null;
					//userPreferencesService.getAllUserPreferencesByUserID(user.getUserID());
		} catch (Exception e) {
			throw new IOException(e);
		}
		ArrayList<Event> eventlist = new ArrayList<Event>();
		/*for(UserPreference up : userPreferenceGroup.getUserPreference() ){
			for(EventType ev : up.getEventTypes())
				eventlist.addAll(ev.getEvents());
		}*/
		HttpSession session = request.getSession();
		session.setAttribute("eventList", eventlist);
		request.setAttribute("userPreferenceGroup", userPreferenceGroup);
		String nextJSP = "/WEB-INF/jsp/UserPreferenceList.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(nextJSP);
		rd.forward(request, response);
		
	}

}
