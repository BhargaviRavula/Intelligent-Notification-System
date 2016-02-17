package beans;

import java.util.Date;

public class Event {

	Integer eventID;
	String eventTitle;
	String eventURL;
	String category;
	String timeDuration;
	String startTime;
	String eventDescription;
	Date eventDate;
	
	
	public Event() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Event(Integer eventID, String eventTitle, String eventURL,
			String category, String timeDuration, String startTime,
			String eventDescription, Date eventDate) {
		super();
		this.eventID = eventID;
		this.eventTitle = eventTitle;
		this.eventURL = eventURL;
		this.category = category;
		this.timeDuration = timeDuration;
		this.startTime = startTime;
		this.eventDescription = eventDescription;
		this.eventDate = eventDate;
	}
	public Integer getEventID() {
		return eventID;
	}
	public void setEventID(Integer eventID) {
		this.eventID = eventID;
	}
	public String getEventTitle() {
		return eventTitle;
	}
	public void setEventTitle(String eventTitle) {
		this.eventTitle = eventTitle;
	}
	public String getEventURL() {
		return eventURL;
	}
	public void setEventURL(String eventURL) {
		this.eventURL = eventURL;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getTimeDuration() {
		return timeDuration;
	}
	public void setTimeDuration(String timeDuration) {
		this.timeDuration = timeDuration;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEventDescription() {
		return eventDescription;
	}
	public void setEventDescription(String eventDescription) {
		this.eventDescription = eventDescription;
	}
	public Date getEventDate() {
		return eventDate;
	}
	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}
	
	
	
	
	
}
