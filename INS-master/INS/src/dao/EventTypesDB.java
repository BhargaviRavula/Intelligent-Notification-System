package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import beans.Category;

public class EventTypesDB {

	public List<Category> getCategories() throws Exception {
		Category category;
		List<Category> categories = new ArrayList<>();
		Connection conn = DBConnectionFactory.getConnectionObject();

		PreparedStatement ps = conn
				.prepareStatement("SELECT Category_ID,Category_Name "
						+ "FROM Categories;");
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			category = new Category(rs.getInt(1),rs.getString(2));
			categories.add(category);
		}
		rs.close();
		ps.close();
		DBConnectionFactory.destroyConnection(conn);
		if (categories.size() == 0)
			return null;
		else
			return categories;
	}

}
