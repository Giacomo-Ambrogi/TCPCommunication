package Server;

import java.io.*;
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
        try {
            BufferedReader tastiera = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            System.out.println("Messaggio SERVER: " );
            String risposta = tastiera.readLine();

            out.println(risposta);
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