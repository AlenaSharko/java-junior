package com.acme.edu.unit;

import com.acme.edu.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Test for chenging states
 */
public class SwichStatesTest {


    Printer printer;

    @Before
    public void setUp() {
        printer = mock(ConsolPrinter.class);

    }

    @Test
    public void ShouldReturnIntStateWhenBeforeWasStringState() {
        //StateString stubS = new StateString(printer);
        StateString stubS = mock(StateString.class);
        //assertEquals(new StateInt(printer), stubS.swichStateToStringState());
        when(stubS.swichStateToStringState()).thenReturn(new StateInt(printer));
    }

    @Test
    public void ShouldReturnStringStateWhenBeforeWasStringState() {
        StateString stub = mock(StateString.class);
        when(stub.swichStateToIntState()).thenReturn(new StateString(printer));
    }

    @Test
    public void ShouldReturnStringStateWhenBeforeWasIntState() {
        StateInt stub = mock(StateInt.class);
        when(stub.swichStateToStringState()).thenReturn(new StateInt(printer));
    }

    @Test
    public void ShouldReturnIntStateWhenBeforeWasStringInt() {
        StateInt stub = mock(StateInt.class);
        when(stub.swichStateToIntState()).thenReturn(new StateInt(printer));
    }


}
