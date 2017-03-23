package com.eahlbrecht.robinrecords.alerts;

/**
 * Created by defq0n on 3/4/17.
 *
 * The Alert class allows the user to have a list of stocks to watch, and retrieve predetermined updates.
 */
public interface Alert {

    public void run();

    public void stop();
}
