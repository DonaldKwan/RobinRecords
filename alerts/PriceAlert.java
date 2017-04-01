package com.eahlbrecht.robinrecords.alerts;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by defq0n on 3/22/17.
 */
public class PriceAlert implements Alert {

    public enum BOUND {
        ABOVE, BELOW;
    }

    public class AlertStockInformation {
        private final String TICKER;
        private final BOUND _BOUND;
        private final double PRICE;

        private AlertStockInformation(String ticker, BOUND bound, double price){
            TICKER = ticker;
            _BOUND = bound;
            PRICE = price;
        }

        public String getTicker(){
            return TICKER;
        }

        public BOUND getBound(){
            return _BOUND;
        }

        public double getPrice(){
            return PRICE;
        }
    }


    private ArrayList<AlertStockInformation> tickerArray;
    private Thread thread;

    public PriceAlert(){
        tickerArray = new ArrayList<>();
    }

    public void addTicker(AlertStockInformation asi){
        tickerArray.add(asi);
    }

    public void removeTicker(String ticker){
        for(int i = 0; i < tickerArray.size(); i++){
            if(tickerArray.get(i).getTicker().equals(ticker)){
                tickerArray.remove(i);
            }
        }
    }

    private void runHelper(){
        //TODO: will probably have to do with locking
        //TODO: add timer
        while(true) {
            if (!tickerArray.isEmpty()) {
                for (int i = 0; i < tickerArray.size(); i++) {
                    try {
                        Stock stock = YahooFinance.get(tickerArray.get(i).getTicker());
                        BigDecimal stockPrice = stock.getQuote().getPrice();
                        switch(tickerArray.get(i).getBound()){
                            case ABOVE:
                                if(stockPrice.doubleValue() >= tickerArray.get(i).getPrice()){
                                    //alert
                                }
                                break;
                            case BELOW:
                                if(stockPrice.doubleValue() <= tickerArray.get(i).getPrice()){
                                    //alert
                                }
                                break;
                            default:
                                break;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    @Override
    public void run(){
        thread = new Thread(new Runnable(){

            @Override
            public void run() {
                runHelper();
            }
        });
        thread.start();
    }

    @Override
    public void stop() {
       if(thread != null && thread.isAlive()){
           thread.interrupt();
       }
    }
}
