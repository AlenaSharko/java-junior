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
        ServerSocket server = mock(ServerSocket.class);
        Socket client = mock(Socket.class);

        printer = mock(FilePrinter.class);

        outputStream = new ByteArrayOutputStream();
        inputStream = new ByteArrayInputStream("test message".getBytes());

        when(server.accept()).thenReturn(client).thenReturn(null);
        when(client.getOutputStream()).thenReturn(outputStream);
        when(client.getInputStream()).thenReturn(inputStream);

        this.server = new Server(4444, Charset.defaultCharset());
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
            e.printStackTrace();
        }
        verify(printer).print("test message");
    }
*/
}

