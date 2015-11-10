package com.acme.edu.unit.printerTests;

import com.acme.edu.exeptions.PrinterExeption;
import com.acme.edu.logger.Server;
import com.acme.edu.printers.FilePrinter;
import com.acme.edu.printers.NetPrinter;
import com.acme.edu.printers.Printer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.ServerSocket;
import java.nio.charset.Charset;
import java.util.concurrent.Executors;

public class NetPrinterTest {
    private Server server;

    @Before
    public void setUp() {
        Executors.newSingleThreadExecutor().execute(() -> {

            try {
                server = new Server(4444, Charset.defaultCharset(), new ServerSocket(4444, 10),
                        new FilePrinter("test.txt", Charset.defaultCharset()));
                server.start();
            } catch (PrinterExeption | IOException e) {
                e.printStackTrace();
            }
        });
    }

    @After
    public void tearDown() {
        if(server != null) {
            server.stop();
        }

    }

    @Test
    public void shouldSendAndReceive() throws PrinterExeption, IOException {

        Printer sut = new NetPrinter("127.0.0.1", 4444, Charset.defaultCharset(), 2);
        sut.print("1");
        sut.print("2");
        sut.print("3");

    }
}