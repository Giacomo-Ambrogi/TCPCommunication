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
            PrintWriter pw = new PrintWriter(outputStream);
            pw.print("Ciao SERVER!");
            pw.flush();
            System.out.println("CLIENT: Il CLIENT ha inviato un messaggio");

            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
            dataOutputStream.writeUTF("Client request");

            outputStream.close();
            socket.close();
        } catch(IOException e){
            System.err.println("----- CLIENT: Errore nella comunicazione con il SERVER! -----");
        }

        System.out.println("----- CLIENT: Fine esecuzione!!! -----");
    }
}