package com.eahlbrecht.robinrecords.testing;

import com.eahlbrecht.robinrecords.Credentials;
import com.eahlbrecht.robinrecords.mail.MailRetriever;
import com.eahlbrecht.robinrecords.parsing.MailParser;

import javax.mail.Message;
import javax.mail.MessagingException;
import java.util.ArrayList;

/**
 * Created by defq0n on 3/6/17.
 */
public class Testing {

    public static void testParsing() throws MessagingException {

    }

    public static void testGetPositionsFromEmail(){
        Message[] messages = MailRetriever.retrieveMessages(Credentials.username, Credentials.password, MailRetriever.GMAIL.PROTOCOL_URI.getContent(), MailRetriever.GMAIL.PROTOCOL.getContent(), MailRetriever.GMAIL.HOST.getContent());
        ArrayList<Message> records = MailRetriever.getRobinhoodEmails(messages, 100);
        for(int i = 0; i < records.size(); i++){
            MailParser ph = new MailParser(records.get(i));
            if(ph.validEmail()) {
                System.out.println(ph.newPosition(records.get(i)).toString());
            }
        }
    }

    public static void testAddPositions(){

    }

}
