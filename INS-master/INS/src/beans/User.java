package beans;

public class User {

	Integer userID;
	String userName;
	String password;
	String lastName;
	String firstName;
	String emailAddress;
	boolean isVerified;
	Integer verificationCode;
	String phone;
	
	public User()
	{
		
	}
	
	public User(Integer userID, String emailAddress, String password, String lastName,
			String firstName,boolean isVerified,
			Integer verificationCode,String phone) {
		super();
		this.userID = userID;
		this.userName = emailAddress.substring(0,emailAddress.indexOf('@'));
		this.password = password;
		this.lastName = lastName;
		this.firstName = firstName;
		this.phone = phone;
		this.emailAddress = emailAddress;
		this.isVerified = isVerified;
		this.verificationCode = verificationCode;
	}
	public Integer getUserID() {
		return userID;
	}
	public void setUserID(Integer userID) {
		this.userID = userID;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public boolean isVerified() {
		return isVerified;
	}
	public void setVerified(boolean isVerified) {
		this.isVerified = isVerified;
	}
	public Integer getVerificationCode() {
		return verificationCode;
	}
	public void setVerificationCode(Integer verificationCode) {
		this.verificationCode = verificationCode;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
	
	
}
