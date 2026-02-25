package Client;

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
            OutputStream outputStream = socket.getOutputStream();
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.print("Ciao SERVER!\n");
            out.flush();
            System.out.println("CLIENT: Il CLIENT ha inviato un messaggio");
        } catch (IOException e) {
            System.err.println("Errore di connessione: " + e.getStackTrace());
        }
    }

    public void chiudi() {

    }
}