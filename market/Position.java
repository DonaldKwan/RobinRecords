package com.eahlbrecht.robinrecords.market;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by defq0n on 3/4/17.
 *
 * Position encapsulates all necessary information about an executed order.
 */
public class Position {

    public enum ORDER {
        MARKET("market order"), LIMIT("limit order"), STOP_LOSS("stop loss"), STOP_LIMIT("stop limit"), BOUGHT("buy"), SOLD("sell");

        private String orderString;

        ORDER(String orderString){
            this.orderString = orderString;
        }

        public String getString(){
            return orderString;
        }
    }

    //starting data
    private final String TICKER;
    private final BigDecimal SHARE_PRICE;
    private final int SHARE_AMOUNT;
    private final Date DATE;
    private final ORDER ORDER_TYPE;
    private final BigDecimal totalPrice;
    private final boolean BOUGHT;

    //inp rogress data
    private boolean completed = false;
    private int remainingShares;
    private ArrayList<Position> additionalPositions;


    public Position(String ticker, double sharePrice, int shareAmount, Date date, ORDER order, boolean bought, boolean completed){
        //starting data
        TICKER = ticker;
        SHARE_PRICE = BigDecimal.valueOf(sharePrice);
        SHARE_AMOUNT = shareAmount;
        DATE = date;
        ORDER_TYPE = order;
        totalPrice = new BigDecimal(sharePrice * shareAmount).setScale(2, RoundingMode.HALF_EVEN);
        BOUGHT = bought;

        //in progress data
        this.completed = completed;
        remainingShares = shareAmount;
        additionalPositions = new ArrayList<>();
    }

    /**
     * Returns the companies ticker.
     *
     * @return  the companies ticker
     * */
    public String getTicker() {
        return TICKER;
    }

    /**
     * Returns the stocks share price when executed.
     *
     * @return  the executed stock share price
     * */
    public BigDecimal getSharePrice() {
        return SHARE_PRICE;
    }

    /**
     * Returns the amount of shares ordered.
     *
     * @return  the amount of ordered shares
     * */
    public int getShareAmount() {
        return SHARE_AMOUNT;
    }

    /**
     * Returns the Date of execution.
     *
     * @return  the date of order execution
     * */
    public Date getDate() {
        return DATE;
    }

    /**
     * Returns the Order type.
     *
     * @return  the Order type
     * */
    public ORDER getOrderType(){
        return ORDER_TYPE;
    }

    /**
     * The total price of the order.
     *
     * @return  the price of the order
     * */
    public BigDecimal getTotalPrice(){
        return totalPrice;
    }

    /**
     * Returns whether the user bought or sold their position.
     *
     * @return  true if the user bought their position
     * */
    public boolean bought(){
        return BOUGHT;
    }

    public boolean isCompleted(){
        return completed;
    }

    public void setCompletion(){
        completed = true;
    }

    public int getRemainingShares(){
        return remainingShares;
    }

    public void setRemainingShares(int amount){
        remainingShares += amount;
    }

    public void addAdditionalPosition(Position position){
        additionalPositions.add(position);
    }

    public ArrayList<Position> getAdditionalPositions(){
        return additionalPositions;
    }

    public boolean removeAdditionalPosition(Position position){
        for(int i = 0; i < additionalPositions.size(); i++){
            if(additionalPositions.get(i).equals(position)){
                additionalPositions.remove(i);
                return true;
            }
        }
        return false;
    }

    public int amountOfAdditionalPositions(){
        return additionalPositions.size();
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
