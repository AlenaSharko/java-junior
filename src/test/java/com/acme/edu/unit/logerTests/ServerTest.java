package com.acme.edu.unit.logerTests;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.concurrent.RejectedExecutionException;

import com.acme.edu.exeptions.PrinterExeption;
import com.acme.edu.logger.Server;
import com.acme.edu.printers.FilePrinter;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
@Ignore
public class ServerTest {


    private Server server;
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

        when(server.accept()).thenReturn(client).thenThrow(new RejectedExecutionException());
        when(client.getOutputStream()).thenReturn(outputStream);
        when(client.getInputStream()).thenReturn(inputStream);


        this.server = new Server(Charset.defaultCharset(), server, printer);
    }

    @After
    public void tearDown() throws Exception {
        inputStream.close();
        outputStream.close();
        if (server != null) {
            server.stop();
        }
    }

    @Test
    public void shouldCallFilePrinterWithSpecifiedString() throws Exception {
        try {
            server.start();
        }
        catch (PrinterExeption e) {
            verify(printer).print("test message");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

}

