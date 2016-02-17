package domain;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import beans.Category;
import beans.Event;
import beans.User;
import beans.UserPreference;
import beans.UserPreferenceGroup;
import dao.EventDB;

public class EventService {

	public void readEvents()
	{
		File file = new File("C:\\Users\\Saquib\\git\\Intelligent Notification Systems\\"
				+ "Notification System\\src\\service\\categorized.txt");
		Scanner scanner;
		Event event;
		List<Event> events = new ArrayList<>();
		int temp;
		Date date = new Date(System.currentTimeMillis()); 
		List<String> eventsList = new ArrayList<>();
		String[] eventString;
		
		try {
			scanner = new Scanner(file);
			String eventString1;
            while (scanner.hasNextLine()) 
	        {
	        	eventString1 = scanner.nextLine();
	        	while(scanner.hasNextLine())
	        	{
	        		if(!eventString1.contains("\t"))
	        			eventString1 += scanner.nextLine();
	        		else break;
	        	}
	        	
	        	eventsList.add(eventString1);
	        	
	        	if(eventString1.split("\t").length == 6 || eventString1.split("\t").length == 5)
	        	{
	        		
	        		event = new Event();
	        		eventString = eventString1.split("\t");
	            	event.setEventTitle(eventString[0]);
	            	event.setEventURL(eventString[1]);
	            	temp = eventString[1].indexOf('=');
	            	event.setEventID(Integer.parseInt(eventString[1].substring(temp+1)));
	            	event.setCategory(eventString[2]);
	                event.setTimeDuration(eventString[3]);
	                event.setStartTime(eventString[4]);
	            	event.setEventDate(date);
	        		if(eventString1.split("\t").length == 6)
	        		{
	        			event.setEventDescription(eventString[5]);
	        		}
	        		events.add(event);
	        	}
	        	
		    }
            
            
            EventDB eventDatabase = new EventDB();
            
            for(Event event1: events)
            {
            	
            	if(!(event1.getEventTitle().length()>50))
            	{
            		eventDatabase.addEvent(event1);
            	}
            }
            
           
		} 
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	public static void main(String[] args)
	{
		EventService file = new EventService();
		file.readEvents();
	}
	
	public List<UserPreferenceGroup> retrieveUnregisteredEvents(User user , List<UserPreference> userPreferences) throws Exception{
		CategoryService categoryService = new CategoryService();
		List<Category> unregisteredCategory = categoryService.getUnregisteredCategory(userPreferences);
		EventDB eventDB = new EventDB();
		List<UserPreferenceGroup> eventList = eventDB.getEventList(unregisteredCategory);
		return eventList;
	}
	public List<Event> retrieveUserRegisteredEvents(User user){
		EventDB eventDB = new EventDB();
		List<Event> eventList = eventDB.getUserRegisteredEvents(user);
		return eventList;
	}
	
	public List<Event> retrieveAllUserRegisteredEvents(User user){
		EventDB eventDB = new EventDB();
		List<Event> eventList = eventDB.getAllUserRegisteredEvents(user);
		return eventList;
	}
	public List<Event> retrieveAllUserRegisteredEvents(User user,String CategoryName){
		EventDB eventDB = new EventDB();
		List<Event> eventList = eventDB.getAllUserRegisteredEvents(user,CategoryName);
		return eventList;
	}
	
	public List<Event> retrieveEventsforCategory(String CategoryName){
		EventDB eventDB = new EventDB();
		List<Event> eventList = eventDB.getEventsforCategory(CategoryName);
		return eventList;
	}
	
	public List<Event> recommendedEvents(User user,String CategoryName) throws Exception{
		
		List<Event> regEvent = this.retrieveAllUserRegisteredEvents(user,CategoryName);
		List<Event> events =this.retrieveEventsforCategory(CategoryName);
		if(regEvent==null || regEvent.size()==0){
			return events;
		}
		List<Event> recomEvent = new ArrayList<Event>();
		for(Event evnt : events){
			for(Event reg : regEvent){
					if(reg.getEventID()!=evnt.getEventID())
				{
					recomEvent.add(evnt);
				}
			}
		}
		return recomEvent;
	}
	public void registerEvent(int eventID,User user,int categoryID) throws Exception{
		EventDB eventDB = new EventDB();
		eventDB.addUserEvent(eventID, user,categoryID);
	}
	
	public List<Event> retrieveEvents(){
		EventDB eventDB = new EventDB();
		List<Event> eventList = eventDB.getEventsList();
		return eventList;
	}
	
	public List<UserPreferenceGroup> retieveEventsForCategory(List<Category> categories){
		EventDB eventDB = new EventDB();
		List<UserPreferenceGroup> pref = eventDB.getEventList(categories);
		return pref;
	}
	
	public List<Event> retrieveEventsTobeNotified(){
	EventDB eventDB = new EventDB();
	List<Event> notificationList = eventDB.getTodaysEvents();
		return notificationList;
	}
}
