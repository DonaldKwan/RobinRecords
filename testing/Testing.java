package com.eahlbrecht.robinrecords.testing;

import com.eahlbrecht.robinrecords.Credentials;
import com.eahlbrecht.robinrecords.mail.MailHelper;
import com.eahlbrecht.robinrecords.parsing.MailParser;

import javax.mail.Message;
import javax.mail.MessagingException;
import java.util.ArrayList;

/**
 * Created by defq0n on 3/6/17.
 */
public class Testing {

    public static void testParsing() throws MessagingException {
        Message[] messages = MailHelper.retrieveMessages(Credentials.username, Credentials.password, MailHelper.GMAIL.PROTOCOL_URI.getContent(), MailHelper.GMAIL.PROTOCOL.getContent(), MailHelper.GMAIL.HOST.getContent());
        ArrayList<Message> records = MailHelper.getRobinhoodEmails(messages, messages.length, 100);
        if(records.size() != 0){
            System.out.println("" + records.size());
        } else {
            System.out.println("none");
        }

        for(int i = 0; i < records.size(); i++){
            String buffer = MailHelper.getMessageContent(records.get(i), new StringBuilder());
            MailParser ph = new MailParser(buffer);
            if(ph.validEmail()) {
                System.out.println(ph.newPosition(records.get(i)).toString());
//                System.out.println(records.get(i).getSentDate().toString());
//                System.out.println(buffer + "\n\n\n\n\n");
//                System.out.println("Ticker: " + ph.parseTicker());
//                System.out.println("Share Amount: " + ph.parseShareAmount());
//                System.out.println("Share Price: " + ph.parseSharePrice());
//                System.out.println("Order Type: " + ph.parseOrderType().getString());
            }
        }
//        String buffer = MailHelper.getMessageContent(records.get(records.size()-1), new StringBuilder());
//        MailParser ph = new MailParser(buffer);
//        System.out.println(buffer + "\n\n\n\n\n");
//        System.out.println("Ticker: " + ph.parseTicker());
//        System.out.println("Share Amount: " + ph.parseShareAmount());
//        System.out.println("Share Price: " + ph.parseSharePrice());
//        System.out.println("Order Type: " + ph.parseOrderType().getString());
    }

}
