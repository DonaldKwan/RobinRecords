package com.eahlbrecht.robinrecords.market;

/**
 * Created by defq0n on 3/5/17.
 */
public enum ORDER {
    MARKET("Market Order"), LIMIT("Limit Order"), STOP_LOSS("Stop Loss"), STOP_LIMIT("Stop Limit");

    private String orderString;

    ORDER(String orderString){
        this.orderString = orderString;
    }

    public String getString(){
        return orderString;
    }
}
