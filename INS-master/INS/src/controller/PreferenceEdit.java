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
 * Servlet implementation class PreferenceEdit
 */
@WebServlet("/EditPreference")
public class PreferenceEdit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PreferenceEdit() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		User user = (User)request.getSession().getAttribute("user");
		try {
			
			UserPreferenceService userPreferencesService = new UserPreferenceService();
			UserService userService = new UserService();
			EventService eventservice = new EventService();
			CategoryService categoryService = new CategoryService();
			List<NotificationType> allNotificationTypes = userPreferencesService.getAllNotificationTypes();
			List<Contact> userContacts = userService.getContactsOfUser(user);
			List<UserPreference> userPreferences = userPreferencesService.getUserPreference(user);
			List<UserPreferenceGroup> userPrefgrp = categoryService.getCategoryName(userPreferences);
			List<UserPreferenceGroup> listofunregpref = eventservice.retrieveUnregisteredEvents(user,userPreferences);
			request.setAttribute("userContacts", userContacts);
			request.setAttribute("allNotificationTypes", allNotificationTypes);
			request.setAttribute("preference", listofunregpref);
			request.setAttribute("userPreference", userPrefgrp);
			
			String nextJSP = "/WEB-INF/jsp/UserPreferenceEdit.jsp";
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

		User user = (User)request.getSession().getAttribute("user");
		try {
			
			UserPreferenceService userPreferencesService = new UserPreferenceService();
			UserService userService = new UserService();
			EventService eventservice = new EventService();
			CategoryService categoryService = new CategoryService();
			List<NotificationType> allNotificationTypes = userPreferencesService.getAllNotificationTypes();
			List<Contact> userContacts = userService.getContactsOfUser(user);
			List<UserPreference> userPreferences = userPreferencesService.getUserPreference(user);
			List<UserPreferenceGroup> userPrefgrp = categoryService.getCategoryName(userPreferences);
			List<UserPreferenceGroup> listofunregpref = eventservice.retrieveUnregisteredEvents(user,userPreferences);
			request.setAttribute("userContacts", userContacts);
			request.setAttribute("allNotificationTypes", allNotificationTypes);
			request.setAttribute("preference", listofunregpref);
			request.setAttribute("userPreference", userPrefgrp);
			
			String nextJSP = "/WEB-INF/jsp/UserPreferenceEdit.jsp";
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
			dispatcher.forward(request,response);
		} catch (Exception e) {
			e.printStackTrace();
			throw new IOException("DB Error Occured. Please contact administrator");
		}
		
	}

}
