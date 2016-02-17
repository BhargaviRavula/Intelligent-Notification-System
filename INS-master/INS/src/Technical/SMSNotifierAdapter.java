package Technical;

import java.net.HttpURLConnection;
import java.net.URL;

import beans.NotificationBean;

public class SMSNotifierAdapter implements INotifierAdapter {
	NotificationBean bean;
SMSNotifierAdapter(NotificationBean bean){
	this.bean = bean;
}
@Override
public void sendNotification() {
    try {
    	String message= bean.getEvent().getCategory()+" " +bean.getEvent().getEventTitle()+bean.getEvent().getTimeDuration();
        URL url = new URL("https://www.textmagic.com/app/api?username=radhika.kasthuri&password=NYpd@A13&cmd=send&text="+message+"&phone="+bean.getContact()+"&unicode=0");
        HttpURLConnection uc = (HttpURLConnection)url.openConnection();

        System.out.println(uc.getResponseMessage());

        uc.disconnect();

} catch(Exception ex) {
        System.out.println(ex.getMessage());

}
}
}
