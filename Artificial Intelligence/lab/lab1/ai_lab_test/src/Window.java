import javax.swing.*;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.util.Arrays;

import static javax.swing.JFrame.EXIT_ON_CLOSE;

public class Window {
    private JFrame frame;
    private JPanel panel;
    private Integer[] names;
    private State[][] data;
    private JTable table;
    private JButton setMon;
    private JButton setBan;
    private JButton setBox;
    private JButton setWall;
    private JButton run;
    private JButton recovery;
    private JList<String> algorithms;
    private JLabel algLabel;
    private JLabel tip;

    public boolean init(){
        //创建窗口
        frame = new JFrame("猴子摘香蕉模拟");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        //frame.setLocation(200, 200);
        frame.setLayout(null);
        frame.setResizable(false);
        //创建panel存放table
        panel = new JPanel();
        panel.setBounds(0,0,Global.COL_WIDTH*Global.COL,Global.ROW_HEIGHT*Global.ROW);
        frame.add(panel);
        //新建table
        names = new Integer[Global.COL];
        Arrays.fill(names, 0);
        data = new State[Global.ROW][Global.COL];
        for (int i = 0; i < Global.ROW; i++) {
            for (int j = 0; j < Global.COL; j++) {
                data[i][j]=State.normal;
            }
        }
        table = new myTable(data,names);

        //设置table的行高列宽
        table.setRowHeight(Global.ROW_HEIGHT);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        for (int j = 0; j < table.getColumnCount(); j++) {
            table.getColumnModel().getColumn(j).setPreferredWidth(Global.COL_WIDTH);
        }
        table.addMouseListener(new TableListener());
        panel.add(table);
        //设置按钮
        recovery = new JButton(Global.RECOVERY);
        recovery.setBounds(Global.COL_WIDTH*Global.COL+30,Global.ROW_HEIGHT*Global.ROW_HEIGHT-150,140,40);
        recovery.addActionListener(new ButtonActionListener());
        frame.add(recovery);
        setMon = new JButton(Global.SET_MON);
        setMon.setBounds(Global.COL_WIDTH*Global.COL+30,Global.ROW_HEIGHT*Global.ROW_HEIGHT-100,140,40);
        setMon.addActionListener(new ButtonActionListener());
        frame.add(setMon);
        setBan = new JButton(Global.SET_BAN);
        setBan.setBounds(Global.COL_WIDTH*Global.COL+30,Global.ROW_HEIGHT*Global.ROW_HEIGHT-50,140,40);
        setBan.addActionListener(new ButtonActionListener());
        frame.add(setBan);
        setBox = new JButton(Global.SET_BOX);
        setBox.setBounds(Global.COL_WIDTH*Global.COL+30,Global.ROW_HEIGHT*Global.ROW_HEIGHT,140,40);
        setBox.addActionListener(new ButtonActionListener());
        frame.add(setBox);
        setWall = new JButton(Global.SET_WALL);
        setWall.setBounds(Global.COL_WIDTH*Global.COL+30,Global.ROW_HEIGHT*Global.ROW_HEIGHT+50,140,40);
        setWall.addActionListener(new ButtonActionListener());
        frame.add(setWall);
        run = new JButton(Global.RUN);
        run.setBounds(Global.COL_WIDTH*Global.COL+30,Global.ROW_HEIGHT*Global.ROW_HEIGHT+100,140,40);
        run.addActionListener(new RunListener());
        frame.add(run);
        //设置列表
        String[] al = new String[]{"A*算法"};
        algorithms = new JList<>(al);
        algorithms.setFixedCellHeight(40);
        algorithms.setBounds(Global.COL_WIDTH*Global.COL+30,20,140,150);
        algorithms.addListSelectionListener(new ListListener());
        frame.add(algorithms);
        //设置label
        algLabel = new JLabel("已选择A*算法");
        algLabel.setBounds(Global.COL_WIDTH*Global.COL+40,170,140,50);
        frame.add(algLabel);
        tip = new JLabel("请设置坐标");
        tip.setBounds(Global.COL_WIDTH*Global.COL+40,200,140,50);
        frame.add(tip);
        frame.setVisible(true);
        return true;
    }
    public void setAlgoLabelText(String str){
        algLabel.setText(str);
    }
    public void setTipText(String str){
        tip.setText(str);
    }
    public JFrame getFrame() {
        return frame;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public JPanel getPanel() {
        return panel;
    }

    public void setPanel(JPanel panel) {
        this.panel = panel;
    }

    public Integer[] getNames() {
        return names;
    }

    public void setNames(Integer[] names) {
        this.names = names;
    }

    public State[][] getData() {
        return data;
    }

    public void setData(State[][] data) {
        this.data = data;
    }

    public JTable getTable() {
        return table;
    }

    public void setTable(JTable table) {
        this.table = table;
    }

    public JButton getSetMon() {
        return setMon;
    }

    public void setSetMon(JButton setMon) {
        this.setMon = setMon;
    }

    public JButton getSetBan() {
        return setBan;
    }

    public void setSetBan(JButton setBan) {
        this.setBan = setBan;
    }

    public JButton getSetWall() {
        return setWall;
    }

    public void setSetWall(JButton setWall) {
        this.setWall = setWall;
    }

    public JButton getRun() {
        return run;
    }

    public void setRun(JButton run) {
        this.run = run;
    }

    public JList<String> getAlgorithms() {
        return algorithms;
    }

    public void setAlgorithms(JList<String> algorithms) {
        this.algorithms = algorithms;
    }

    public JLabel getAlgLabel() {
        return algLabel;
    }

    public void setAlgLabel(JLabel algLabel) {
        this.algLabel = algLabel;
    }
}
