package Technical;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import beans.NotificationBean;

public class EmailNotifierAdapter implements INotifierAdapter{
	NotificationBean bean;
	EmailNotifierAdapter(NotificationBean bean){
		this.bean = bean;
	}
	@Override
	public void sendNotification() {
		/*String uuid = UUID.randomUUID().toString();
		System.out.println("uuid = " + uuid);*/
		
		int val = ((int)(Math.random()*9000)+1000);
	
		// Send Email to the registerd user for verification
				   final String username = "ooadgroup7@outlook.com";//senders username"
			       final String password = "Events@2";//senders password"
			        
			        //If you want to use TLS
			        Properties props = new Properties();
			        props.put("mail.smtp.auth", "true");
			        props.put("mail.smtp.starttls.enable", "true");
			        //props.put("mail.smtp.host", "smtp.gmail.com");//for gmail id
			        //props.put("mail.smtp.host", "smtp-mail.outlook.com");//for desktop outlook
			        props.put("mail.smtp.host", "smtp.live.com");
			        props.put("mail.smtp.port", "587");
			        
			        //If you want to use SSL
			        props.put("mail.smtp.socketFactory.port", "587");
			        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			        props.put("mail.smtp.auth", "true");
			        props.put("mail.smtp.port", "587");
			        
			       
			        Session session = Session.getInstance(props,
			          new javax.mail.Authenticator() {
			            protected PasswordAuthentication getPasswordAuthentication() {
			                return new PasswordAuthentication(username, password);
			            }
			          });

			        try {

			            Message message = new MimeMessage(session);
			            			            		            			            
			            message.setFrom(new InternetAddress(username)); //senders email 
			            message.setRecipients(Message.RecipientType.TO,
			                    InternetAddress.parse(bean.getContact()));//set receivers email here
			            
			            message.setSubject("Event Notification :" + bean.getEvent().getEventTitle());
			            
			            String html = "This is a Notification for the Event"+bean.getEvent().getEventTitle()+" .<br/>" + "Description : "+bean.getEvent().getEventDescription()+
			            "<br/>Event Duration:"
			            		+bean.getEvent().getTimeDuration();	
			            Multipart mp = new MimeMultipart();
			            MimeBodyPart htmlPart = new MimeBodyPart();
			            htmlPart.setContent(html, "text/html");
			            mp.addBodyPart(htmlPart);
			            message.setContent(mp);			            			            
			            			            
			            Transport.send(message);
			           

			        } 
			        catch (MessagingException e) {
			        	//System.out.println(e.toString());
			        	e.printStackTrace();
			        	
			        }
				}

}
