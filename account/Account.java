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
    public ArrayList<Position> currentPositions;
    public ArrayList<Position> previousPositions;

    public Account(){
        currentPositions = new ArrayList<>();
        previousPositions = new ArrayList<>();
    }

    public void addPosition(Position position){
        Position currentHolding = getCurrentHolding(position.getTicker());
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
                //this manages the users one and only position
                if(currentHolding.getRemainingShares() == position.getShareAmount()){
                    currentHolding.setCompletion();
                    addPreviousPosition(new Position[]{currentHolding, position});
                    removeCurrentPosition(currentHolding);
                } else {
                    currentHolding.setRemainingShares(-position.getShareAmount());
                    addPreviousPosition(new Position[]{position});
                }
            } else {
                //this manages if the user has more than one position
                //will complete positions with lowest amount of shares first
                int shareCount = position.getRemainingShares();
                while(shareCount != 0) {
                    Position lowestPosition = getLowestPosition(currentHolding);
                    if(position.getRemainingShares() > lowestPosition.getRemainingShares()){
                        position.setRemainingShares(-lowestPosition.getRemainingShares());
                        shareCount -= lowestPosition.getRemainingShares();
                        //loop to get next lowest position and remove current lowestPosition
                        addPreviousPosition(new Position[]{lowestPosition});
                        currentHolding.removeAdditionalPosition(lowestPosition);
                    } else if(position.getRemainingShares() < lowestPosition.getRemainingShares()){
                        //additional position with the lowest amount of shares has less than the
                        // new position
                        //update lowestPosition shares to remove new positions shares
                        lowestPosition.setRemainingShares(-position.getRemainingShares());
                        addPreviousPosition(new Position[]{position});
                        shareCount = 0;
                    } else if(position.getRemainingShares() == lowestPosition.getRemainingShares()){
                        //additional positions with the lowest amount of shares has the same as
                        //the new position
                        addPreviousPosition(new Position[]{position, lowestPosition});
                        currentHolding.removeAdditionalPosition(lowestPosition);
                        shareCount = 0;
                    }
                }
            }
        }
    }

    private Position getLowestPosition(Position currentHolding){
        Position lowestPosition = null;
        for (Position p : currentHolding.getAdditionalPositions()) {
            if (lowestPosition == null) {
                lowestPosition = p;
            } else if (p.getRemainingShares() < lowestPosition.getRemainingShares()) {
                lowestPosition = p;
            }
        }
        return lowestPosition;
    }

    private int getCurrentPositionIndex(Position currentHolding){
        int currentPositionIndex = 0;
        for(int i = 0; i < currentPositions.size(); i++){
            if(currentPositions.get(i).equals(currentHolding)){
                currentPositionIndex = i;
            }
        }
        return currentPositionIndex;
    }

    private Position getCurrentHolding(String ticker){
        Position currentHolding = null;
        for(Position p : currentPositions){
            if(p.getTicker().equals(ticker)){
                currentHolding = p;
            }
        }
        return currentHolding;
    }

    private void addPreviousPosition(Position[] positions){
        for(Position p : positions){
            previousPositions.add(p);
        }
    }

    private void addCurrentPosition(Position[] positions){
        for(Position p : positions){
            currentPositions.add(p);
        }
    }

    private void removeCurrentPosition(Position p){
        currentPositions.remove(getCurrentPositionIndex(p));
    }
}
