package com.eahlbrecht.robinrecords.market;

import java.util.Date;

/**
 * Created by defq0n on 3/4/17.
 *
 * Keep track of current stock position
 */
public class Position {

    private final String TICKER;
    private final double SHARE_PRICE;
    private final int SHARE_AMOUNT;
    private final Date BUY_DATE;
    private final ORDER BUY_ORDER;
    private ORDER sellOrder;
    private Date sellDate;

    public Position(String ticker, double sharePrice, int shareAmount, Date buyDate, ORDER buyOrder){
        TICKER = ticker;
        SHARE_PRICE = sharePrice;
        SHARE_AMOUNT = shareAmount;
        BUY_DATE = buyDate;
        BUY_ORDER = buyOrder;
    }

    public Position(String ticker, double sharePrice, int shareAmount, Date buyDate, ORDER buyOrder, Date sellDate, ORDER sellOrder){
        TICKER = ticker;
        SHARE_PRICE = sharePrice;
        SHARE_AMOUNT = shareAmount;
        BUY_DATE = buyDate;
        BUY_ORDER = buyOrder;
        this.sellDate = sellDate;
        this.sellOrder = sellOrder;
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

    public Date getBuyDate() {
        return BUY_DATE;
    }

    public void setSellDate(Date sellDate){
        this.sellDate = sellDate;
    }

    public Date getSellDate() {
        return sellDate;
    }

    public ORDER getBuyOrder(){
        return BUY_ORDER;
    }

    public ORDER getSellOrder(){
        return sellOrder;
    }

    public void setSellOrder(ORDER sellOrder){
        this.sellOrder = sellOrder;
    }

}
