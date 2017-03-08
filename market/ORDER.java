package com.eahlbrecht.robinrecords.market;

/**
 * Created by defq0n on 3/5/17.
 *
 * An enum containing the different types of Orders, which contain a string representation of the Order.
 */
public enum ORDER {
    MARKET("market order"), LIMIT("limit order"), STOP_LOSS("stop loss"), STOP_LIMIT("stop limit");

    private String orderString;

    ORDER(String orderString){
        this.orderString = orderString;
    }

    public String getString(){
        return orderString;
    }
}
