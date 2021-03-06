package com.acme.edu.unit.printerTests;

import com.acme.edu.exeptions.PrinterExeption;
import com.acme.edu.logger.Logger;
import com.acme.edu.printers.ConsolPrinter;
import com.acme.edu.printers.Printer;
import com.acme.edu.SysoutCaptureAndAssertionAbility;
import org.junit.Before;
import org.junit.Test;

/**
 * Printer Test
 */
public class PrinterTest implements SysoutCaptureAndAssertionAbility {

    Printer printer;


    @Before
    public void setUp() {
        printer= new ConsolPrinter();
    }

    @Test
    public void shouldPrint() {
        captureSysout();
        try{
            printer.print("TestString");
        } catch (PrinterExeption ex){
            ex.printStackTrace();
        }

        assertSysoutContains("TestString");
        assertSysoutEquals("TestString" + Logger.SEP);
    }
}
