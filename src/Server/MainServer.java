package Server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class MainServer {
    public static void main(String[] args) {
        System.out.println("----- SERVER: Inizio esecuzione!!! -----");

        try {
            //comunicazione
            ServerSocket serverSocket = new ServerSocket(3000);
            System.out.println("SERVER: In attesa di richieste dei CLIENT!");
            Socket clientSocket = serverSocket.accept();
            System.out.println("SERVER: Il CLIENT si è connesso!");

            //lettura
            InputStream inputStream = clientSocket.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            String messaggio = br.readLine();
            System.out.println("SERVER: Il CLIENT " + clientSocket + "ha scritto il messaggio --> " + messaggio);

            inputStream.close();
            clientSocket.close(); //chiusura data socket
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("----- SERVER: Fine esecuzione!!! -----");
    }
}