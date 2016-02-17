package beans;

import java.util.Date;

public class Notification {

	Integer notificationID;
	NotificationType notificationType;
	Contact contact;
	int unitCount;
	Date timeNewsLetter;
	UserPreference userPreference;
	
	
	
	public int getUnitCount() {
		return unitCount;
	}
	public void setUnitCount(Integer unitCount) {
		this.unitCount = unitCount;
	}
	public Integer getNotificationID() {
		return notificationID;
	}
	public void setNotificationID(int notificationID) {
		this.notificationID = notificationID;
	}
	public NotificationType getNotificationType() {
		return notificationType;
	}
	public void setNotificationType(NotificationType notificationType) {
		this.notificationType = notificationType;
	}
	public Date getTimeNewsLetter() {
		return timeNewsLetter;
	}
	public void setTimeNewsLetter(Date timeNewsLetter) {
		this.timeNewsLetter = timeNewsLetter;
	}
	public Contact getContact() {
		return contact;
	}
	public void setContact(Contact contact) {
		this.contact = contact;
	}
	
	public UserPreference getUserPreference() {
		return userPreference;
	}
	public void setUserPreference(UserPreference userPreference) {
		this.userPreference = userPreference;
	}
	
	
	public Notification(int notificationID,NotificationType notificationType, Contact contact, int unitCount,
			Date timeNewsLetter, UserPreference userPreference) {
		super();
		this.notificationID = notificationID;
		this.notificationType = notificationType;
		this.contact = contact;
		this.unitCount = unitCount;
		this.timeNewsLetter = timeNewsLetter;
		this.userPreference = userPreference;
	}
	public Notification() {
		super();
	}
	
	
	
}
