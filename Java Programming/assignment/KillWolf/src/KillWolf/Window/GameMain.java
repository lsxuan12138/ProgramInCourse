package KillWolf.Window;

import KillWolf.Data.DataUser;
import KillWolf.KillWolf;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameMain {
//    public static void main(String[] args) {
//        GameMain game = new GameMain();
//        game.reset();
//    }
    public boolean freeSpeak = false;
    private JFrame frame = new JFrame("开始游戏");
    private JPanel panel = new JPanel();
    //玩家列表
    private JLabel userListLabel = new JLabel("在场玩家");
    private JList<String> userList = null;
    //出局玩家列表
    private JLabel outUserListLabel = new JLabel("淘汰玩家");
    private JList<String> OutUserList = null;
    //
    private JLabel identity = null;
    //公屏
    private JLabel screenStr = new JLabel("公屏");
    private JTextArea screen = new JTextArea();
    private JScrollPane jsp = null;
//    //投票按钮
//    private JButton vote = new JButton("投票");
    //发言框
    private JLabel submitBoxStr = new JLabel("发言框");
    private JTextField  submitStr = new JTextField();
    //发言按钮
    private JButton submit = new JButton("发言");
    public String[] UserListData ;
    public String[] UserListTipData ;
    public String[] UserOutListData ;
    public DataUser userSelf=null;
    public void reset(){
        //本地玩家信息初始化

        for(DataUser user : KillWolf.Users){
            if(user != null) {
                if (user.identify.equals("女巫")) {
                    user.haveAntidote = true;
                    user.havePoison = true;
                }
                user.live = true;
            }
        }
        //设置窗体参数
        frame.setSize(800,600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setResizable(false);
        frame.setVisible(false);
        frame.setLocationRelativeTo(null);
        //在场玩家列表设置
        userListLabel.setBounds(20,5,100,20);
        frame.add(userListLabel);
        String[] items1=new String[10];
        userList=new JList(items1);    //创建JList
        userList.setBounds(10, 30, 200, 240);
        userList.setFixedCellHeight(20);
        frame.add(userList);
        //淘汰玩家列表设置
        outUserListLabel.setBounds(20,275,100,20);
        frame.add(outUserListLabel);
        String[] items2=new String[10];
        OutUserList=new JList(items2);    //创建JList
        OutUserList.setBounds(10, 300, 200, 240);
        OutUserList.setFixedCellHeight(20);
        frame.add(OutUserList);


        //顶端字体设置
        DataUser[] users= KillWolf.Users;
        String[] usn = new String[KillWolf.UsersRealLen];
        String[] usna = new String[KillWolf.UsersRealLen];
        String[] usnt = new String[KillWolf.UsersRealLen];
        int n = 0;
        for (int i=0;i<KillWolf.UsersLen;i++) {
            DataUser user = users[i];
            if(user == null) continue;
            if(user.name.equals(KillWolf.UserName)){
                userSelf=user;
            }
            usn[n]= user.name;
            //System.out.println("name:" + user.name);
            usna[n]=usn[n];
            usnt[n] = "";
            n++;
        }
        n=0;
        if(userSelf.identify.equals("狼人")){
            for (int i=0;i<KillWolf.UsersLen;i++) {
                DataUser user = users[i];
                if(user == null) continue;
                if(user.identify.equals("狼人")) {
                    usnt[n] = "(狼人)";
                }
                usna[n]=usn[n]+" "+usnt[n];
                n++;
            }
        }
        userList.setListData(usna);
        UserListData = usn;
        UserListTipData = usnt;
        UserOutListData = new String[0];

        identity = new JLabel(userSelf.name +"，你的身份是：" +userSelf.identify); ;
        identity.setBounds(270,0,450,100);
        identity.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
        frame.add(identity);

        //公屏设置
        panel.setBounds(270,100,450,380);
        panel.setLayout(null);
        jsp = new JScrollPane(screen);
        jsp.setBounds(0,0,450,380);

        screen.setBounds(0,0,450,380);
        screen.setFont(new Font(Font.DIALOG,Font.BOLD,16));
        screen.setEditable(false);
        screen.setLineWrap(true);        //激活自动换行功能 
        screen.setWrapStyleWord(true);            // 激活断行不断字功能
        screenStr.setBounds(270,60,100,50);

        jsp.setViewportView(screen);
//        screen.setBackground(Color.black);
//        jsp.setBackground(Color.BLUE);
//        panel.setBackground(Color.green);
        //jsp.setOpaque(false);
        jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        panel.add(jsp);
        frame.add(panel);
        frame.add(screenStr);

        submitBoxStr.setBounds(270,470,100,50);
        submitStr.setBounds(270,510,300,30);
        submitStr.setFont(new Font(Font.DIALOG,Font.BOLD,15));
        //submitStr.setText("请发言");
        submitStr.setColumns(3);
        frame.add(submitBoxStr);
        frame.add(submitStr);

        submit.setBounds(600,510,120,30);
        submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                submit();
            }
        });
        frame.add(submit);

        this.freeSpeak = KillWolf.freeSpeak;
        ableSubmit();

        frame.setVisible(true);
        KillWolf.client.send("GAMEREADY"+KillWolf.UserName);
        addToScreen("【系统】等待玩家...");
    }
    public void forbidSubmit(){
        if(freeSpeak)return;
        ClockRun = false;
        submit.setText("发言");
        this.submit.setEnabled(false);
    }
    public void ableSubmit(){
        //if(freeSpeak)return;
        this.submit.setEnabled(true);
    }
    public boolean ClockRun = false;
    class TimeTh extends Thread{
        int time;
        public TimeTh(int time) {
            this.time = time-1;
        }
        public void run() {
            try {
                submit.setText("发言("+time+"s)");
                Thread.sleep(1000);
                while(time>1 && ClockRun) {
                    time--;
                    submit.setText("发言("+time+"s)");
                    Thread.sleep(1000);

                }
                submit.setText("发言");
                forbidSubmit();
            }catch(Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void startSpeakClock(){
        if(freeSpeak){
            TimeTh tt = new TimeTh(120);
            ClockRun = true;
            tt.start();
            return;
        }
        TimeTh tt = new TimeTh(30);
        ClockRun = true;
        tt.start();
    }
    public void submit(){
        System.out.println(submitStr.getText());
        if(submitStr != null && (submitStr.getText() != null && !"".equals(submitStr.getText()))){
            KillWolf.client.send("USERSPEAK"+userSelf.name+"\t"+submitStr.getText());
            submitStr.setText("");
            if(!KillWolf.freeSpeak){
                this.submit.setEnabled(false);
                ClockRun = false;
                submit.setText("发言");
            }

        }

    }
    public void addToScreen(String str){
        //添加问本到公屏
        screen.append(str+"\r\n");
        //显示最新内容
        screen.setCaretPosition(screen.getText().length());
    }
    public  void setDark(){
        Color c = new Color(-12828863);
        if(userList != null) {
            userList.setBackground(c);
            userList.setForeground(Color.white);
        }
        if(OutUserList != null) {
            OutUserList.setBackground(c);
            OutUserList.setForeground(Color.white);
        }
        if(jsp != null){
            jsp.setBackground(c);
            jsp.setForeground(Color.white);
        }
        if(screen != null){
            screen.setBackground(c);
            screen.setForeground(Color.white);
        }
        if(submitStr != null){
            submit.setBackground(c);
            submit.setForeground(Color.white);
        }
        if(userListLabel != null)userListLabel.setForeground(Color.white);
        if(outUserListLabel != null)outUserListLabel.setForeground(Color.white);
        if(screenStr != null)screenStr.setForeground(Color.white);
        if(submitBoxStr != null)submitBoxStr.setForeground(Color.white);
        if(identity != null)identity.setForeground(Color.white);
        if(frame != null) frame.getContentPane().setBackground(Color.black);

//        if(CenterLabel != null) CenterLabel.setForeground(Color.white);
    }
    public  void setWhite(){
        if(userList != null) {
            userList.setBackground(Color.white);
            userList.setForeground(Color.black);
        }
        if(OutUserList != null) {
            OutUserList.setBackground(Color.white);
            OutUserList.setForeground(Color.black);
        }
        if(screen != null){
            screen.setBackground(Color.white);
            screen.setForeground(Color.black);
        }
//        if(jsp != null){
//            jsp.setBackground(Color.white);
//            jsp.setForeground(Color.black);
//        }
        if(submitStr != null){
            submit.setBackground(Color.white);
            submit.setForeground(Color.black);
        }
        if(userListLabel != null)userListLabel.setForeground(Color.black);
        if(outUserListLabel != null)outUserListLabel.setForeground(Color.black);
        if(screenStr != null)screenStr.setForeground(Color.black);
        if(submitBoxStr != null)submitBoxStr.setForeground(Color.black);
        if(identity != null)identity.setForeground(Color.black);
        if(frame != null) frame.getContentPane().setBackground(new Color(-1118482));

//        if(CenterLabel != null) CenterLabel.setForeground(Color.white);
    }

    public JFrame getFrame() {
        return frame;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public JLabel getUserListLabel() {
        return userListLabel;
    }

    public void setUserListLabel(JLabel userListLabel) {
        this.userListLabel = userListLabel;
    }

    public JList<String> getUserList() {
        return userList;
    }

    public void setUserList(JList<String> userList) {
        this.userList = userList;
    }

    public JLabel getOutUserListLabel() {
        return outUserListLabel;
    }

    public void setOutUserListLabel(JLabel outUserListLabel) {
        this.outUserListLabel = outUserListLabel;
    }

    public JList<String> getOutUserList() {
        return OutUserList;
    }

    public void setOutUserList(JList<String> outUserList) {
        OutUserList = outUserList;
    }

    public JLabel getIdentity() {
        return identity;
    }

    public void setIdentity(JLabel identity) {
        this.identity = identity;
    }

    public JLabel getScreenStr() {
        return screenStr;
    }

    public void setScreenStr(JLabel screenStr) {
        this.screenStr = screenStr;
    }

    public JTextArea getScreen() {
        return screen;
    }

    public void setScreen(JTextArea screen) {
        this.screen = screen;
    }

    public JLabel getSubmitBoxStr() {
        return submitBoxStr;
    }

    public void setSubmitBoxStr(JLabel submitBoxStr) {
        this.submitBoxStr = submitBoxStr;
    }

    public JTextField getSubmitStr() {
        return submitStr;
    }

    public void setSubmitStr(JTextField submitStr) {
        this.submitStr = submitStr;
    }

    public JButton getSubmit() {
        return submit;
    }

    public void setSubmit(JButton submit) {
        this.submit = submit;
    }

    public String[] getUserListData() {
        return UserListData;
    }

    public void setUserListData(String[] userListData) {
        UserListData = userListData;
    }

    public String[] getUserOutListData() {
        return UserOutListData;
    }

    public void setUserOutListData(String[] userOutListData) {
        UserOutListData = userOutListData;
    }
}
