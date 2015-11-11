package com.acme.edu.printers;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import com.acme.edu.exeptions.PrinterExeption;
import com.acme.edu.logger.Logger;

/**
 * Print message in remote file
 */
public class NetPrinter implements Printer {

    private String addressIP;
    private int bufferSize;
    private int port;
    private Charset charSet;

    private List<String> buffer = new LinkedList<>();
    private static final String BAD = "<<Error>>";

    /**
     * Constructon which allow set charset port and ip addderss
     */
    public NetPrinter(String addressIP, int port, Charset charSet, int bufferSize) {
        this.addressIP = addressIP;
        this.port = port;
        this.charSet = charSet;
        this.bufferSize = bufferSize;
    }

    /**
     * Send message to server
     */
    @Override
    public void print(String message) throws PrinterExeption {
        if (buffer.size() <= bufferSize) {
            buffer.add(message + Logger.SEP);
        }
        else {
            flush();
            buffer.add(message + Logger.SEP);
        }
    }

    private void flush() throws PrinterExeption {
        try (Socket socket = new Socket(addressIP, port);
             BufferedWriter outStream = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), charSet))) {
            Collections.sort(buffer, (o1, o2) -> {
                if (o1.contains("ERROR")) {
                    return 1;
                }
                else {
                    return -1;
                }
            });
            outStream.write(buffer.toString());
            outStream.write("");
            outStream.flush();
            chekServer(socket);
        }
        catch (IOException e) {
            throw new PrinterExeption("cant send message to server", e);
        }
    }

    private void chekServer(Socket socket) throws PrinterExeption {
        try (ObjectInputStream inStream = new ObjectInputStream(socket.getInputStream())) {
            while (true) {
                String stream = inStream.readObject().toString();
                if (stream.isEmpty()) {
                    break;
                }
                else {
                    if (stream.equals(BAD)) {
                        throw (PrinterExeption) inStream.readObject();
                    }

                }
            }
        }
        catch (IOException | ClassNotFoundException ex) {
            throw new PrinterExeption("Cant read server", ex);
        }
    }
}

