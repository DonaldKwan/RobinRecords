package com.eahlbrecht.robinrecords;

import com.eahlbrecht.robinrecords.exporter.Exporter;
import com.eahlbrecht.robinrecords.testing.Testing;

/**
 * Created by defq0n on 3/2/17.
 */
public class RobinRecords {


    public static void main(String[] args) {
        try{
            Exporter.simpleExport("test");
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
