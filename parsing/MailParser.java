package com.eahlbrecht.robinrecords.parsing;

import com.eahlbrecht.robinrecords.mail.MailRetriever;
import com.eahlbrecht.robinrecords.market.ORDER;
import com.eahlbrecht.robinrecords.market.Position;

import javax.mail.Message;
import javax.mail.MessagingException;
import java.util.Date;

/**
 * Created by defq0n on 3/4/17.
 *
 * MailParser takes a message as a parameter, and therefore retrieves various valuable information
 * about the current message.
 */
public class MailParser {

    /*
    Side note: I don't have much experience parsing information, but this class will work fine for all
    current emails from Robinhood. May have to be updates in the future if the structure of their messages
    changes.
    */

    private String buffer;
    private final Message MESSAGE;

    public MailParser(Message message){
        MESSAGE = message;
        buffer = MailRetriever.getMessageContent(message, new StringBuilder());
    }

    /**
     * Parses the message and returns the order type.
     *
     * @return  the order type, otherwise null if the order can't be parsed
     * */
    public ORDER parseOrderType(){
        String buffer = this.buffer.toLowerCase();
        for(ORDER order : ORDER.values()){
            if(buffer.contains(order.getString())){
                return order;
            }
        }
        return null;
    }

    /**
     * Parses the message and returns the companies ticker.
     *
     * @return  the companies ticker
     * */
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

    /**
     * Parses the message and returns the share price.
     *
     * @return  the share price
     * */
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

    /**
     * Parses the message and returns how many shares were bought/sold.
     *
     * @return  the share amount
     * */
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

    /**
     * Parses the message and returns the date and time the order was executed.
     *
     * @return  the order date and time
     * */
    public Date parseDate(){
        try {
            return MESSAGE.getSentDate();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Returns whether the current message from Robinhood is a valid email - meaning
     * there was an order placed.
     *
     * @return true if the email is valid
     * */
    public boolean validEmail(){
        return buffer.contains("was executed");
    }

    /**
     * Returns a new position object, encapsulating all the needed information about the order.
     *
     * @return new position object
     * */
    public Position newPosition(Message currentMessage) {
        return new Position(parseTicker(), parseSharePrice(), parseShareAmount(), parseDate(), parseOrderType());
    }

}
