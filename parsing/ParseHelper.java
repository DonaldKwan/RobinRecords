package com.eahlbrecht.robinrecords.parsing;

import com.eahlbrecht.robinrecords.market.ORDER;

/**
 * Created by defq0n on 3/4/17.
 *
 * Static class used for parsing email and file data
 */
public class ParseHelper {

    private final String BUFFER;

    public ParseHelper(String buffer){
        BUFFER = buffer;
    }

    public ORDER parseOrderType(){
        return null;
    }

    public String parseTicker(){
        return null;
    }

    public double parseSharePrice(){
        return 0.0d;
    }

    public double parseTotalPrice(){
        return 0.0d;
    }

}
