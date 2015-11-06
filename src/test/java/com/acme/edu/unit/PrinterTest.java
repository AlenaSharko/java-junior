package com.acme.edu.unit;

import com.acme.edu.Printers.ConsolPrinter;
import com.acme.edu.Printers.Printer;
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
        printer.print("TestString");
        assertSysoutContains("TestString");
        assertSysoutEquals("TestString\n");
    }
}
