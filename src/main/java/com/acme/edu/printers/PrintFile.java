package com.acme.edu.printers;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Prints message in file
 */
public class PrintFile implements Printer {

    @Override
    public void print(String message) {
        try(FileWriter file = new FileWriter("ResultOutput.txt");)  {
            file.write(message);
        } catch (IOException ex) {
            System.out.println("File Output error");
        }
    }
}
