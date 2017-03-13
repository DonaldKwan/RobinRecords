package com.eahlbrecht.robinrecords.account;

import java.util.HashMap;

/**
 * Created by defq0n on 3/4/17.
 *
 * The Alert class allows the user to have a list of stocks to watch, and retrieve predetermined updates.
 */
public class Alert {

    public enum BOUND {
        ABOVE, BELOW;
    }

    private class DoubleWrapper {
        private double d;
        private DoubleWrapper(double d){
            this.d = d;
        }
        private double getDouble(){
            return d;
        }
    }

    private HashMap<String, HashMap<DoubleWrapper, BOUND>> tickers;

    public Alert(){
        tickers = new HashMap<>();
    }

    public void addTicker(String ticker, double priceTarget, BOUND bound){
        DoubleWrapper d = new DoubleWrapper(priceTarget);
        HashMap<DoubleWrapper, BOUND> hm = new HashMap<>();
        hm.put(d, bound);
        tickers.put(ticker, hm);
    }

    public void removeTicker(String ticker){
        tickers.remove(ticker);
    }
}
