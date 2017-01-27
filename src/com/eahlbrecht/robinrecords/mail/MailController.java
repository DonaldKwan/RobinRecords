package com.eahlbrecht.robinrecords.mail;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.search.FlagTerm;

public class MailController {

	public MailController() {

	}

	public void check() {
		try {

			Properties properties = System.getProperties();

	        properties.setProperty("mail.pop3.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
	        properties.setProperty("mail.pop3.socketFactory.fallback", "false");
	        properties.setProperty("mail.pop3.port", "995");
	        properties.setProperty("mail.pop3.socketFactory.port", "995");
	        properties.put("mail.pop3.host", "pop.gmail.com");
			Session emailSession = Session.getDefaultInstance(properties, null);

			Store store = emailSession.getStore("pop3s");

			store.connect("pop.gmail.com", "ahlberik@gmail.com", ""); //Last argument is password

			Folder emailFolder = store.getFolder("Inbox");
			emailFolder.open(Folder.READ_ONLY);

			Message[] messages = emailFolder.search(new FlagTerm(new Flags(Flags.Flag.RECENT), false));
//			System.out.println("FROM: " + messages[0].getFrom()[0]);
//			System.out.println("SUBJECT: " + messages[messages.length - 1].getSubject());
//			System.out.println("DATE: " + messages[messages.length-1].getSentDate().toString());
			//writePart(messages[messages.length-1]);
			
			System.out.println(messages.length);
			for(int i = 0; i < messages.length; i++){
				System.out.println("FROM: " + messages[i].getFrom()[0]);
				System.out.println("SUBJECT: " + messages[i].getSubject() + "\n");
			}

		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	public static void writePart(Part p) throws Exception {
		if (p instanceof Message)
			// Call methos writeEnvelope
			writeEnvelope((Message) p);

		System.out.println("----------------------------");
		System.out.println("CONTENT-TYPE: " + p.getContentType());

		// check if the content is plain text
		if (p.isMimeType("text/plain")) {
			System.out.println("This is plain text");
			System.out.println("---------------------------");
			System.out.println((String) p.getContent());
		} else if (p.isMimeType("multipart/*")) {
			System.out.println("This is a Multipart");
			System.out.println("---------------------------");
			Multipart mp = (Multipart) p.getContent();
			int count = mp.getCount();
			for (int i = 0; i < count; i++)
				writePart(mp.getBodyPart(i));
		}
		

	}

	public static void writeEnvelope(Message m) throws Exception {
		System.out.println("This is the message envelope");
		System.out.println("---------------------------");
		Address[] a;

		// FROM
		if ((a = m.getFrom()) != null) {
			for (int j = 0; j < a.length; j++)
				System.out.println("FROM: " + a[j].toString());
		}

		// TO
		if ((a = m.getRecipients(Message.RecipientType.TO)) != null) {
			for (int j = 0; j < a.length; j++)
				System.out.println("TO: " + a[j].toString());
		}

		// SUBJECT
		if (m.getSubject() != null)
			System.out.println("SUBJECT: " + m.getSubject());

	}

}
