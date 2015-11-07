package com.acme.edu.iteration03;

import com.acme.edu.*;
import com.acme.edu.loger.Logger;
import com.acme.edu.printers.ConsolPrinter;
import com.acme.edu.printers.FilePrinter;
import com.acme.edu.printers.NetPrinter;
import com.acme.edu.printers.Printer;
import com.acme.edu.states.State;
import com.acme.edu.states.StateUnBuffered;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

@Ignore
public class LoggerTest implements SysoutCaptureAndAssertionAbility {
    private Logger logger;
    //region given
    @Before
    public void setUpSystemOut() throws IOException {
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
    public void shouldLogIntegersArray() throws IOException {
        //region when
        logger.log(new int[] {-1, 0, 1});
        //endregion

        //region then
        assertSysoutEquals(
                "primitives array: {-1, 0, 1}\n"
        );
        //endregion
    }

    @Test
    public void shouldLogIntegersMatrix() throws IOException {
        //region when
        logger.log(new int[][] {{-1, 0, 1}, {1, 2, 3}, {-1, -2, -3}});
        //endregion

        //region then
        assertSysoutEquals(
                "primitives matrix: {\n" +
                        "{-1, 0, 1}\n" +
                        "{1, 2, 3}\n" +
                        "{-1, -2, -3}\n" +
                        "}\n"
        );
        //endregion
    }

    @Test
    public void shouldLogIntegersMulitidimentionalArray() throws IOException {
        //region when
        logger.log(new int[][][][] {{{{0}}}});
        //endregion

        //region then
        assertSysoutEquals(
            "primitives multimatrix: {\n" +
                "{\n" + "{\n" + "{\n" +
                    "0\n" +
                "}\n" + "}\n" + "}\n" +
            "}\n"
        );
        //endregion
    }

    @Test
    public void shouldLogStringsWithOneMethodCall() throws IOException {
        //region when
        logger.log("str1", "string 2", "str 3");
        //endregion

        //region then
        assertSysoutContains("str1\nstring 2\nstr 3");
        //endregion
    }

    @Test
    public void shouldLogIntegersWithOneMethodCall() throws IOException {
        //region when
        logger.log(-1, 0, 1, 3);
        //endregion

        //region then
        assertSysoutContains("3");
        //endregion
    }

    @Test
    public void shouldCorrectDealWithIntegerOverflowWhenOneMethodCall() throws IOException {
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