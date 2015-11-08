package com.acme.edu.logger;

import com.acme.edu.exeptions.PrinterExeption;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;

/**
 * Accept message and print in file
 */
public class Server {

    private int port;
    Charset charSet;
    ObjectOutputStream objStream;
    Socket client;
    private static final String BED = "<<Error>>";
    private static final String GOOD = "<<Error>>";

    /**
     * Constructor which allow set port and charset
     */
    public Server(int port, Charset charSet) {
        this.port = port;
        this.charSet = charSet;
    }

    /**
     * Start server working
     * Print message on file
     */
    public void start() throws PrinterExeption , IOException {
        try (ServerSocket server = new ServerSocket(port)) {
            client = server.accept();

            DataInputStream inStream = new DataInputStream(client.getInputStream());
            while (!(inStream.readUTF()).isEmpty()) {
                try (BufferedWriter outStream = new BufferedWriter
                        (new OutputStreamWriter(client.getOutputStream(), charSet))) {
                    outStream.write(inStream.readUTF());
                }
            }

            notificationIfAllright(client);


        } catch (IOException ex) {
            notificationIfAllbed(client, ex);
        }
    }

    private void notificationIfAllright(Socket socket) throws IOException {
        objStream = new ObjectOutputStream(socket.getOutputStream());
        objStream.writeUTF(GOOD);
        objStream.close();
    }

    private void notificationIfAllbed(Socket socket, Exception ex) throws IOException {
        objStream = new ObjectOutputStream(socket.getOutputStream());
        objStream.writeUTF(BED);
        objStream.writeObject(ex);
        objStream.close();
    }

}



