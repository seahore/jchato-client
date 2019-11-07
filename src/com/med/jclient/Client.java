package com.med.jclient;

import javafx.application.Application;

import java.io.*;
import java.net.*;

public class Client implements Runnable {
    private Socket socket;
    private DataInputStream din;
    private DataOutputStream dout;

    public Client(String addr, int port){
        try {
            this.socket = new Socket(addr, port);
            din = new DataInputStream(socket.getInputStream());
            dout = new DataOutputStream(socket.getOutputStream());
        } catch (ConnectException ce) {
            System.out.println("错误：远程主机拒绝连接，可能未在运行。客户端退出。");
            System.exit(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected synchronized void send(String contents) throws IOException {
        try {
            dout.writeUTF(contents);
        } catch (IOException e) {
            throw e;
        }
    }

    public void run() {
        try {
            System.out.println("连接到服务端主机：" + socket.getRemoteSocketAddress() + " ，端口号：" + socket.getPort());
            send("萌新报道！坐标" + socket.getLocalSocketAddress() + "，(✪ω✪)");
            new Thread(new ReaderThread(System.in, this)).start();
            while(true) {
                System.out.println(din.readUTF());
            }
        } catch (SocketException se) {
            System.out.println("服务器强制关闭了连接，客户端程序结束。");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
