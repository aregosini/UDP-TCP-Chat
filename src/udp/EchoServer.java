package udp;

import java.net.*;

public class EchoServer extends Thread {
    public final static int PORT = 4445;
    private DatagramSocket socket;
 
    public EchoServer() throws Exception {
        socket = new DatagramSocket(PORT);
    }
 
    public void run() {
        byte[] buf = new byte[256];
        
        System.out.println("EchoServer running on port "+PORT);
        DatagramPacket packet = new DatagramPacket(buf, buf.length);
        while (true) {
            try {
                socket.receive(packet);

                InetAddress address = packet.getAddress();
                int port = packet.getPort();
                String received = new String(packet.getData(), 0, packet.getLength());
                System.out.println("received "+received+" from "+address+":"+port);
                socket.send(packet);                
            } catch(Exception e) {
                System.err.println(e);
            }
        }
        //socket.close();
    }
    public static void main(String args[]) throws Exception {
        new EchoServer().start();
    }
}
