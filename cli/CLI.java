package com.eahlbrecht.robinrecords.cli;

import com.eahlbrecht.robinrecords.files.FileHelper;

import java.util.Scanner;

/**
 * Created by defq0n on 3/4/17.
 *
 * TODO:
 *      currentHoldings()
 *      alerts()
 *      pastTrades()
 *      quit()
 *      importData()
 *
 *      TODO:   figure out data handling
 *      TODO:   algorithm trading?
 *      TODO:   YahooFinance jar
 *
 */
public class CLI {

    private static FileHelper fh;
    private static Scanner sc;

    public static void runCLI(String[] args){
        log("Welcome to RobinRecords - defq0n 2017\n\n");
        log("Checking for previous data, please wait...");

        fh = new FileHelper("records.data");
        if(fh.fileExists()){
            System.out.println("Preivous data found, importing data now.\n\n");
            //import
        } else {
            System.out.println("Previous data not found, lets set up a new document\n\n");
            fh.createFile();
        }

        sc = new Scanner(System.in);
        while(true){
            printOptions();
            switch (sc.nextInt()){
                case 1:
                    //currentholdings
                    break;
                case 2:
                    //alerts
                    break;
                case 3:
                    //past trades
                    break;
                default:
                    //quit
                    break;
            }
        }

    }

    private static void printOptions(){
        log("1: Current Holdings");
        log("2: Alerts");
        log("3: Past Trades");
    }

    private static void log(String log){
        System.out.println(log);
    }
}
