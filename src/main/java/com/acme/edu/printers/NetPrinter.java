package com.acme.edu.printers;

import com.acme.edu.exeptions.PrinterExeption;
import com.acme.edu.logger.Logger;
import java.io.*;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.*;


/**
 * Print message in remote file
 */
public class NetPrinter implements Printer {

    private String addressIP;
    private int bufferSize;
    private int port;
    private Charset charSet;
    private List<String> buffer = new LinkedList<>();
    private static final String BED = "<<Error>>";

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
        } else {
            flush();
            buffer.add(message + Logger.SEP);
        }
    }

    private void flush() throws PrinterExeption {
        try (Socket socket = new Socket(addressIP, port);
             BufferedWriter outStream = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), charSet))) {
            Collections.sort(buffer, (o1, o2) -> {
                if(o1.contains("ERROR")){
                    return 1;
                } else {
                    return -1;
                }
            });
            outStream.write(buffer.toString());
            chekServer(socket);
        } catch (IOException e) {
            throw new PrinterExeption("cant send message to server");
        }
    }



    private void chekServer(Socket socket) throws PrinterExeption {
        try(ObjectInputStream inStream = new ObjectInputStream(socket.getInputStream())){
            while (!inStream.readUTF().isEmpty()){
                if(inStream.readUTF().equals(BED)) {
                    throw (PrinterExeption) inStream.readObject();
                }
            }

        } catch (IOException | ClassNotFoundException ex) {
            throw new PrinterExeption("Cant read server", ex);
        }
    }
}

