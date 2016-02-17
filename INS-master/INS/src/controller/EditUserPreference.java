package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Contact;
import beans.NotificationType;
import beans.User;
import beans.UserPreference;
import beans.UserPreferenceGroup;
import domain.CategoryService;
import domain.EventService;
import domain.UserPreferenceService;
import domain.UserService;

/**
 * Servlet implementation class EditUserPreference
 */
@WebServlet("/EditUserPreference")
public class EditUserPreference extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditUserPreference() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		User user = (User)request.getSession().getAttribute("user");
		List<NotificationType> allNotificationTypes=null;
		List<Contact> userContacts=null;
		List<UserPreference> userPreferences=null;
		List<UserPreferenceGroup> userPrefgrp=null;
		List<UserPreferenceGroup> listofunregpref=null;
		try {
			
			UserPreferenceService userPreferencesService = new UserPreferenceService();
			UserService userService = new UserService();
			EventService eventservice = new EventService();
			CategoryService categoryService = new CategoryService();
			allNotificationTypes = userPreferencesService.getAllNotificationTypes();
			userContacts = userService.getContactsOfUser(user);
			userPreferences = userPreferencesService.getUserPreference(user);
			userPrefgrp = categoryService.getCategoryName(userPreferences);
			listofunregpref = eventservice.retrieveUnregisteredEvents(user,userPreferences);
			       
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		for(UserPreferenceGroup g :listofunregpref ){
		System.out.println(g.getCategoryName());
		}
		request.setAttribute("userContacts", userContacts);
		request.setAttribute("allNotificationTypes", allNotificationTypes);
		request.setAttribute("preference", listofunregpref);
		request.setAttribute("userPreferencegrp", userPrefgrp);
		
		String nextJSP = "/WEB-INF/jsp/EditPreference.jsp";
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
		dispatcher.forward(request,response);
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
