package Server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.Buffer;

public class MainServer {
    public static void main(String[] args) {
        System.out.println("----- SERVER: Inizio esecuzione!!! -----");

        Server server = new Server(3000);

        System.out.println("SERVER: In attesa di richieste dei CLIENT!");
        server.attendi();
        System.out.println("SERVER: Il CLIENT si è connesso!");

        server.leggi();

        server.scrivi();

        server.chiudi();

        System.out.println("----- SERVER: Fine esecuzione!!! -----");
    }
}