package SocketServe;
/*
*	author: wbx
*/
import java.net.*;
import java.io.*;
import java.util.Scanner;

public class sClient {
	public int PORT = 15648;
	public boolean Log = true;
	public Socket sk = null;
	public CallBack cb = null;
	void wlog(String log) {
		if(Log) System.out.println(log);
	}
	public boolean close() {
		try {
			sk.close();
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	public boolean send(String Text)
	{
		try{
			PrintStream ps = new PrintStream(sk.getOutputStream());//���ͻ����׽��ֵ��������printStream��װ������������C�����е�fprintf����ת��
			ps.println(Text);//�ѿ���̨������������뱻printstream���װ�����������	
			ps.flush();
			//if(Text.equals("END")) {				
			//	sk.close();//�ر�socket�׽��֣��Ѿ��������ݣ����ܹر�
			//}else {
			this.wlog(sk.getLocalPort()+" Client Send: "+Text);
			//}
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
	public boolean start(String ServerIp,CallBack cb) //= "127.0.0.1") 
	{
		this.cb = cb;
		try{
			sk = new Socket(ServerIp,PORT);	
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
		this.wlog("Connecting Server on "+ServerIp+","+PORT);
		try {
			ClientThread st = new ClientThread(sk);//����һ���̣߳����̴߳���һ���׽���
			st.start();
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		this.wlog("Server is success connected on "+ServerIp+","+PORT);
		return true;
	}
	//�ͻ����߳���
	class ClientThread extends Thread
	{
		Socket sk;
		public ClientThread(Socket sk){
			this.sk= sk;
		}
		public void run() {
			try {
				Scanner sn = new Scanner(sk.getInputStream());
				while(true) {
					if(sn.hasNextLine()) {
						String line = sn.nextLine();//br.readLine();
						//System.out.println(sk.getLocalPort()+" Client Receive: "+line);	
						cb.run(line,sk);
					}else {
						try { 
							sk.sendUrgentData(0); 
						} catch (IOException e) { 
							System.out.println("Disconnect from Server");
							cb.run("Disconnect from Server",sk);
							break;    //����׳����쳣����ô���ǶϿ�������  ��������ѭ��
						} 


					}
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
}