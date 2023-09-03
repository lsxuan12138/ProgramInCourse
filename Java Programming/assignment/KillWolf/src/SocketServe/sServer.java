package SocketServe;
/*
*	author: wbx
*/
import java.net.*;
import java.io.*;
import java.util.Scanner;

public class sServer {
	public int PORT = 15648;
	public boolean Log = true;
	public ServerSocket ss = null;
	public Socket[] users = new Socket[50];
	public int usersLen = 0;
	public CallBack cb = null;
	void wlog(String log) {
		if(Log) System.out.println(log);
	}
	public boolean close() {
		try {
			ss.close();
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	public boolean sendto(String Text,Socket sk) {
		if(sk == null) return false;
		PrintStream ps;
		try {
			ps = new PrintStream(sk.getOutputStream());
			ps.println(Text);//�ѿ���̨������������뱻printstream���װ�����������	
			ps.flush();
			this.wlog("Send to "+sk.getPort()+" : "+Text);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}//���ͻ����׽��ֵ��������printStream��װ������������C�����е�fprintf����ת��
		
		return true;
	}
	public boolean send(String Text) {
		try{
			for(int i=0;i<usersLen;i++) {
				Socket sk = users[i];
				if(sk == null) continue;
				PrintStream ps = new PrintStream(sk.getOutputStream());//���ͻ����׽��ֵ��������printStream��װ������������C�����е�fprintf����ת��
				ps.println(Text);//�ѿ���̨������������뱻printstream���װ�����������	
				ps.flush();
				this.wlog("Send "+(i+1)+"/"+usersLen+" to "+sk.getPort()+" : "+Text+"####");
				Thread.sleep(10);
			}
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
	public boolean start(CallBack cb) //= "127.0.0.1")
	{
		this.cb = cb;
		try {
			ss = new ServerSocket(PORT);
			ServerThreadAC st = new ServerThreadAC();//����һ���̣߳����̴߳���һ���׽���
			st.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}//��һ���ܹ���������ͨ��ʵ���������
		return true;
	}
	//�������¿ͻ��߳���
	public class ServerThreadAC extends Thread
	{
		//String ServerIp;
		int id = 0;
		boolean Log = true;
		//public ServerThreadAC(String ServerIp){
		//	this.ServerIp = ServerIp;
		//}
		public void run() {
			try {
				if(Log) System.out.println("Server is open on "+PORT);
				//��һ��whileѭ������ͬʱ��Ӧ����ͻ��˵�����
				while(true){
					 Socket sk = ss.accept();//������������Ӧ�˿ڵ�����
					 ServerThread st = new ServerThread(sk);//����һ���̣߳����̴߳���һ���׽���
					 st.start();
				}	
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	//�������ͻ��߳���
	public class ServerThread extends Thread
	{
		Socket sk;
		int id = 0;
		boolean Log = true;
		public ServerThread(Socket sk){
			this.sk= sk;
		}
		public void run() {
			try {
				users[usersLen] = sk;
				this.id = usersLen;
				usersLen++;
				if(Log) System.out.println("New User in "+sk.getPort());
				cb.run("New User: "+sk.getPort(),sk);
				Scanner sn = new Scanner(sk.getInputStream());
				while(true) {
					if(sn.hasNextLine()) {
						String line = sn.nextLine();//br.readLine();
						//System.out.println("Server Receive from "+sk.getPort()+": "+line);
						cb.run(line,sk);
					}else {
						try { 
								sk.sendUrgentData(0); 
							} catch (IOException e) { 
								System.out.println("User "+sk.getPort()+" has disconnected.");
								cb.run("User Disconnect "+sk.getPort(),sk);
								users[this.id] = null;
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
