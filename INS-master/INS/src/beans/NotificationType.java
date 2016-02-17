package beans;

public class NotificationType {

	int notificationTypeID;
	String notificationTypeName;
	
	public int getNotificationTypeID() {
		return notificationTypeID;
	}
	public void setNotificationTypeID(int notificationTypeID) {
		this.notificationTypeID = notificationTypeID;
	}
	public String getNotificationTypeName() {
		return notificationTypeName;
	}
	public void setNotificationTypeName(String notificationTypeName) {
		this.notificationTypeName = notificationTypeName;
	}
	public NotificationType(int notificationTypeID, String notificationTypeName) {
		super();
		this.notificationTypeID = notificationTypeID;
		this.notificationTypeName = notificationTypeName;
	}
	public NotificationType() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
