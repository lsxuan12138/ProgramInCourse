package KillWolf.Data;

import KillWolf.KillWolf;
import SocketServe.sServer;

public class SelectionEvent {
	public static SelectionEvent Selection[] = new SelectionEvent[200];
	public static int SelectionLen = 0;
	public static void UserQuit(String name) {
		for(SelectionEvent i : Selection) {
			if(i!=null) {
				if(i.open) {
					for(int j=0;j<i.openusers.length;j++) {
						if(i.openusers[j].equals(name)) {
							if(i.target[j] == null) {
								i.target[j] = "**NONE**";
								i.isFinish();
								break;
							}							
						}
					}
				}
			}
		}
	}
	public boolean open;
	
	public String[] users; //要选的目标人
	public int[] count;
	
	public String[] openusers; //需要选人的人
	public String[] target;
	
	public String ReceiveID;
	public int timeLimit;
	public SelectionEventCallBack cb;
	public sServer server;
	public SelectionEvent(String ReceiveID,String[] users,int timeLimit,SelectionEventCallBack cb) {
		this.users = new String[users.length];
		this.count = new int[users.length];
		this.cb = cb;
		this.ReceiveID = ReceiveID;
		this.timeLimit= timeLimit;
		for(int i=0;i<users.length;i++)
			if(users[i] != null) this.users[i] = users[i];
			else  this.users[i] = "**NONE**";
	}
	public void update(String user,String toUser) {
		for(int i=0;i<users.length;i++) {
			if(users[i].equals(user)) {
				for( int j=0;j<openusers.length;j++) {
					if(openusers[j].equals(user)) {
						target[j] = toUser;
					}
				}
			}
			if(users[i].equals(toUser))	count[i]++;
		}
		System.out.println("Finish ? " + isFinish());
	}
	public boolean isFinish() {
		System.out.println("###### IS FINISH ? " + target.length);
		for(String i : target) {
			if(i == null) {
				//System.out.println("#######################");
				return false;
			}
		}
		closeSelection(this.server,false);
		return true;
	}
	public int[] getResult() {
		return count;
	}
	public boolean closeSelection(sServer server,boolean run) {
		//if(run) if(this.cb != null) this.cb.run(ReceiveID, count, users, target);
		System.out.println("CLOSESELECTION");
		if(this.open) {
			this.open = false;
			for(int i=0;i<openusers.length;i++){
				if(openusers[i]!=null){
					DataUser user = KillWolf.serverData.getUser(openusers[i]);
					if(user!=null)
					server.sendto("CLOSESELECTION"+ReceiveID,user.sk);
				}
			}
			//没选的默认弃权
			if(this.cb != null) this.cb.run(ReceiveID, count, users, target, openusers);
		}
		return true;
	}
	public void openSeletion(sServer server,DataUser du[],int RealLen, String WindowTitle,String ButtonTitle,String Tip,boolean isDark) {
		this.open = true;
		this.server = server;
		Selection[SelectionLen] = this;
		SelectionLen++;
		
		
		
		String STR = "";
		for(String i : users) {
			STR = STR +"\t"+ i;
		}
		openusers = new String[RealLen];
		this.target = new String[RealLen];
		int j = 0;
		for(DataUser i : du) {
			if(i != null) {
				openusers[j] = i.name;
				server.sendto("OPENSELETION"+timeLimit+"\t\t"+ReceiveID+"\t"+STR+"\t\t"+WindowTitle+"\t\t"+Tip+"\t\t"+ButtonTitle+"\t\t"+ (isDark?"DARK":"LIGHT"),i.sk); //RECEIVEID \t\t user1 \t user2
				j++;
			}
		}
		ThreadWait a = new ThreadWait(server);
		a.start();
	}
	public class ThreadWait extends Thread
	{
		sServer s;
		public ThreadWait(sServer server) {
			s = server;
		}
		public void run() {
			try {
				Thread.sleep(timeLimit*1000);
				closeSelection(s,false);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}
