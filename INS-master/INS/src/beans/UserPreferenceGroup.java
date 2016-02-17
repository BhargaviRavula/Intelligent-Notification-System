package beans;

import java.util.List;

public class UserPreferenceGroup {

	String CategoryName;
	List<Event> events;
	UserPreference userpref;
	int CategoryID;
	public UserPreferenceGroup() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserPreferenceGroup(String CategoryName, List<Event> events) {
		super();
		this.CategoryName = CategoryName;
		this.events = events;
	}
	public String getCategoryName() {
		return CategoryName;
	}
	public void setCategoryName(String categoryName) {
		CategoryName = categoryName;
	}
	public List<Event> getEvents() {
		return events;
	}
	public void setEvents(List<Event> events) {
		this.events = events;
	}
	public UserPreference getUserpref() {
		return userpref;
	}
	public void setUserpref(UserPreference userpref) {
		this.userpref = userpref;
	}
	public int getCategoryID() {
		return CategoryID;
	}
	public void setCategoryID(int categoryID) {
		CategoryID = categoryID;
	}
	
	
	
}
