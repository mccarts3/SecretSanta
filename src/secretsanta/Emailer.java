package secretsanta;
import java.util.Properties;
import java.util.Scanner;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Emailer {
	private String from;
	private String username;
	private Properties props;
	private Session session;
	final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
	
	public Emailer(String username, String from) {
		this.username = username;
		this.from = from;
		this.props = System.getProperties(); 
		setSessionObjects();
	}
	
	private void setSessionObjects() {
		props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
		
		Scanner in = new Scanner(System.in);
		System.out.print("Enter password for " + username + ": ");
		String password = in.next();
		in.close();
		
		session = Session.getDefaultInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username,password);
					}
				});
	}
	
	public void sendEmail(String to, String secretSanta) {
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(to));		// Change to "to"
			message.setSubject("Secret Santa");
			message.setText("Your secret santa is: " + secretSanta + ".  \n\nReminder, " + 
			" there is a $25 maximum for gifts.  Be ready to give gifts before the Christmas" +
			" party on December 3rd at 7pm. If you have any questions, contact Scott McCartney or" + 
			" Kevin Kane.");

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
} 	
