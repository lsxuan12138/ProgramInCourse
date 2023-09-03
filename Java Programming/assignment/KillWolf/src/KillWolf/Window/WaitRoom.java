package KillWolf.Window;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

import KillWolf.KillWolf;
import KillWolf.Data.DataUser;
import KillWolf.SocketDeal.ClientDeal;

import java.awt.*;
import java.awt.event.*;
import java.util.Random;

import SocketServe.*;

public class WaitRoom {
	public JButton LoginButton = null;
	public JTextField peoCount = null;
	public JTextField wolfCount = null;
	public JCheckBox wizardOption;
	public JCheckBox hunterOption;
	public JCheckBox yuyanjiaOption;
	public JCheckBox winmodeOption;
	public JCheckBox speakOption;
	//public JCheckBox optionD;
	public JList UserList;
	public JFrame window = null;
	public JLabel Userlenlabel;
	public JLabel Charlenlabel;
	public String MIP = null;
	public int CharLen = 0;
	public int peoLen = 1;
	public int wolfLen = 1;
	public int wizardLen = 0;
	public int hunterLen = 0;
	public int yuyanjiaLen = 0;
	public void reset(String ip,String name,boolean isServer) {
		KillWolf.serverData.freeSpeak = false;
		KillWolf.serverData.playMode = false;
		
		window = new JFrame("狼人杀 "+name+" "+ip);
		window.setVisible(false);
		window.setSize(400, 420);//设置大小
		window.setLocationRelativeTo(null);//设置居中
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//设置可关闭
		window.setLayout(null);//设置绝对布局（窗口里面的内容不会随着窗口的改变而改变）
		window.setResizable(false);//设置窗口不可拉伸改变大小
		//设置用户名标签
		JLabel username_label =new JLabel("用户名："+name);
		username_label.setBounds(10,10,200,20);
		window.add(username_label);
		//设置标签
		JLabel ServerIp_label =new JLabel("服务器地址："+KillWolf.ServerIp);
		ServerIp_label.setBounds(10,40,200,20);
		window.add(ServerIp_label);
		//设置标签
		Userlenlabel =new JLabel("在线玩家数："+KillWolf.UsersRealLen);
		Userlenlabel.setBounds(10,150,200,20);
		window.add(Userlenlabel);
		JLabel label2 =new JLabel("等待开始...");
		label2.setBounds(10,70,200,20);
		window.add(label2);
		this.MIP = ip;
		//设置按钮
		
		if(isServer) {
			LoginButton = new JButton("开始游戏");
			//LoginButton.setEnabled(false);
		}else {
			LoginButton = new JButton("等待房主开始");
			LoginButton.setEnabled(false);
		}
		LoginButton.setBounds(10, 120, 100, 20);
		window.add(LoginButton);
		
        LinkAction LinkAction = new LinkAction();  
        LoginButton.addActionListener(LinkAction);  
        
        
        String[] items=new String[10];
        UserList=new JList(items);    //创建JList
        UserList.setBounds(200, 10, 150, 300);
        UserList.setFixedCellHeight(20);
        window.add(UserList);
        
        if(KillWolf.isServer) {
	        //设置文本框
	        Charlenlabel =new JLabel("已选角色数："+CharLen);
	        Charlenlabel.setBounds(10,170,200,20);
			window.add(Charlenlabel);
			
			JLabel Peolenlabel =new JLabel("平民");
			Peolenlabel.setBounds(10,200,40,20);
			window.add(Peolenlabel);
	        peoCount = new JTextField();
	        peoCount.setBounds(50,200,40,20);
	        peoCount.setText(Integer.toString(peoLen));
	  		window.add(peoCount);
	        
	  		JLabel Wolflenlabel =new JLabel("狼人");
	  		Wolflenlabel.setBounds(10,220,40,20);
			window.add(Wolflenlabel);
			wolfCount = new JTextField();
			wolfCount.setBounds(50,220,40,20);
			wolfCount.setText(Integer.toString(wolfLen));
	  		window.add(wolfCount);
	  		
	  		
	  		Document dt = wolfCount.getDocument();
	        dt.addDocumentListener(new TextListener("wolf"));
	        wolfCount.addKeyListener(new CountKeyListener());
	        
	        
	        Document dt2 = peoCount.getDocument();
	        dt2.addDocumentListener(new TextListener("people"));
	        peoCount.addKeyListener(new CountKeyListener());
	        
	  		//TextOnValueChanged WAL = new TextOnValueChanged();
	  		//wolfCount.addActionListener(WAL);
	  		//peoCount.addActionListener(WAL);
	  		
	  		wizardOption = new JCheckBox("女巫");
	  		wizardOption.setBounds(10,240,80,20);
			hunterOption = new JCheckBox("猎人");
			hunterOption.setBounds(10,260,80,20);
			yuyanjiaOption = new JCheckBox("预言家");
			yuyanjiaOption.setBounds(10,280,100,20);
			//optionD = new JCheckBox("D.");
			
			
			wizardOption.addItemListener(new CheckValueChanged("wizard"));
			hunterOption.addItemListener(new CheckValueChanged("hunter"));
			yuyanjiaOption.addItemListener(new CheckValueChanged("yuyanjia"));
			
			window.add(wizardOption);
			window.add(hunterOption);
			window.add(yuyanjiaOption);
			
			
			winmodeOption = new JCheckBox("屠城玩法（狼人需要全部淘汰民和神）");
	  		winmodeOption.setBounds(10,320,300,20);
			speakOption = new JCheckBox("允许随时发言");
			speakOption.setBounds(10,340,100,20);
			
			winmodeOption.addItemListener(new CheckValueChanged("获胜玩法"));
			speakOption.addItemListener(new CheckValueChanged("发言"));
			
			window.add(winmodeOption);
			window.add(speakOption);
			
			updateCharCount(false);
        }
		window.setVisible(true);//设置面板可见
		
		
		//run(new ListTest(),250,375);
		
	}
	public void setUserCount(String users[]) {
		Userlenlabel.setText("在线玩家数："+KillWolf.UsersRealLen);
		UserList.setListData(users);
        UserList.setFixedCellHeight(20);
        
        updateCharCount(false);
		//window.setTitle(Title);
	}
	class LinkAction implements ActionListener{//开始游戏
	    public void actionPerformed(ActionEvent event){  
	    	if(updateCharCount(true)) {
	    		LoginButton.setEnabled(false);
	    		Random r = new Random();
	    		//r.nextInt();
	    	    r.setSeed(System.currentTimeMillis());
	    	    int n;
	    	    if(wizardOption.isSelected()) {
	    	    	do{n = r.nextInt(KillWolf.serverData.UsersLen);}
	    	    	while(KillWolf.serverData.ServerUsers[n] == null || KillWolf.serverData.ServerUsers[n].identify != null);
	    	    	KillWolf.serverData.ServerUsers[n].identify = "女巫";
	    	    }
	    	    if(hunterOption.isSelected()) {
	    	    	do{n = r.nextInt(KillWolf.serverData.UsersLen);}
	    	    	while(KillWolf.serverData.ServerUsers[n] == null || KillWolf.serverData.ServerUsers[n].identify != null);
	    	    	KillWolf.serverData.ServerUsers[n].identify = "猎人";
	    	    }	
	    	    if(yuyanjiaOption.isSelected()) {
	    	    	do{n = r.nextInt(KillWolf.serverData.UsersLen);}
	    	    	while(KillWolf.serverData.ServerUsers[n] == null || KillWolf.serverData.ServerUsers[n].identify != null);
	    	    	KillWolf.serverData.ServerUsers[n].identify = "预言家";
	    	    }
	    	    for(int i=0;i<wolfLen;i++){
	    	    	do{n = r.nextInt(KillWolf.serverData.UsersLen);}
	    	    	while(KillWolf.serverData.ServerUsers[n] == null || KillWolf.serverData.ServerUsers[n].identify != null);
	    	    	KillWolf.serverData.ServerUsers[n].identify = "狼人";
	    	    }
	    	    
	    	    for(int i=0;i<KillWolf.serverData.UsersLen;i++){
	    	    	if(KillWolf.serverData.ServerUsers[i] != null && KillWolf.serverData.ServerUsers[i].identify == null) {
	    	    		KillWolf.serverData.ServerUsers[i].identify = "平民";
	    	    	}
	    	    }
	    	    
	    	    
	    	    String STR = "";
	    	    for(int i=0;i<KillWolf.serverData.UsersLen;i++) {
	    	    	DataUser p = KillWolf.serverData.ServerUsers[i];
	    	    	if(p!=null)STR = STR + "#u#" + p.name+"\t"+p.identify;
	    	    }
	    	    System.out.print(STR);
	    	    KillWolf.server.send("GAMESTART"+(KillWolf.serverData.freeSpeak?"TRUE":"FALSE")+STR);
	    	}
	    }
	}
	
	class CheckValueChanged implements ItemListener{
		String ID;
		public CheckValueChanged(String ID) {
			this.ID = ID;
		}

		public void itemStateChanged(ItemEvent e) {
			JCheckBox jcb = (JCheckBox) e.getItem();// 将得到的事件强制转化为JCheckBox类
			if (jcb.isSelected()) {// 推断是否被选择
				if(this.ID.equals("wizard")) wizardLen = 1;
				else if(this.ID.equals("hunter")) hunterLen = 1;
				else if(this.ID.equals("yuyanjia")) yuyanjiaLen = 1;
				else if(this.ID.equals("发言")) KillWolf.serverData.freeSpeak = true;
				else if(this.ID.equals("获胜玩法")) KillWolf.serverData.playMode = true;
			} else {
				if(this.ID.equals("wizard")) wizardLen = 0;
				else if(this.ID.equals("hunter")) hunterLen = 0;
				else if(this.ID.equals("yuyanjia")) yuyanjiaLen = 0;
				else if(this.ID.equals("发言")) KillWolf.serverData.freeSpeak = false;
				else if(this.ID.equals("获胜玩法")) KillWolf.serverData.playMode = false;
			}
			updateCharCount(false);
		}
	}
	class TextListener implements DocumentListener{      
		String ID;
		public TextListener(String ID) {
			this.ID = ID;
		}
        public void insertUpdate(DocumentEvent e) {
            //System.out.println("insertUpdate" + e.toString());
        	changedUpdate(e);
        } 
        public void removeUpdate(DocumentEvent e) {
            //System.out.println("removeUpdate"+e.toString());
        	changedUpdate(e);
        }
        
        public void changedUpdate(DocumentEvent e) {
        	
            try {
            	int num = Integer.parseInt(e.getDocument().getText(0, e.getDocument().getLength()));
            	if(ID.equals("people")) {
            		peoLen = Math.abs(num);
            	}else {
            		wolfLen = Math.abs(num);
            	}
            	updateCharCount(false);
            	
            }catch (BadLocationException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}catch(Exception e21) {
				updateCharCount(false);
			}
            
            
        }
	}
	public class CountKeyListener implements KeyListener {
		@Override
		public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		int keyChar=e.getKeyChar();
		if (keyChar>=KeyEvent.VK_0 && keyChar<=KeyEvent.VK_9) {
		} else {
		e.consume();
		}
		}
		@Override
		public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		}
		@Override
		public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		}
		}
	boolean updateCharCount(boolean isFinal) {
		CharLen = wolfLen+peoLen+wizardLen+hunterLen+yuyanjiaLen;
		if(Charlenlabel!=null) Charlenlabel.setText("已选角色数："+CharLen);
		if(!KillWolf.isServer) return true;
		if(wolfLen<1 && isFinal) {
			JOptionPane.showMessageDialog(null, "狼人角色数量至少为1");
			return false;
		}
		
		if(CharLen == KillWolf.serverData.UsersRealLen) {
			
			LoginButton.setEnabled(true);
			LoginButton.setText("开始游戏");
			return true;
		}else {
			LoginButton.setEnabled(false);
			LoginButton.setText("角色不匹配");
			return false;
		}
	}
	//定义新类，实现Exit按钮的时间监听 
	/*class btnAction implements ActionListener 
　　{ //接收事件 
		public void actionPerformed(ActionEvent event) 
		{ 
			Object object = event.getSource(); 
			if (object == JButton1) 
				JButton1_actionPerformed(event); 
　　　　} 
　　} */
}
