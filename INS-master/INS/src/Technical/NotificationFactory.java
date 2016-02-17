package Technical;

import beans.NotificationBean;

public class NotificationFactory {
INotifierAdapter inotifierAdapter;
private static NotificationFactory instance = null;
public NotificationFactory() {
	// TODO Auto-generated constructor stub
}
public static NotificationFactory getinstance(){
	if(instance==null){
		instance = new NotificationFactory();
	}
	return instance;
}
public INotifierAdapter getNotifierAdapter(NotificationBean notificationBean){
	if(notificationBean.getNID()==1)
	{
		INotifierAdapter emailNotifierAdapter = new EmailNotifierAdapter(notificationBean);
		return emailNotifierAdapter;
	}
	else{
		INotifierAdapter smsNotifierAdapter = new SMSNotifierAdapter(notificationBean);
		return smsNotifierAdapter;
	}
}
}
