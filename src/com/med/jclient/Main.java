package com.med.jclient;

import java.net.*;
import java.io.*;

public class Main {
    public static void main(String [] args) {
        String addr;
        int port;
        try {
            addr = args[0];
            port = Integer.parseInt(args[1]);
            Client c = new Client(addr, port);
            c.run();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("请输入完整的参数。");
            return;
        } catch (NumberFormatException e) {
            System.out.println("请输入正确格式的参数。");
            return;
        }
    }
}
