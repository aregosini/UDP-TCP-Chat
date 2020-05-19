/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import udp.EchoClient;

/**
 *
 * @author alby
 */
public class Test {

    public static void main(String args[]) throws Exception {
        try {
            InetAddress address = InetAddress.getLocalHost();
            String addressHost = address.getHostAddress();
            System.out.println("addressHost: "+addressHost);
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}
