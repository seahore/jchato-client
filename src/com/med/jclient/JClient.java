package com.med.jclient;

import java.net.*;
import java.io.*;

public class JClient {
    public static void main(String [] args) {
        String serverName;
        int port;
        try {
            serverName = args[0];
            port = Integer.parseInt(args[1]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("请输入完整的参数。");
            return;
        }
        try {
            System.out.println("连接到主机：" + serverName + " ，端口号：" + port);
            Socket client = new Socket(serverName, port);
            System.out.println("远程主机地址：" + client.getRemoteSocketAddress());
            OutputStream outToServer = client.getOutputStream();
            DataOutputStream dout = new DataOutputStream(outToServer);

            dout.writeUTF("萌新报道！坐标" + client.getLocalSocketAddress() + "，(✪ω✪)");
            InputStream inFromServer = client.getInputStream();
            DataInputStream in = new DataInputStream(inFromServer);
            System.out.println("服务器响应： " + in.readUTF());
            client.close();
        } catch (ConnectException ce) {
            System.out.println("错误：远程主机拒绝连接，可能未在运行。客户端退出。");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
