package com.eahlbrecht.robinrecords.mail;

import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;

public class MailController {
	
	private final Properties properties;
	private Store store;
	private Session session;
	private Folder inbox;
	
	public MailController(){
		properties = new Properties();
		properties.setProperty("mail.store.protocol", "imap");
		session = Session.getInstance(properties, null);
		try {
			store = session.getStore();
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		}
	}

	public boolean connect(String email, String password){
		try {
			store.connect("imap.gmail.com", email, password);
			return store.isConnected();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public Folder loadInbox(){
		try {
			inbox = store.getFolder("INBOX");
			return inbox;
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Folder getInbox(){
		return inbox;
	}
}
