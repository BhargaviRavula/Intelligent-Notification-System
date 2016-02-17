package domain;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import beans.Contact;
import beans.User;
import dao.UserDB;

public class UserService {

	
	public User registerUser(User student) throws Exception
	{
		UserDB userDatabase = new UserDB();
		
		student = encryptUserPassword(student);
		if(userDatabase.isUserNameUnique(student))	
			student = userDatabase.AddUser(student);
		else
			student = null;
		
		return student;
	}
	
	public boolean isUniqueUserName(User student) throws Exception
	{
		UserDB userDatabase = new UserDB();
		return userDatabase.isUserNameUnique(student);
	}
	
	private User encryptUserPassword(User student)
	{
		String encryptedPassword;
		encryptedPassword = byteArrayToHexString((computeHash(student.getPassword())));
		student.setPassword(encryptedPassword);
		return student;
	}
	
	private byte[] computeHash(String x)  
	{
	     java.security.MessageDigest d =null;
	     try {
			d = java.security.MessageDigest.getInstance("SHA-1");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	     d.reset();
	     d.update(x.getBytes());
	     return  d.digest();
	}
			  
	private static String byteArrayToHexString(byte[] b)
	{
		StringBuffer sb = new StringBuffer(b.length * 2);
		for (int i = 0; i < b.length; i++) {
			int v = b[i] & 0xff;
			if (v < 16) {
				sb.append('0');
			}
			sb.append(Integer.toHexString(v));
		}
		return sb.toString().toUpperCase();
	}
	
	
	
	
	public boolean checkVerificationCode(User student) throws Exception
	{
		UserDB userDatabase = new UserDB();
		return userDatabase.checkVerificationCode(student);
	}
	
	
	public User checkLoginDetails(User student) throws Exception
	{
		UserDB userDatabase = new UserDB();
		student = encryptUserPassword(student);
		return userDatabase.checkUserNamePasswordExists(student);	
	}
	
	public boolean markUserAsVerfied(User student) throws Exception
	{
		UserDB userDatabase = new UserDB();
		return userDatabase.setIsVerifiedAsTrue(student);
	}
	
	public List<Contact> getContactsOfUser(User student) throws Exception
	{
		UserDB userDatabase = new UserDB();
		return userDatabase.getAllContactsFromUserID(student);
	}
	
}
