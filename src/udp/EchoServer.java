package udp;

import java.net.*;

public class EchoServer extends Thread {
    public final static int PORT = 4445;

 
    private DatagramSocket socket;
    private byte[] buf = new byte[256];
 
    public EchoServer() throws SocketException {
        socket = new DatagramSocket(PORT);
    }
 
    public void run() {
        System.out.println("EchoServer running on port "+PORT);
        while (true) {
            try {
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);

                InetAddress address = packet.getAddress();
                int port = packet.getPort();
                packet = new DatagramPacket(buf, buf.length, address, port);
                String received = new String(packet.getData(), 0, packet.getLength());
                System.out.println("received "+received+" from "+address+":"+port);
                /*
                if (received.equals("end")) {
                    break;
                }
                */
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
