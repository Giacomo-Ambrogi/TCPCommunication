package Client;

import java.io.*;
import java.net.Socket;

public class MainClient {
    public static void main(String[] args) {
        System.out.println("----- CLIENT: Avvio del client!!! -----");

        try{
            Socket socket = new Socket("localhost", 3000);

            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();

            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
            dataOutputStream.writeUTF("Client request");

            DataInputStream dataInputStream = new DataInputStream(inputStream);
            String serverResponse = dataInputStream.readUTF();

            inputStream.close();
            outputStream.close();
            socket.close();
        } catch(IOException e){
            System.err.println("----- CLIENT: Chiusura comunicazione! -----");
        }

        System.out.println("----- CLIENT: Fine esecuzione!!! -----");
    }
}