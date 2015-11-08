package com.acme.edu.printers;


import com.acme.edu.exeptions.PrinterExeption;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Print message in remote file
 */
public class NetPrinter implements Printer {

    /**
     * Send message to server
     */
    @Override
    public void print(String message) throws PrinterExeption {

        try (Socket socket = new Socket("127.0.0.1", 127);
             DataOutputStream outStream = new DataOutputStream(socket.getOutputStream())) {
            outStream.writeUTF(message);
            socket.getInputStream();

        } catch (IOException e) {
            throw new PrinterExeption("cant send message to server");
        }

    }
}
