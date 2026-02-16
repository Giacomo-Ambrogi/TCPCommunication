package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private ServerSocket serverSocket;
    private Socket clientSocket;
    private BufferedReader reader;
    private PrintWriter writer;
    private int porta;

    public Server(int porta) {
        this.porta = porta;
    }

    /*
     * AVVIO DEL SERVER
     * Crea il ServerSocket sulla porta specificata.
     * Il ServerSocket è il "centralino": resta in ascolto e aspetta i client.
     * Ritorna true se tutto va bene, false se la porta è già occupata o non disponibile.
     */
    public boolean avvia() {
        try {
            serverSocket = new ServerSocket(porta);
            System.out.println("SERVER: Avviato sulla porta " + porta);
            return true;
        } catch (IOException e) {
            System.err.println("SERVER ERRORE - Impossibile avviare sulla porta " + porta + ": " + e.getMessage());
            return false;
        }
    }

    /*
     * CONNESSIONE CON IL CLIENT
     * serverSocket.accept() è una chiamata BLOCCANTE: il server si ferma qui
     * e aspetta finché un client non si connette.
     * Quando arriva un client, crea il Socket dedicato a quella comunicazione
     * e inizializza reader e writer per leggere e scrivere su quel canale.
     * Ritorna il Socket del client, oppure null in caso di errore.
     */
    public Socket attendi() {
        try {
            System.out.println("SERVER: In attesa di una connessione client...");
            clientSocket = serverSocket.accept();
            System.out.println("SERVER: Client connesso -> " + clientSocket.getInetAddress() + ":" + clientSocket.getPort());
            reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            writer = new PrintWriter(clientSocket.getOutputStream(), true); // autoFlush = true: invia subito senza flush() manuale
            return clientSocket;
        } catch (IOException e) {
            System.err.println("SERVER ERRORE - Errore durante l'attesa del client: " + e.getMessage());
            return null;
        }
    }

    /*
     * LETTURA RICHIESTA
     * readLine() legge una riga intera dal client, fino al carattere '\n'.
     * È BLOCCANTE: aspetta finché il client non invia qualcosa.
     * Ritorna null se il client ha chiuso la connessione prima di inviare dati.
     */
    public String leggi() {
        try {
            String messaggio = reader.readLine();
            if (messaggio == null) {
                System.out.println("SERVER: Il client ha chiuso la connessione.");
                return null;
            }
            System.out.println("SERVER: Ricevuto dal client -> \"" + messaggio + "\"");
            return messaggio;
        } catch (IOException e) {
            System.err.println("SERVER ERRORE - Errore durante la lettura: " + e.getMessage());
            return null;
        }
    }

    /*
     * INVIO RISPOSTA
     * println() invia la stringa al client aggiungendo '\n' alla fine.
     * Grazie all'autoFlush=true impostato nel costruttore del PrintWriter,
     * i dati vengono trasmessi immediatamente senza dover chiamare flush().
     */
    public void scrivi(String risposta) {
        if (writer != null) {
            writer.println(risposta);
            System.out.println("SERVER: Inviato al client -> \"" + risposta + "\"");
        } else {
            System.err.println("SERVER ERRORE - Writer non inizializzato.");
        }
    }

    /*
     * CHIUSURA COMUNICAZIONE
     * Chiude reader, writer e il socket del client in quest'ordine.
     * Il ServerSocket rimane aperto: il server può ancora accettare nuovi client.
     * Ogni risorsa viene controllata prima di chiuderla per evitare NullPointerException.
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
            if (clientSocket != null && !clientSocket.isClosed()) {
                clientSocket.close();
                clientSocket = null;
            }
            System.out.println("SERVER: Comunicazione con il client chiusa.");
        } catch (IOException e) {
            System.err.println("SERVER ERRORE - Errore durante la chiusura: " + e.getMessage());
        }
    }

    /*
     * CHIUSURA SERVIZIO
     * Chiama prima chiudi() per liberare la comunicazione col client attivo,
     * poi chiude il ServerSocket, terminando definitivamente il servizio.
     * Dopo questa chiamata il server non può più accettare connessioni.
     */
    public void termina() {
        try {
            chiudi();
            if (serverSocket != null && !serverSocket.isClosed()) {
                serverSocket.close();
                serverSocket = null;
            }
            System.out.println("SERVER: Servizio terminato.");
        } catch (IOException e) {
            System.err.println("SERVER ERRORE - Errore durante la terminazione: " + e.getMessage());
        }
    }
}