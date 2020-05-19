/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package udp;

import java.io.*;
import java.net.*;
/**
 *
 * @author alby
 */
public class EchoClient {
    public final static int PORT = 4445;
    private DatagramSocket socket;
    private InetAddress address;
 
    private byte[] buf;
 
    public EchoClient(String hostname) throws Exception {
        socket = new DatagramSocket();
        address = InetAddress.getByName(hostname);
    }
 
    public String sendEcho(String msg) throws IOException {
        buf = msg.getBytes();
        DatagramPacket packet = new DatagramPacket(buf, buf.length, address, PORT);
        socket.send(packet);
        socket.receive(packet);
        String received = new String(packet.getData(), 0, packet.getLength());
        return received;
    }
 
    public void close() {
        socket.close();
    }
    
    public static void main(String args[]) throws Exception {
        String host = null,msg;
        if (args.length < 1) {
            System.out.println("Usage: java EchoClient <server_hostname>");
            System.exit(0);
        } else {
            host = args[0];
        }
        EchoClient c = new EchoClient(host);
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            try {
                System.out.print("send: ");
                msg = c.sendEcho(in.readLine());
                System.out.println("receive: "+msg);
            } catch(Exception e) {
                System.err.println(e);
            }
        }
    }

}
