package com.eahlbrecht.robinrecords.exporter;

import com.eahlbrecht.robinrecords.Credentials;
import com.eahlbrecht.robinrecords.files.FileHelper;
import com.eahlbrecht.robinrecords.mail.MailRetriever;
import com.eahlbrecht.robinrecords.parsing.MailParser;

import javax.mail.Message;
import java.util.ArrayList;

/**
 * Created by defq0n on 5/9/17.
 */
public class Exporter {

    public static void simpleExport(String filename){
        Message[] messages = MailRetriever.retrieveMessages(Credentials.username, Credentials.password,
                MailRetriever.GMAIL.PROTOCOL_URI.getContent(), MailRetriever.GMAIL.PROTOCOL.getContent(),
                MailRetriever.GMAIL.HOST.getContent());

        ArrayList<Message> records = MailRetriever.getRobinhoodEmails(messages, 6000);

        FileHelper fh = new FileHelper(filename + ".csv");
        fh.createFile();
        fh.writeToFile("Date, Ticker, Share Price, Share Amount, Total Price, Order Type, Side");
        for(Message m : records){
            MailParser mp = new MailParser(m);
            if(mp.validEmail()){
                System.out.println(mp.newPosition(m).toCSVString());
                fh.writeToFile(mp.newPosition(m).toCSVString());
            }
        }
    }
}
