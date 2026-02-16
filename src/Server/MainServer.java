package Server;

public class MainServer {
    public static void main(String[] args) {
        System.out.println("----- SERVER: Inizio esecuzione!!! -----");

        Server server = new Server(3000);

        server.avvia();
        server.attendi();
        server.leggi();
        server.scrivi("SERVER ha ricevuto la tua richiesta!");
        server.chiudi();
        server.termina();

        System.out.println("----- SERVER: Fine esecuzione!!! -----");
    }
}