package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private int porta;

    public Server (int porta) {
        this.porta = porta;
    }

    public Socket attendi() {
        try {
            serverSocket = new ServerSocket(3000);
            clientSocket = serverSocket.accept();
        } catch (IOException e) {
            System.err.println("Errore di connessione: " + e.getStackTrace());
        }
        return clientSocket;
    }

    public void scrivi() {
        BufferedReader tastiera = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.println("Messaggio SERVER: " );
            String risposta = tastiera.readLine();
        } catch (IOException e) {
            System.err.println("Errore di scrittura: " + e.getStackTrace());
        }
    }

    public void leggi() {
        try {
            InputStream inputStream = clientSocket.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String messaggio = br.readLine();
            System.out.println("SERVER: Il CLIENT " + clientSocket + "ha scritto il messaggio --> " + messaggio);
        } catch (IOException e) {
            System.err.println("Errore di lettura (lato Server): " + e.getStackTrace());
        }
    }

    public void chiudi() {

    }

    public void termina() {

    }
}