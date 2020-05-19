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
public class UDPReceiver {
    public static void main(String args[]) {
        DatagramSocket ds;
        try {
            ds = new DatagramSocket(3000);
            byte[] buff = new byte[1024];
            DatagramPacket dp = new DatagramPacket(buff, buff.length);
            ds.receive(dp);
            String str = new String (dp.getData(),0,dp.getLength());
            System.out.println("received: "+str);
            ds.close();
        } catch (Exception e) {
            System.err.println(e);
        }
    }
    
}
