package com.acme.edu.unit;

import com.acme.edu.Printers.Printer;
import com.acme.edu.States.StateUnBuffered;
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
        printer = mock(Printer.ConsolPrinter.class);
        state = new StateUnBuffered(printer);
    }


    @Test
    public void shouldCallPrinterWhenDifferentStatesCome() {
        Object object = new Object();
        boolean dummy = true;

        state.log("char: " + 'a');
        state.log("reference: " + "@" + object);
        state.log("primitive: " + dummy);

        verify(printer, times(1)).print("char: " + 'a');
        verify(printer, times(1)).print("reference: " + "@" + object);
        verify(printer, times(1)).print("primitive: " + true);

    }
}
