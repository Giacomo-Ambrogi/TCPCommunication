package Client;

import java.io.IOException;
import java.net.Socket;

public class MainClient {
    public static void main(String[] args) {
        System.out.println("----- CLIENT: Avvio del client!!! -----");

        try {
            Socket socket = new Socket("localhost", 3000);
        } catch (IOException e) {
            System.err.println("Errore nella connessione con il server!!!");
        }
    }
}