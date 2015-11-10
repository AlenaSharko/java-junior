package com.acme.edu.unit.logerTests;

import com.acme.edu.logger.Server;
import com.acme.edu.printers.FilePrinter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.awt.print.PrinterException;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;

import static org.mockito.Mockito.*;

/**
 * Created by alena
 */

public class ServerTest {

/*
    Server server;
    private ByteArrayOutputStream outputStream;
    private ByteArrayInputStream inputStream;
    private FilePrinter printer;

    @Before
    public void setUpServer() throws Exception {
        ServerSocket stubServerSocket = mock(ServerSocket.class);
        Socket socket = mock(Socket.class);

        printer = mock(FilePrinter.class);

        outputStream = new ByteArrayOutputStream();
        inputStream = new ByteArrayInputStream("test message".getBytes());

        when(stubServerSocket.accept()).thenReturn(socket).thenReturn(null);
        when(socket.getOutputStream()).thenReturn(outputStream);
        when(socket.getInputStream()).thenReturn(inputStream);

        server = new Server(4444, Charset.defaultCharset()) {

            private ServerSocket createSocket() throws IOException {
                return stubServerSocket;
            }

            private FilePrinter createFilePrinter() throws PrinterException {
                return printer;
            }
        };
    }

    @After
    public void tearDown() throws Exception {
        inputStream.close();
        outputStream.close();
    }

    @Test
    public void shouldCallFilePrinterWithSpecifiedString() throws Exception {
        try {
            server.start();
        } catch (Exception e) {

        }
        verify(printer).print("test message");
    }

*/
}

