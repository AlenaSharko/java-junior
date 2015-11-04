package com.acme.edu.unit;

import com.acme.edu.ConsolPrinter;
import com.acme.edu.Printer;
import com.acme.edu.StateDefault;
import com.acme.edu.StateString;
import org.junit.Before;
import org.junit.Test;

import java.util.Objects;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Test all states expect String and Integer State
 */
public class StateDefaultTest {
    StateDefault state;
    Printer printer;

    @Before
    public void setUp() {
        printer = mock(ConsolPrinter.class);
        state = new StateDefault(printer);
    }


    @Test
    public void shouldCallPrinterWhenDifferentStatesCome() {
        Object object = new Object();
        boolean dummy = true;

        state.log("char: " + 'w');
        state.log("reference: " + "@" + object);
        state.log("primitive: " + dummy);

        verify(printer, times(1)).print("char: " + 'w');
        verify(printer, times(1)).print("reference: " + "@" + object.toString());
        verify(printer, times(1)).print("primitive: " + true);

    }
}
