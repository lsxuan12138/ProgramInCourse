package KillWolf.SocketDeal;

import java.awt.Color;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import KillWolf.Data.SelectCallBack;
import KillWolf.KillWolf;
import KillWolf.Data.DataUser;
import KillWolf.Window.GameMain;
import KillWolf.Window.SelectWindow;
import SocketServe.CallBack;

public class ClientDeal implements CallBack {
	//public SelectWindow SelectWindow;
	public void run(String text,Socket sk){
		System.out.println(sk.getLocalPort()+" Client Receive: "+text+"####");
		if(text.startsWith("ENTERSUCCESS"))
		{
			KillWolf.UserName = text.substring(12);
			KillWolf.loginwindow.LoginButton.setEnabled(false);
			KillWolf.loginwindow.userName.setEnabled(false);
			KillWolf.goWaitRoom();
		}else if(text.startsWith("ENTERFAIL")){
			JOptionPane.showMessageDialog(null, text.substring(9));
			/*try {
				sk.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
		}else if(text.startsWith("NEWWAITUSERLIST")){
			String users[] = text.substring(15).split("\t");
			//KillWolf.WaitRoom.setList
			for(int i=0;i<users.length;i++) {
				KillWolf.Users[i]= new DataUser();
				KillWolf.Users[i].name = users[i];
			}
			KillWolf.UsersRealLen = KillWolf.UsersLen = users.length;
			//KillWolf.WaitRoom.UserList.setListData(users);
			KillWolf.WaitRoom.setUserCount(users);
			
			System.out.println(sk.getLocalPort()+" USERS: " + KillWolf.UserName+"\t"+KillWolf.UsersRealLen);
		}else if(text.startsWith("Disconnect from Server")){
			if(KillWolf.GameMain != null) {
				KillWolf.GameMain.getFrame().setAlwaysOnTop(true);
				JOptionPane.showMessageDialog(KillWolf.GameMain.getFrame(), "服务器连接丢失");
			}else {
				JOptionPane.showMessageDialog(null, "服务器连接丢失");
			}
			//JOptionPane.setDefaultLocale(null);
			System.exit(0);
		}else if(text.startsWith("USERQUIT")){
			String user = text.substring(8),STR = "";
			for(int i=0;i<KillWolf.Users.length;i++) {
				if(KillWolf.Users[i] != null)
					if(KillWolf.Users[i].name.equals(user)) {
						KillWolf.Users[i] = null;
					}else {
						STR = STR + "\t" +KillWolf.Users[i].name;
					}
			}
			String users[] = STR.substring(1).split("\t");
			//KillWolf.WaitRoom.UserList.setListData();
			KillWolf.UsersRealLen--;
			//KillWolf.WaitRoom.settitle("玩家数量："+ KillWolf.UsersRealLen)//+" 总数："+KillWolf.UsersLen);
			KillWolf.WaitRoom.setUserCount(users);

			if(KillWolf.GameMain == null) return;

			int usdi=0,temp=1;
			for(int i=0;i<KillWolf.GameMain.UserOutListData.length;i++){
				if(KillWolf.GameMain.UserOutListData[i].startsWith(user+" ")){
					temp=0;
					KillWolf.GameMain.UserOutListData[i] = KillWolf.GameMain.UserOutListData[i] + "(断线)";
					break;
				}//else usod[i]=KillWolf.GameMain.UserOutListData[i];
			}

			String usod[]=new String[KillWolf.GameMain.UserOutListData.length+temp];
			for(int i=0;i<KillWolf.GameMain.UserOutListData.length;i++){
				usod[i] = KillWolf.GameMain.UserOutListData[i];
			}

			if(temp==1){
				String usda[]=new String[KillWolf.GameMain.UserListData.length-1],
						usd[]=new String[KillWolf.GameMain.UserListData.length-1],
						usdt[]=new String[KillWolf.GameMain.UserListData.length-1];
				usod[usod.length-1] = user+" (断线)";
				for(int i=0;i<KillWolf.GameMain.UserListData.length;i++) {
					if(KillWolf.GameMain.UserListData[i].equals(user))
					{
						usod[usod.length-1] = usod[usod.length-1] + KillWolf.GameMain.UserListTipData[i];
					}else{
						usd[usdi]=KillWolf.GameMain.UserListData[i];
						usdt[usdi]=KillWolf.GameMain.UserListTipData[i];
						usda[usdi]=usd[usdi]+usdt[usdi];
						usdi++;
					}
				}
				KillWolf.GameMain.UserListData = usd;
				KillWolf.GameMain.UserListTipData = usdt;
				KillWolf.GameMain.getUserList().setListData(usda);
			}
			KillWolf.GameMain.UserOutListData = usod;
			KillWolf.GameMain.getOutUserList().setListData(usod);
			KillWolf.GameMain.addToScreen("【系统】用户 "+user+" 退出房间。");
			//KillWolf.UsersLen = KillWolf.Users.length;
		}else if(text.startsWith("GAMESTART")) {
			String[] STR = text.substring(9).split("#u#");
			DataUser[] du = new DataUser[STR.length];
			if(STR[0].equals("TRUE")){
				KillWolf.freeSpeak = true;
			}else{
				KillWolf.freeSpeak = false;
			}
    	    for(int i=0;i<STR.length-1;i++) {
    	    	du[i] = new DataUser();
    	    	String[] p = STR[i+1].split("\t");

    	    	du[i].name = p[0];
    	    	du[i].identify = p[1];
    	    }
			KillWolf.Users = du;
			KillWolf.goGameMain();
		}else if(text.startsWith("SELECT")) {
			if(KillWolf.SelectWindow != null) KillWolf.SelectWindow.SELECTInfo(text.substring(6));
		}else if(text.startsWith("OPENSELETION")) {
			String a[] = (text.substring(12).split("\t\t")) ;
			JFrame fra = null;
			if(KillWolf.GameMain!=null) fra = KillWolf.GameMain.getFrame();
			KillWolf.SelectWindow = new SelectWindow(a[3],true,a[1],true,fra);
			if(a[6].equals("DARK"))KillWolf.SelectWindow.setColorMode(true);
			String[] b = a[2].split("\t");//KillWolf.GameMain.UserListData;

			String myId = KillWolf.GameMain.userSelf.identify;
			String[] tip = new String[b.length];
			if(myId.equals("狼人")) {//|| myId.equals("预言家")
				for (int j = 0; j < b.length; j++) {
					for (int i = 0; i < KillWolf.UsersLen; i++) {
						if (KillWolf.Users[i] != null) {
							if (KillWolf.Users[i].name.equals(b[j])) {
								if(myId.equals("狼人")){
									if(KillWolf.Users[i].identify.equals("狼人")){
										tip[j] = "("+KillWolf.Users[i].identify+")";
									}
								}/*else if(myId.equals("预言家" )&& KillWolf.Users[i].hasConfirmID){
									{
										if(KillWolf.Users[i].identify.equals("狼人")){
	“)";
										}
									}

								}*/else{
									tip[j] = "";
								}
							}
						}
					}
				}
			}

			KillWolf.SelectWindow.start(a[4], a[5], b, true,null, Integer.parseInt(a[0]),tip);
		}else if(text.startsWith("CLOSESELECTION")) {
			KillWolf.SelectWindow.getSelectionAndClose(text.substring(14));
		}else if(text.startsWith("MESSAGE")) {
			if(text.substring(7).startsWith("【系统】天黑请闭眼")){
				KillWolf.GameMain.setDark();
				KillWolf.GameMain.getFrame().setTitle("狼人杀 "+text.substring(19));
				//天黑请闭眼 - Night
			}
			if(text.substring(7).startsWith("【系统】天亮请睁眼")){
				KillWolf.GameMain.getFrame().setTitle("狼人杀 "+text.substring(19));
				KillWolf.GameMain.setWhite();
			}
			KillWolf.GameMain.addToScreen(text.substring(7));
		}else if(text.startsWith("YUYANJIASTART")){
			if(!KillWolf.GameMain.userSelf.identify.equals("预言家"))return;

			KillWolf.SelectWindow = new SelectWindow("预言",false,"预言家",true,KillWolf.GameMain.getFrame());
			KillWolf.SelectWindow.setColorMode(true);
			//String[] users=new String[]
			SelectCallBack cb = Selection -> {
				System.out.println("SelectFinal:" + Selection);
				//System.out.println("预言："+st);
				if(Selection.equals("**NONE**")) {
					KillWolf.GameMain.addToScreen("【预言】你没有进行预言。");
					KillWolf.SelectWindow.getSelectionAndClose("预言家");
					KillWolf.client.send("YUYANEND");
					return;
				}
				String str = "";
				for(int i=0;i<KillWolf.UsersLen;i++){
					if(KillWolf.Users[i] == null) continue;
					if(KillWolf.Users[i].name.equals(Selection)){
						for(int j=0;j<KillWolf.GameMain.UserListData.length;j++){
							System.out.println("###################List:" + KillWolf.GameMain.UserListData[j]+"#");
							if(KillWolf.GameMain.UserListData[j].equals(Selection)){
								str = (KillWolf.Users[i].identify.equals("狼人")?"坏人":"好人");
								KillWolf.GameMain.UserListTipData[j] = "(" + str + ")";
								str = "预言："+KillWolf.Users[i].name+"是"+str;
								KillWolf.SelectWindow.CenterLabel.setText(str);
								KillWolf.SelectWindow.CenterLabel.setForeground(Color.yellow);
								break;
							}
						}

					}
				}
				String [] usn = KillWolf.GameMain.UserListData;
				String [] usnt = KillWolf.GameMain.UserListTipData;
				String [] usna = new String[usn.length];
				for (int i=0;i<usn.length;i++) {
					usna[i]=usn[i]+" "+usnt[i];
				}
				KillWolf.GameMain.getUserList().setListData(usna);
				KillWolf.SelectWindow.getSelectionAndClose("预言家");
				KillWolf.GameMain.addToScreen("【预言】"+str.substring(3));
				KillWolf.client.send("YUYANEND");
				//JOptionPane.showMessageDialog(KillWolf.GameMain.getFrame(),str);
				
			};
			KillWolf.SelectWindow.start("请选择你要预言的人", "预言", KillWolf.GameMain.UserListData,true,cb,20,KillWolf.GameMain.UserListTipData);
			SleepThread sth = new SleepThread("预言家");
			sth.start();
			
			//System.out.println("预言："+st);
			//if(st.equals("**NONE**")) {
		//		KillWolf.GameMain.addToScreen("【预言】你没有进行预言。");
			//}
		}
		else if(text.startsWith("HUNTERSTART")){
			if(!KillWolf.GameMain.userSelf.identify.equals("猎人"))return;
			int res=JOptionPane.showConfirmDialog(KillWolf.GameMain.getFrame(), "你已死亡，是否使用技能？", "消息", JOptionPane.YES_NO_OPTION);
			if(res==JOptionPane.YES_OPTION){
				if(text.substring(11).startsWith("LAST"))KillWolf.client.send("HUNTSTARTLASTYES");
				else KillWolf.client.send("HUNTSTARTYES");
				System.out.println("选择是后执行的代码");    //点击“是”后执行这个代码块
			}else{
				if(text.substring(11).startsWith("LAST"))KillWolf.client.send("HUNTSTARTLASTYES");
				else KillWolf.client.send("HUNTSTARTNO");
				System.out.println("选择否后执行的代码");    //点击“否”后执行这个代码块
				return;
			}

		}else if(text.startsWith("HUNTSELECT")){
			if(!KillWolf.GameMain.userSelf.identify.equals("猎人"))return;

			KillWolf.SelectWindow = new SelectWindow("开枪",false,"猎人技能",true,KillWolf.GameMain.getFrame());
			KillWolf.SelectWindow.setColorMode(true);

			SelectCallBack cb = Selection -> {
				System.out.println("SelectFinal:" + Selection);
				//System.out.println("预言："+st);
				if(Selection.equals("**NONE**")) {
					KillWolf.GameMain.addToScreen("【猎人】你没有开枪。");
					KillWolf.SelectWindow.getSelectionAndClose("猎人技能");
					if(text.substring(10).startsWith("LAST"))KillWolf.client.send("HUNTSELELASTNO");
					else KillWolf.client.send("HUNTSELENO");
					return;
				}
				KillWolf.GameMain.addToScreen("【猎人】你对"+Selection+"进行射击。");
				KillWolf.SelectWindow.getSelectionAndClose("猎人技能");
				if(text.substring(10).startsWith("LAST"))KillWolf.client.send("HUNTSELELASTYES"+KillWolf.UserName+"\t"+Selection);
				else KillWolf.client.send("HUNTSELEYES"+KillWolf.UserName+"\t"+Selection);
			};
			KillWolf.SelectWindow.start("你要对谁开枪？", "开枪", KillWolf.GameMain.UserListData,true,cb,20,null);
			SleepThread sth = new SleepThread("猎人技能");
			sth.start();
		}else if(text.equals("FORBIDSPEAK")){
			KillWolf.GameMain.forbidSubmit();
		}else if(text.equals("ENABLESPEAK")){
			KillWolf.GameMain.ableSubmit();
		}else if(text.equals("ENABLESPEAKEND")){
			KillWolf.freeSpeak = true;
			KillWolf.GameMain.ableSubmit();


			String usda[]=new String[KillWolf.GameMain.UserListData.length];
			for(int i=0;i<KillWolf.GameMain.UserListData.length;i++) {
				for(int j=0;j<KillWolf.UsersLen;j++){
					if(KillWolf.Users[j]==null)continue;
					if(KillWolf.Users[j].name.equals(KillWolf.GameMain.UserListData[i])){
						usda[i]=KillWolf.GameMain.UserListData[i]+" ("+KillWolf.Users[j].identify+")";
					}
				}
			}
			KillWolf.GameMain.getUserList().setListData(usda);

		}else if(text.equals("ENABLESPEAKAC")){
			KillWolf.GameMain.ableSubmit();
			KillWolf.GameMain.startSpeakClock();
		}else if(text.startsWith("WIZARDSTART")) {
			if(!KillWolf.GameMain.userSelf.identify.equals("女巫"))return;
			String name = text.substring(11);
			if(KillWolf.GameMain.userSelf.haveAntidote) {
				if(name.equals("**NONE**")) {
					KillWolf.client.send("WIZARDANTINO");
					return;
				}
				int res=JOptionPane.showConfirmDialog(KillWolf.GameMain.getFrame(), "昨夜"+name+"死亡，是否使用解药？", "消息", JOptionPane.YES_NO_OPTION);
		        if(res==JOptionPane.YES_OPTION){ 
		        	KillWolf.GameMain.userSelf.haveAntidote = false;
		        	KillWolf.client.send("WIZARDANTIYES");
		            System.out.println("选择是后执行的代码");    //点击“是”后执行这个代码块
		        }else{
		        	KillWolf.client.send("WIZARDANTINO");
		            System.out.println("选择否后执行的代码");    //点击“否”后执行这个代码块
		            return;
		        } 
			}else {
				KillWolf.client.send("WIZARDANTINONE");
			}
		}else if(text.startsWith("WIZARDPOS")) {
			if(!KillWolf.GameMain.userSelf.identify.equals("女巫"))return;

			KillWolf.SelectWindow = new SelectWindow("毒药",false,"女巫毒药",true,KillWolf.GameMain.getFrame());
			KillWolf.SelectWindow.setColorMode(true);
			//String[] users=new String[]
			SelectCallBack cb = Selection -> {
				System.out.println("SelectFinal:" + Selection);
				//System.out.println("预言："+st);
				if(Selection.equals("**NONE**")) {
					KillWolf.GameMain.addToScreen("【女巫】你没有使用毒药。");
					KillWolf.SelectWindow.getSelectionAndClose("女巫毒药");
					KillWolf.client.send("WIZARDPOSNO");
					return;
				}
				KillWolf.GameMain.addToScreen("【女巫】你对"+Selection+"使用了毒药。");
				KillWolf.SelectWindow.getSelectionAndClose("女巫毒药");
				KillWolf.client.send("WIZARDPOSYES"+Selection);
				//JOptionPane.showMessageDialog(KillWolf.GameMain.getFrame(),str);
			};
			KillWolf.SelectWindow.start("你要对谁使用毒药？", "使用", KillWolf.GameMain.UserListData,true,cb,20,KillWolf.GameMain.UserListTipData);
			SleepThread sth = new SleepThread("女巫毒药");
			sth.start();
		}else if(text.startsWith("USERDIED")){
			String user = text.substring(8),STR = "";
			/*for(int i=0;i<KillWolf.Users.length;i++) {
				if(KillWolf.Users[i] != null)
					if(KillWolf.Users[i].name.equals(user)) {
						KillWolf.Users[i].live = false;
					}else {
						//STR = STR + "\t" +KillWolf.Users[i].name;
					}
			}
			String users[] = STR.substring(1).split("\t");
			*///KillWolf.WaitRoom.UserList.setListData();
			//KillWolf.UsersRealLen--;
			//KillWolf.WaitRoom.settitle("玩家数量："+ KillWolf.UsersRealLen)//+" 总数："+KillWolf.UsersLen);
			//KillWolf.WaitRoom.setUserCount(users);

			//if(KillWolf.GameMain == null) return;
			int pp=0;
			for(;pp<KillWolf.UsersLen;pp++){
				if(KillWolf.Users[pp] == null) continue;
				if(KillWolf.Users[pp].name.equals(user)){
					pp=-1;
					break;
				}
			}
			if(pp!=-1) return;

			String usda[]=new String[KillWolf.GameMain.UserListData.length-1],
					usd[]=new String[KillWolf.GameMain.UserListData.length-1],
					usdt[]=new String[KillWolf.GameMain.UserListData.length-1],
					usod[]=new String[KillWolf.GameMain.UserOutListData.length+1];
			int usdi=0;
			for(int i=0;i<KillWolf.GameMain.UserOutListData.length;i++){
				usod[i]=KillWolf.GameMain.UserOutListData[i];
			}
			usod[usod.length-1] = user+" (淘汰)";
			if(user.equals(KillWolf.UserName)){
				KillWolf.GameMain.getIdentity().setText(KillWolf.UserName+"("+KillWolf.GameMain.userSelf.identify+")，你已被淘汰");
			}
			for(int i=0;i<KillWolf.GameMain.UserListData.length;i++) {
				if(KillWolf.GameMain.UserListData[i].equals(user))
				{
					usod[usod.length-1] = usod[usod.length-1] + KillWolf.GameMain.UserListTipData[i];
				}else{
					usd[usdi]=KillWolf.GameMain.UserListData[i];
					usdt[usdi]=KillWolf.GameMain.UserListTipData[i];
					usda[usdi]=usd[usdi]+usdt[usdi];
					usdi++;
				}
			}

			KillWolf.GameMain.UserListData = usd;
			KillWolf.GameMain.UserListTipData = usdt;
			KillWolf.GameMain.UserOutListData = usod;
			KillWolf.GameMain.getUserList().setListData(usda);
			KillWolf.GameMain.getOutUserList().setListData(usod);
//			System.out.println(text);
//			String[] result = text.substring(8).split("\t");
//			String[] users = new String[KillWolf.GameMain.UserListData.length- result.length];
//			String[] outUsers = new String[KillWolf.GameMain.UserOutListData.length+result.length];
//			for(int j=0;j< result.length;j++){
//				if(result[j].equals(KillWolf.GameMain.userSelf.name))KillWolf.GameMain.userSelf.live=false;
//				for(int k=0;k< KillWolf.GameMain.UserListData.length;k++){
//					if(KillWolf.GameMain.UserListData[k].startsWith(result[j])){
//						result[j]=KillWolf.GameMain.UserListData[k];
//						KillWolf.GameMain.UserListData[k]=null;
//					}
//				}
//			}
//			int i=0;
//			for (;i<KillWolf.GameMain.UserOutListData.length;i++){
//				outUsers[i] = KillWolf.GameMain.UserOutListData[i];
//			}
//			int j=i;
//			for(;i<outUsers.length;i++){
//				outUsers[i]=result[i-j]+"(淘汰)";
//			}
//			KillWolf.GameMain.UserOutListData=outUsers;
//			KillWolf.GameMain.getOutUserList().setListData(outUsers);
//			int i2 = 0;
//			for(int i1 = 0;i1< KillWolf.GameMain.UserListData.length;i1++){
//				if(KillWolf.GameMain.UserListData[i1]!=null&& !KillWolf.GameMain.UserListData[i1].equals("**NONE**")){
//					users[i2++]=KillWolf.GameMain.UserListData[i1];
//				}
//			}
//			KillWolf.GameMain.UserListData = users;
//			KillWolf.GameMain.getUserList().setListData(users);
 		}



			//	+timeLimit+"\t\t"+ReceiveID+"\t"+STR,du[j].sk); //RECEIVEID \t\t user1 \t user2
		
	}

}


class SleepThread extends Thread{
	String sss;
	public SleepThread(String a) {
		this.sss = a;
	}
	public void run() {
		try {
			Thread.sleep(20*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		KillWolf.SelectWindow.getSelectionAndClose(sss);
	}
}

