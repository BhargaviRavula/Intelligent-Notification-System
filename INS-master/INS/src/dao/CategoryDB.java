package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import beans.Category;

public class CategoryDB {

	public int getCategoryID(String categoryname) throws Exception{
		PreparedStatement ps=null;
		ResultSet rs = null;
		Connection conn  = DBConnectionFactory.getConnectionObject();
		int categoryID=0;
		ps = conn.prepareStatement("Select Category_ID from Categories where Category_Name =?;");;
	    ps.setString(1, categoryname);
	    rs = ps.executeQuery();
	    if(rs.next())
	    	categoryID = rs.getInt(1);
	    	
    	return categoryID;
	}
	
	public String getCategoryName(int categoryID) throws Exception{
		PreparedStatement ps= null;
		ResultSet rs = null;
		Connection conn  = DBConnectionFactory.getConnectionObject();
		String categoryName = "";
		ps = conn.prepareStatement("Select Category_Name from Categories where  Category_ID=?;");;
	    ps.setInt(1, categoryID);
	    rs = ps.executeQuery();
	    if(rs.next())
	    	categoryName = rs.getString(1);
	    	
    	return categoryName;
		
	}
	public List<Category> getCategories() throws Exception{
		PreparedStatement ps=null;
		ResultSet rs = null;
		List<Category> categories = new ArrayList<Category>();
		Connection conn  = DBConnectionFactory.getConnectionObject();
		ps = conn.prepareStatement("Select Category_ID,Category_Name from Categories");
		rs = ps.executeQuery();
		while(rs.next()){
			Category category = new Category(rs.getInt(1),rs.getString(2));
			categories.add(category);
		}
		return categories;
	}
}
