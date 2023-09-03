package KillWolf.Window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import KillWolf.KillWolf;
import KillWolf.SocketDeal.ClientDeal;
import KillWolf.SocketDeal.ServerDeal;
import SocketServe.sClient;
import SocketServe.sServer;

public class LoginWindow {
	public JButton ServerButton = null;
	public JButton LoginButton = null;
	public JTextField userName = null;
	public JTextField ServerIp = null;
	public JFrame window = null;
	public String MIP = null;
	public void reset(String ip) {
		window = new JFrame("狼人杀 V.0.1");
		window.setVisible(false);
		window.setSize(600, 200);//设置大小
		window.setLocationRelativeTo(null);//设置居中
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//设置可关闭
		window.setLayout(null);//设置绝对布局（窗口里面的内容不会随着窗口的改变而改变）
		window.setResizable(false);//设置窗口不可拉伸改变大小
		//设置用户名标签
		JLabel username_label =new JLabel("用户名");
		username_label.setBounds(100,30,100,20);
		window.add(username_label);
		//设置文本框
		userName = new JTextField();
		userName.setBounds(160, 30, 300, 20);
		Random r = new Random();
		//r.nextInt();
	    r.setSeed(System.currentTimeMillis());
		userName.setText("user"+r.nextInt(10000));
		window.add(userName);
		//设置标签
		JLabel ServerIp_label =new JLabel("服务器地址");
		ServerIp_label.setBounds(80,80,100,20);
		window.add(ServerIp_label);
		//设置文本框
		ServerIp = new JTextField();
		ServerIp.setBounds(160, 80, 300, 20);
		ServerIp.setText(ip);
		this.MIP = ip;
		window.add(ServerIp);
		//JPasswordField pwd=new JPasswordField();//隐藏密码
		//pwd.setBounds(150, 200, 300, 50);
		//window.add(pwd);
		//设置按钮
		LoginButton = new JButton("连接服务器");
		//Login.setEnabled(false);
		LoginButton.setBounds(180, 120, 100, 20);
		window.add(LoginButton);
		ServerButton = new JButton("作为服务器");
		//Login.setEnabled(false);
		ServerButton.setBounds(320, 120, 100, 20);
		window.add(ServerButton);
		//设置作为主机动作
        ButtonAction ServerAction = new ButtonAction();  
        ServerButton.addActionListener(ServerAction);  
        //设置连接服务器动作
        LinkAction LinkAction = new LinkAction();  
        LoginButton.addActionListener(LinkAction);  
		window.setVisible(true);//设置面板可见
	}
	class ButtonAction implements ActionListener{
	    public void actionPerformed(ActionEvent event){  
	    	if(!setName()) return;
	    	ServerIp.setText(MIP);
	    	KillWolf.server = new sServer();
	    	ServerDeal cb = new ServerDeal();
	    	if(!KillWolf.server.start(cb)){
				JOptionPane.showMessageDialog(null, "端口被占用，服务器启动失败！");
				return;
			}
	    	if(!createClient(MIP)){
				JOptionPane.showMessageDialog(null, "客户端启动失败！");
				return;
			}
	    	//window.setTitle(ServerIp.getText());
	    	KillWolf.ServerIp = MIP;
	    	KillWolf.isServer = true;
	    	
	    	ServerIp.setEnabled(false);
	    	ServerButton.setEnabled(false);
	    	KillWolf.client.send("ENTER"+KillWolf.UserName);
	    	
	    	//window.setTitle(ServerIp.getText());
	    }
	}
	class LinkAction implements ActionListener{
	    public void actionPerformed(ActionEvent event){  
	    	if(!setName()) return;
	    	if(KillWolf.ServerIp == null) {
	    		if(!createClient(ServerIp.getText())){
					JOptionPane.showMessageDialog(null, "客户端启动失败！可能是服务器未连接成功。");
					return;
				}
		    	KillWolf.ServerIp = ServerIp.getText();
		    	KillWolf.isServer = false;
	    	}
	    	
	    	ServerIp.setEnabled(false);
	    	ServerButton.setEnabled(false);
	    	KillWolf.client.send("ENTER"+KillWolf.UserName);
	    }
	}
	public boolean createClient(String IP) {
		KillWolf.client = new sClient();
		ClientDeal cb = new ClientDeal();
		//System.out.println("Connecting Server on "+IP+"...");
		return KillWolf.client.start(IP,cb);
	}
	public boolean setName() {
		if( userName.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "请输入用户名");
			return false;
		}
		KillWolf.UserName = userName.getText();
		return true;
		
	}
}
