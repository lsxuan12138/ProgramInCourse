package host;

import util.timer.Timer;

import java.io.IOException;
import java.net.*;
import java.util.ArrayDeque;
import java.util.Queue;

public class SRHost extends Host {
    private boolean[] mark;
    //缓存队列
    private Queue<Integer> cache = new ArrayDeque<>();


    public SRHost(String name,int sendPort,int receivePort,int windowSize,int dataNumber) {
        super(name,sendPort,receivePort,windowSize,dataNumber);
    }

    @Override
    public void send() throws Exception {
        timer = new Timer(this);
        timer.start();
        senderDatagramSocket =new DatagramSocket();
        mark = new boolean[dataNumber +1];
        while(true){
            //向服务器端发送数据
            sendData();
            //从服务器端接受ACK
            byte[] bytes = new byte[4096];
            senderDatagramPacket = new DatagramPacket(bytes, bytes.length);
            senderDatagramSocket.receive(senderDatagramPacket);

            String fromServer = new String(bytes, 0, bytes.length);
            int ack = Integer.parseInt(fromServer.substring(fromServer.indexOf("ACK: ") + 5).trim());
            mark[ack] = true;
            System.out.println(hostName + "接收到了ACK: " + ack);

            //收到base的ACK
            if(base == ack && base != dataNumber){
                base++;
                //乱序之后，把base值移到最远的位置
                for(int i = base; i < nextSeq;i++){
                    if(mark[i] == true){
                        base = i + 1;
                    }
                }
            }else if(base == ack && base == dataNumber){
                timer.pauseThread();
                sendEnd();
                break;
            }

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
        inetAddress = InetAddress.getLocalHost();
        while (nextSeq < base + windowSize && nextSeq <= dataNumber) {
            //不发编号为3的数据
            if(nextSeq == 3||nextSeq == 12) {
                nextSeq++;
                continue;
            }

            String sendData = hostName + ": Sending to port " + sendPort + ", Seq = " + nextSeq;

            // 模拟发送分组
            byte[] data = sendData.getBytes();
            DatagramPacket datagramPacket = new DatagramPacket(data, data.length, inetAddress, sendPort);
            senderDatagramSocket.send(datagramPacket);
            System.out.println(hostName + "发送到" + sendPort + "端口， Seq = " + nextSeq);
            if(nextSeq == base){
                //开始计时
                //model.setTime(3);
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

    @Override
    public void timeOut() {
        try {

            String resendData = hostName
                    + ": Resending to port " + sendPort + ", Seq = " + base;
            byte[] data = resendData.getBytes();
            DatagramPacket datagramPacket = new DatagramPacket(data, data.length, inetAddress, sendPort);
            senderDatagramSocket.send(datagramPacket);
            System.out.println(hostName
                    + "重新发送发送到" + sendPort + "端口， Seq = " + base);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    /**
     * 向服务器发送结束信号
     */
    public void sendEnd() throws IOException {
        inetAddress = InetAddress.getLocalHost();
        int end = -1;
        String clientData = hostName + ": Sending to port " + sendPort + ", Seq = " + end;
        System.out.println("向服务器发送结束信号");

        byte[] data = clientData.getBytes();
        DatagramPacket datagramPacket = new DatagramPacket(data, data.length, inetAddress, sendPort);
        senderDatagramSocket.send(datagramPacket);
    }
    @Override
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
                if(ack == -1){
                    System.out.println("本次传输结束");
                    System.exit(0);
                    break;
                }else{
                    sendAck(ack);

                    //收到了预期的数据
                    if (ack == exceptedSeq) {
                        System.out.println(hostName + "已收到预期编号 期待的数据Seq = " + exceptedSeq);
                        //期待值加1
                        exceptedSeq++;
                        //滑动窗口到最大值
                        while( cache.peek() != null && cache.peek()== exceptedSeq){
                            System.out.println("从服务器端缓存中读出数据:"+cache.element());
                            cache.poll();
                            exceptedSeq++;
                        }

                        System.out.println('\n');
                    } else {
                        System.out.println(hostName + "未收到预期编号 期待的数据Seq = " + exceptedSeq);
                        cache.add(ack);
                        System.out.println('\n');
                    }
                }
            }
        }catch(SocketException e){
            e.printStackTrace();
        }
    }
    //向客户端发送ack
    public void sendAck(int ack) throws IOException {
        String response = hostName + " responses ACK: " + ack;
        byte[] responseData = response.getBytes();
        InetAddress responseAddress = receiverDatagramPacket.getAddress();
        int responsePort = receiverDatagramPacket.getPort();
        receiverDatagramPacket = new DatagramPacket(responseData,responseData.length,responseAddress,responsePort);
        receiverDatagramSocket.send(receiverDatagramPacket);
    }
    public static void main(String[] args) throws InterruptedException {
        Host sender = new SRHost("sender",33333,33334,4,20);
        Host receiver = new SRHost("receiver",33334,33333,4,20);

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
