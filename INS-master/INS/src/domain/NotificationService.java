package domain;

import java.util.ArrayList;
import java.util.List;

import beans.Event;
import beans.NotificationBean;
import dao.NotificationDAO;

public class NotificationService {

	public List<NotificationBean> getnotificationList() throws Exception{
		EventService eventService = new EventService();
    	List<Event> eventList = eventService.retrieveEventsTobeNotified();
    	List<NotificationBean> NotificationList = new ArrayList<NotificationBean>();
    	NotificationDAO notificationDao = new NotificationDAO();
    	for(Event event: eventList){
    		NotificationList.addAll(notificationDao.notificationListRetrieval(event));
    	}
    	return NotificationList;
	}
}
