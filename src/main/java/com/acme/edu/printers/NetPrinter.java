package com.acme.edu.printers;

import com.acme.edu.exeptions.PrinterExeption;
import com.acme.edu.logger.Logger;
import java.io.*;
import java.net.Socket;
import java.nio.charset.Charset;

/**
 * Print message in remote file
 */
public class NetPrinter implements Printer {

    private String addressIP;
    private int port;
    private Charset charSet;
    private StringBuilder buffer = new StringBuilder();
    private int bufferCounter = 0;

    /**
     * Constructon which allow set charset port and ip addderss
     */
    public NetPrinter(String addressIP, int port, Charset charSet) {
        this.addressIP = addressIP;
        this.port = port;
        this.charSet = charSet;
    }

    /**
     * Send message to server
     */
    @Override
    public void print(String message) throws PrinterExeption {
        if (bufferCounter <= 50) {
            buffer.append(message).append(Logger.SEP);
            bufferCounter++;
        } else {
            flush();
            buffer.append(message).append(Logger.SEP);
        }


    }

    private void flush() throws PrinterExeption {
        try (Socket socket = new Socket(addressIP, port);
             BufferedWriter outStream = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), charSet))) {
            outStream.write(buffer.toString());
            bufferCounter = 0;
            chekServer(socket);
        } catch (IOException e) {
            throw new PrinterExeption("cant send message to server");
        }
    }

    private void chekServer(Socket socket) throws PrinterExeption {
        try(ObjectInputStream inStream = new ObjectInputStream(socket.getInputStream())){
            while (!inStream.readUTF().isEmpty()){
                if(inStream.readUTF().equals("<<Error>>")) {
                    throw (PrinterExeption) inStream.readObject();
                }
            }

        } catch (IOException | ClassNotFoundException ex) {
            throw new PrinterExeption("Cant read server", ex);
        }
    }
}

