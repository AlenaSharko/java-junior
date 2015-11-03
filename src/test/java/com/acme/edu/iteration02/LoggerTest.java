package com.acme.edu.iteration02;

import com.acme.edu.Logger;
import com.acme.edu.SysoutCaptureAndAssertionAbility;
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
    public void setUpSystemOut() throws IOException {
        resetOut();
        captureSysout();
        logger = new Logger();
    }
    //endregion
    @After
    public void tearDown() {
        resetOut();
    }


    @Test
    public void shouldLogSequentIntegersAsSum() throws IOException {
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
    public void shouldLogCorrectlyIntegerOverflowWhenSequentIntegers() {
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
//    @Ignore
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
//
    @Test
    public void shouldLogSameSubsequentStringsWithoutRepeat() throws IOException {
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