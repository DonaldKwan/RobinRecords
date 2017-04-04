package com.eahlbrecht.robinrecords.account;

import com.eahlbrecht.robinrecords.market.Position;

import java.util.ArrayList;

/**
 * Created by defq0n on 3/4/17.
 *
 * Used to keep track of account data, i.e. name, current holdings, set alerts, etc
 */
public class Account {

    //current positions will hold multiples of tickers, the plan is when displaying the information to combine it
    // when selling shares, the plan is to work off of total price in stead of share price
    public ArrayList<Position> currentPositions;
    public ArrayList<Position> previousPositions;

    public Account(){
        currentPositions = new ArrayList<>();
        previousPositions = new ArrayList<>();
    }

    public void addPosition(Position position){
        String ticker = position.getTicker();
        Position currentHolding = null;
        for(Position p : currentPositions){
            if(p.getTicker().equals(ticker)){
                currentHolding = p;
            }
        }
        if(position.bought()){
            if(currentHolding == null){
                currentPositions.add(position);
            } else {
                position.addAdditionalPosition(position);
            }
        }else {
            //there are a few different things we have to take care of here
            //1. user buys/sells the same amount of stock
            //2. user buys/sells a different amount of stock
            //3. user buys/sells an incomplete number of stock
            //4. user does not have the ticker in current holdings - didnt grab all emails or glitch
            if(currentHolding == null){
                //user did not import or glitch
                //TODO
            } else if(currentHolding.amountOfAdditionalPositions() == 0){
                if(currentHolding.getShareAmount() == position.getShareAmount()){
                    currentHolding.setCompletion();
                    int currentPositionIndex = 0;
                    for(int i = 0; i < currentPositions.size(); i++){
                        if(currentPositions.get(i).equals(currentHolding)){
                            currentPositionIndex = i;
                        }
                    }
                    previousPositions.add(currentHolding);
                    currentPositions.remove(currentPositionIndex);
                    previousPositions.add(position);
                } else {
                    currentHolding.setRemainingShares(-position.getShareAmount());
                    previousPositions.add(position);
                }
            } else {
                
            }
        }
    }
}
