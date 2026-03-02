package Client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    private String nome;
    private String colore;
    private Socket socket;

    public Client (String nome) {
        this.nome = nome;
    }

    public Client (String nome, String colore) {
        this.nome = nome;
        this.colore = colore;
    }

    public void connetti (String nomeServer, int portaServer) {
        try {
            this.socket = new Socket(nomeServer, portaServer);
            System.out.println("CLIENT: Il CLIENT si è connesso al SERVER (porta: " + portaServer + ")");
        } catch (IOException e) {
            System.err.println("Errore di connessione: " + e.getStackTrace());
        }
    }

    public void scrivi() {
        try {
            String messaggio = "";
            BufferedReader tastiera = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Scrivi un messaggio per il SERVER: ");
            messaggio = tastiera.readLine();

            System.out.println("CLIENT: Il CLIENT ha inviato un messaggio");
        } catch (IOException e) {
            System.err.println("Errore di connessione: " + e.getStackTrace());
        }
    }

    public void leggi() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String rispostaServer = br.readLine();
            System.out.println("Risposta SERVER: " + rispostaServer);
        } catch (IOException e) {
            System.err.println("Errore di lettura (lato Client): " + e.getStackTrace());
        }
    }

    public void chiudi() {

    }
}