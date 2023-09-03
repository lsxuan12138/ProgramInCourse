import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


public class ListListener implements ListSelectionListener {


    @Override
    public void valueChanged(ListSelectionEvent e) {
        //System.out.println(
        String cmd = Global.window.getAlgorithms().getSelectedValue();
        System.out.println(cmd);
        switch (cmd){
            case Global.DFS:
                Global.algorithm=Algorithm.DFS;
                break;
            case Global.BFS:
                Global.algorithm=Algorithm.BFS;
                break;
            case Global.A:
                Global.algorithm=Algorithm.A;
                break;
        }
    }
}
