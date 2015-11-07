package com.acme.edu.unit.stateTests;

import com.acme.edu.exeptions.LoggerExeption;
import com.acme.edu.exeptions.PrinterExeption;
import com.acme.edu.printers.ConsolPrinter;
import com.acme.edu.printers.Printer;
import com.acme.edu.states.StateUnBuffered;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Test all states expect String and Integer State
 */
public class StateDefaultTest {
    StateUnBuffered state;
    Printer printer;


    @Before
    public void setUp() {
        printer = mock(ConsolPrinter.class);
        state = new StateUnBuffered(printer);
    }


    @Test
    public void shouldCallPrinterWhenDifferentStatesCome() throws PrinterExeption{
        Object object = new Object();
        boolean dummy = true;

        state.log("char: " + 'a');
        state.log("reference: " + "@" + object);
        state.log("primitive: " + dummy);

        verify(printer, times(1)).print("char: " + 'a');
        verify(printer, times(1)).print("reference: " + "@" + object);
        verify(printer, times(1)).print("primitive: " + true);

    }


    @Test
    public void shouldntChengeStatw() throws PrinterExeption {

        Assert.assertEquals(state, state.swichToNewState(state));

    }

}
