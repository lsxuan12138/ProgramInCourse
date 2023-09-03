
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonActionListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        //System.out.println(e.getActionCommand());
        String cmd = e.getActionCommand();
        switch (cmd) {
            case Global.SET_MON:
                Global.action = Action.setMon;
                break;
            case Global.SET_BAN:
                Global.action = Action.setBan;
                break;
            case Global.SET_BOX:
                Global.action=Action.setBox;
                break;
            case Global.SET_WALL:
                Global.action = Action.setWall;
                break;
            case Global.RECOVERY:
                Global.action = Action.normal;
                Global.monNum=0;
                Global.banNum=0;
                Global.boxNum=0;
                JTable table = Global.window.getTable();
                for (int i = 0; i < Global.ROW; i++) {
                    for (int j = 0; j < Global.COL; j++) {
                        table.setValueAt(State.normal,i,j);
                    }
                }
        }
        System.out.println(Global.action);

    }
}
