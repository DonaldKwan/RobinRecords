package com.eahlbrecht.robinrecords.files;

import java.io.*;
import java.util.Scanner;

/**
 * Created by defq0n on 10/7/16.
 */
public class FileHelper {

    private String textFile = null;
    private String currentLine = null;
    private File file = null;
    private File dir = null;

    public FileHelper(String textFile){
        this.textFile = textFile;
        this.dir = new File("./data/");
        this.file = new File(dir, textFile);
    }

    public String getTextFile() {
        return textFile;
    }

    public void setTextFile(String textFile) {
        this.textFile = textFile;
    }

    public boolean fileExists(){
        return file.exists();
    }

    public boolean createFile(){
        if (file.exists()) {
            boolean overwrite = overwriteHelper();
            if (overwrite) {
                file.delete();
                createFileHelper();
                return true;
            } else {
                System.out.println("File creation failed.");
                return false;
            }
        } else {
            createFileHelper();
        }
        return false;
    }

    private void createFileHelper(){
        try {
            if(!dir.exists()){
                dir.mkdir();
            }
            file.createNewFile();
        } catch(Exception e){
            System.out.println(e.getStackTrace());
        }
    }

    private boolean overwriteHelper(){
        Scanner scanner = new Scanner(System.in);
        String input;
        System.out.println(textFile + " already exists. Are you sure you want to overwrite? (y/n)");
        input = scanner.nextLine();
        if(input.equals("Y") || input.equals("y")){
            return true;
        } else if(input.equals("N") || input.equals("n")){
            return false;
        } else {
            overwriteHelper();
        }
        return false;
    }

    public String readFile(){
        if(file.exists()) {
            BufferedReader reader;
            StringBuilder returnString = new StringBuilder();
            try {
                reader = new BufferedReader(new FileReader("./data/" + textFile));
                while ((currentLine = reader.readLine()) != null) {
                    returnString.append(currentLine + "\n");
                }
                reader.close();
                return returnString.toString();
            } catch (Exception e) {
                System.out.println(e.getStackTrace());
            }
        } else {
            System.out.println("File does not exist, please create a file to read from first.");
            return null;
        }
        return null;
    }

    public boolean writeToFile(String writeString){
        if(file.exists()) {
            String currentFileString = readFile();
            BufferedWriter writer;
            try {
                writer = new BufferedWriter(new FileWriter("./data/" + textFile));
                writer.write(currentFileString);
                writer.write(writeString);
                writer.close();
                return true;
            } catch (Exception e) {
                System.out.println(e.getStackTrace());
            }
        } else {
            System.out.println("File does not exist, please create a file to read from first.");
            return false;
        }
        return false;
    }
}

