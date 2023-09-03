import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TableListener implements MouseListener {


    @Override
    public void mouseClicked(MouseEvent e) {
        int col = Global.window.getTable().getSelectedColumn();
        int row = Global.window.getTable().getSelectedRow();
        //System.out.println("("+x+","+y+")");
        JTable table = Global.window.getTable();
        switch (Global.action){
            case setMon:
                if(table.getValueAt(row,col)==State.mon){
                    Global.monNum =0;
                    table.setValueAt(State.normal,row,col);
                    break;
                }
                if(Global.monNum ==0){
                    if(table.getValueAt(row,col)==State.ban){
                        Global.banNum =0;
                    }
                    if(table.getValueAt(row,col)==State.box){
                        Global.boxNum =0;
                    }
                    table.setValueAt(State.mon,row,col);
                    Global.monNum++;
                    Global.monRow=row;
                    Global.monCol=col;
                    Global.window.setTipText("猴子坐标已设置");
                }

                break;
            case setBan:
                if(table.getValueAt(row,col)==State.ban){
                    Global.banNum =0;
                    table.setValueAt(State.normal,row,col);
                    break;
                }
                if(Global.banNum ==0) {
                    if ( table.getValueAt(row, col) == State.mon) {
                        Global.monNum = 0;
                    }
                    if ( table.getValueAt(row, col) == State.box) {
                        Global.boxNum = 0;
                    }
                    table.setValueAt(State.ban, row, col);
                    Global.banNum++;
                    Global.banRow = row;
                    Global.banCol=col;
                    Global.window.setTipText("香蕉坐标已设置");
                }
                break;
            case setBox:
                if(table.getValueAt(row,col)==State.box){
                    Global.boxNum =0;
                    table.setValueAt(State.normal,row,col);
                    break;
                }
                if(Global.boxNum ==0) {
                    if ( table.getValueAt(row, col) == State.mon) {
                        Global.monNum = 0;
                    }
                    if ( table.getValueAt(row, col) == State.ban) {
                        Global.banNum = 0;
                    }
                    table.setValueAt(State.box, row, col);
                    Global.boxNum++;
                    Global.boxRow=row;
                    Global.boxCol=col;
                    Global.window.setTipText("盒子坐标已设置");
                }
                break;
            case setWall:
                table.setValueAt(State.wall,row,col);
                if ( table.getValueAt(row, col) == State.mon) {
                    Global.monNum = 0;
                }
                if ( table.getValueAt(row, col) == State.ban) {
                    Global.banNum = 0;
                }
                if ( table.getValueAt(row, col) == State.box) {
                    Global.boxNum = 0;
                }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {


    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
