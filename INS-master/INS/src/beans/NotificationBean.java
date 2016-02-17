package beans;

public class NotificationBean {

	int UserID;
	int Cid;
	int NID;
	String contact;
	Event event;
	public int getUserID() {
		return UserID;
	}
	public void setUserID(int userID) {
		UserID = userID;
	}
	public int getCid() {
		return Cid;
	}
	public void setCid(int cid) {
		Cid = cid;
	}
	public int getNID() {
		return NID;
	}
	public void setNID(int nID) {
		NID = nID;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public Event getEvent() {
		return event;
	}
	public void setEvent(Event event) {
		this.event = event;
	}
	
}
