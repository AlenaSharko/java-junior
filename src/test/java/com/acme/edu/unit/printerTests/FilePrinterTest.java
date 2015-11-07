package com.acme.edu.unit.printerTests;

import com.acme.edu.exeptions.PrinterExeption;
import com.acme.edu.logger.Logger;
import com.acme.edu.printers.FilePrinter;
import com.acme.edu.printers.Printer;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;

/**
 * Test for writing in test file
 */
public class FilePrinterTest {

    Printer printer;
    File testFile;

    @Before
    public void setUp() throws PrinterExeption {
        testFile = new File("ClientFile.txt");
        testFile.delete();
        printer = new FilePrinter();
    }

    @After
    public void ternDown() {
        testFile.delete();
    }

    @Test
    public void shouldPrintMessageInFile() throws PrinterExeption , IOException{

        printer.print("test string");

        Assert.assertEquals("test string", FileUtils.readFileToString(testFile));
    }

}
