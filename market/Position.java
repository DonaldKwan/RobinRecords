package com.eahlbrecht.robinrecords.market;

import java.util.Date;

/**
 * Created by defq0n on 3/4/17.
 *
 * Keep track of current stock position
 *
 * TODO share price and totalprice to BigDecimal
 */
public class Position {

    private final String TICKER;
    private final double SHARE_PRICE;
    private final int SHARE_AMOUNT;
    private final Date DATE;
    private final ORDER ORDER_TYPE;
    private double totalPrice;

    public Position(String ticker, double sharePrice, int shareAmount, Date date, ORDER order){
        TICKER = ticker;
        SHARE_PRICE = sharePrice;
        SHARE_AMOUNT = shareAmount;
        DATE = date;
        ORDER_TYPE = order;
        totalPrice = sharePrice * shareAmount;
    }


    public String getTicker() {
        return TICKER;
    }

    public double getSharePrice() {
        return SHARE_PRICE;
    }

    public int getShareAmount() {
        return SHARE_AMOUNT;
    }

    public Date getDate() {
        return DATE;
    }

    public ORDER getOrderType(){
        return ORDER_TYPE;
    }

    public double getTotalPrice(){
        return totalPrice;
    }

    @Override
    public String toString(){
        return new StringBuilder().append("<").append(TICKER).append(",")
                .append(SHARE_PRICE).append(",")
                .append(SHARE_AMOUNT).append(",")
                .append(DATE.toString()).append(",")
                .append(totalPrice).append(",")
                .append(ORDER_TYPE.getString()).append(">").toString();
    }

}
