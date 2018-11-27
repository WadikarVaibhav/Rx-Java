package rxjava;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

class EmailBroadcast {

	private Session session;
	private final String username;
	private final String password;

	/**
	 * @param username
	 * @param password
	 */
	public EmailBroadcast(String username, String password) {
		this.username = username;
		this.password = password;
		init(username, password);
	}

	/**
	 * @param username
	 * @param password
	 */
	public final void init(String username, String password) {
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		session = Session.getInstance(props, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(EmailBroadcast.this.username, EmailBroadcast.this.password);
			}
		});

	}

	/**
	 * @param recipient
	 * @param subject
	 * @param body
	 * @return
	 */
	public boolean send(String recipient, String subject, String body) {
		boolean status = false;
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
			message.setSubject(subject);
			message.setText(body);
			Transport.send(message);
			status = true;

		} catch (MessagingException ex) {
			Logger.getLogger(EmailBroadcast.class.getName()).log(Level.SEVERE, null, ex);
		}
		return status;
	}

}