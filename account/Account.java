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
        if(position.bought()){
            currentPositions.add(position);
        }else {
            //there are a few different things we have to take care of here
            //1. user buys/sells the same amount of stock
            //2. user buys/sells a different amount of stock
            //3. user buys/sells an incomplete number of stock
            
        }
    }
}
