package test;

import host.FileHost;
import host.GBNHost;
import host.Host;
import host.SRHost;

import java.io.IOException;

public class Main {
    private static int host1Port = 808;    // host 1占用端口

    private static int host2Port = 809;

    public static void main(String[] args) throws IOException {
//        startGBN();
//        startStopAndWait();
//        startDual();
//        startSR();
        startFile();
    }
    private static void startFile()throws IOException{
        Host sender = new FileHost("Sender",host1Port,host2Port,5);
        Host receive = new FileHost("Receiver",host2Port,host1Port,5);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    receive.receive();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    sender.send();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }
    private static void startSR() throws IOException {
        Host sender = new SRHost("Sender",host1Port,host2Port,3,25);
        Host receiver = new SRHost("Receiver",host2Port,host1Port,0,0);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    receiver.receive();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    sender.send();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }

    private static void startStopAndWait() throws IOException {
        Host sender = new GBNHost("Sender",host1Port,host2Port,1,20);
        Host receiver = new GBNHost("Receiver",host2Port,host1Port,0,0);

        new Thread(() -> {
            try {
                receiver.receive();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                sender.send();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).start();
    }

    private static void startGBN() throws IOException {
        Host sender = new GBNHost("Sender",host1Port,host2Port,5,20);
        Host receiver = new GBNHost("Receiver",host2Port,host1Port,0,0);

        new Thread(() -> {
            try {
                receiver.receive();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                sender.send();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).start();
    }

    private static void startDual() throws IOException {

        GBNHost host1 = new GBNHost("Host A",host1Port,host2Port,3,20);
        GBNHost host2 = new GBNHost("Host B",host2Port,host1Port,4,30);

        // thread 2
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    host1.receive();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        // thread 4
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    host2.receive();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        // thread1
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    host1.send();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    host2.send();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }


}
