package KillWolf.SocketDeal;

import KillWolf.KillWolf;
import KillWolf.Data.DataUser;
import KillWolf.Data.SelectionEvent;
import KillWolf.Window.GameMain;

public class GameMainThread extends Thread{
	public boolean YuYanCompleted = false;
	public boolean WizardCompleted = false;
	public void run() {
		KillWolf.serverData.deathUser = "**NONE**";
		KillWolf.serverData.deathUser2 = "**NONE**";
		KillWolf.serverData.deathUser3 =  "**NONE**";
		KillWolf.serverData.deathUser4 =  "**NONE**";
		gameNight();
	}
	public void gameNight(){
		if(KillWolf.serverData.GameMainThread.isWin()) return;

		KillWolf.serverData.Day++;
		KillWolf.serverData.night = true;


		KillWolf.server.send("MESSAGE【系统】天黑请闭眼 - Night"+KillWolf.serverData.Day);
		KillWolf.server.send("FORBIDSPEAK");

		//KillWolf.server.send("ENABLESPEAK");

		String[] allUserName = (String[])KillWolf.serverData.getLivingUsers(true);
		//给狼人弹窗
		DataUser[] wolfUser0 = KillWolf.serverData.getUsersWithID("狼人");
		DataUser[] wolfUser = new DataUser[wolfUser0.length];
		int wi = 0;
		for(int k=0;k<wolfUser0.length;k++) {
			if(wolfUser0[k].live) {
				wolfUser[wi] = wolfUser0[k];
				wi++;
			}
		}
		//String[] wolfUserName = KillWolf.serverData.ArrayDatatoName(wolfUser);

		try {
			Thread.sleep(2*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		KillWolf.server.send("MESSAGE【系统】狼人请睁眼，请选择你要暗杀的目标");
		KillWolf.serverData.Selection[KillWolf.serverData.SelectionLen] = new SelectionEvent(Integer.toString(KillWolf.serverData.SelectionLen),allUserName,30,new WolfSelect());
		KillWolf.serverData.Selection[KillWolf.serverData.SelectionLen].openSeletion(KillWolf.server, wolfUser, wi,"狼人出没","暗杀他","请选择你要暗杀的人",true);
		KillWolf.serverData.SelectionLen++;
		//预言家弹窗
		KillWolf.server.send("MESSAGE【系统】预言家请睁眼，请选择你要预言的目标");
		DataUser d[] = KillWolf.serverData.getUsersWithID("预言家");
		if (d.length == 1 && d[0].live) {

			YuYanCompleted = false;
			KillWolf.server.sendto("YUYANJIASTART",d[0].sk);
		}else YuYanCompleted = true;
		
		//女巫弹窗在狼人结束才开始，此处先置为false
		WizardCompleted = false;


		//等待预言、女巫完成再进入白天，进入白天自动进行判断是否能进入，在女巫结束还会调用一次。
		try {
			Thread.sleep(20*1000);
			YuYanCompleted = true;//等20秒，预言一定已经完成了
			gameDay();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void gameWizard(String diedUser) {
		DataUser d[] = KillWolf.serverData.getUsersWithID("女巫");
		//System.out.println(null.length);
		if (d.length == 1  && d[0].live) {
			WizardCompleted = false;
			KillWolf.server.sendto("WIZARDSTART"+diedUser,d[0].sk);	
		}else {
			if(!diedUser.equals("**NONE**")) {
				KillWolf.serverData.getUser(diedUser).live = false;
			}
			WizardCompleted = true;
			KillWolf.serverData.GameMainThread.gameDay();
		}
	}
	boolean isRunGameDay = false;
	public void gameDay() {
		if(isRunGameDay) return;
		if(!YuYanCompleted || !WizardCompleted) return;
		//如果都完成了则继续
		String death = KillWolf.serverData.deathUser;
		String death2 = KillWolf.serverData.deathUser2;
		isRunGameDay = true;

		KillWolf.server.send("MESSAGE【系统】天亮请睁眼 - Day"+KillWolf.serverData.Day);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String death3 = KillWolf.serverData.deathUser3;
		if(death2.equals("**NONE**") && death.equals("**NONE**")) {
			KillWolf.server.send("MESSAGE【系统】昨夜无人死亡");
		}else {
			if(!death.equals("**NONE**")){
				DataUser u = KillWolf.serverData.getUser(death);
				if(u!=null) u.live =false;
				KillWolf.server.send("USERDIED"+death);
			}
			if(!death2.equals("**NONE**")){
				DataUser u = KillWolf.serverData.getUser(death2);
				if(u!=null) u.live =false;
				KillWolf.server.send("USERDIED"+death2);
			}
			if(!death3.equals("**NONE**")){
				DataUser u = KillWolf.serverData.getUser(death3);
				if(u!=null) u.live =false;
				KillWolf.server.send("USERDIED"+death3);
			}
			//KillWolf.server.send("USERDIED"+(death.equals("**NONE**")?"":death)+(death2.equals("**NONE**")?"":("\t"+death2))+(death3.equals("**NONE**")?"":("\t"+death3)));
			if(death2.equals("**NONE**"))KillWolf.server.send("MESSAGE【系统】昨夜"+(death.equals("**NONE**")?"":death)+(death3.equals("**NONE**")?"":("、"+death3))+"死亡");
			else KillWolf.server.send("MESSAGE【系统】昨夜"+(death.equals("**NONE**")?"":death) +(death2.equals("**NONE**")?"":("、"+death2))+(death3.equals("**NONE**")?"":("、"+death3))+"死亡");
		}

		if(KillWolf.serverData.GameMainThread.isWin())return;

		//判断猎人是否存活，若死亡，向猎人弹窗，使用技能
		DataUser user = KillWolf.serverData.getUser(death);
		DataUser user2 = KillWolf.serverData.getUser(death2);
		if(user == null && user2 == null) dayWork();
		else if(user != null && user.identify.equals("猎人")){KillWolf.server.sendto("HUNTERSTART",user.sk);}
		else if(user2 != null && user2.identify.equals("猎人")){KillWolf.server.sendto("HUNTERSTART",user2.sk);}
		else dayWork();

		//向所有玩家弹窗，投票

		//向公屏输出，投票结果，若平票，则再次投票

		//再次判断猎人是否存活，若死亡，向猎人弹窗

//		}
		//KillWolf.server.send("MESSAGE【系统】"+whoWin+"胜，将在5秒后退出该窗口");
		//重置游戏
	}
	class TimeTha extends Thread{
		public void run() {
			try {
				Thread.sleep(1000*120);
				gameVote();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	public void dayWork (){
		String death = KillWolf.serverData.deathUser;
		String death2 = KillWolf.serverData.deathUser2;
		//String death3 = KillWolf.serverData.deathUser3;
		String firstdeath = "";


		//}else if(death2.equals("**NONE**") && !death.equals("**NONE**")){
//			//KillWolf.server.send("MESSAGE【系统】昨夜"+death+"死亡");
//			firstdeath = death;
//		}else if(death2.equals("**NONE**") && death.equals("**NONE**")) {
//			//KillWolf.server.send("MESSAGE【系统】昨夜无人死亡");
//		}
//		else if(!death2.equals("**NONE**") && !death.equals("**NONE**")){
//			firstdeath = death;
//			//KillWolf.server.send("MESSAGE【系统】昨夜"+death+"、"+death2+"死亡");
//		}
		if(!death.equals("**NONE**") ){
			firstdeath = death;
		}else if(!death2.equals("**NONE**") ){
			firstdeath = death2;
		}

		if(KillWolf.serverData.GameMainThread.isWin())return;
		//判断是否获胜

		//玩家轮流发言
		if(KillWolf.serverData.freeSpeak){
			KillWolf.server.send("MESSAGE【系统】现在有120秒的交流时间");
			KillWolf.server.send("ENABLESPEAKAC");
			TimeTha spt = new TimeTha();
			spt.start();
			return;
		}
		KillWolf.server.send("MESSAGE【系统】进入轮流发言阶段，每人有30秒发言时间");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		DataUser user = null;//(DataUser[]) KillWolf.serverData.getUser(firstdeath);

		if(firstdeath.equals("")) for(int j=0;j<KillWolf.serverData.UsersLen;j++){
			if(KillWolf.serverData.ServerUsers[j] != null)
				if(KillWolf.serverData.ServerUsers[j].live) firstdeath = KillWolf.serverData.ServerUsers[j].name;
		}
		for(int i = 0;i<KillWolf.serverData.UsersLen;i++){
			if(KillWolf.serverData.ServerUsers[i]==null) continue;

			if(firstdeath.equals(KillWolf.serverData.ServerUsers[i].name)){
				int j=i+1;
				for(;j<KillWolf.serverData.UsersLen;j++){
					if(KillWolf.serverData.ServerUsers[j] != null)
						if(KillWolf.serverData.ServerUsers[j].live) user = KillWolf.serverData.ServerUsers[j];
				}
				if(user == null) for(j=0;j<i;j++){
					if(KillWolf.serverData.ServerUsers[j] != null)
						if(KillWolf.serverData.ServerUsers[j].live) user = KillWolf.serverData.ServerUsers[j];
				}
				if(user == null) {
					KillWolf.serverData.GameMainThread.gameNext();
					System.out.println("ERROR !!!!!!!!!!!!!");
					return;
				}
				KillWolf.serverData.StartSpeakName = user.name;
				KillWolf.serverData.nowSpeakUser = user.name;
				KillWolf.server.send("MESSAGE【系统】轮到"+user.name+"发言");
				KillWolf.server.sendto("ENABLESPEAKAC",user.sk);

				SpeakThread spt = new SpeakThread(user.name,KillWolf.serverData.Day);
				spt.start();
				break;
			}
			//if(i==0)

		}
	}

	public void gameVote() {
		KillWolf.server.send("MESSAGE【系统】发言结束，现在开始进行投票");

		//投票弹窗
		String[] allUserName = (String[])KillWolf.serverData.getLivingUsers(true);
		DataUser[] allUser = (DataUser[]) KillWolf.serverData.getLivingUsers(false);
		//String[] wolfUserName = KillWolf.serverData.ArrayDatatoName(wolfUser);

		KillWolf.serverData.Selection[KillWolf.serverData.SelectionLen] = new SelectionEvent(Integer.toString(KillWolf.serverData.SelectionLen),allUserName,20,new VoteSelection());
		KillWolf.serverData.Selection[KillWolf.serverData.SelectionLen].openSeletion(KillWolf.server, allUser, allUser.length,"开始投票","投他","请选择",false);
		KillWolf.serverData.SelectionLen++;

	}
	public void gameHunt(String death) {

		DataUser user = KillWolf.serverData.getUser(death);
		if (user != null && user.identify.equals("猎人")) {
			KillWolf.server.sendto("HUNTERSTARTLAST", user.sk);
		}
	}
	public void gameNext(){
		if(!isWin()){
			(KillWolf.serverData.GameMainThread = new GameMainThread()).start();
		}
	}
	public boolean isWin(){
		String whoWin = KillWolf.serverData.whoWin();
		if(!(whoWin.equals("无"))){
			KillWolf.server.send("MESSAGE【系统】游戏结束，"+whoWin+"胜");

			gameEnd();
			return true;
			//KillWolf.server.send("MESSAGE【系统】将在5秒后关闭窗口");
		}
		return false;
	}
	public void gameEnd(){
		KillWolf.server.send("MESSAGE【系统】游戏统计");
		DataUser[] d = KillWolf.serverData.ServerUsers;
		for(int i=0;i<KillWolf.serverData.UsersLen;i++){
			if(d[i]!=null){
				KillWolf.server.send("MESSAGE【系统】"+d[i].name+" "+d[i].identify+" "+(d[i].live?"存活":"淘汰"));
			}
		}
		KillWolf.serverData.freeSpeak = true;
		KillWolf.server.send("ENABLESPEAKEND");

	}
}
