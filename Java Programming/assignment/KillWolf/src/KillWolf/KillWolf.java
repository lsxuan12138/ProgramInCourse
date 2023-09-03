package KillWolf;
import java.net.Socket;

import java.util.Scanner;

import javax.swing.JFrame;

import KillWolf.Data.DataUser;
import KillWolf.Data.SelectionEvent;
import KillWolf.Data.SelectionEventCallBack;
import KillWolf.KillWolf.serverData;
import KillWolf.SocketDeal.GameMainThread;
import KillWolf.Window.GameMain;
import KillWolf.Window.LoginWindow;
import KillWolf.Window.SelectWindow;
import KillWolf.Window.WaitRoom;
import SocketServe.*;


public class KillWolf {
	public class serverData {
		public static String StartSpeakName = null;
		//public static int startSpeakUserArrayId;
		public static String nowSpeakUser;
		//public static int nowSpeakUserArrayId;		
		public static String deathUser = null;	//狼人杀死/女巫解救
		public static String deathUser2 = null; //女巫毒死      为**NONE**表示无人死亡
		public static String deathUser3 = null; //猎人杀死
		public static String deathUser4 = null; //投票出局
		public static GameMainThread GameMainThread = null;
		public static int Day = 0;
		public static boolean night = false;
		public static boolean playMode = false; //true表示屠城玩法，false表示屠边玩法
		public static boolean freeSpeak = false;
		public static DataUser ServerUsers[] = new DataUser[50];
		public static int UsersLen = 0;
		public static int UsersRealLen = 0;
		public static SelectionEvent Selection[] = new SelectionEvent[200];
		public static int SelectionLen = 0;
		public static DataUser[] getUsersWithID(String identify) {
			int[] a = new int[ServerUsers.length];
			int n = 0;
			for(int i=0;i< UsersLen;i++) {
				if(ServerUsers[i] != null)
				if(ServerUsers[i].identify.equals(identify)) {
					a[n] = i;
					n++;
				}
			}
			DataUser[] b = new DataUser[n];
			for(int i=0;i<n;i++) b[i] = ServerUsers[a[i]];
			return b;
		}
		public static String[] ArrayDatatoName(DataUser[] d) {
			int[] a = new int[d.length];
			int n = 0;
			for(int i=0;i<d.length;i++) {
				if(d[i] != null)
				if(d[i].live) {
					a[n] = i;
					n++;
				}
			}
			String[] b = new String[n];
			for(int i=0;i<n;i++) {
				b[i] = d[a[i]].name;
			}
			return b;
		}
		public static Object[] getLivingUsers(boolean onlyName) {
			int[] a = new int[ServerUsers.length];
			int n = 0;
			for(int i=0;i<UsersLen;i++) {
				if(ServerUsers[i] != null)
				if(ServerUsers[i].live) {
					a[n] = i;
					n++;
				}
			}
			String[] b = new String[n];
			DataUser[] c = new DataUser[n];
			for(int i=0;i<n;i++) {
				if(onlyName) b[i] = ServerUsers[a[i]].name;
				else c[i] = ServerUsers[a[i]];
				System.out.println("LIVING : "+ServerUsers[a[i]].name);
			}
			if(onlyName) return b;
			else return c;
		}
		public static boolean isAllWolfDied() {
			DataUser[] b = getUsersWithID("狼人");
			for(int i=0;i<b.length;i++) if(b[i].live) return false;
			return true;
		}
		public static boolean isAllPeoDied() {
			DataUser[] b = getUsersWithID("平民");
			for(int i=0;i<b.length;i++) if(b[i].live) return false;
			return true;
		}
		public static boolean isAllGodDied() {
			DataUser[] b = getUsersWithID("猎人");
			for(int i=0;i<b.length;i++) if(b[i].live) return false;
			b = getUsersWithID("预言家");
			for(int i=0;i<b.length;i++) if(b[i].live) return false;
			b = getUsersWithID("女巫");
			for(int i=0;i<b.length;i++) if(b[i].live) return false;
			return true;
		}
		public static String whoWin() {
			for (int i=0;i<serverData.UsersLen;i++){
				if(serverData.ServerUsers[i]!=null){
					System.out.println(serverData.ServerUsers[i].name +" "+serverData.ServerUsers[i].identify+" "+serverData.ServerUsers[i].live);
				}
			}
			if(playMode) {
				if(isAllWolfDied()) return "平民";
				if(isAllPeoDied() && isAllGodDied()) return "狼人";
				return "无";
			}else {
				if(isAllWolfDied()) return "平民";
				if(isAllPeoDied() || isAllGodDied()) return "狼人";
				return "无";
			}
		}
		public static DataUser getUser(String name) {
			for(int i=0;i<UsersLen;i++) {
				if(ServerUsers[i] != null)
				if(ServerUsers[i].name.equals(name)) {
					return ServerUsers[i];
				}
			}
			return null;
		}
		
	}
	
	public static sServer server;
	public static sClient client;
	public static boolean freeSpeak = false;
	public static boolean isServer = false;
	public static String ServerIp;
	public static String Mip;
	public static String UserName;
	public static DataUser Users[] = new DataUser[50];
	public static int UsersLen = 0;
	public static int UsersRealLen = 0;
	public static LoginWindow loginwindow;
	public static WaitRoom WaitRoom;
	public static GameMain GameMain;
	public static SelectWindow SelectWindow;
	public static void main(String[] args) {
		//Scanner sn = new Scanner(System.in);
		Mip = sIP.getIP();
		
		loginwindow = new LoginWindow();
		loginwindow.reset(Mip);
		
		// TODO Auto-generated method stub
		//IP ip = new IP()
		//String IP = "127.0.0.1";
		
		System.out.println("Server on IP: "+Mip);
		//System.out.print("������������");
		//String name = sn.next();
		//System.out.println("����������"+name);
		
		//System.out.print("Enter Server IP:");
		//IP = sn.next();
		
		
		
		//server = new sServer();
		//server.start(cb);
//		while(true) {
//			if(sn.hasNext()) {
//				String tmp = sn.next();
//				if(tmp.equals("a")) {
//					String b[] = new String[KillWolf.serverData.UsersLen];
//					for(int i=0;i<b.length;i++) {
//						if(KillWolf.serverData.ServerUsers[i] != null)
//							b[i] = KillWolf.serverData.ServerUsers[i].name;
//					}
//					aaa c = new aaa();
//					KillWolf.serverData.Selection[KillWolf.serverData.SelectionLen] = new SelectionEvent(Integer.toString(KillWolf.serverData.SelectionLen),b,30,c);
//					KillWolf.serverData.Selection[KillWolf.serverData.SelectionLen].openSeletion(server, serverData.ServerUsers, serverData.UsersRealLen,"请选择","选择一个人","投票");
//					KillWolf.serverData.SelectionLen++;
//				}
//			}
//		}
	}
	
	
	public static void goWaitRoom() {
		/*SelectWindow = new SelectWindow("选择",true,"123",true,loginwindow.window);
		String[] b = {"a","b","c",UserName};
		SelectWindow.start("请选择", "投票", b, true);
		*/
		
		loginwindow.window.setVisible(false);
		WaitRoom = new WaitRoom();
		WaitRoom.reset(Mip,UserName,isServer);
	}
	public static void goGameMain() {
		WaitRoom.window.setVisible(false);
		GameMain = new GameMain();
		GameMain.reset();

//		GameMain.getFrame().setVisible(false);
	}
}
//class aaa implements SelectionEventCallBack {
//	public void run(String ReceiveID,int[] count,String users[],String target[],String[] aaa) {
//		System.out.println("############# "+ ReceiveID);
//		for(int i=0;i<count.length;i++) {
//			System.out.println("## "+ users[i]+" "+ count[i]);
//		}
//	}
//}