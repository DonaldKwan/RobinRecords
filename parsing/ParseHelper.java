package com.eahlbrecht.robinrecords.parsing;

import com.eahlbrecht.robinrecords.market.ORDER;

/**
 * Created by defq0n on 3/4/17.
 *
 * Class used for parsing email and file data
 */
public class ParseHelper {

    private final String BUFFER;

    public ParseHelper(String buffer){
        BUFFER = buffer;
    }

    public ORDER parseOrderType(){
        String buffer = BUFFER.toLowerCase();
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
        int startingIndex = BUFFER.indexOf("shares of ") + offset;
        String newBuffer = BUFFER.substring(startingIndex, startingIndex + maxTickerLength);
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
        int startingIndex = BUFFER.indexOf("average price of ") + 17;
        int endingIndex = 0;
        for(int i = startingIndex; i < BUFFER.length(); i++){
            if(Character.isDigit(BUFFER.charAt(i)) || BUFFER.charAt(i) == '.'){
                startingIndex = i;
                int j = i;
                while(Character.isDigit(BUFFER.charAt(j)) || BUFFER.charAt(j) == '.'){
                    j++;
                }
                endingIndex = j;
                break;
            }
        }
        return Double.parseDouble(BUFFER.substring(startingIndex, endingIndex));
    }

    public int parseShareAmount(){
        int startingIndex = 0;
        int endingIndex = 0;
        for(int i = 0; i < BUFFER.length(); i++){
            if(Character.isDigit(BUFFER.charAt(i))){
                startingIndex = i;
                int j = i;
                while(Character.isDigit(BUFFER.charAt(j))){
                    j++;
                }
                endingIndex = j;
                break;
            }
        }
        return Integer.parseInt(BUFFER.substring(startingIndex, endingIndex));
    }

    public boolean validEmail(){
        return BUFFER.contains("was executed");
    }

}
