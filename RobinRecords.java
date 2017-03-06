package com.eahlbrecht.robinrecords;

import com.eahlbrecht.robinrecords.mail.MailHelper;

import javax.mail.*;
import java.util.ArrayList;
import java.util.Properties;

/**
 * Created by defq0n on 3/2/17.
 */
public class RobinRecords {

    private static String username = "";
    private static String password = "";

    public static void main(String[] args) throws MessagingException {
        Message[] messages = MailHelper.retrieveMessages(username, password, MailHelper.GMAIL.PROTOCOL_URI.getContent(), MailHelper.GMAIL.PROTOCOL.getContent(), MailHelper.GMAIL.HOST.getContent());
        ArrayList<Message> records = MailHelper.getRobinhoodEmails(messages, messages.length, 1000);
        if(records.size() != 0){
            System.out.println("" + records.size());
        } else {
            System.out.println("none");
        }

        for(int i = 0; i < records.size(); i++){
            MailHelper.getMessageContent(records.get(i));
        }
    }

}
