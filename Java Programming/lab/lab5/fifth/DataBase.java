package fifth;

import java.util.ArrayList;

public class DataBase {
    private ArrayList<Item> listItem = new ArrayList<Item>();

    public void add(Item item) {
        listItem.add(item);
    }

    public void list() {
        for( Item item : listItem) {
            //System.out.println(item);
            item.print();
        }
    }

    public ArrayList<Item> getListItem() {
        return listItem;
    }

    public void setListItem(ArrayList<Item> listItem) {
        this.listItem = listItem;
    }

    public static void main(String[] args) {
        DataBase db = new DataBase();
        db.add(new CD("CD-AAA", 350, "CD-AAAAAAAAAAAAAAAA", "Tom", 1000));
        db.add(new DVD("DVD-DDD",30000,"DVD-这是DVD","Jack"));
        db.add(new VideoGame("GAME-1",30*60,"游戏内容123",200,"网易"));
        db.add(new BoardGame("GAME-2",40*60,"游戏内容23333",400));

        db.list();
    }

}
