/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package udp;

import java.net.*;

/**
 *
 * @author alby
 */
public class UDPSender {

    public static void main(String args[]) {
        DatagramSocket ds;
        try {
            ds = new DatagramSocket();
            String str = "Hello!";
            InetAddress ip = InetAddress.getLocalHost();

            DatagramPacket dp = new DatagramPacket(str.getBytes(), str.length(), ip, 3000);
            ds.send(dp);
            ds.close();
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}
