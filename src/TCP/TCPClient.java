package TCP;

import java.io.*;
import java.net.*;
public class TCPClient {
    public static void main(String[] args) throws Exception {
        System.out.println("starting TCPClient main");
        String sentence = "";

        System.out.println("trying to connect");

        Socket clientSocket = new Socket("127.0.0.1", 5656);
        System.out.println("we are connected");

        while (true) {
            BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
            DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            System.out.print("Please type your text: ");
            sentence = inFromUser.readLine();

            if (sentence.equalsIgnoreCase("quit")) break;
            outToServer.writeBytes(sentence + '\n');
            sentence = inFromServer.readLine();
            if (sentence.equalsIgnoreCase("quit")) break;
            System.out.println("FROM SERVER: " + sentence);
        }
        clientSocket.close();
    }
}