package Client;

import java.io.*;
import java.net.Socket;

public class MainClient {
    public static void main(String[] args) {
        System.out.println("----- CLIENT: Avvio del client!!! -----");

        try{
            Socket socket = new Socket("localhost", 3000);
            System.out.println("CLIENT: Il CLIENT si è connesso al SERVER");

            OutputStream outputStream = socket.getOutputStream();
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.print("Ciao SERVER!\n");
            out.flush();
            System.out.println("CLIENT: Il CLIENT ha inviato un messaggio");

            //DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
            //dataOutputStream.writeUTF("Client request");

            String messaggio = "";
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedReader tastiera = new BufferedReader(new InputStreamReader(System.in));

            while(true) {
                System.out.println("Scrivi un messaggio per il SERVER: ");
                messaggio = tastiera.readLine();

                out.println(messaggio);

                if(messaggio.equalsIgnoreCase("esci")) {
                    break;
                }

                String risposta = br.readLine();
                System.out.println("Risposta SERVER: " + risposta);
            }

            outputStream.close();
            socket.close();
            br.close();
            out.close();
        } catch(IOException e){
            System.err.println("----- CLIENT: Errore nella comunicazione con il SERVER! -----");
        }

        System.out.println("----- CLIENT: Fine esecuzione!!! -----");
    }
}