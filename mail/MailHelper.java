package com.eahlbrecht.robinrecords.mail;

import javax.mail.*;
import java.util.ArrayList;
import java.util.Properties;

/**
 * Created by defq0n on 3/6/17.
 */
public class MailHelper {

    public enum GMAIL {
        PROTOCOL_URI("mail.store.protocol"),
        PROTOCOL("imaps"),
        HOST("imap.gmail.com");

        GMAIL(String content){
            this.content = content;
        }

        public String getContent(){
            return content;
        }

        private String content;
    }

    public static Message[] retrieveMessages(String username, String password, String protocolURI, String protocol, String host){
        try {
            Properties properties = new Properties();
            properties.put(protocolURI, protocol);

            Session emailSession = Session.getDefaultInstance(properties);
            Store store = emailSession.getStore();
            store.connect(host, username, password);

            Folder inbox = store.getFolder("INBOX");
            inbox.open(Folder.READ_WRITE);
            return inbox.getMessages();
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static ArrayList<Message> getRobinhoodEmails(Message[] inbox, int inboxSize, int numberToCheck){
        int counter = 0;
        ArrayList<Message> records = new ArrayList<>();
        for(int i  = inboxSize - numberToCheck, n = inbox.length; i < n; i++){
            System.out.println(i);
            Message currentMessage = inbox[i];
            try {
                Address[] senders = currentMessage.getFrom();
                for(int j = 0; j < senders.length; j++){
                    if(senders[j].toString().contains(("notifications@robinhood.com"))){
                        records.add(currentMessage);
                    }
                }
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }
        return records;
    }

    public static void getMessageContent(Part p) throws MessagingException {
        if(p instanceof Message){
            System.out.println("From: " + ((Message) p).getFrom()[0].toString());
            System.out.println("Date: " + ((Message) p).getReceivedDate().toString());
        }
        try {
            if(p.isMimeType("text/plain")){
                System.out.println((String) p.getContent());
            } else if(p.isMimeType("multipart/*")){
                Multipart mp = (Multipart) p.getContent();
                for(int i = 0; i < mp.getCount(); i++){
                    getMessageContent(mp.getBodyPart(i));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
