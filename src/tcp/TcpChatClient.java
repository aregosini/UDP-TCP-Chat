/*
 * Main.java
 */
package tcp;

import java.io.BufferedReader; 
import java.io.BufferedWriter; 
import java.io.IOException; 
import java.io.InputStreamReader; 
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author maurizio
 */
public class TcpChatClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // Creazione di un socket sulla porta 6789 sulla macchina "localhost" 
        Socket clientSocket = new Socket("151.65.59.126", 6789);
        
        // Stream proveniente dal server 
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        // Stream diretto verso il server 
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
        
        // Lettura dei dati da tastiera
        Scanner input = new Scanner(System.in); 
        System.out.print("Inserisci una frase: "); 
        String sentence = input.nextLine();
      
        // Invio dei dati dati al server 
        out.write(sentence);
        
        // Nuova riga 
        out.newLine();
        
        // Forza l'invio dei dati verso il server 
        out.flush();
        
        // Lettura della stringa dal server (il server la trasforma in maiuscolo) 
        String modifiedSentence = in.readLine();
        
        // Stampa sulla console
        System.out.println("Dal server: " + modifiedSentence);
        
        // Chiusura
        in.close();
        out.close();
        clientSocket.close(); 
    }
}
