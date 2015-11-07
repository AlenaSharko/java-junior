package com.acme.edu.logger;

import java.io.DataInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Accept message and print in file
 */
public class Server {

    /**
     * compound with client
     */
    public static void main(String[] args) {


        try {
            ServerSocket socket = new ServerSocket(127);

            Socket client = socket.accept();

            DataInputStream stream = new DataInputStream(client.getInputStream());
            String readLine;
            while ((readLine = stream.readUTF()) != null) {
                try(FileWriter file = new FileWriter("ServerFile")) {

                    file.write(readLine);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


