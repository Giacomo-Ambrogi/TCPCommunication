package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

    private String nome;
    private String colore;
    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;

    public Client(String nome) {
        this.nome = nome;
    }

    public Client(String nome, String colore) {
        this.nome = nome;
        this.colore = colore;
    }

    /*
     * CONNESSIONE AL SERVER
     * new Socket(nomeServer, portaServer) tenta di connettersi al server.
     * È una chiamata BLOCCANTE: aspetta finché la connessione non viene accettata.
     * Se il server non esiste o non risponde, lancia una IOException.
     * Una volta connesso, inizializza reader e writer per comunicare.
     * Ritorna 0 se la connessione ha successo, -1 in caso di errore.
     */
    public int connetti(String nomeServer, int portaServer) {
        try {
            socket = new Socket(nomeServer, portaServer);
            System.out.println("CLIENT [" + nome + "]: Connesso a " + nomeServer + ":" + portaServer);
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream(), true); // autoFlush = true
            return 0;
        } catch (IOException e) {
            System.err.println("CLIENT ERRORE - Impossibile connettersi: " + e.getMessage());
            return -1;
        }
    }

    /*
     * INVIO RICHIESTA
     * println() invia il messaggio al server aggiungendo '\n' alla fine.
     * Il '\n' è fondamentale: il server usa readLine() che aspetta proprio
     * quel carattere per sapere che il messaggio è terminato.
     */
    public void scrivi(String messaggio) {
        if (writer != null) {
            writer.println(messaggio);
            System.out.println("CLIENT [" + nome + "]: Inviato al server -> \"" + messaggio + "\"");
        } else {
            System.err.println("CLIENT ERRORE - Writer non inizializzato.");
        }
    }

    /*
     * LETTURA RISPOSTA
     * readLine() aspetta una riga intera dal server (fino al '\n').
     * È BLOCCANTE: il client si ferma qui finché il server non risponde.
     * Ritorna null se il server ha chiuso la connessione senza rispondere.
     */
    public String leggi() {
        try {
            String risposta = reader.readLine();
            if (risposta == null) {
                System.out.println("CLIENT [" + nome + "]: Il server ha chiuso la connessione.");
                return null;
            }
            System.out.println("CLIENT [" + nome + "]: Ricevuto dal server -> \"" + risposta + "\"");
            return risposta;
        } catch (IOException e) {
            System.err.println("CLIENT ERRORE - Errore durante la lettura: " + e.getMessage());
            return null;
        }
    }

    /*
     * CHIUSURA COMUNICAZIONE
     * Chiude reader, writer e il socket verso il server.
     * L'ordine è importante: prima gli stream, poi il socket.
     * Dopo questa chiamata il client non può più comunicare col server.
     */
    public void chiudi() {
        try {
            if (reader != null) {
                reader.close();
                reader = null;
            }
            if (writer != null) {
                writer.close();
                writer = null;
            }
            if (socket != null && !socket.isClosed()) {
                socket.close();
                socket = null;
            }
            System.out.println("CLIENT [" + nome + "]: Connessione chiusa.");
        } catch (IOException e) {
            System.err.println("CLIENT ERRORE - Errore durante la chiusura: " + e.getMessage());
        }
    }
}