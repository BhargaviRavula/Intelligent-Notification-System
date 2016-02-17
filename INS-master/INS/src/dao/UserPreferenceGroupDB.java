package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import beans.Category;
import beans.UserPreference;

public class UserPreferenceGroupDB {
		
	
	/**
	 * Inserts the new userPreference into the database
	 * Accepts SQLConnection as a Connection object.
	 * IMPORTANT: TO BE CALLED FROM ANOTHER DB LAYER METHOD ONLY
	 * 
	 * @param userPreference
	 * @param user
	 * @param conn
	 * @throws Exception
	 */
	public void insertUserPreferenceGroup(List<UserPreference> userPreference) throws Exception
	{
		String sql;
		PreparedStatement ps;
		int affectedRows;
		Connection conn  = DBConnectionFactory.getConnectionObject();
		for(UserPreference pref : userPreference){
			sql = "INSERT INTO [UserPreference] ([UserID],[CategoryID],[NotificationID],[NOTIFICATIONPERIOD] ) values (?,?,?,?);";
		    ps = conn.prepareStatement(sql);
		    ps.setInt(1, pref.getUserID());
		    ps.setInt(2, pref.getCategoryID());
		    ps.setInt(3, pref.getNotificationID());
		    ps.setInt(4, pref.getNotificationPeriod());
		    affectedRows = ps.executeUpdate();
		    if (affectedRows == 0) {
	            throw new Exception("Creating userPreferenceGroups failed, no rows affected.");
	        }

	       }
		
	}
	
	
	/**
	 * Saves the UserPreferenceGroup into the database
	 * 
	 * @param userPreference
	 * @param eventTypes 
	 * @param notifications 
	 * @throws Exception 
	 */
	public List<Category> getUserPreferenceGroup(Integer userID) throws Exception          
	{
		List<Category> userPreferences = null;
		
		Connection conn  = DBConnectionFactory.getConnectionObject();
    	    PreparedStatement ps = conn.prepareStatement("Select CategoryID from UserPreference where UserID =?;");
    	    ps.setInt(1, userID);
    	    ResultSet name;
	    	ResultSet rs = ps.executeQuery();
	    	if(!rs.next())
	    	{
	    		return userPreferences;
	    	}
	    	userPreferences = new ArrayList<Category>();
	    	do 
	    	{
	    		ps = conn.prepareStatement("Select Category_Name from Categories where Category_ID =?;");
	    		ps.setInt(1, rs.getInt(1));
	    		name = ps.executeQuery();
	    		if(name.next()){
	    		Category category = new Category(rs.getInt(1),name.getString(1));
	    		userPreferences.add(category);
	    		}
	    	}while(rs.next());
    	    
    	    rs.close();
    	    ps.close();
    	    DBConnectionFactory.destroyConnection(conn);
        	return userPreferences;
		
	}
	public List<Category> getCategoryID(List<Category> Category) throws Exception          
	{
		List<Category> userPreferences = null;
		PreparedStatement ps=null;
		ResultSet rs = null;
		Connection conn  = DBConnectionFactory.getConnectionObject();
		for(Category pref : Category){
    	    ps = conn.prepareStatement("Select Category_ID from Categories where Category_Name =?;");;
    	    ps.setString(1, pref.getCategory_Name());
    	    
	    	rs = ps.executeQuery();
	    	if(!rs.next())
	    	{
	    		return userPreferences;
	    	}
	    	userPreferences = new ArrayList<Category>();
	    	do 
	    	{	pref.setCategory_ID(rs.getInt(1));
	    		userPreferences.add(pref);	
	    			    		
	    	}while(rs.next());
    	    
    	   
		
	}
		 rs.close();
 	    ps.close();
 	    DBConnectionFactory.destroyConnection(conn);
     	return userPreferences;
}
}
