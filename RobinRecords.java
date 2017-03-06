package com.eahlbrecht.robinrecords;

import com.eahlbrecht.robinrecords.mail.MailHelper;
import com.eahlbrecht.robinrecords.parsing.ParseHelper;

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
        Message[] messages = MailHelper.retrieveMessages(Credentials.username, Credentials.password, MailHelper.GMAIL.PROTOCOL_URI.getContent(), MailHelper.GMAIL.PROTOCOL.getContent(), MailHelper.GMAIL.HOST.getContent());
        ArrayList<Message> records = MailHelper.getRobinhoodEmails(messages, messages.length, 100);
        if(records.size() != 0){
            System.out.println("" + records.size());
        } else {
            System.out.println("none");
        }

        for(int i = 0; i < records.size(); i++){
            String buffer = MailHelper.getMessageContent(records.get(i), new StringBuilder());
            ParseHelper ph = new ParseHelper(buffer);
            if(ph.validEmail()) {
                System.out.println(buffer + "\n\n\n\n\n");
//                System.out.println("Ticker: " + ph.parseTicker());
//                System.out.println("Share Amount: " + ph.parseShareAmount());
//                System.out.println("Share Price: " + ph.parseSharePrice());
//                System.out.println("Order Type: " + ph.parseOrderType().getString());
            }
        }
//        String buffer = MailHelper.getMessageContent(records.get(records.size()-1), new StringBuilder());
//        ParseHelper ph = new ParseHelper(buffer);
//        System.out.println(buffer + "\n\n\n\n\n");
//        System.out.println("Ticker: " + ph.parseTicker());
//        System.out.println("Share Amount: " + ph.parseShareAmount());
//        System.out.println("Share Price: " + ph.parseSharePrice());
//        System.out.println("Order Type: " + ph.parseOrderType().getString());
    }

}
