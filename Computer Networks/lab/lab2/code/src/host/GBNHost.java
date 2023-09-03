package host;

import util.timer.Timer;

import java.io.*;
import java.net.*;

/**
 * GBNHost
 */
public class GBNHost extends Host {


    public GBNHost(String name,int sendPort,int receivePort,int windowSize,int dataNumber){
        super(name,sendPort,receivePort,windowSize,dataNumber);
    }

    public void send() throws Exception {
        timer = new Timer(this);
        timer.start();
        senderDatagramSocket = new DatagramSocket();

        while(true){
            //向服务器端发送数据
            sendData();
            //从服务器端接受ACK
            byte[] bytes = new byte[4096];
            senderDatagramPacket = new DatagramPacket(bytes, bytes.length);
            senderDatagramSocket.receive(senderDatagramPacket);
            String fromServer = new String(bytes, 0, bytes.length);
            // 解析出ACK编号
            int ack = Integer.parseInt(fromServer.substring(fromServer.indexOf("ACK: ") + 5).trim());
            System.out.println(hostName + "接收到了ACK: " + ack);
            if(ack==dataNumber){
                sendEnd();
                timer.pauseThread();
                break;
            }
            base = ack + 1;
            if(base == nextSeq){
                //停止计时器
                timer.clear();
            }else {
                //开始计时器
                timer.clear();
            }

        }

    }


    /**
     * 向服务器发送数据
     *
     * @throws Exception
     */
    private void sendData() throws Exception {

        while (nextSeq < base + windowSize && nextSeq <= dataNumber) {
            //不发编号为3的数据，模拟数据丢失
            if(nextSeq % 3 == 0) {
                System.out.println(hostName + "假装丢失Seq = " + nextSeq);
                nextSeq++;
                continue;
            }
            String sendData = hostName + ": Sending to port " + sendPort + ", Seq = " + nextSeq;

            byte[] data = sendData.getBytes();
            DatagramPacket datagramPacket = new DatagramPacket(data, data.length, inetAddress, sendPort);
            senderDatagramSocket.send(datagramPacket);
            System.out.println(hostName + "发送到" + sendPort + "端口， Seq = " + nextSeq);
            if(nextSeq == base){
                //开始计时
                timer.clear();
            }
            nextSeq++;
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public void timeOut() {
        try {
            for(int i = base;i < nextSeq;i++){
                String resendData = hostName
                        + ": Resending to port " + sendPort + ", Seq = " + i;

                byte[] data = resendData.getBytes();
                DatagramPacket datagramPacket = new DatagramPacket(data, data.length, inetAddress, sendPort);
                senderDatagramSocket.send(datagramPacket);
                System.out.println(hostName
                        + "重新发送发送到" + sendPort + "端口， Seq = " + i);
            }

        }catch (IOException e){
            e.printStackTrace();
        }

    }


    public void receive() throws IOException {
        int exceptedSeq =1;
        try {
            receiverDatagramSocket = new DatagramSocket(receivePort);
            while (true) {
                byte[] receivedData = new byte[4096];
                receiverDatagramPacket = new DatagramPacket(receivedData, receivedData.length);
                receiverDatagramSocket.receive(receiverDatagramPacket);
                //收到的数据
                String received = new String(receivedData, 0, receivedData.length);//offset是初始偏移量
                //System.out.println(received);
                int seqIndex = received.indexOf("Seq = ");
                int ack = Integer.parseInt(received.substring(seqIndex + 6).trim());
                if(ack!=-1)System.out.println(hostName+"接收到的报文为：["+received.trim()+"]");
                if(ack==-1){
                    System.out.println("本次传输结束");
                    System.exit(0);
                    break;
                }
                //收到了预期的数据
                if (ack== exceptedSeq) {
                    //发送ack
                    sendAck(exceptedSeq);
                    System.out.println(hostName + "已收到预期编号 期待的数据Seq = " + exceptedSeq);
                    //期待值加1
                    exceptedSeq++;
                } else {
                    // 未收到预期的Seq
                    System.out.println(hostName + "未收到预期编号 期待的数据Seq = " + exceptedSeq);
                    //仍发送之前的ack
                    sendAck(exceptedSeq - 1);
                    System.out.println('\n');
                }
            }
        }catch(SocketException e){
            e.printStackTrace();
        }
    }

    @Override
    public void sendAck(int ack) throws IOException {
        String response = hostName + " responses ACK: " + ack;
        byte[] responseData = response.getBytes();
        InetAddress responseAddress = receiverDatagramPacket.getAddress();
        int responsePort = receiverDatagramPacket.getPort();
        receiverDatagramPacket = new DatagramPacket(responseData,responseData.length,responseAddress,responsePort);
        receiverDatagramSocket.send(receiverDatagramPacket);
    }
    @Override
    public void sendEnd() throws IOException {
        inetAddress = InetAddress.getLocalHost();
        int end = -1;
        String clientData = hostName + ": Sending to port " + sendPort + ", Seq = " + end;
        System.out.println(hostName+"向服务器发送结束信号");

        byte[] data = clientData.getBytes();
        DatagramPacket datagramPacket = new DatagramPacket(data, data.length, inetAddress, sendPort);
        senderDatagramSocket.send(datagramPacket);
    }
    public static void main(String[] args) throws InterruptedException {
        Host sender = new GBNHost("sender",33333,33334,1,20);
        Host receiver = new GBNHost("receiver",33334,33333,0,0);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    receiver.receive();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
        Thread.sleep(1500);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    sender.send();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }
}
