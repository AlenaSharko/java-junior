package com.acme.edu.printers;

import com.acme.edu.exeptions.PrinterExeption;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;

/**
 * Prints message in file
 */
public class FilePrinter implements Printer {

    private String fileName;
    private Charset charSet;

    public FilePrinter(String fileName, Charset charSet) {
        this.fileName = fileName;
        this.charSet = charSet;
    }

    @Override
    public void print(String message) throws PrinterExeption {
        try (FileOutputStream file = new FileOutputStream(fileName);
            OutputStreamWriter writer = new OutputStreamWriter(file, charSet)) {
            writer.write(message);
        } catch (IOException ex) {
            System.out.println("File Output error");
            throw new PrinterExeption("Can't write in file", ex);

        }
    }
}
