package Server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.Buffer;

public class MainServer {
    public static void main(String[] args) {
        System.out.println("----- SERVER: Inizio esecuzione!!! -----");

        Server server = new Server(3000);

        System.out.println("SERVER: In attesa di richieste dei CLIENT!");
        server.attendi();
        System.out.println("SERVER: Il CLIENT si è connesso!");
        server.leggi();

        server.scrivi();

        server.chiudi();

        /*try {
            //comunicazione
            ServerSocket serverSocket = new ServerSocket(3000);
            System.out.println("SERVER: In attesa di richieste dei CLIENT!");
            Socket clientSocket = serverSocket.accept();
            System.out.println("SERVER: Il CLIENT si è connesso!");

            //lettura
            InputStream inputStream = clientSocket.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String messaggio = br.readLine();
            System.out.println("SERVER: Il CLIENT " + clientSocket + "ha scritto il messaggio --> " + messaggio);

            BufferedReader tastiera = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            while(true) {
                messaggio = br.readLine();

                if (messaggio == null || messaggio.equalsIgnoreCase("esci")) {
                    break;
                }

                System.out.println("Messaggio CLIENT: " + messaggio);
                System.out.println("Risposta SERVER: " );
                String risposta = tastiera.readLine();

                if (risposta.equalsIgnoreCase("esci")) {
                    break;
                }

                out.println(risposta);
            }

            inputStream.close();
            clientSocket.close(); //chiusura data socket
            br.close();
            out.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }*/

        System.out.println("----- SERVER: Fine esecuzione!!! -----");
    }
}