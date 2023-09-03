package KillWolf.SocketDeal;

import java.net.Socket;
import java.security.PublicKey;

import KillWolf.Data.SelectionEventCallBack;
import KillWolf.KillWolf;
import KillWolf.Data.DataUser;
import KillWolf.Data.SelectionEvent;
import KillWolf.KillWolf.serverData;
import SocketServe.CallBack;
import com.sun.source.tree.ReturnTree;

public class ServerDeal implements CallBack {
	public void run(String text,Socket sk){
		System.out.println(sk.getPort()+" Server Receive: "+text+"####");
		if(text.startsWith("ENTER")){
			if(KillWolf.GameMain!=null) {
				KillWolf.server.sendto("ENTERFAIL游戏已经开始，请稍后再试",sk);
				return;
			}
			String name = text.substring(5);
			for(int i=0;i<KillWolf.serverData.UsersLen;i++) {
				if(KillWolf.serverData.ServerUsers[i]!=null)
				if(name.equals(KillWolf.serverData.ServerUsers[i].name)) {
					//System.out.println("NAME: "+name+" "+i);
					KillWolf.server.sendto("ENTERFAIL当前房间存在重名，请更换用户名后再尝试连接。",sk);
					return;
				}
			}
			KillWolf.server.sendto("ENTERSUCCESS["+(KillWolf.serverData.UsersLen+1)+"]"+name,sk);
			KillWolf.serverData.ServerUsers[KillWolf.serverData.UsersLen] = new DataUser();
			KillWolf.serverData.ServerUsers[KillWolf.serverData.UsersLen].name="["+(KillWolf.serverData.UsersLen+1)+"]"+name;
			KillWolf.serverData.ServerUsers[KillWolf.serverData.UsersLen].sk=sk;
			KillWolf.serverData.ServerUsers[KillWolf.serverData.UsersLen].port=sk.getPort();
			KillWolf.serverData.UsersLen++;
			KillWolf.serverData.UsersRealLen++;
			String STR = "";
			for(int i=0;i<KillWolf.serverData.UsersLen;i++) {
				if(KillWolf.serverData.ServerUsers[i]!=null)
				STR = STR +'\t'+ KillWolf.serverData.ServerUsers[i].name;
			}
			KillWolf.server.send("NEWWAITUSERLIST"+STR.substring(1));
			return;
		}
		else if(text.equals("GETUSERS")) {
			String STR = "";
			for(DataUser i : KillWolf.serverData.ServerUsers) {
				if(i!=null)
				STR = STR +'\t'+ i.name;
			}
			KillWolf.server.send(STR.substring(1));
			return;
		}else if(text.startsWith("User Disconnect ")) {
			int port = Integer.parseInt(text.substring(16));
			for(int i=0;i<KillWolf.serverData.UsersLen;i++) {
				if(KillWolf.serverData.ServerUsers[i]!=null)
				if(port == KillWolf.serverData.ServerUsers[i].port) {
					System.out.println("NAME QUIT: "+KillWolf.serverData.ServerUsers[i].name);
					KillWolf.server.send("USERQUIT"+KillWolf.serverData.ServerUsers[i].name);//KillWolf.server.sendto("ENTERFAIL",sk);
					SelectionEvent.UserQuit(KillWolf.serverData.ServerUsers[i].name);
					KillWolf.serverData.ServerUsers[i] = null;
					KillWolf.serverData.UsersRealLen--;
					//添加选择时用户退出
					return;
				}
			}
			
			
		}else if(text.startsWith("SELECT")) {
			KillWolf.server.send(text);
		}else if(text.startsWith("FINALSELECT")) {
			String s[] = text.substring(11).split("\t");
			SelectionEvent se = KillWolf.serverData.Selection[Integer.parseInt(s[0])];
			se.update(s[1],s[2]);
		}else if(text.startsWith("GAMEREADY")){
			serverData.getUser(text.substring(9)).live = true;
			for(int i=0;i<serverData.UsersLen;i++){
				if(serverData.ServerUsers[i]!=null)
				if(!serverData.ServerUsers[i].live) return;
			}
			KillWolf.server.send("MESSAGE【系统】玩家全部进入，游戏将于3秒后开始");
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			KillWolf.serverData.Day = 0;
			KillWolf.serverData.night = false;

			(KillWolf.serverData.GameMainThread = new GameMainThread()).start();
		}else if(text.startsWith("USERSPEAK")){
			String[] submitMsg = text.substring(9).split("\t");
			KillWolf.server.send("MESSAGE【玩家】"+submitMsg[0]+" ："+submitMsg[1]);
			if(!KillWolf.serverData.freeSpeak) nextSpeak(submitMsg[0],serverData.Day);

		}else if(text.startsWith("YUYANEND")){
			KillWolf.serverData.GameMainThread.YuYanCompleted = true;
			KillWolf.serverData.GameMainThread.gameDay();
		}else if(text.startsWith("WIZARDANTI")) {
			String result = text.substring(10);
			if(result.equals("YES")) {
				DataUser u = KillWolf.serverData.getUser(KillWolf.serverData.deathUser);
				if(u!=null) u.live = true;
				KillWolf.server.sendto("MESSAGE【女巫】你为"+KillWolf.serverData.deathUser+"使用了解药。", sk);
				KillWolf.serverData.deathUser = "**NONE**";
				KillWolf.serverData.GameMainThread.WizardCompleted = true;
				KillWolf.serverData.GameMainThread.gameDay();
			}else if(result.equals("NO")){
				if(!KillWolf.serverData.deathUser.equals("**NONE**")) {
					DataUser u = KillWolf.serverData.getUser(KillWolf.serverData.deathUser);
					if(u!=null) u.live = false;
					KillWolf.server.sendto("MESSAGE【女巫】昨夜"+KillWolf.serverData.deathUser+"死亡，你没有使用解药。", sk);
				}
				else KillWolf.server.sendto("MESSAGE【女巫】昨夜无人死亡。", sk);
				KillWolf.server.sendto("WIZARDPOS", sk);
			}else if(result.equals("NONE")){
				if(!KillWolf.serverData.deathUser.equals("**NONE**")){
					DataUser u = KillWolf.serverData.getUser(KillWolf.serverData.deathUser);
					if(u!=null) u.live = false;
				}
				KillWolf.server.sendto("WIZARDPOS", sk);
			}
		}else if(text.startsWith("WIZARDPOS")) {
			String result = text.substring(9);
			KillWolf.serverData.deathUser2 = "**NONE**";
			if(result.startsWith("YES")) {
				DataUser u = KillWolf.serverData.getUser(result.substring(3));
				if(u!=null) u.live = false;
				KillWolf.serverData.deathUser2 = result.substring(3);
			}else if(result.equals("NO")){
				
			}else if(result.equals("NONE")){

			}
			KillWolf.serverData.GameMainThread.WizardCompleted = true;
			KillWolf.serverData.GameMainThread.gameDay();
		}
		else if(text.startsWith("HUNTSTART")){
			String result = text.substring(9);
			if(result.equals("YES")){
				KillWolf.server.sendto("HUNTSELECT", sk);
			}else if(result.equals("NO")){
				KillWolf.server.sendto("MESSAGE【猎人】你已死亡，没有使用技能。",sk);
				KillWolf.serverData.GameMainThread.dayWork();
			}else if(result.startsWith("LAST")){
				if(result.substring(4).equals("YES")){
					KillWolf.server.sendto("HUNTSELECTLAST", sk);
				}else if(result.substring(4).equals("NO")) {
					KillWolf.server.sendto("MESSAGE【猎人】你已死亡，没有使用技能。", sk);
					serverData.GameMainThread.gameNext();
				}	//KillWolf.serverData.GameMainThread.dayWork();
			}
		}else if(text.startsWith("HUNTSELE")){
			//System.out.println("猎人技能成功#########");
			String result = text.substring(8);
			if(result.startsWith("YES")) {
				String[] u = result.substring(3).split("\t");
				DataUser us = KillWolf.serverData.getUser(u[1]);
				if(us!=null) us.live = false;
				KillWolf.serverData.deathUser3 = u[1];
				System.out.println("猎人技能成功#########");
				KillWolf.server.send("MESSAGE【猎人】"+u[0]+"死前开枪杀死了"+ serverData.deathUser3);
				KillWolf.server.send("USERDIED"+serverData.deathUser3);
				KillWolf.serverData.GameMainThread.dayWork();
			}
			else if(result.equals("NO")){
				KillWolf.serverData.GameMainThread.dayWork();
			}else if (result.startsWith("LAST")){
				result = result.substring(4);
				if(result.startsWith("YES")) {
					String[] u = result.substring(3).split("\t");
					DataUser us = KillWolf.serverData.getUser(u[1]);
					if(us!=null) us.live = false;
					//KillWolf.serverData.getUser(u[1]).live = false;
					KillWolf.serverData.deathUser3 = u[1];
					System.out.println("猎人技能成功#########");
					KillWolf.server.send("MESSAGE【猎人】"+u[0]+"死前开枪杀死了"+ serverData.deathUser3);
					KillWolf.server.send("USERDIED"+serverData.deathUser3);
					//KillWolf.serverData.GameMainThread.dayWork();
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				else if(result.equals("NO")) {
				}
				serverData.GameMainThread.gameNext();
			}

		}

		//KillWolf.server.send("["+sk.getPort()+"]"+text);
	}
	public static boolean nextSpeak(String str,int Day){
		System.out.println("NOW SPEAK:"+KillWolf.serverData.nowSpeakUser+" CALLBACK:"+str);
		if(!KillWolf.serverData.nowSpeakUser.equals(str) || Day != serverData.Day) return false;
		else {
			DataUser[] liveUsers = (DataUser[]) KillWolf.serverData.getLivingUsers(false);
			String lastSpeak = str;
			if(serverData.StartSpeakName.equals("**VOTE**")) return true;
			for(int i = 0;i<liveUsers.length;i++){
				if(lastSpeak.equals(liveUsers[i].name)){
					KillWolf.server.sendto("FORBIDSPEAK",liveUsers[i].sk);
					
					if(i==liveUsers.length-1) i=-1;
					i=i+1;
					
					System.out.println("CHANGE SPEAK "+KillWolf.serverData.nowSpeakUser+" TO "+liveUsers[i].name);
					if(liveUsers[i].name.equals(serverData.StartSpeakName)){
						//一轮发言结束
						serverData.StartSpeakName = "**VOTE**";
						KillWolf.serverData.GameMainThread.gameVote();
						return true;
					}
					KillWolf.serverData.nowSpeakUser = liveUsers[i].name;
					KillWolf.server.send("MESSAGE【系统】轮到"+liveUsers[i].name+"发言");
					KillWolf.server.sendto("ENABLESPEAKAC",liveUsers[i].sk);
					SpeakThread spt = new SpeakThread(liveUsers[i].name, serverData.Day);
					spt.start();
					return true;
				}
				//if(i==0)

			}
			//找遍了所有人，但还没找到上一个发言人，可能已经退出，从第一个人开始重新发言。
			KillWolf.server.send("MESSAGE【系统】有用户退出，发言阶段重新开始。");
			for(int j=0;j<KillWolf.serverData.UsersLen;j++){
				if(KillWolf.serverData.ServerUsers[j] != null)
					if(KillWolf.serverData.ServerUsers[j].live) {
						serverData.StartSpeakName = KillWolf.serverData.ServerUsers[j].name;
						KillWolf.serverData.nowSpeakUser = KillWolf.serverData.ServerUsers[j].name;
						KillWolf.server.send("MESSAGE【系统】轮到"+KillWolf.serverData.ServerUsers[j].name+"发言");
						KillWolf.server.sendto("ENABLESPEAKAC",KillWolf.serverData.ServerUsers[j].sk);
						SpeakThread spt = new SpeakThread(KillWolf.serverData.ServerUsers[j].name, serverData.Day);
						spt.start();
						break;
					}
			}
			return true;
		}
	}
	
	
}

class SpeakThread extends Thread{
	String user;
	int Day;
	public SpeakThread (String user,int Day){
		this.user = user;
		this.Day = Day;
	}
	@Override
	public void run() {
		try {
			Thread.sleep(30*1000);
		}catch (InterruptedException e){
			e.printStackTrace();
		}
		ServerDeal.nextSpeak(user,Day);
	}


}
class WolfSelect implements SelectionEventCallBack {
	public void run(String ReceiveID,int[] count,String users[],String target[],String[] openusers) {
		String name = "**NONE**",sameName = "**NONE**";
		int max = 0,samemax = 0;
		System.out.println("####狼人投票--------");
		String STR = "Night"+KillWolf.serverData.Day;
		for(int i=0;i<users.length;i++){
			if(max < count[i]){
				max = count[i];
				name = users[i];
			}else if(max == count[i]) {
				sameName = users[i];
				samemax = max;
			}
			System.out.println("#"+users[i]+" "+count[i]);
			if(count[i] == 0) continue;
			STR = STR +"\n"+ "("+count[i]+")"+users[i];
			boolean first = true;
			for(int j=0;j<target.length;j++) {
				if(target[j] != null)
				if(target[j].equals(users[i])) {
					if(first) {
						first = false;
						STR = STR + " <- "+openusers[j];
					}else STR = STR +"、"+openusers[j];
				}
			}
		}
		System.out.println(name + "--------"+ max);
//		KillWolf.serverData.getUser(name).live = false;
		String tip ="";
		if(name == "**NONE**") {
			name = "存在平票，无人出局";
			KillWolf.serverData.deathUser="**NONE**";
		}else if(samemax == max){
			name = "存在平票，无人出局";
			KillWolf.serverData.deathUser="**NONE**";
		}else {
			tip = "被狼人暗杀";
			KillWolf.serverData.deathUser=name;
			//KillWolf.serverData.getUser(KillWolf.serverData.deathUser).live = false;
		}
		DataUser[] wu = KillWolf.serverData.getUsersWithID("狼人");
		String[] s = STR.split("\n");
		for(int i=0;i<wu.length;i++) {
			for(int j=0;j<s.length;j++) {
				KillWolf.server.sendto("MESSAGE【狼人投票】"+s[j],wu[i].sk); 
			}
			KillWolf.server.sendto("MESSAGE【最终结果】"+name+"("+max+")"+tip,wu[i].sk);
		}
		KillWolf.serverData.GameMainThread.gameWizard(KillWolf.serverData.deathUser); 
	}
}
class VoteSelection implements SelectionEventCallBack {

	public void run(String ReceiveID,int[] count,String users[],String target[],String[] openusers) {
		String name = "**NONE**",sameName = "**NONE**";
		int max = 0,samemax = 0;
		System.out.println("####投票--------");
		String STR = "投票结束";
		for(int i=0;i<users.length;i++){
			if(max < count[i]){
				max = count[i];
				name = users[i];
			}else if(max == count[i]) {
				sameName = users[i];
				samemax = max;
			}
			System.out.println("#"+users[i]+" "+count[i]);
			if(count[i] == 0) continue;
			STR = STR +"\n"+ "("+count[i]+")"+users[i];
			boolean first = true;
			for(int j=0;j<target.length;j++) {
				if(target[j] != null)
				if(target[j].equals(users[i])) {
					if(first) {
						first = false;
						STR = STR + " <- "+openusers[j];
					}else STR = STR +"、"+openusers[j];
				}
			}
		}
		System.out.println(name + "--------"+ max);
//		KillWolf.serverData.getUser(name).live = false;
		String tip ="";
		if(name == "**NONE**") {
			name = "存在平票，无人出局";
			KillWolf.serverData.deathUser4="**NONE**";
		}else if(samemax == max){
			name = "存在平票，无人出局";
			KillWolf.serverData.deathUser4="**NONE**";
		}else {
			tip = "投票出局";
			KillWolf.serverData.deathUser4=name;
			DataUser u =KillWolf.serverData.getUser(KillWolf.serverData.deathUser4);
			if(u!=null) u.live = false;
		}

//		System.out.println("--------------------"+STR);
//		DataUser[] wu = KillWolf.serverData.getUsersWithID("狼人");
		//DataUser[] wu = new DataUser[users.length];
//		if(KillWolf.serverData.getLivingUsers(false) instanceof DataUser[])
//		 wu = (DataUser[]) KillWolf.serverData.getLivingUsers(false);
		String id = "";
		//参与本场投票的人
		for (int i =0;i<users.length;i++){
			//wu[i]=KillWolf.serverData.getUser(users[i]);
			if(users[i].equals(name))
			{
				DataUser u = KillWolf.serverData.getUser(users[i]);
				if(u!=null)	id = u.identify;
			}
		}
		String[] s = STR.split("\n");
		//for(int i=0;i<wu.length;i++) {
			for(int j=0;j<s.length;j++) {
				KillWolf.server.send("MESSAGE【投票】"+s[j]);
				//KillWolf.server.sendto();
			}
			//if("".equals(id))
			//else KillWolf.server.sendto("MESSAGE【最终结果】"+name+"("+max+")"+tip+"，他是"+id,wu[i].sk);
		//}
		try {
			Thread.sleep(2*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		KillWolf.server.send("MESSAGE【最终结果】"+name+"("+max+")"+tip);
		if(!"存在平票，无人出局".equals(name))KillWolf.server.send("USERDIED"+name);

		if(serverData.GameMainThread.isWin()) return;

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if(id.equals("猎人"))KillWolf.serverData.GameMainThread.gameHunt(name);
		else{
			serverData.GameMainThread.gameNext();
		}

	}
}