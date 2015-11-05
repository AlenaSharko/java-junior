package com.acme.edu.unit;

import com.acme.edu.ConsolPrinter;
import com.acme.edu.Printer;
import com.acme.edu.StateInt;
import com.acme.edu.StateString;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Testing state string values
 */
public class StateStringTest {
    StateString state;
    Printer printer;

    @Before
    public void setUp() {
        printer = mock(ConsolPrinter.class);
        state = new StateString(printer);
    }


    @Test
    public void shouldCallPrinterOneTimeWhenStringCome() {

        state.log("string 2");
        state.flush();
        verify(printer, times(1)).print("string: string 2");
    }

    @Test
    public void shouldCallPrinterTwoTimesWhenTwoDifferentStringsCome() {

        state.log("string 2");
        state.log("string 3");
        state.flush();
        verify(printer, times(1)).print("string: string 2");
        verify(printer, times(1)).print("string: string 3");
    }

    @Test
    public void shouldCallPrinterOneTimesWhenTwoSameStringsCome() {

        state.log("string 2");
        state.log("string 2");
        state.flush();
        verify(printer, times(1)).print("string: string 2 (x2)");

    }

    @Test
    public void shouldSaveState() {
        assertEquals(state, state.swichStateToIntState());
    }








}
