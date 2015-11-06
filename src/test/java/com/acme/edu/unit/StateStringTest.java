package com.acme.edu.unit;

import com.acme.edu.Printers.Printer;
import com.acme.edu.States.StateString;
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
        printer = mock(Printer.ConsolPrinter.class);
        state = new StateString(printer);
    }


    @Test
    public void shouldCallPrinterOneTimeWhenStringCome() {

        state.log("string 1");
        state.flush();
        verify(printer, times(1)).print("string: string 1");
    }

    @Test
    public void shouldCallPrinterTwoTimesWhenTwoDifferentStringsCome() {

        state.log("string 1");
        state.log("string 2");
        state.flush();
        verify(printer, times(1)).print("string: string 1");
        verify(printer, times(1)).print("string: string 2");
    }

    @Test
    public void shouldCallPrinterOneTimesWhenTwoSameStringsCome() {

        state.log("string 1");
        state.log("string 1");
        state.flush();
        verify(printer, times(1)).print("string: string 1 (x2)");

    }

//    @Test
//    public void shouldSaveState() {
//        assertEquals(state, state.swichStateToIntState());
//    }








}
