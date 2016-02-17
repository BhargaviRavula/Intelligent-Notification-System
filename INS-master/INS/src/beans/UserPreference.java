package beans;

import java.util.List;

public class UserPreference {

	Integer UserID;
	Integer CategoryID;
	Integer NotificationID;	
	Integer NotificationPeriod; 
	
	
	public UserPreference(Integer UserID, Integer CategoryID,
			Integer NotificationID, Integer NotificationPeriod) {
		super();
		this.UserID = UserID;
		this.CategoryID = CategoryID;
		this.NotificationID = NotificationID;
		this.NotificationPeriod = NotificationPeriod;
	}
	public UserPreference() {
		super();
	}
	public Integer getUserID() {
		return UserID;
	}
	public void setUserID(Integer userID) {
		UserID = userID;
	}
	public Integer getCategoryID() {
		return CategoryID;
	}
	public void setCategoryID(Integer categoryID) {
		CategoryID = categoryID;
	}
	public Integer getNotificationID() {
		return NotificationID;
	}
	public void setNotificationID(Integer notificationID) {
		NotificationID = notificationID;
	}
	public Integer getNotificationPeriod() {
		return NotificationPeriod;
	}
	public void setNotificationPeriod(Integer notificationPeriod) {
		NotificationPeriod = notificationPeriod;
	}
	
	
	
}
