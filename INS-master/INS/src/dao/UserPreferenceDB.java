package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import beans.EventType;
import beans.User;
import beans.UserPreference;
import beans.UserPreferenceGroup;

public class UserPreferenceDB {

	public List<UserPreferenceGroup> getUserPreferenceFromUserPreferenceID(
			int userPreferenceID) throws Exception {
		String sql = "Select * from ";
		
		/*
		String sql = "Select  et.EventTypeID, et.EventTypeName, et.EventTypeParentID, et.Tag, up.UserPreferenceName "
				+ "from UserPreference up INNER JOIN UserPreferenceEventTypes etl ON up.UserPreferenceID=etl.UserPreferenceID  "
				+ "INNER JOIN EventType et ON et.EventTypeID = etl.EventTypeID "
				+ "where up.UserPreferenceID = ?;";
		List<EventType> eventTypes = new ArrayList<>();
		UserPreference userPreference = new UserPreference();
		userPreference.setEventTypes(eventTypes);
		EventType eventType;
		Connection conn = DBConnectionFactory.getConnectionObject();

		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, userPreferenceID);
		ResultSet rs = ps.executeQuery();

		if (rs.next()) {
			eventType = new EventType();
			eventType.setEventTypeID(rs.getInt(1));
			eventType.setEventTypeName(rs.getString(2));
			eventType.setEventTypeParent(null);
			eventType.setTag(rs.getString(4));
			eventTypes.add(eventType);
			userPreference.setUserPreferenceID(userPreferenceID);
			userPreference.setPreferenceName(rs.getString(5));
		}

		while (rs.next()) {
			eventType = new EventType();
			eventType.setEventTypeID(rs.getInt(1));
			eventType.setEventTypeName(rs.getString(2));
			eventType.setEventTypeParent(null);
			eventType.setTag(rs.getString(4));
			eventTypes.add(eventType);
		}

		rs.close();
		ps.close();
		DBConnectionFactory.destroyConnection(conn);
		
		NotificationDB notificationDB = new NotificationDB();
		userPreference.setNotifications(notificationDB.getNotification(userPreferenceID));
		if (eventTypes.size() == 0)
			return null;
		return userPreference;

	*/
		
		return null;}

	public UserPreference insertUserPreference(UserPreference userPreference,
			Connection conn) throws Exception {/*
		String sql = "INSERT INTO UserPreference (UserPreferenceName) values (?);";
		PreparedStatement ps = conn.prepareStatement(sql,
				Statement.RETURN_GENERATED_KEYS);
		ps.setString(1, userPreference.getPreferenceName());
		int affectedRows = ps.executeUpdate();
		if (affectedRows == 0) {
			DBConnectionFactory.destroyConnection(conn);
			throw new Exception(
					"Creating UserPreference failed, no rows affected.");
		}

		try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
			if (generatedKeys.next()) 
			{
				userPreference.setUserPreferenceID((int) generatedKeys
						.getLong(1));
				return userPreference;
			} 
			else 
			{	
				conn.rollback();
				DBConnectionFactory.destroyConnection(conn);
				throw new Exception(
						"Creating UserPreference failed, no ID obtained.");
			}
		}
	*/
		return null;}
	
	public void insertEventTypeList(List<EventType> eventTypes, Integer userPreferenceID,
			Connection conn) throws Exception {
		String sql;
		PreparedStatement ps;
		int rowsAffected;

		for (EventType eventType : eventTypes) {
			sql = "INSERT INTO UserPreferenceEventTypes (UserPreferenceID,EventTypeID) values (?,?);";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, userPreferenceID);
			ps.setInt(2, eventType.getEventTypeID());

			rowsAffected = ps.executeUpdate();
			if (rowsAffected == 0) {
				conn.rollback();
				DBConnectionFactory.destroyConnection(conn);
				throw new Exception(
						"Creating EventTypeList failed, no rows affected.");
			}
		}
	}
	
	public boolean saveEditedUserPreference(UserPreference userPreference) throws Exception
	{
		/*Connection conn = DBConnectionFactory.getConnectionObject();
		conn.setAutoCommit(false);
		this.saveUserPreference(userPreference, conn);
		NotificationDB notificationDB = new NotificationDB();
		notificationDB.deleteNotificationsByPreferenceID(userPreference.getUserPreferenceID(), conn);
		for(Notification notification : userPreference.getNotifications())
		{
				notification.setUserPreference(userPreference);
				notificationDB.insertNotification(notification, conn);		
		}
		
		this.saveEventTypeList(userPreference.getEventTypes(), userPreference.getUserPreferenceID(), conn);
		conn.commit();
		DBConnectionFactory.destroyConnection(conn);*/
		return true;
	}
	
	public boolean saveUserPreference(UserPreference userPreference,
			Connection conn) throws Exception {/*
		String sql = "UPDATE UserPreference SET UserPreferenceName=? WHERE UserPreferenceID =?;";
		PreparedStatement ps = conn.prepareStatement(sql,
				Statement.RETURN_GENERATED_KEYS);
		ps.setString(1, userPreference.getPreferenceName());
		ps.setInt(2, userPreference.getUserPreferenceID());
		int affectedRows = ps.executeUpdate();
		if (affectedRows != 0) {
			return true;
		}
		else {
				conn.rollback();
				DBConnectionFactory.destroyConnection(conn);
				throw new Exception(
						"Updating UserPreference failed, no ID obtained.");
			}
		*/return true;}

	 void saveEventTypeList(List<EventType> eventTypes, Integer userPreferenceID,
			Connection conn) throws Exception {
		String sql;
		PreparedStatement ps;
		int rowsAffected;
		sql = "DELETE FROM UserPreferenceEventTypes WHERE UserPreferenceID=?;";
		ps = conn.prepareStatement(sql);
		ps.setInt(1, userPreferenceID);
		
		rowsAffected = ps.executeUpdate();
		if (rowsAffected == 0) {
			conn.rollback();
			DBConnectionFactory.destroyConnection(conn);
			throw new Exception(
					"Deleting EventTypeList failed for updation, no rows affected.");
		}
		
		
		for (EventType eventType : eventTypes) {
			sql = "INSERT INTO UserPreferenceEventTypes (UserPreferenceID,EventTypeID) values (?,?);";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, userPreferenceID);
			ps.setInt(2, eventType.getEventTypeID());

			rowsAffected = ps.executeUpdate();
			if (rowsAffected == 0) {
				conn.rollback();
				DBConnectionFactory.destroyConnection(conn);
				throw new Exception(
						"Creating EventTypeList failed for updation, no rows affected.");
			}
		}

	}
	 public List<UserPreference> getUserPreference(User user) throws Exception{
		 Connection conn  = DBConnectionFactory.getConnectionObject();
 	    PreparedStatement ps = conn.prepareStatement("Select UserID,CategoryID,NotificationID,NOTIFICATIONPERIOD from UserPreference where UserID =?;");
 	    ps.setInt(1, user.getUserID());
 	    ResultSet rs = ps.executeQuery();
 	   List<UserPreference> userPreferences = new ArrayList<UserPreference>();
 	    while(rs.next()){
 	    	UserPreference userPreference = new UserPreference(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getInt(4));
 	    	userPreferences.add(userPreference);
 	    }
		 return userPreferences;
	 }

}
