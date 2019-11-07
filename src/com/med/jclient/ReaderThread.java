package com.med.jclient;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class ReaderThread implements Runnable {
    private Scanner inputScanner;
    private Client receiver;

    public ReaderThread(InputStream is, Client rcv) {
        this.inputScanner = new Scanner(is);
        this.receiver = rcv;
    }

    public void run() {
        try {
            while (true) {
                receiver.send(inputScanner.next());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
