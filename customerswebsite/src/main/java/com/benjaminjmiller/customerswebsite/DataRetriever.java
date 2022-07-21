package com.benjaminjmiller.customerswebsite;

import java.io.*;

public class DataRetriever {

    // Gets the customer data using the customer-management-app. The exported CSV is read and
    // used to render the index page.
    public static BufferedReader getCustomerData() {
        Process process = null;
        try {
            process = Runtime.getRuntime().exec("java -jar customer-management-app-1.0-SNAPSHOT.jar export all customers.csv");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            process.waitFor();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        File file = new File("customers.csv");
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return new BufferedReader(fileReader);
    }
}
