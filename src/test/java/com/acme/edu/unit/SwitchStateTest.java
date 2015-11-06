package com.acme.edu.unit;

import com.acme.edu.Printers.Printer;
import org.junit.Before;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;

/**
 * Test swiching between states
 */
public class SwitchStateTest {

    Printer printer;

    @Before
    public void setUp() {
        printer = mock(Printer.class);
    }


//    @Test
//    public void shouldSwichToInt() {
//        State state = new StateString(printer);
//        State stete1 = mock(StateInt.class);
//        when(state.swichToNewState(any())).thenReturn(stete1);
//
//        assertEquals(stete1,state.swichToNewState(state));
//    }

}
