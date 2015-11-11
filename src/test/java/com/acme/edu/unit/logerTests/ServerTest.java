package com.acme.edu.unit.logerTests;

import com.acme.edu.exeptions.PrinterExeption;
import com.acme.edu.logger.Logger;
import com.acme.edu.logger.Server;
import com.acme.edu.printers.FilePrinter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ServerTest {

    private Server server;
    private ByteArrayInputStream inputStream;
    private FilePrinter printer;
    BufferedWriter outStream;
    Socket client;

    @Before
    public void setUpServer() throws Exception {

        printer = mock(FilePrinter.class);
        ServerSocket serverSocket = new ServerSocket(2345, 1);
        server = new Server(Charset.defaultCharset(), serverSocket, printer);

        new Thread(() -> {
            try {
                server.start();
            } catch (PrinterExeption printerExeption) {
                printerExeption.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();


        client = new Socket("127.0.0.1", 2345);
        outStream = new BufferedWriter(new OutputStreamWriter(client.getOutputStream(),
                Charset.defaultCharset()));

    }

    @After
    public void tearDown() throws Exception {
        //inputStream.close();

        outStream.close();
        if (server != null) {
            server.stop();
        }
        client.close();
    }

    @Test
    public void shouldCallFilePrinterWithSpecifiedString() throws Exception {
        try {

            outStream.write("test message\n");
            outStream.newLine();
            outStream.flush();

            verify(printer).print("test message");
        } catch (PrinterExeption e) {
            e.printStackTrace();
            // verify(printer).print("test message");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

