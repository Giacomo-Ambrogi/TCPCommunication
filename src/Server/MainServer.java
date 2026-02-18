package Server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class MainServer {
    public static void main(String[] args) {
        System.out.println("----- SERVER: Inizio esecuzione!!! -----");

        try {
            ServerSocket serverSocket = new ServerSocket(3000);
            Socket clientSocket = serverSocket.accept();

            InputStream inputStream = clientSocket.getInputStream();
            OutputStream outputStream = clientSocket.getOutputStream();

            DataInputStream dataInputStream = new DataInputStream(inputStream);
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);

            String clientMessage = dataInputStream.readUTF();
            dataOutputStream.writeUTF("Server response: " + clientMessage);

            inputStream.close();
            outputStream.close();
            clientSocket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("----- SERVER: Fine esecuzione!!! -----");
    }
}