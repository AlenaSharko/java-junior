package com.acme.edu.iteration01;

import com.acme.edu.*;
import com.acme.edu.exeptions.LoggerExeption;
import com.acme.edu.logger.Logger;
import com.acme.edu.printers.*;
import com.acme.edu.states.*;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.*;
import java.util.*;


import org.junit.After;
@Ignore
public class LoggerTest implements SysoutCaptureAndAssertionAbility {
    Logger logger;

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
    public void shouldLogInteger() throws LoggerExeption {
        //region when

        logger.log(1);
        logger.log(0);
        logger.log(-1);
        logger.close();
        //endregion

        //region then
        assertSysoutContains("primitive: ");
        assertSysoutEquals("primitive: " + "0\n");
        //endregion
    }



//    @Test
//    public void shouldLogByte() throws IOException {
//        //region when
//        logger.log((byte)1);
//        logger.log((byte)0);
//        logger.log((byte)-1);
//        logger.close();
//        //endregion
//
//        //region then
//        assertSysoutContains("primitive: ");
//        assertSysoutEquals("primitive: " + "0\n");
//        //endregion
//    }

    @Test
    public void shouldLogChar() throws LoggerExeption {
        //region when
        logger.log('a');
        logger.log('b');
        //endregion

        //region then
        assertSysoutContains("char: ");
        assertSysoutContains("a");
        assertSysoutContains("b");
        //endregion
    }

    @Test
    public void shouldLogBoolean() throws LoggerExeption {
        //region when
        logger.log(true);
        logger.log(false);
        //endregion

        //region then
        assertSysoutContains("primitive: ");
        assertSysoutContains("true");
        assertSysoutContains("false");
        //endregion
    }


    @Test
    public void shouldLogString() throws LoggerExeption {
        //region when
        logger.log("test string 1");
        logger.log("other str 0");
        logger.close();
        //endregion

        //region then
        assertSysoutContains("string: ");
        assertSysoutContains("test string 1");
        assertSysoutContains("other str");
        //endregion
    }


    @Test
    public void shouldLogReference() throws LoggerExeption {
        //region when
        logger.log(new Object());
        //endregion

        //region then
        assertSysoutContains("reference: ");
        assertSysoutContains("@");
        //endregion
    }


}