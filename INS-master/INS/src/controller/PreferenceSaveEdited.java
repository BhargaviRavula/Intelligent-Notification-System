package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Contact;
import beans.EventType;
import beans.Notification;
import beans.NotificationType;
import beans.UserPreference;
import domain.UserPreferenceService;

/**
 * Servlet implementation class PreferenceSaveEdited
 */
@WebServlet("/SaveEditedPreference")
public class PreferenceSaveEdited extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PreferenceSaveEdited() {
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
		UserPreferenceService userPreferenceService = new UserPreferenceService();
		UserPreference userPreference = (UserPreference)request.getSession().getAttribute("userPreference");
		request.getSession().removeAttribute("userPreference");
		
		//Get the list of EventTypeIDs
		String[] tempEvenType = request.getParameterValues("EventTypeCheckBox");
		List<EventType> eventTypes = new ArrayList<>();
		for(String temp:tempEvenType)
		{
			eventTypes.add(new EventType(Integer.parseInt(temp), null, null, null,null));
		}
		
		//Get the list of new Notifications
		Notification tempNotification = new Notification();
		List<Notification> notifications = new ArrayList<>();
		int count=0;
		do
		{
			int unitCount = Integer.parseInt(request.getParameter("notificationUnitCount"+count));
			int notificationContactID = Integer.parseInt(request.getParameter("notificationContact"+count));
			int notificationTypeID = Integer.parseInt(request.getParameter("notificationType"+count));
			tempNotification = new Notification();
			tempNotification.setContact(new Contact(notificationContactID, "", null, null));
			tempNotification.setUnitCount(unitCount);
			tempNotification.setNotificationType(new NotificationType(notificationTypeID, ""));
			notifications.add(tempNotification);
			count++;
		}
		while(request.getParameter("notificationUnitCount"+count)!=null);
		
		//Get the new userPreferenceName
		String userPreferenceName = (String)request.getParameter("UserPreferenceName");
		
		//Set the new details of userPreference into the object
		/*userPreference.setNotifications(notifications);
		userPreference.setEventTypes(eventTypes);
		userPreference.setPreferenceName(userPreferenceName);*/
		
		try {
			userPreferenceService.saveEditedUserPreference(userPreference);
		} catch (Exception e) {
			throw new IOException(e);
		}
		response.sendRedirect("Dashboard");
	}

}
