package com.eahlbrecht.robinrecords.market;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by defq0n on 3/4/17.
 *
 * Keep track of current stock position
 */
public class Position {

    private final String TICKER;
    private final BigDecimal SHARE_PRICE;
    private final int SHARE_AMOUNT;
    private final Date DATE;
    private final ORDER ORDER_TYPE;
    private final BigDecimal totalPrice;

    public Position(String ticker, double sharePrice, int shareAmount, Date date, ORDER order){
        TICKER = ticker;
        SHARE_PRICE = BigDecimal.valueOf(sharePrice);
        SHARE_AMOUNT = shareAmount;
        DATE = date;
        ORDER_TYPE = order;
        totalPrice = new BigDecimal(sharePrice * shareAmount).setScale(2, RoundingMode.HALF_EVEN);
    }


    public String getTicker() {
        return TICKER;
    }

    public BigDecimal getSharePrice() {
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

    public BigDecimal getTotalPrice(){
        return totalPrice;
    }

    @Override
    public String toString(){
        return new StringBuilder().append("<").append(TICKER).append(",")
                .append(SHARE_PRICE).append(",")
                .append(SHARE_AMOUNT).append(",")
                .append(DATE.toString()).append(",")
                .append(totalPrice.doubleValue()).append(",")
                .append(ORDER_TYPE.getString()).append(">").toString();
    }

}
