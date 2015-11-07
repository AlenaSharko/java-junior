package com.acme.edu.iteration03;

import com.acme.edu.SysoutCaptureAndAssertionAbility;
import com.acme.edu.exeptions.LoggerExeption;
import com.acme.edu.logger.Logger;
import com.acme.edu.printers.ConsolPrinter;
import com.acme.edu.states.StateUnBuffered;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

@Ignore
public class LoggerTest implements SysoutCaptureAndAssertionAbility {
    private Logger logger;

    //region given
    @Before
    public void setUpSystemOut() throws LoggerExeption {
        resetOut();
        captureSysout();
        logger = new Logger(new StateUnBuffered(new ConsolPrinter()));
    }

    //endregion
    @After
    public void tearDown() {
        resetOut();
    }


    @Test
    public void shouldLogIntegersArray() throws LoggerExeption {
        //region when
        logger.log(new int[]{-1, 0, 1});
        //endregion

        //region then
        assertSysoutEquals(
                "primitives array: {-1, 0, 1}" + Logger.SEP);
        //endregion
    }

    @Test
    public void shouldLogIntegersMatrix() throws LoggerExeption {
        //region when
        logger.log(new int[][]{{-1, 0, 1}, {1, 2, 3}, {-1, -2, -3}});
        //endregion

        //region then
        assertSysoutEquals(
                "primitives matrix: {" + Logger.SEP +
                        "{-1, 0, 1}" + Logger.SEP +
                        "{1, 2, 3}" + Logger.SEP +
                        "{-1, -2, -3}" + Logger.SEP +
                        "}" + Logger.SEP);
        //endregion
    }

    @Test
    public void shouldLogIntegersMulitidimentionalArray() throws LoggerExeption {
        //region when
        logger.log(new int[][][][]{{{{0}}}});
        //endregion

        //region then
        assertSysoutEquals(
                "primitives multimatrix: {" + Logger.SEP +
                        "{" + Logger.SEP + "{" + Logger.SEP + "{" + Logger.SEP +
                        "0" + Logger.SEP +
                        "}" + Logger.SEP + "}" + Logger.SEP + "}" + Logger.SEP +
                        "}" + Logger.SEP
        );
        //endregion
    }

    @Test
    public void shouldLogStringsWithOneMethodCall() throws LoggerExeption {
        //region when
        logger.log("str1", "string 2", "str 3");
        //endregion

        //region then
        assertSysoutContains("str1" + Logger.SEP + "string 2" + Logger.SEP + "str 3");
        //endregion
    }

    @Test
    public void shouldLogIntegersWithOneMethodCall() throws LoggerExeption {
        //region when
        logger.log(-1, 0, 1, 3);
        //endregion

        //region then
        assertSysoutContains("3");
        //endregion
    }

    @Test
    public void shouldCorrectDealWithIntegerOverflowWhenOneMethodCall() throws LoggerExeption {
        //region when
        logger.log(1);
        logger.log("str 0");
        logger.log(Integer.MAX_VALUE - 10);
        logger.log(11);
        logger.close();
        //endregion

        //region then
        assertSysoutContains(1 + "");
        assertSysoutContains("str 0");
        assertSysoutContains((Integer.MAX_VALUE - 10) + "");
        assertSysoutContains(11 + "");
        //endregion
    }

//
}