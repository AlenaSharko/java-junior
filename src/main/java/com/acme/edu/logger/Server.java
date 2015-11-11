package com.acme.edu.logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;

import com.acme.edu.exeptions.PrinterExeption;
import com.acme.edu.printers.Printer;

/**
 * Accept message and print in file
 */
public class Server {
    private enum State {
        GOOD("<<OK>>"), BAD("<<Error>>");

        private final String name;

        State(String name) {
            this.name = name;
        }

        void notification(ObjectOutputStream objStream, Object... objects) throws IOException {
            objStream.writeObject(name);
            for (Object object : objects) {
                objStream.writeObject(object);
            }
        }
    }

    private static final int MAX_CLIENTS = 10;
    private final ExecutorService executorService = Executors.newFixedThreadPool(MAX_CLIENTS);
    private Charset charSet;
    private ServerSocket server;
    private Printer printer;


    /**
     * Constructor which allow set port
     */
    public Server(Charset charSet, ServerSocket server, Printer printer) {
        this.charSet = charSet;
        this.server = server;
        this.printer = printer;
    }

    /**
     * Start server working
     * Print message on file
     */
    public void start() throws PrinterExeption, IOException {
        while (!executorService.isShutdown()) {
            try {
                final Socket client = server.accept();
                executorService.execute(() -> {
                    try {
                        BufferedReader inStream = new BufferedReader(new InputStreamReader(client.getInputStream(), charSet));
                        ObjectOutputStream outStream = new ObjectOutputStream(client.getOutputStream());
                        try {
                            while (!Thread.currentThread().isInterrupted()) {
                                String stream = inStream.readLine();
                                if (stream == null || stream.isEmpty()) {
                                    break;
                                }
                                outStream.writeObject(stream);
                                outStream.writeObject("");
                                outStream.flush();
                                printer.print(stream);
                            }
                            State.GOOD.notification(outStream);
                        }
                        catch (IOException | PrinterExeption e) {
                            State.BAD.notification(outStream, e);
                        }
                    }
                    catch (IOException e) {
                        throw new RejectedExecutionException(e);
                    }
                });
            }
            catch (RejectedExecutionException ex) {
                if (!executorService.isShutdown()) {
                    throw new PrinterExeption(ex.getMessage(), ex);
                }
            }
        }
    }

    /**
     * Stop servise
     */
    public void stop() {
        executorService.shutdownNow();
    }
}



