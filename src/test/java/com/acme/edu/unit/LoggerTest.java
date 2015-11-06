package com.acme.edu.unit;

import com.acme.edu.Loger.Logger;
import com.acme.edu.States.State;
import org.junit.Before;
import org.junit.Test;

import java.util.Objects;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyVararg;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Test Logger
 */
public class LoggerTest {


    State state;
    Logger logger;

    @Before
    public void setUp() {
        state = mock(State.class);
    }

    @Test
    public void ShouldCallLogIntegerStateMethodWhenIntagerValueCome() {

        when(state.swichToNewState(any())).thenReturn(state);
        logger = new Logger(state);

        logger.log(5);
        verify(state).log(5 + "");
    }

    @Test
    public void ShouldCallLogStingStateMethodWhenStringValueCome() {
        when(state.swichToNewState(any())).thenReturn(state);
        logger = new Logger(state);

        logger.log("5");
        verify(state).log("5");
    }

    @Test
    public void ShouldCallLogUnbufferedStateMethodWhenBoolValueCome() {
        when(state.swichToNewState(any())).thenReturn(state);
        logger = new Logger(state);

        logger.log(true);
        verify(state).log("primitive: true");
    }

    @Test
    public void ShouldCallLogUnbufferedStateMethodWhenCharValuecome() {
        when(state.swichToNewState(any())).thenReturn(state);
        logger = new Logger(state);

        logger.log('a');
        verify(state).log("char: a");
    }

    @Test
    public void ShouldCallLogUnbufferedStateMethodWhenObjectValueCome() {
        when(state.swichToNewState(any())).thenReturn(state);
        logger = new Logger(state);
        Object dummy = new Object();

        logger.log(dummy);
        verify(state).log("reference: @" + dummy.toString());
    }


}
