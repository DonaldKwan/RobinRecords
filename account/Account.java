package com.eahlbrecht.robinrecords.account;

import com.eahlbrecht.robinrecords.market.Position;

import java.util.ArrayList;

/**
 * Created by defq0n on 3/4/17.
 *
 * Used to keep track of account data, i.e. name, current holdings, set alerts, etc
 *
 * TODO when determining profit, base off of total price instead of each position
 */
public class Account {

    //current positions will hold multiples of tickers, the plan is when displaying the information to combine it
    // when selling shares, the plan is to work off of total price in stead of share price
    private ArrayList<Position> currentPositions;
    private ArrayList<Position> previousPositions;

    public Account(){
        currentPositions = new ArrayList<>();
        previousPositions = new ArrayList<>();
    }

    public ArrayList<Position> getCurrentPositions(){
        return currentPositions;
    }

    public ArrayList<Position> getPreviousPositions(){
        return previousPositions;
    }

    public void newPosition(Position newPosition){
        if(newPosition.bought()){
            //check if the user already has a holding of the current company
            Position currentPosition = currentCompanyPosition(newPosition);
            if(currentPosition == null){
                currentPositions.add(newPosition);
            } else {
                currentPosition.addAdditionalPosition(newPosition);
            }

            System.out.println(currentPositions.get(0).toString());
        } else {
            Position currentPosition = currentCompanyPosition(newPosition);
            if(currentPosition == null){
                System.out.println("NULLNULLNULLNULLNULLNULLNULLNULLNULL");
                //TODO
            } else {
                int amountOfPartialPositions = currentPosition.amountOfPartialPositions();
                if(amountOfPartialPositions == 0){
                    // there are no additional positions, handle here
                } else {
                    Position lowestAdditionalPosition = currentLowestPartialPosition(currentPosition);
                    if(lowestAdditionalPosition.getRemainingShares() == newPosition.getRemainingShares()){
                        //remove current company position from currentPositions

                    } else if(lowestAdditionalPosition.getRemainingShares() < newPosition.getRemainingShares()){

                    } else if(lowestAdditionalPosition.getRemainingShares() > newPosition.getRemainingShares()){

                    }
                }
            }
        }
    }

    private Position currentCompanyPosition(Position newPosition){
        for(int i = 0; i < currentPositions.size(); i++){
            if(newPosition.getTicker().equals(currentPositions.get(i).getTicker())){
                return currentPositions.get(i);
            }
        }
        return null;
    }

    private boolean removeCurrentPosition(String ticker){
        for(int i = 0; i < currentPositions.size(); i++){
            if(currentPositions.get(i).getTicker().equals(ticker)){
                currentPositions.remove(i);
                return true;
            }
        }
        return false;
    }

    private Position currentLowestPartialPosition(Position currentPosition){
        ArrayList<Position> additionalPositions = currentPosition.getPartialPositions();
        Position lowestPosition = null;
        for(int i = 0; i < additionalPositions.size(); i++){
            if(lowestPosition == null){
                lowestPosition =  additionalPositions.get(i);
            } else if(additionalPositions.get(i).getRemainingShares() < lowestPosition.getRemainingShares()){
                lowestPosition = additionalPositions.get(i);
            }
        }
        return lowestPosition;
    }
}
