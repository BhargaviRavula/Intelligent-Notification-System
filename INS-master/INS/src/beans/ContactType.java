package beans;

public class ContactType {

	Integer contactTypeID;
	String contactTypeName;
	
	public Integer getContactTypeID() {
		return contactTypeID;
	}
	public void setContactTypeID(Integer contactTypeID) {
		this.contactTypeID = contactTypeID;
	}
	public String getContactTypeName() {
		return contactTypeName;
	}
	public void setContactTypeName(String contactTypeName) {
		this.contactTypeName = contactTypeName;
	}
	public ContactType() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ContactType(Integer contactTypeID, String contactTypeName) {
		super();
		this.contactTypeID = contactTypeID;
		this.contactTypeName = contactTypeName;
	}
	
	
	
}
