package com.eahlbrecht.robinrecords.files;

/**
 * Created by defq0n on 3/13/17.
 *
 * The data class will be responsible for all writing and reading data to files, and handling
 * them responsibly.
 */
public class Data extends FileHelper{

    private final String CURRENT_POS_FILENAME;

    public Data(String dataFile, String currentPositionFilename) {
        super(dataFile);
        CURRENT_POS_FILENAME = currentPositionFilename;
    }





}
