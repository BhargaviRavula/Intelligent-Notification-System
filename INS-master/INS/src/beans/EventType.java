package beans;

import java.util.List;

public class EventType {
	
	Integer eventTypeID;
	String eventTypeName;
	EventType eventTypeParent;
	List<Event> events;
	String tag;
	
	public Integer getEventTypeID() {
		return eventTypeID;
	}
	public void setEventTypeID(Integer eventTypeID) {
		this.eventTypeID = eventTypeID;
	}
	public String getEventTypeName() {
		return eventTypeName;
	}
	public void setEventTypeName(String eventTypeName) {
		this.eventTypeName = eventTypeName;
	}
	public EventType getEventTypeParent() {
		return eventTypeParent;
	}
	public void setEventTypeParent(EventType eventTypeParent) {
		this.eventTypeParent = eventTypeParent;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	
	public List<Event> getEvents() {
		return events;
	}
	public void setEvents(List<Event> events) {
		this.events = events;
	}
	public EventType(int eventTypeID, String eventTypeName,
			EventType eventTypeParent, String tag, List<Event>events) {
		super();
		this.eventTypeID = eventTypeID;
		this.eventTypeName = eventTypeName;
		this.eventTypeParent = eventTypeParent;
		this.tag = tag;
		this.events = events;
	}
	public EventType() {
		// TODO Auto-generated constructor stub
	}
	
	
	
}
