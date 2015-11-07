package com.acme.edu.printers;

import com.acme.edu.exeptions.PrinterExeption;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Prints message in file
 */
public class FilePrinter implements Printer {

    @Override
    public void print(String message) throws PrinterExeption {
        try (FileWriter file = new FileWriter("ClientFile.txt")) {
            file.write(message);
        } catch (IOException ex) {
            System.out.println("File Output error");
            throw new PrinterExeption("Can't write in file", ex);

        }
    }
}
