package com.eahlbrecht.robinrecords.parsing;

import com.eahlbrecht.robinrecords.mail.MailHelper;
import com.eahlbrecht.robinrecords.market.ORDER;
import com.eahlbrecht.robinrecords.market.Position;

import javax.mail.Message;
import javax.mail.MessagingException;
import java.util.Date;

/**
 * Created by defq0n on 3/4/17.
 *
 * Class used for parsing email and file data
 */
public class MailParser {

    private String buffer;
    private final Message MESSAGE;

    public MailParser(Message message){
        MESSAGE = message;
        try {
            buffer = MailHelper.getMessageContent(message, new StringBuilder());
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public ORDER parseOrderType(){
        String buffer = this.buffer.toLowerCase();
        for(ORDER order : ORDER.values()){
            if(buffer.contains(order.getString())){
                return order;
            }
        }
        return null;
    }

    public String parseTicker(){
        int offset = 10;
        int maxTickerLength = 5;
        int startingIndex = buffer.indexOf("shares of ") + offset;
        String newBuffer = buffer.substring(startingIndex, startingIndex + maxTickerLength);
        if(newBuffer.contains(" ") || newBuffer.contains(".")){
            if(newBuffer.contains(" ")){
                return newBuffer.substring(0, newBuffer.indexOf(" "));
            } else {
                return newBuffer.substring(0, newBuffer.indexOf("."));
            }
        }
        return newBuffer;
    }

    public double parseSharePrice(){
        int offset = 17;
        int startingIndex = buffer.indexOf("average price of ") + offset;
        int endingIndex = 0;
        for(int i = startingIndex; i < buffer.length(); i++){
            if(Character.isDigit(buffer.charAt(i)) || buffer.charAt(i) == '.'){
                startingIndex = i;
                int j = i;
                while(Character.isDigit(buffer.charAt(j)) || buffer.charAt(j) == '.'){
                    j++;
                }
                endingIndex = j;
                break;
            }
        }
        return Double.parseDouble(buffer.substring(startingIndex, endingIndex));
    }

    public int parseShareAmount(){
        int startingIndex = 0;
        int endingIndex = 0;
        for(int i = 0; i < buffer.length(); i++){
            if(Character.isDigit(buffer.charAt(i))){
                startingIndex = i;
                int j = i;
                while(Character.isDigit(buffer.charAt(j))){
                    j++;
                }
                endingIndex = j;
                break;
            }
        }
        return Integer.parseInt(buffer.substring(startingIndex, endingIndex));
    }

    public Date parseDate(){
        try {
            return MESSAGE.getSentDate();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean validEmail(){
        return buffer.contains("was executed");
    }

    public Position newPosition(Message currentMessage) {
        return new Position(parseTicker(), parseSharePrice(), parseShareAmount(), parseDate(), parseOrderType());
    }

}
