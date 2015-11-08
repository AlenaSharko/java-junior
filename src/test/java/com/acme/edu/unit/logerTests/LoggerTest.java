package com.acme.edu.unit.logerTests;

import com.acme.edu.exeptions.LoggerExeption;
import com.acme.edu.exeptions.PrinterExeption;
import com.acme.edu.logger.Logger;
import com.acme.edu.states.State;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

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
    public void ShouldCallLogIntegerStateMethodWhenIntagerValueCome() throws PrinterExeption, LoggerExeption {

        when(state.swichToNewState(any())).thenReturn(state);
        logger = new Logger(state);

        logger.log(5);
        verify(state).log(5 + "");
    }

    @Test
    public void ShouldCallLogStingStateMethodWhenStringValueCome() throws PrinterExeption, LoggerExeption {
        when(state.swichToNewState(any())).thenReturn(state);
        logger = new Logger(state);

        logger.log("5");
        verify(state).log("5");
    }

    @Test
    public void ShouldCallLogUnbufferedStateMethodWhenBoolValueCome() throws PrinterExeption, LoggerExeption {
        when(state.swichToNewState(any())).thenReturn(state);
        logger = new Logger(state);

        logger.log(true);
        verify(state).log("primitive: true");
    }

    @Test
    public void ShouldCallLogUnbufferedStateMethodWhenCharValuecome() throws PrinterExeption, LoggerExeption {
        when(state.swichToNewState(any())).thenReturn(state);
        logger = new Logger(state);

        logger.log('a');
        verify(state).log("char: a");
    }

    @Test
    public void ShouldCallLogUnbufferedStateMethodWhenObjectValueCome() throws PrinterExeption, LoggerExeption {
        when(state.swichToNewState(any())).thenReturn(state);
        logger = new Logger(state);
        Object dummy = new Object();

        logger.log(dummy);
        verify(state).log("reference: @" + dummy.toString());
    }

    @Test
    public void ShouldCallLogUnbufferedStateMethodWhenArrayCome() throws PrinterExeption, LoggerExeption {
        when(state.swichToNewState(any())).thenReturn(state);
        logger = new Logger(state);
        int[] arr = {1, 2, 3};

        logger.log(arr);
        verify(state).log("primitives array: {1, 2, 3}");
    }

    @Test
    public void ShouldCallLogUnbufferedStateMethodWhenVarragsArrayCome() throws PrinterExeption, LoggerExeption {
        when(state.swichToNewState(any())).thenReturn(state);
        logger = new Logger(state);

        logger.log(1, 2, 3);
        verify(state).log("primitives array: {1, 2, 3}");
    }

    @Test
    public void ShouldCallLogUnbufferedStateMethodWhenVarragsStringArrayCome() throws PrinterExeption, LoggerExeption {
        when(state.swichToNewState(any())).thenReturn(state);
        logger = new Logger(state);

        logger.log("test str1", "test str2", "test str3");
        verify(state).log("primitives array: test str1" + Logger.SEP + "test str2" + Logger.SEP + "test str3" + Logger.SEP);
    }

    @Test
    public void ShouldCallLogUnbufferedStateMethodWhenMatrixCome() throws PrinterExeption, LoggerExeption {
        when(state.swichToNewState(any())).thenReturn(state);
        logger = new Logger(state);
        int[][] arr = new int[][]{{1, 1, 1}, {0, 0, 0}, {-1, -1, -1}};

        logger.log(arr);
        verify(state).log("primitives matrix: {\n" +
                "{1, 1, 1}" + Logger.SEP +
                "{0, 0, 0}" + Logger.SEP +
                "{-1, -1, -1}" + Logger.SEP +
                "}");
    }


    @Test
    public void ShouldCallLogUnbufferedStateMethodWhenMultiMatrixCome() throws PrinterExeption, LoggerExeption {
        when(state.swichToNewState(any())).thenReturn(state);
        logger = new Logger(state);
        int[][][][] arr = new int[][][][]{{{{1}}}};

        logger.log(arr);
        verify(state).log("primitives multimatrix: {" + Logger.SEP + "{" + Logger.SEP + "{" + Logger.SEP + "{" + Logger.SEP +
                "1" + Logger.SEP + "}" + Logger.SEP + "}" + Logger.SEP + "}" + Logger.SEP + "}");
    }

    @Test(expected = LoggerExeption.class)
    public void shouldNotLogWhenStringValueCome() throws PrinterExeption, LoggerExeption {
        when(state.swichToNewState(any())).thenReturn(state);
        logger = new Logger(state);
        logger.log((String) null);
    }

    @Test(expected = LoggerExeption.class)
    public void shouldNotLogwhenIntValueCome() throws PrinterExeption, LoggerExeption {
        when(state.swichToNewState(any())).thenReturn(state);
        logger = new Logger(state);
        logger.log((Object) null);
    }
}
