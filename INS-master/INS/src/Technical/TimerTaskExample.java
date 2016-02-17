package Technical;

import java.util.Date;
import java.util.List;
import java.util.TimerTask;

import beans.NotificationBean;
import domain.NotificationService;

public class TimerTaskExample extends TimerTask {

 

   @Override

    public void run() {

        System.out.println("Start time:" + new Date());

        SendNotification();

        System.out.println("End time:" + new Date());

    }

 

    // simulate a time consuming task

    private void SendNotification() {
    	List<NotificationBean> notificationList;
    	NotificationService notificationService = new NotificationService();
    	try {
			notificationList = notificationService.getnotificationList();
			NotificationFactory notifier = NotificationFactory.getinstance();
			for(NotificationBean bean : notificationList){
				INotifierAdapter notifieradapter = notifier.getNotifierAdapter(bean);
				notifieradapter.sendNotification();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}
