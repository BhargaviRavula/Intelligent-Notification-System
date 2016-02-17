package domain;



import java.util.ArrayList;
import java.util.List;

import beans.Category;
import beans.Event;
import beans.Notification;
import beans.NotificationType;
import beans.User;
import beans.UserPreference;
import beans.UserPreferenceGroup;
import dao.EventTypesDB;
import dao.NotificationDB;
import dao.UserPreferenceDB;
import dao.UserPreferenceGroupDB;

public class UserPreferenceService {
	
	
	public void saveUserPreferencesForNewUser(List<Category> categories, List<Notification> notifications, User user) throws Exception
	{
		UserPreferenceGroupDB userPreferenceDB = new UserPreferenceGroupDB();
		List<UserPreference> preferences = new ArrayList<UserPreference>();
		List<Category> userPref = categories;
		for(Category category : userPref){
			for (Notification notification : notifications){
				UserPreference pref = new UserPreference(user.getUserID(),category.getCategory_ID(),notification.getNotificationType().getNotificationTypeID(),notification.getUnitCount());
				preferences.add(pref);
			}
		}
		
		userPreferenceDB.insertUserPreferenceGroup(preferences);
	}
	
	public List<Category> getAllUserPreferencesByUserID(Integer userID) throws Exception
	{
		UserPreferenceGroupDB userPreferenceGroupDB = new UserPreferenceGroupDB();
		List<Category> userPreferences = userPreferenceGroupDB.getUserPreferenceGroup(userID);
		return userPreferences;
	}
	
	public List<UserPreferenceGroup> getUserPreference(List<Category> categories, List<Event> events) throws Exception
	{
		List<UserPreferenceGroup> userPreferences = new ArrayList<UserPreferenceGroup>();
		List<Event> eventlist = null;
		for(Category category : categories){
			UserPreferenceGroup userpref = new UserPreferenceGroup();
			userpref.setCategoryName(category.getCategory_Name());
			eventlist = new ArrayList<Event>();
			for(Event event : events){
				if(event.getCategory().equalsIgnoreCase(category.getCategory_Name())){
					eventlist.add(event);
				}
			}
			userpref.setEvents(eventlist);
			userPreferences.add(userpref);
		}
				
		return userPreferences;
	}
	public List<UserPreferenceGroup> getRecommendedEvents(List<Category> categories,User user) throws Exception
	{
		List<UserPreferenceGroup> userPreferences = new ArrayList<UserPreferenceGroup>();
		EventService eventService = new EventService();
		List<Event> eventlist = null;
		for(Category category : categories){
			UserPreferenceGroup userpref = new UserPreferenceGroup();
			userpref.setCategoryName(category.getCategory_Name());
			UserPreference userselec = new UserPreference();
			userselec.setUserID(user.getUserID());
			userselec.setCategoryID(category.getCategory_ID());
			eventlist = eventService.recommendedEvents(user, category.getCategory_Name());
			userpref.setEvents(eventlist);
			userPreferences.add(userpref);
		}
				
		return userPreferences;
	}
	public void saveEditedUserPreference(UserPreference userPreference) throws Exception
	{
		UserPreferenceDB userPreferenceDB = new UserPreferenceDB();
		//userPreferenceDB.saveEditedUserPreference(userPreference);
	}
	
	public List<Category> getCategories() throws Exception
	{
		EventTypesDB eventTypesDB = new EventTypesDB();
		return eventTypesDB.getCategories();
	}
	
	public List<NotificationType> getAllNotificationTypes() throws Exception
	{
		NotificationDB notificationDB = new NotificationDB();
		return notificationDB.getListOfAllNotificationTypes();
	}
	
	public List<UserPreference> getUserPreference(User user) throws Exception{
		UserPreferenceDB userpreferencedb = new UserPreferenceDB();
		return userpreferencedb.getUserPreference(user);
	}

}