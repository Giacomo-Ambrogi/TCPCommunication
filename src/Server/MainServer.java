package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MainServer {
    public static void main(String[] args) {
        System.out.println("----- SERVER: Inizio esecuzione!!! -----");

        try {
            ServerSocket server = new ServerSocket(3000);
            System.out.println("SERVER: In attesa della richiesta del cliente!");
            Socket clientSocket = server.accept();
            System.out.println("SERVER: Il client si è connesso!");
        } catch (IOException e) {
            System.err.println("Errore nella connessione con il client!!!");
        }
    }
}