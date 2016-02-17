package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import beans.Contact;
import beans.ContactType;
import beans.Notification;
import beans.NotificationType;

public class NotificationDB {

	public List<Notification> getNotification(int userPreferenceID) throws Exception {

		String sql = "Select n.UnitCount,n.TimeOfNewsLetter, "
				+ "nt.NotificationTypeID, nt.NotificationTypeName,"
				+ "c.ContactID,c.ContactValue, "
				+ "ct.ContactTypeID,ct.ContactTypeName "
				+ "from Notification n INNER JOIN NotificationType nt ON n.NotificationTypeID = nt.NotificationTypeID "
				+ "INNER JOIN Contact c ON c.ContactID = n.ContactID "
				+ "INNER JOIN ContactType ct ON ct.ContactTypeID=c.ContactID "
				+ "WHERE n.UserPreferenceID = ?;";

		Connection conn = DBConnectionFactory.getConnectionObject();

		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, userPreferenceID);
		Notification notification = null;
		List<Notification> allNotifications = new ArrayList<>(); 
		Contact contact = new Contact();
		ContactType contactType = new ContactType();

		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			notification = new Notification();
			
			// Save the Notification fields
			notification.setUnitCount(rs.getInt("UnitCount"));
			Time tempTime = rs.getTime("TimeOfNewsLetter");
			
			Date tempDate;
			if(tempTime!=null)
				tempDate = new Date(tempTime.getTime());
			else
				tempDate = null;
			notification.setTimeNewsLetter(tempDate);

			// Save the NotificationType object
			notification.setNotificationType(new NotificationType(rs
					.getInt("NotificationTypeID"), rs
					.getString("NotificationTypeName")));

			// Save the Contact Object
			contactType = new ContactType(rs.getInt("ContactTypeID"),
					rs.getString("ContactTypeName"));
			contact = new Contact(rs.getInt("ContactID"),
					rs.getString("ContactValue"), contactType, null);
			notification.setContact(contact);
			
			allNotifications.add(notification);
		}
		
		rs.close();
		ps.close();
		DBConnectionFactory.destroyConnection(conn);
		return allNotifications;
	}

	public List<NotificationType> getListOfAllNotificationTypes()
			throws Exception {
		NotificationType notificationType;
		List<NotificationType> notificationTypeList = new ArrayList<>();
		Connection conn = DBConnectionFactory.getConnectionObject();
		PreparedStatement ps = conn
				.prepareStatement("SELECT NotificationTypeID,NotificationTypeName "
						+ "FROM NotificationType;");
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			notificationType = new NotificationType();
			notificationType.setNotificationTypeID(rs
					.getInt("NotificationTypeID"));
			notificationType.setNotificationTypeName(rs
					.getString("NotificationTypeName"));
			notificationTypeList.add(notificationType);
		}
		rs.close();
		ps.close();
		DBConnectionFactory.destroyConnection(conn);
		if (notificationTypeList.size() == 0)
			return null;
		else
			return notificationTypeList;
	}

	public Notification insertNotification(Notification notification,
			Connection conn) throws Exception {
		String sql = "INSERT INTO Notification (UserPreferenceID,NotificationTypeID,TimeOfNewsLetter, "
				+ "UnitCount,ContactID) values (?,?,?,?,?);";
		PreparedStatement ps = conn.prepareStatement(sql,
				Statement.RETURN_GENERATED_KEYS);
		//ps.setInt(1, notification.getUserPreference().getUserPreferenceID());
		ps.setInt(2, notification.getNotificationType().getNotificationTypeID());
		if(notification.getTimeNewsLetter()!=null)
			ps.setTime(3, new Time(notification.getTimeNewsLetter().getTime()));
		else
			ps.setTime(3, null);
		ps.setInt(4, notification.getUnitCount());
		ps.setInt(5, notification.getContact().getContactID());

		int affectedRows = ps.executeUpdate();
		if (affectedRows == 0) {
			throw new Exception(
					"Creating Notification failed, no rows affected.");
		}

		ResultSet generatedKeys = ps.getGeneratedKeys();
		if (generatedKeys.next()) {
			notification.setNotificationID((int) generatedKeys.getLong(1));
			ps.close();
			return notification;
		} else {
			ps.close();
			DBConnectionFactory.destroyConnection(conn);
			return null;
		}
	}
	
	public void deleteNotificationsByPreferenceID(Integer preferenceID, Connection conn) throws Exception
	{
		String sql = "DELETE FROM Notification WHERE UserPreferenceID=?;";
		PreparedStatement ps = conn.prepareStatement(sql,
				Statement.RETURN_GENERATED_KEYS);
		ps.setInt(1, preferenceID);
		int affectedRows = ps.executeUpdate();
		if (affectedRows == 0) {
			conn.rollback();
			DBConnectionFactory.destroyConnection(conn);
			throw new Exception(
					"Deleting all old Notifications for updateUserPreference failed, no rows affected.");
		}
		else {
			ps.close();
		}
	}
	
	public Notification saveNotification(Notification notification,
			Connection conn) throws Exception {
		String sql = "UPDATE Notification SET NotificationTypeID=?,TimeOfNewsLetter=?, "
				+ "UnitCount=?,ContactID=? WHERE NotificationID=?;";
		PreparedStatement ps = conn.prepareStatement(sql,
				Statement.RETURN_GENERATED_KEYS);
		ps.setInt(1, notification.getNotificationType().getNotificationTypeID());
		if(notification.getTimeNewsLetter()!=null)
			ps.setTime(2, new Time(notification.getTimeNewsLetter().getTime()));
		else
			ps.setTime(2, null);
		ps.setInt(3, notification.getUnitCount());
		ps.setInt(4, notification.getContact().getContactID());
		//ps.setInt(5, notification.getUserPreference().getUserPreferenceID());
		int affectedRows = ps.executeUpdate();
		if (affectedRows == 0) {
			conn.rollback();
			DBConnectionFactory.destroyConnection(conn);
			throw new Exception(
					"Updating Notification failed, no rows affected.");
		}
		else {
			ps.close();
			DBConnectionFactory.destroyConnection(conn);
			return notification;
		}
	}
	
}