package com.company;

import java.io.*;
import java.net.Socket;

public class client implements Runnable {
    BufferedReader br1, br2;
    PrintWriter pr1;
    Socket socket;
    Thread t1, t2;
    String in = "", out = "";

    public client() {
        try {
            t1 = new Thread(this);
            t2 = new Thread(this);
            socket = new Socket("127.0.0.1", 8088);
            t1.start();;
            t2.start();

        } catch (Exception e) {
        }
    }

    public void run() {

        try {
            if (Thread.currentThread() == t2) {
                do {
                    br1 = new BufferedReader(new InputStreamReader(System.in));
                    pr1 = new PrintWriter(socket.getOutputStream(), true);
                    in = br1.readLine();
                    pr1.println(in);
                } while (!in.equals("END"));
            } else {
                do {
                    br2 = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    out = br2.readLine();
                    System.out.println("Server says : " + out);
                } while (!out.equals("END"));
            }
        } catch (Exception e) {
        }

    }

    public static void main(String[] args) {
        new client();
    }
}
