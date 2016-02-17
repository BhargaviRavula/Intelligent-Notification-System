package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import beans.Contact;
import beans.ContactType;
import beans.User;

public class UserDB {

	/**
	 * Adds the User into the Student table in the database
	 * 
	 * 
	 * 
	 * @param student
	 * @return null if the insert statement fails. student object with userID if
	 *         the insert is successfull
	 * @throws Exception 
	 * @throws Exception
	 */
	public User AddUser(User student) throws Exception {

		Connection conn = DBConnectionFactory.getConnectionObject();
		conn.setAutoCommit(false);
		PreparedStatement ps = conn.prepareStatement("INSERT INTO Student values (?,?,?,?,?,?,?);", 
				Statement.RETURN_GENERATED_KEYS);
		ps.setString(1, student.getUserName());
		ps.setString(2, student.getPassword());
		ps.setString(3, student.getLastName());
		ps.setString(4, student.getFirstName());
		ps.setString(5, student.getEmailAddress());
		ps.setBoolean(6, student.isVerified());
		ps.setInt(7, student.getVerificationCode());

		int affectedRows = ps.executeUpdate();
		if (affectedRows == 0) {
			conn.rollback();
			DBConnectionFactory.destroyConnection(conn);
		    throw new Exception("Creating User failed, no rows affected.");
		}
		
		ResultSet generatedKeys = ps.getGeneratedKeys();
	    if (generatedKeys.next()) {
	    	student.setUserID((int)generatedKeys.getLong(1));
	    	ps.close();
	    }
	    
		
		int contactTypeID = 1;//Email
		
		ps = conn.prepareStatement("INSERT INTO Contact (ContactValue,ContactTypeID,UserID) values (?,?,?);");
		ps.setString(1, student.getEmailAddress());
		ps.setInt(2, contactTypeID);
		ps.setInt(3, student.getUserID());

		affectedRows = ps.executeUpdate();
		if (affectedRows == 0) {
			conn.rollback();
			DBConnectionFactory.destroyConnection(conn);
		    throw new Exception("Creating Contact for User failed, no rows affected.");
		}

		contactTypeID = 2;//mobile
		
		ps = conn.prepareStatement("INSERT INTO Contact (ContactValue,ContactTypeID,UserID) values (?,?,?);");
		ps.setString(1, student.getPhone());
		ps.setInt(2, contactTypeID);
		ps.setInt(3, student.getUserID());

		affectedRows = ps.executeUpdate();
		if (affectedRows == 0) {
			conn.rollback();
			DBConnectionFactory.destroyConnection(conn);
		    throw new Exception("Creating Contact for User failed, no rows affected.");
		}
		
		ps.close();
		conn.commit();
		DBConnectionFactory.destroyConnection(conn);
		return student;

	}

	public boolean isUserNameUnique(User student) throws Exception {
		// This function will add a user to our database

		boolean returnValue = false;

		Connection conn = DBConnectionFactory.getConnectionObject();

		PreparedStatement ps = conn
				.prepareStatement("SELECT UserID FROM Student WHERE UserName=?");
		ps.setString(1, student.getUserName());

		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			returnValue = false;
		} else {
			returnValue = true;
		}

		rs.close();
		ps.close();
		DBConnectionFactory.destroyConnection(conn);
		return returnValue;

	}

	public User checkUserNamePasswordExists(User student) throws Exception {
		User user;
		Connection conn = DBConnectionFactory.getConnectionObject();
		PreparedStatement ps = conn
				.prepareStatement("SELECT UserID,UserName,UserPassword,LastName,FirstName,registeredEmailAddress,"
						+ "IsVerified,VerificationCode FROM Student WHERE UserName=? AND UserPassword=?");
		ps.setString(1, student.getUserName());
		ps.setString(2, student.getPassword());
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			user = new User();
			user.setUserID(rs.getInt("UserID"));
			user.setUserName(rs.getString("UserName"));
			user.setPassword(rs.getString("UserPassword"));
			user.setLastName(rs.getString("LastName"));
			user.setFirstName(rs.getString("FirstName"));
			user.setEmailAddress(rs.getString("registeredEmailAddress"));
			user.setVerified(rs.getBoolean("IsVerified"));
			user.setVerificationCode(rs.getInt("VerificationCode"));
		} else
			user = null;

		rs.close();
		ps.close();
		DBConnectionFactory.destroyConnection(conn);
		return user;

	}

	public boolean checkVerificationCode(User student) throws Exception {
		// This function will add a user to our database

		String[] UserIDColumnName = new String[1];
		UserIDColumnName[0] = "UserID";
		boolean returnValue = false;

		Connection conn = DBConnectionFactory.getConnectionObject();
		PreparedStatement ps = conn
				.prepareStatement("SELECT UserID FROM Student WHERE UserID=? AND VerificationCode=?");
		ps.setInt(1, student.getUserID());
		ps.setInt(2, student.getVerificationCode());

		ResultSet rs = ps.executeQuery();

		if (rs.next()) {
			returnValue = true;
		} else {
			returnValue = false;
		}

		rs.close();
		ps.close();
		DBConnectionFactory.destroyConnection(conn);
		return returnValue;
	}

	public boolean setIsVerifiedAsTrue(User student) throws Exception {
		// This function will add a user to our database

		String[] UserIDColumnName = new String[1];
		UserIDColumnName[0] = "UserID";
		boolean returnValue = false;

		Connection conn = DBConnectionFactory.getConnectionObject();
		PreparedStatement ps = conn
				.prepareStatement("UPDATE Student SET IsVerified=? WHERE UserID=?;");
		ps.setBoolean(1, true);
		ps.setInt(2, student.getUserID());

		if (ps.executeUpdate() == 1)
			returnValue = true;
		else
			returnValue = false;
		ps.close();
		DBConnectionFactory.destroyConnection(conn);
		return returnValue;

	}

	public List<Contact> getAllContactsFromUserID(User student) throws Exception {

		Connection conn = DBConnectionFactory.getConnectionObject();
		PreparedStatement ps = conn
				.prepareStatement("Select c.ContactID, c.ContactValue, ct.ContactTypeID, ct.ContactTypeName "
						+ "from [INSINSTANCE].[dbo].[Contact] c INNER JOIN ContactType ct "
						+ "ON c.ContactTypeID=ct.ContactTypeID "
						+ "WHERE c.UserID = ?;");
		ps.setInt(1, student.getUserID());

		ResultSet rs = ps.executeQuery();
		Contact contact;
		List<Contact> allContacts = new ArrayList<>();
		while (rs.next()) {
			contact = new Contact(rs.getInt("ContactID"), rs.getString("ContactValue"), 
					new ContactType(rs.getInt("ContactTypeID"),rs.getString("ContactTypeName")), student);
			allContacts.add(contact);
		}
		rs.close();
		ps.close();
		DBConnectionFactory.destroyConnection(conn);
		if (allContacts.size() == 0)
			return null;

		return allContacts;

	}
}