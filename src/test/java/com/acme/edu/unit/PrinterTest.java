package com.acme.edu.unit;

import com.acme.edu.ConsolPrinter;
import com.acme.edu.Printer;
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
        printer.print("ParamPam");
        assertSysoutContains("ParamPam");
        assertSysoutEquals("ParamPam\n");
    }
}
