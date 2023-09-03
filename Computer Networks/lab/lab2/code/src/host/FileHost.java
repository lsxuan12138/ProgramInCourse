package host;

import util.timer.*;

import java.io.*;
import java.net.*;

/**
 * TODO
 *
 * @author: lsxuan
 * @email: 1146887979@qq.com
 * @create: 2022-10-10 10:21
 */
public class FileHost extends Host{
    private byte[][] cache;

    public FileHost(String name,int sendPort,int receivePort,int windowSize){
        super(name,sendPort,receivePort,windowSize,100);

    }

    public void send() throws Exception {

        senderDatagramSocket = new DatagramSocket();
        BufferedInputStream in = null;
        try {
            File file = new File("src\\file\\upload\\uploadFileTemp.png");
            if(!file.isFile()|| !file.exists()){
                throw new FileNotFoundException();
            }
            in = new BufferedInputStream(new FileInputStream(file));
            long fileLength = file.length();
            //单个报文数据大小
            long size = 4000;
            int dataNumber = (fileLength % size != 0) ? (int) (fileLength / size + 1)
                    : (int) (fileLength / size);
            System.out.println("该文件将分为"+dataNumber+"个报文发送");
            this.dataNumber=dataNumber;
            cache = new byte[dataNumber][];
            for (int i = 0; i < dataNumber; i++) {
                byte[] buffer = new byte[4096];
                StringBuilder sb = new StringBuilder();
                sb.append(hostName).append(": Sending to port ").append(sendPort).append(", Seq = ").append(i+1);
                //System.out.println();
                byte[] temp = sb.toString().getBytes();
                System.arraycopy(temp,0,buffer,0,temp.length);
                in.read(buffer,96,4000);
                cache[i] = buffer;
            }
            timer = new Timer(this);
            timer.start();
        while(true) {
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
            if (ack == dataNumber) {
                timer.pauseThread();
                sendEnd();
                break;
            }
            base = ack + 1;
            if (base == nextSeq) {
                //停止计时器
                timer.clear();
            } else {
                //开始计时器
                timer.clear();
            }

        }

        }catch (FileNotFoundException e){
            e.printStackTrace();
        }

    }


    /**
     * 向服务器发送数据
     *
     * @throws Exception
     */
    private void sendData() throws Exception {

        while (nextSeq < base + windowSize && nextSeq <= dataNumber) {

            byte[] data = cache[nextSeq-1];
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

    /**
     * 超时数据重传
     */
    public void timeOut() {
        try {
            for(int i = base;i < nextSeq;i++){
                String resendHead = hostName
                    + ": Resending to port " + sendPort + ", Seq = " + i;

                byte[] data = cache[i-1];
                System.arraycopy(resendHead.getBytes(),0,data,0,96);
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
            File d = new File("src\\file\\download\\downloadTmp.png");
            if(d.exists())d.delete();
            File dir = new File("src\\file\\download");
            if(!dir.exists()){
                dir.mkdirs();
            }
            File file = new File(dir,"downloadTmp.png");
            if(!file.exists()){
                file.createNewFile();
            }
            BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file));
            receiverDatagramSocket = new DatagramSocket(receivePort);
            while (true) {
                byte[] receivedData = new byte[4096];
                receiverDatagramPacket = new DatagramPacket(receivedData, receivedData.length);
                receiverDatagramSocket.receive(receiverDatagramPacket);
                //收到的数据
                String received = new String(receivedData, 0, 96);
                int seqIndex = received.indexOf("Seq = ");
                int ack = Integer.parseInt(received.substring(seqIndex + 6).trim());
                if(ack!=-1)System.out.println(hostName+"接收到的报文为：["+received.trim()+"]");
                if(ack==-1){
                    System.out.println("本次传输结束");
                    if(out!=null){
                        out.flush();
                        out.close();
                    }
                    System.exit(0);
                    break;
                }
                //收到了预期的数据
                if (ack== exceptedSeq) {
                    byte[] buffer = new byte[4000];
                    System.arraycopy(receivedData,96,buffer,0,4000);
                    //发送ack
                    sendAck(exceptedSeq);
                    System.out.println(hostName + "已收到预期编号 期待的数据Seq = " + exceptedSeq);
                    //期待值加1
                    out.write(buffer);
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


    //向客户端发送ack
    public void sendAck(int ack) throws IOException {
        String response = hostName + " responses ACK: " + ack;
        byte[] responseData = response.getBytes();
        InetAddress responseAddress = receiverDatagramPacket.getAddress();
        int responsePort = receiverDatagramPacket.getPort();
        receiverDatagramPacket = new DatagramPacket(responseData,responseData.length,responseAddress,responsePort);
        receiverDatagramSocket.send(receiverDatagramPacket);
    }
    public void sendEnd() throws IOException {
        inetAddress = InetAddress.getLocalHost();
        int end = -1;
        String clientData = hostName + ": Sending to port " + sendPort + ", Seq = " + end;
        System.out.println("向服务器发送结束信号");

        byte[] data = clientData.getBytes();
        DatagramPacket datagramPacket = new DatagramPacket(data, data.length, inetAddress, sendPort);
        senderDatagramSocket.send(datagramPacket);
    }
    public static void main(String[] args) throws InterruptedException {
        Host sender = new FileHost("sender",33333,33334,5);
        Host receiver = new FileHost("receiver",33334,33333,1);

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