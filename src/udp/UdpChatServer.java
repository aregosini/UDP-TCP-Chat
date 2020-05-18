package udp;

import java.io.*;
import java.net.*;
import java.util.*;

public class UdpChatServer extends Thread {
    public final static int PORT = 7331;
    private final static int BUFFER = 1024;
    
    private DatagramSocket socket;
    private ArrayList<InetAddress> clientAddresses;
    private ArrayList<Integer> clientPorts;
    private HashSet<String> existingClients;
    
    public UdpChatServer() throws IOException {
        socket = new DatagramSocket(PORT);
        clientAddresses = new ArrayList();
        clientPorts = new ArrayList();
        existingClients = new HashSet();
    }
    
    public void run() {
        byte[] buf = new byte[BUFFER];
        while (true) {
            try {
                Arrays.fill(buf, (byte)0);
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);
                
                String content = new String(buf, 0, buf.length);
                
                InetAddress clientAddress = packet.getAddress();
                int clientPort = packet.getPort();
                
                String id = clientAddress.toString() + "," + clientPort;
                if (!existingClients.contains(id)) {
                    existingClients.add( id );
                    clientPorts.add( clientPort );
                    clientAddresses.add(clientAddress);
                }
                
                System.out.println(id + " : " + content);
                byte[] data = (id + " : " +  content).getBytes();
                for (int i=0; i < clientAddresses.size(); i++) {
                    InetAddress cl = clientAddresses.get(i);
                    int cp = clientPorts.get(i);
                    packet = new DatagramPacket(data, data.length, cl, cp);
                    socket.send(packet);
                }
            } catch(Exception e) {
                System.err.println(e);
            }
        }
    }
    
    public static void main(String args[]) throws Exception {
        UdpChatServer s = new UdpChatServer();
        s.start();
    }
}
