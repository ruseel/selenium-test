

import java.io.File;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SimpleSendMail {
	public static void mail(final String username, final String passwd, String from, String to, String subject, String body,
			File file) {
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, passwd);
			}
		});

		try {
			MimeMessage message = buildMessage(from, to, subject, body, file, session);
			Transport.send(message);
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

	private static MimeMessage buildMessage(String from, String to, String subject, String body, File file, Session session)
			throws MessagingException, AddressException {
		MimeMessage message = new MimeMessage(session);
		message.setFrom(new InternetAddress(from));
		message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
		message.setSubject(subject);

		MimeMultipart mp = new MimeMultipart();
		MimeBodyPart textbp = new MimeBodyPart();
		textbp.setText(body);
		mp.addBodyPart(textbp);

		if (file != null) {
			MimeBodyPart filebp = new MimeBodyPart();
			filebp.setDataHandler(new DataHandler(new FileDataSource(file)));
			filebp.setFileName(file.getName());
			mp.addBodyPart(filebp);
		}

		message.setContent(mp);
		return message;
	}

}
