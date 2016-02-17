package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import beans.Event;
import beans.NotificationBean;

public class NotificationDAO {
public List<NotificationBean> notificationListRetrieval(Event event) throws Exception{
	List<NotificationBean> notificationList = new ArrayList<NotificationBean>();
	
	
	PreparedStatement ps=null;
	ResultSet rs = null;
	int i =0;
	Connection conn  = DBConnectionFactory.getConnectionObject();
	
	// For User ID and Category ID and Event
	ps = conn.prepareStatement("Select UserID,CategoryID from UserRegisteredEvents where EventID=?");
	ps.setInt(1, event.getEventID());
	rs = ps.executeQuery();
	while(rs.next()){
		
		NotificationBean notificationBean = new NotificationBean();
		notificationBean.setEvent(event);
		notificationBean.setUserID(rs.getInt(1));
		notificationBean.setCid(rs.getInt(2));
		notificationList.add(notificationBean);
		
	}
	for(NotificationBean notificationBean :notificationList){
	
	/*ps = conn.prepareStatement("Select Category_ID from UserRegisteredEvents where Category_Name=?");
		ps.setString(1, event.getCategory());
		rs = ps.executeQuery();
		if(rs.next())
		notificationBean.setCid(rs.getInt(1));*/
	
		//For Notification ID
	ps = conn.prepareStatement("Select NotificationID from UserPreference where UserID=? and CategoryID=?");
	ps.setInt(1, notificationBean.getUserID());
	ps.setInt(2, notificationBean.getCid());
		rs = ps.executeQuery();
	if(rs.next())
		notificationBean.setNID(rs.getInt(1));
	//For Contact
	ps=conn.prepareStatement("Select ContactValue from Contact where UserID=? and ContactTypeID=?");
	ps.setInt(1, notificationBean.getUserID());
	ps.setInt(2, notificationBean.getNID());
	rs=ps.executeQuery();
	if(rs.next())
		notificationBean.setContact(rs.getString(1));
	
	}
	return notificationList;
}
}
