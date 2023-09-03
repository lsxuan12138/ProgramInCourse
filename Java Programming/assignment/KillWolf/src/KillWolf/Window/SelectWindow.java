package KillWolf.Window;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JRootPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import KillWolf.KillWolf;
import KillWolf.Data.SelectCallBack;


/*
	构造类：String name,boolean ReceiveFromServer,String ReceiveID,boolean DisableParent,JFrame ParentWindow;
	方法：设置标题（可用于显示剩余时间） setTitle(title)
		启动 start(String tip,String ButtonTitle,String[] users, boolean canClose,SelectCallBack cb)
		关闭 getSelectionAndClose()
	接口：SelectCallBack 实现方法： run(String Selection) 当选择完毕后会回调，如果Selection为 **NONE** 表示没有选择/弃权
	
	for example: 
		SelectWindow = new SelectWindow("选择",true,"123",true,loginwindow.window);
		String[] b = {"a","b","c",UserName};
		SelectWindow.start("请选择", "投票", b, true,cb);
		
		cb另需构造
*/


public class SelectWindow {
	public boolean isRunCB = false;
	public String ReceiveID;
	public boolean ReceiveFromServer;
	public boolean DisableParent;
	public JFrame ParentWindow;
	public JFrame window;
	public JLabel CenterLabel;
	public JList<String> List;
	public String name;
	public JButton ConfirmButton;
	public JButton CloseButton;
	public String[] originUsers;
	public String[] usersTip;
	public String[] targetUsers;
	public int[] selectCount;
	public String LastSelect = "**NONE**";
	public boolean isSendFinal = false;
	public SelectWindow(String name,boolean ReceiveFromServer,String ReceiveID,boolean DisableParent,JFrame ParentWindow) {
		this.ReceiveFromServer = ReceiveFromServer;
		this.ReceiveID = ReceiveID;
		this.DisableParent = DisableParent;
		this.ParentWindow = ParentWindow;
		this.name = name;
	}
	public boolean DarkMode = false;
	public SelectCallBack cb;
	public void setColorMode(boolean dark) {
		this.DarkMode = dark;
		if(dark) {
			if(List != null) {
				List.setBackground(Color.black);
				List.setForeground(Color.white);
			}
			if(window != null) window.getContentPane().setBackground(Color.black);
			if(CenterLabel != null) CenterLabel.setForeground(Color.white);
			//if()	
		}
	}
	public void start(String tip,String ButtonTitle,String[] users, boolean canClose,SelectCallBack cb, int TimeLimit, String[] usertip) {
		this.cb = cb;
		if(usertip==null) {
			usertip = new String[users.length];
		}
		this.usersTip = usertip;
		window = new JFrame(name);
		window.setVisible(false);
		window.setSize(300, 400);//设置大小
		window.setLocationRelativeTo(null);//设置居中
		window.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);//设置可关闭
		window.setLayout(null);//设置绝对布局（窗口里面的内容不会随着窗口的改变而改变）
		window.setResizable(false);//设置窗口不可拉伸改变大小
		window.setAlwaysOnTop(true);
		window.setUndecorated(true);                                           //不显示标题栏,最大化,最小化,退出按钮
		window.getRootPane().setWindowDecorationStyle(JRootPane.WARNING_DIALOG);//使frame只剩下标题栏
		CenterLabel =new JLabel(tip);
		CenterLabel.setBounds(10,10,260,20);
		window.add(CenterLabel);
		users = users.clone();
		originUsers = new String[users.length];
		targetUsers = new String[users.length];
		for(int i=0;i<users.length;i++) {
			originUsers[i] = users[i];
			targetUsers[i] = "";
			if(usersTip[i]==null) usersTip[i]="";
			if(ReceiveFromServer) users[i] = "(0)"+users[i]+" "+usersTip[i];
			else users[i] = users[i]+" "+usersTip[i];

		}
		selectCount = new int[users.length];
		List = new JList(users);    //创建JList
		List.setBounds(10, 40, 260, 260);
		List.setFixedCellHeight(20);
		List.addListSelectionListener(new ListSelectionHandler());
		List.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        window.add(List);
		
        ConfirmButton = new JButton(ButtonTitle);
        ConfirmButton.setEnabled(false);
        ConfirmButton.setBounds(30, 310, 100, 40);
        ConfirmButton.addActionListener(new ButtonAction("confirm"));
		window.add(ConfirmButton);
        
		
		CloseButton = new JButton("弃权");
		if(canClose) {
			CloseButton.setEnabled(true);
		}else {
			CloseButton.setEnabled(false);
		}
		CloseButton.setBounds(160, 310, 100, 40);
		CloseButton.addActionListener(new ButtonAction("cancel"));
		window.add(CloseButton);
		
		
		if(this.DisableParent && this.ParentWindow != null) this.ParentWindow.setEnabled(false);
		
		TimeTh at = new TimeTh(TimeLimit);
		at.start();
		
		setColorMode(this.DarkMode);
		
        window.setVisible(true);
	}
	class TimeTh extends Thread{
		int time;
		public TimeTh(int time) {
			this.time = time-1;
		}
		public void run() {
			try {
				window.setTitle(time+"s "+name);
				while(time>0) {
					Thread.sleep(1000);
					time--;
					window.setTitle(time+"s "+name);
				}
				window.setTitle(name);
				ConfirmButton.setEnabled(false);
				CloseButton.setEnabled(false);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	public void setTitle(String Title) {
		window.setTitle(name + Title);
	}
	public void SELECTInfo(String ServerText) {
		if(!ReceiveFromServer) return;
		String[] c = ServerText.split("\t");
		// c[0] ReceiveID ,c[1] 发出者 ,c[2] 目标者, c[3] 原始目标（被取消）
		String[] nu = new String[this.originUsers.length];
		
		if(c[0].equals(this.ReceiveID)) {
			
			for(int i=0;i<this.originUsers.length;i++) {
				//nu[i] = this.originUsers[i];
				
				if(this.originUsers[i].equals(c[1])) {
					if(c[2].equals("**NONE**")) targetUsers[i] = " 弃权";
					else targetUsers[i] = " -> " + c[2]; 
				}
				if(this.originUsers[i].equals(c[2])) {
					selectCount[i]++;
					System.out.println(this.originUsers[i]+" +1 => "+ selectCount[i]);
				}
				if(this.originUsers[i].equals(c[3])) {
					selectCount[i]--;
					System.out.println(this.originUsers[i]+" -1 => "+ selectCount[i]);
				}
				System.out.println(this.originUsers[i]+" "+c[2]+" "+ selectCount[i]);
				//System.out.println("LIST0: "+nu[i]);
				//System.out.println("LISTc: "+selectCount[i]);
			}
			for(int i=0;i<nu.length;i++) {
				nu[i] = "(" +selectCount[i] + ")" +originUsers[i]+" "+usersTip[i]+ targetUsers[i]; 
				//System.out.println("LIST: "+nu[i]);
			}
			List.setListData(nu);
		}
		
	}
	class ListSelectionHandler implements ListSelectionListener {
        public void valueChanged(ListSelectionEvent e) { 
        	if(e.getValueIsAdjusting()) {
        		String t;
        		if(List.getSelectedIndex() == -1) {
        			//t = "**NONE**"; 
        			return;
        			}
        		else t = originUsers[List.getSelectedIndex()];
        		System.out.printf("Select: %s\n",t);
        		ConfirmButton.setEnabled(true);
        		
        		String a = LastSelect;
        		if(List.getSelectedIndex() == -1)
        			LastSelect = "**NONE**";
        		else LastSelect = originUsers[List.getSelectedIndex()];
        		
        		if(ReceiveFromServer) KillWolf.client.send("SELECT"+ReceiveID+"\t"+KillWolf.UserName+"\t"+t+"\t"+a);
        	}
            
            //output.append("LeadSelectionIndex is " + lsm.getLeadSelectionIndex() + "\n");
            
            
        }
    }
	class ButtonAction implements ActionListener{//开始游戏
		String ID;
		public ButtonAction(String ID) {
			this.ID = ID;
		}
		public void actionPerformed(ActionEvent event){
			isSendFinal = true;
			List.setEnabled(false);
    		CloseButton.setEnabled(false);
    		ConfirmButton.setEnabled(false);
    		isRunCB = true;
	    	if(ID.equals("confirm")) {
	    		//List.setEnabled(false);
	    		if(ReceiveFromServer) {
	    			KillWolf.client.send("FINALSELECT"+ReceiveID+"\t"+KillWolf.UserName+"\t"+LastSelect);
	    		}
	    		if(cb != null) cb.run(LastSelect);
	    	}else {
	    		//取消
	    		if(ReceiveFromServer) {
	    			KillWolf.client.send("SELECT"+ReceiveID+"\t"+KillWolf.UserName+"\t"+"**NONE**"+"\t"+LastSelect);
	    			KillWolf.client.send("FINALSELECT"+ReceiveID+"\t"+KillWolf.UserName+"\t"+"**NONE**");
	    		}
	    		if(cb != null) cb.run("**NONE**");
	    	}
	    	
	    	
    		
	    }
	}
	public String getSelectionAndClose(String ReceiveID) {
		if(!ReceiveID.equals(this.ReceiveID)) return "";
		boolean a = isRunCB;
		isRunCB = true;
		window.setVisible(false);
		window.setEnabled(false);
		if(!a && cb != null) {
			cb.run(LastSelect);
		}
		if(!isSendFinal && ReceiveFromServer){
			isSendFinal = true;
			KillWolf.client.send("FINALSELECT"+ReceiveID+"\t"+KillWolf.UserName+"\t"+LastSelect);
		}
		if(DisableParent && ParentWindow != null) ParentWindow.setEnabled(true);
		
		return LastSelect;
	}
}
