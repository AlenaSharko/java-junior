package com.acme.edu.unit.printerTests;

import com.acme.edu.exeptions.PrinterExeption;
import com.acme.edu.printers.FilePrinter;
import com.acme.edu.printers.Printer;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * Test for writing in test file
 */
public class FilePrinterTest {

    Printer printer;
    File testFile;

    @Before
    public void setUp() throws PrinterExeption {
        testFile = new File("testFile.txt");
    }

    @After
    public void ternDown() {
        testFile.delete();
    }

    @Test
    public void shouldPrintMessageInFile() throws PrinterExeption, IOException {
        printer = new FilePrinter(testFile.getPath(), Charset.defaultCharset());
        printer.print("test string");

        Assert.assertEquals("test string", FileUtils.readFileToString(testFile));
    }

    @Test(expected = PrinterExeption.class)

    public void shouldntPrintMessageInFileWhenWrongFileName() throws PrinterExeption, IOException {
        printer = new FilePrinter("///invalid file path///", Charset.defaultCharset());
        printer.print("test string");
    }

//    @Test(expected = PrinterExeption.class)
//
//    public void shouldntPrintMessageInFileWhenWrongFileCharSet() throws PrinterExeption, IOException {
//        Charset charset = "UTF";
//        printer = new FilePrinter("testFile.txt", charset);
//        printer.print("test string");
//    }


}
