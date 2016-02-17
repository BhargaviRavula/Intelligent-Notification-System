package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import beans.Category;
import beans.Event;
import beans.User;
import beans.UserPreferenceGroup;


public class EventDB {
	
	
	public void addEvent(Event event)
	{
		String[] UserIDColumnName = new String[1]; 
		UserIDColumnName[0] = "UserID";
		
		
		try (Connection connection  = DBConnectionFactory.getConnectionObject())
		{
			PreparedStatement ps;
			ps = connection.prepareStatement("INSERT INTO EventUTD VALUES (?,?,?,?,?,?,?)");
			ps.setInt(1, event.getEventID());
		    ps.setString(2, event.getEventTitle());
		    ps.setString(3, event.getEventURL());
		    ps.setString(4, event.getCategory());
		    ps.setString(5, event.getTimeDuration());
		    ps.setString(6, event.getStartTime());
		    ps.setString(7, event.getEventDescription());
		    
		    ps.executeUpdate();
		   
		}
		catch (SQLException e) {
		    e.printStackTrace();
		} 
		catch (Exception e1) {
			
			e1.printStackTrace();
		}
		

	}
	public List<UserPreferenceGroup> getEventList(List<Category> categories){
		
		Connection connection;
		PreparedStatement ps;
		ResultSet rs;
		
		List<UserPreferenceGroup> preference = new ArrayList<UserPreferenceGroup>();
		try {
			connection  = DBConnectionFactory.getConnectionObject();
			
			for(Category category : categories){
				UserPreferenceGroup pref = new UserPreferenceGroup();
				List<Event> events = new ArrayList<Event>();
			ps = connection.prepareStatement("Select EventID,EventTitle,EventURL,Category,TimeDuration,StartTime,EventDescription from Event where Category=?");
			ps.setString(1, category.getCategory_Name());
			rs = ps.executeQuery();
			while(rs.next()){
				Event event = new Event(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),Calendar.getInstance().getTime());
				events.add(event);
			}
			pref.setCategoryID(category.getCategory_ID());
			pref.setCategoryName(category.getCategory_Name());
			pref.setEvents(events);
			preference.add(pref);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
				
		
		return preference;
	}
	public List<Event> getUserRegisteredEvents(User user){

		
		Connection connection;
		PreparedStatement ps;
		ResultSet rs;
		ResultSet eventset;
		List<Event> events = new ArrayList<Event>();
		try {
			connection  = DBConnectionFactory.getConnectionObject();
			ps = connection.prepareStatement("Select EventID from UserRegisteredEvents where UserID=?");
			ps.setInt(1, user.getUserID());
			rs = ps.executeQuery();
			Date sysdate = new Date();
			while(rs.next()){
			ps = connection.prepareStatement("Select EventID,EventTitle,EventURL,Category,TimeDuration,StartTime,EventDescription,EventDate from Event where EventID=?");
			ps.setInt(1, rs.getInt(1));
			eventset = ps.executeQuery();
			while(eventset.next()){
				Date eventdate = eventset.getDate(8);
				if(eventdate.getDate()==sysdate.getDate() && eventdate.getMonth()==(sysdate.getMonth())&& eventdate.getYear()==sysdate.getYear()){
				Event event = new Event(eventset.getInt(1),eventset.getString(2),eventset.getString(3),eventset.getString(4),eventset.getString(5),eventset.getString(6),eventset.getString(7),eventset.getDate(8));
				events.add(event);
				}
			}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
				
		
		return events;
	
	}
	
public List<Event> getAllUserRegisteredEvents(User user){

		
		Connection connection;
		PreparedStatement ps;
		ResultSet rs;
		ResultSet eventset;
		List<Event> events = new ArrayList<Event>();
		try {
			connection  = DBConnectionFactory.getConnectionObject();
			ps = connection.prepareStatement("Select EventID from UserRegisteredEvents where UserID=?");
			ps.setInt(1, user.getUserID());
			rs = ps.executeQuery();
			Date sysdate = new Date();
			while(rs.next()){
			ps = connection.prepareStatement("Select EventID,EventTitle,EventURL,Category,TimeDuration,StartTime,EventDescription,EventDate from Event where EventID=?");
			ps.setInt(1, rs.getInt(1));
			eventset = ps.executeQuery();
			while(eventset.next()){
				Date eventdate = eventset.getDate(8);
				if((eventdate.getMonth()>=(sysdate.getMonth())&& eventdate.getYear()>=sysdate.getYear())||(eventdate.getDate()>=sysdate.getDate() && eventdate.getMonth()==(sysdate.getMonth())&& eventdate.getYear()==sysdate.getYear())){
				Event event = new Event(eventset.getInt(1),eventset.getString(2),eventset.getString(3),eventset.getString(4),eventset.getString(5),eventset.getString(6),eventset.getString(7),eventset.getDate(8));
				events.add(event);
				}
			}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
				
		
		return events;
	
	}
public List<Event> getAllUserRegisteredEvents(User user, String CategoryName){

	
	Connection connection;
	PreparedStatement ps;
	ResultSet rs;
	ResultSet eventset;
	List<Event> events = new ArrayList<Event>();
	try {
		connection  = DBConnectionFactory.getConnectionObject();
		ps = connection.prepareStatement("Select EventID from UserRegisteredEvents where UserID=?");
		ps.setInt(1, user.getUserID());
		rs = ps.executeQuery();
		Date sysdate = new Date();
		while(rs.next()){
		ps = connection.prepareStatement("Select EventID,EventTitle,EventURL,Category,TimeDuration,StartTime,EventDescription,EventDate from Event where EventID=?and Category=? ");
		ps.setInt(1, rs.getInt(1));
		ps.setString(2, CategoryName);
		eventset = ps.executeQuery();
		while(eventset.next()){
			Date eventdate = eventset.getDate(8);
			if((eventdate.getMonth()>=(sysdate.getMonth())&& eventdate.getYear()>=sysdate.getYear())||(eventdate.getDate()>=sysdate.getDate() && eventdate.getMonth()==(sysdate.getMonth())&& eventdate.getYear()==sysdate.getYear())){
			Event event = new Event(eventset.getInt(1),eventset.getString(2),eventset.getString(3),eventset.getString(4),eventset.getString(5),eventset.getString(6),eventset.getString(7),eventset.getDate(8));
			events.add(event);
			}
		}
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
			
	
	return events;

}
public List<Event> getEventsList(){

	Connection connection;
	PreparedStatement ps;
	ResultSet eventset;
	List<Event> events = new ArrayList<Event>();
	try{
	connection  = DBConnectionFactory.getConnectionObject();
	ps = connection.prepareStatement("Select EventID,EventTitle,EventURL,Category,TimeDuration,StartTime,EventDescription,EventDate from Event ");
	eventset = ps.executeQuery();
	while(eventset.next()){
		Event event = new Event(eventset.getInt(1),eventset.getString(2),eventset.getString(3),eventset.getString(4),eventset.getString(5),eventset.getString(6),eventset.getString(7),eventset.getDate(8));
		events.add(event);
	}
	}
	catch(Exception e){
		e.printStackTrace();
	}
	return events;
}
	
	
	public List<Event> getEventsforCategory(String categoryName) {
		
		Connection connection;
		PreparedStatement ps;
		ResultSet eventset;
		List<Event> events = new ArrayList<Event>();
		
		try {
			connection  = DBConnectionFactory.getConnectionObject();
			ps = connection.prepareStatement("Select EventID,EventTitle,EventURL,Category,TimeDuration,StartTime,EventDescription,EventDate from Event where Category=?");
			ps.setString(1, categoryName);
			eventset = ps.executeQuery();
			while(eventset.next()){
				Event event = new Event(eventset.getInt(1),eventset.getString(2),eventset.getString(3),eventset.getString(4),eventset.getString(5),eventset.getString(6),eventset.getString(7),eventset.getDate(8));
				events.add(event);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return events;
		
	}
	public void addUserEvent(int eventID, User user,int categoryID) throws Exception{
		Connection conn;
		PreparedStatement ps;
		int affectedRows;
		String sql;
		
			conn  = DBConnectionFactory.getConnectionObject();
			sql = "INSERT INTO [UserRegisteredEvents] ([UserID],[EventID],[CategoryID]) values (?,?,?);";
		    ps = conn.prepareStatement(sql);
		    ps.setInt(1, user.getUserID());
		    ps.setInt(2, eventID);
		    ps.setInt(3, categoryID);
		    affectedRows = ps.executeUpdate();
		    if (affectedRows == 0) {
	            throw new Exception("Creating userPreferenceGroups failed, no rows affected.");
	        }
		
		
	}
public List<Event> getTodaysEvents() {
		
		Connection connection;
		PreparedStatement ps;
		ResultSet eventset;
		List<Event> events = new ArrayList<Event>();
		
		try {
			connection  = DBConnectionFactory.getConnectionObject();
			ps = connection.prepareStatement("SELECT [EventID],[EventTitle],[EventURL],[Category],[TimeDuration],[StartTime],[EventDescription] FROM [INSINSTANCE].[dbo].[Event] where DATEDIFF(DAY,EventDate, SYSDATETIME()) =0");
			eventset = ps.executeQuery();
			while(eventset.next()){
				Event event = new Event(eventset.getInt(1),eventset.getString(2),eventset.getString(3),eventset.getString(4),eventset.getString(5),eventset.getString(6),eventset.getString(7),null);
				events.add(event);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return events;
		
	}
}
