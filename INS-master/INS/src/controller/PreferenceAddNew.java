package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Category;
import beans.Contact;
import beans.Notification;
import beans.NotificationType;
import beans.User;
import beans.UserPreference;
import domain.UserPreferenceService;

/**
 * Servlet implementation class SavePreferences
 */
@WebServlet("/SaveInitialSettings")
public class PreferenceAddNew extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PreferenceAddNew() {
        super();
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
		UserPreferenceService userPreferencesService = new UserPreferenceService();
		try {
			User user = (User)request.getSession().getAttribute("user");
			int count = 0;
			Notification tempNotification;
			List<Notification> notifications = new ArrayList<>();
			do
			{
				int unitCount = Integer.parseInt(request.getParameter("notificationUnitCount"+count));
				int notificationContactID = Integer.parseInt(request.getParameter("notificationContact"+count));
				int notificationTypeID = Integer.parseInt(request.getParameter("notificationType"+count));
				tempNotification = new Notification();
				tempNotification.setContact(new Contact(notificationContactID, "", null, user));
				tempNotification.setUnitCount(unitCount);
				tempNotification.setNotificationType(new NotificationType(notificationTypeID, ""));
				notifications.add(tempNotification);
				count++;
			}
			while(request.getParameter("notificationUnitCount"+count)!=null);
			
			
			List<Category> category = new ArrayList<>();
			String[] tempEvenType = request.getParameterValues("EventTypeCheckBox");
			
			for(String temp:tempEvenType)
			{	
				Category pref = new Category(Integer.parseInt(temp), null);
				category.add(pref);
			}
			userPreferencesService.saveUserPreferencesForNewUser(category,notifications, user);
			response.sendRedirect("Dashboard");
		} catch (Exception e) {
			throw new IOException(e);
		}
	}

}
