package com.eahlbrecht.robinrecords.mail;

import javax.mail.*;
import java.util.ArrayList;
import java.util.Properties;

/**
 * Created by defq0n on 3/6/17.
 *
 *  MailRetriever is a simple static class that can retrieve a users inbox and sort through the messages for
 *  messages from Robinhood. The content of the messages can also easily be returned.
 */
public class MailRetriever {

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

    /**
     * Retrieves each email in the user's inbox as a Message,
     * and stores it in a Message Array.
     *
     * @param   username    the user's email
     * @param   password    the user's corresponding password
     * @param   protocolURI protocol uri for the desired mail client
     * @param   protocol    protocol for the mail client
     * @param   host        host uri for the mail client
     * @return              an array of emails as a Message
     * */
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

    /**
     * Sorts through the users email messages and returns an ArrayList
     * of Messages from Robinhood.
     *<p>
     * Checking emails is time consuming, numberToCheck will sort through
     * the last number specified messages.
     *</p>
     * @param   inbox           array of messages or the users inbox
     * @param   numberToCheck   number of last frequent messages to check
     * @return                  returns an ArrayList of messages from Robinhood
     * */
    public static ArrayList<Message> getRobinhoodEmails(Message[] inbox, int numberToCheck){
        ArrayList<Message> records = new ArrayList<>();
        for(int i  = inbox.length - numberToCheck, n = inbox.length; i < n; i++){
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

    /**
     * Returns the content of the message as a String.
     *
     * @param   part    Message or Part to retrieve content from
     * @param   builder StringBuilder to hold the content
     * @return          A string of the message's content
     * */
    public static String getMessageContent(Part part, StringBuilder builder){
        try {
            if(part.isMimeType("text/plain")){
                builder.append((String) part.getContent());
            } else if(part.isMimeType("multipart/*")){
                Multipart mp = (Multipart) part.getContent();
                for(int i = 0; i < mp.getCount(); i++){
                    getMessageContent(mp.getBodyPart(i), builder);
                }
            }
            return builder.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
