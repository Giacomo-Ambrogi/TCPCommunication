package Client;

public class MainClient {
    public static void main(String[] args) {
        System.out.println("----- CLIENT: Avvio del client!!! -----");

        Client client = new Client("Giacomo", "nero");

        client.connetti("localhost", 3000);
        client.scrivi("Ciao Server, sono Giacomo!");
        client.leggi();
        client.chiudi();

        System.out.println("----- CLIENT: Fine esecuzione!!! -----");
    }
}