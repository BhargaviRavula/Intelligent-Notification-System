package beans;

public class Contact {

	Integer contactID;
	String contactValue;
	ContactType contactType; 
	User user;
	
	public Contact() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	public Contact(Integer contactID, String contactValue,
			ContactType contactType, User user) {
		super();
		this.contactID = contactID;
		this.contactValue = contactValue;
		this.contactType = contactType;
		this.user = user;
	}



	public Integer getContactID() {
		return contactID;
	}

	public void setContactID(Integer contactID) {
		this.contactID = contactID;
	}

	public String getContactValue() {
		return contactValue;
	}

	public void setContactValue(String contactValue) {
		this.contactValue = contactValue;
	}

	public ContactType getContactType() {
		return contactType;
	}

	public void setContactType(ContactType contactType) {
		this.contactType = contactType;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
	
	
}
