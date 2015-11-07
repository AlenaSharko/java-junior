package com.acme.edu.iteration02;

import com.acme.edu.SysoutCaptureAndAssertionAbility;
import com.acme.edu.exeptions.LoggerExeption;
import com.acme.edu.logger.Logger;
import com.acme.edu.printers.ConsolPrinter;
import com.acme.edu.states.StateUnBuffered;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;

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
    public void shouldLogSequentIntegersAsSum() throws LoggerExeption {
        //region when
        logger.log("str 1");
        logger.log(1);
        logger.log(2);
        logger.log("str 2");
        logger.log(0);
        logger.close();
        //endregion

        //region then
        assertSysoutEquals(
                "string: str 1\n" +
                        "primitive: " + "3\n" +
                        "string: str 2\n" +
                        "primitive: " + "0\n"
        );
        //endregion
    }

    @Test
    public void shouldLogCorrectlyIntegerOverflowWhenSequentIntegers() throws LoggerExeption {
        //region when
        logger.log("str 1");
        logger.log(10);
        logger.log(Integer.MAX_VALUE);
        logger.log("str 2");
        logger.log(0);
        logger.close();
        //endregion

        //region then
        assertSysoutEquals(
                "string: str 1\n" +
                        "primitive: 10\n" +
                        "primitive: " + Integer.MAX_VALUE + "\n" +
                        "string: str 2\n" +
                        "primitive: 0\n"
        );
        //endregion
    }

//    @Test
//
//    public void shouldLogCorrectlyByteOverflowWhenSequentBytes() {
//        //region when
//        logger.log("str 1");
//        logger.log((byte)10);
//        logger.log(Byte.MAX_VALUE);
//        logger.log("str 2");
//        logger.log(0);
//        logger.close();
//        //endregion
//
//        //region then
//        assertSysoutEquals(
//            "string: str 1\n" +
//            "primitive: 10\n" + "primitive: " +
//        Byte.MAX_VALUE + "\n" +
//            "string: str 2\n" +
//            "primitive: 0\n"
//        );
//        //endregion
//    }

    @Test
    public void shouldLogSameSubsequentStringsWithoutRepeat() throws LoggerExeption {
        //region when
        logger.log("str 1");
        logger.log("str 2");
        logger.log("str 2");
        logger.log(0);
        logger.log("str 2");
        logger.log("str 3");
        logger.log("str 3");
        logger.log("str 3");
        logger.close();
        //endregion

        //region then
        assertSysoutEquals(
                "string: str 1\n" +
                        "string: str 2 (x2)\n" +
                        "primitive: 0\n" +
                        "string: str 2\n" +
                        "string: str 3 (x3)\n"
        );
        //endregion
    }


}