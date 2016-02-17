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
import beans.Contact;
import beans.NotificationType;
import beans.User;
import beans.UserPreferenceGroup;
import domain.EventService;
import domain.UserPreferenceService;
import domain.UserService;

/**
 * Servlet implementation class EditPreferences
 */
@WebServlet("/BlankPreference")
public class PreferenceBlank extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PreferenceBlank() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		User user = (User)request.getSession().getAttribute("user");
		if(user == null)
		{
			response.sendRedirect("Login.html");
		}
		
		try {
			UserPreferenceService userPreferencesService = new UserPreferenceService();
			UserService userService = new UserService();
			EventService eventservice = new EventService();
			List<Category> category = userPreferencesService.getCategories();
			List<NotificationType> allNotificationTypes = userPreferencesService.getAllNotificationTypes();
			List<Contact> userContacts = userService.getContactsOfUser(user);
			List<UserPreferenceGroup> preference = eventservice.retieveEventsForCategory(category);
			request.setAttribute("userContacts", userContacts);
			request.setAttribute("category", category);
			request.setAttribute("user", user);
			request.setAttribute("allNotificationTypes", allNotificationTypes);
			request.setAttribute("preference", preference);
			String nextJSP = "/WEB-INF/jsp/UserPreferenceBlank.jsp";
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
			dispatcher.forward(request,response);
		} catch (Exception e) {
			e.printStackTrace();
			throw new IOException("DB Error Occured. Please contact administrator");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
