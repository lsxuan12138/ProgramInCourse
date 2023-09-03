package host;

import util.timer.Timer;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Host
 *
 * @author: lsxuan
 * @email: 1146887979@qq.com
 * @create: 2022-10-09 12:02
 */
public abstract class Host {
    protected String hostName;
    protected int sendPort;
    protected int receivePort;
    protected InetAddress inetAddress;
    protected DatagramSocket senderDatagramSocket;
    protected DatagramPacket senderDatagramPacket;
    protected DatagramSocket receiverDatagramSocket;
    protected DatagramPacket receiverDatagramPacket;
    protected int nextSeq;
    protected int base;
    protected int windowSize;
    protected int dataNumber;
    protected Timer timer;

    /**
     * 发送数据
     * @throws Exception
     */
    abstract public void send() throws Exception;
    /**
     * 超时数据重传
     */
    abstract public void timeOut();

    /**
     * 接受数据
     * @throws IOException
     */
    abstract public void receive() throws IOException ;

    /**
     * 发送ack
     * @param ack
     * @throws IOException
     */
    abstract public void sendAck(int ack) throws IOException;

    /**
     * 发送结束信息
     * @throws IOException
     */
    abstract public void sendEnd() throws IOException;

    public Host(String hostName,int sendPort,int receivePort,int windowSize,int dataNumber) {
        try {
            this.hostName = hostName;
            inetAddress = InetAddress.getLocalHost();
            nextSeq=1;
            base=1;
            this.windowSize = windowSize;
            this.dataNumber=dataNumber;
            this.sendPort = sendPort;
            this.receivePort = receivePort;
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }



}