package Client;

import java.io.*;
import java.net.Socket;

public class MainClient {
    public static void main(String[] args) {
        System.out.println("----- CLIENT: Avvio del client!!! -----");

        Client client = new Client("Giacomo", "rosso");

        client.connetti("localhost", 3000);

        client.scrivi();

        client.leggi();

        client.chiudi();

        System.out.println("----- CLIENT: Fine esecuzione!!! -----");
    }
}