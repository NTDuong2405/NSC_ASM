package com.company;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class server implements  Runnable{
    ServerSocket serversocket;
    BufferedReader br1, br2;
    PrintWriter pr1;
    Socket socket;
    Thread t1, t2;
    String in="",out="";

    public server() {
        try {
            t1 = new Thread(this);
            t2 = new Thread(this);
            serversocket = new ServerSocket(8088);
            System.out.println("Server is waiting. . . . ");
            socket = serversocket.accept();
            System.out.println("Client connected with Ip " + socket.getInetAddress().getHostAddress());
            t1.start();;
            t2.start();

        } catch (Exception e) {
        }
    }

    public void run() {
        try {
            if (Thread.currentThread() == t1) {
                do {
                    br1 = new BufferedReader(new InputStreamReader(System.in));
                    pr1 = new PrintWriter(socket.getOutputStream(), true);
                    in = br1.readLine();
                    pr1.println(in);
                } while (!in.equals("END"));
            } else {
                do {
                    br2 = new BufferedReader(new   InputStreamReader(socket.getInputStream()));
                    out = br2.readLine();
                    System.out.println("Client says:" + out);
                } while (!out.equals("END"));
            }
        } catch (Exception e) {
        }
    }

    public static void main(String[] args) {
        new server();
    }
}
