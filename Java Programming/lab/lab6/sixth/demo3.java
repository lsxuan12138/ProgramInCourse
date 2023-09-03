package sixth;

import fifth.*;

import java.io.*;
import java.util.ArrayList;

public class demo3 {
    public static void main(String[] args) {
        //创建Database
        DataBase db = new DataBase();
        //向database中增添数据
        db.add(new CD("CD-AAA", 350, "CD-AAAAAAAAAAAAAAAA", "Tom", 1000));
        db.add(new DVD("DVD-DDD",30000,"DVD-这是DVD","Jack"));
        db.add(new VideoGame("GAME-1",30*60,"游戏内容123",200,"网易"));
        db.add(new BoardGame("GAME-3",40*60,"游戏内容23333",400));
        db.add(new BoardGame("GAME-2",40*60,"游戏内容23333",400));
        ObjectOutputStream oos = null;
        ObjectOutputStream oos2 = null;
        ObjectInputStream ois = null;
        try{
            oos = new ObjectOutputStream(new FileOutputStream("database.txt",false));
            for(Item item : db.getListItem()){//输出对象
                oos.writeObject(item);
                //System.out.println(item);
            }
            //标记结束
            oos.writeObject(null);
            //刷新缓冲区
            oos.flush();
            //临时存放数据
            ArrayList<Item>  al = new ArrayList<Item>();
            Item item;
            ois = new ObjectInputStream(new FileInputStream("database.txt"));
            while ((item=(Item) ois.readObject())!=null){//遍历所有Item
                //System.out.println(item);
                int size = al.size();
                if(size==0){//如果al中无item则将第一个item加进去
                    al.add(item);
                    continue;
                }
                for(int i = 0;i<size;i++){//遍历al中已存在的item
                    if(al.get(i).getTitle().compareToIgnoreCase(item.getTitle())>0){//若新读取的item按照字典序排序比某一个小
                        al.add(i,item);//则将读取的item放在i处
                        break;
                    }else {
                        if(i==size-1)al.add(item);//若比最后一个大，将读取的item放在末尾
                        else continue;//若未比到最后一个，继续比对下一个
                    }
                }
            }

            oos2 = new ObjectOutputStream(new FileOutputStream("database_1.txt",false));
            for(Item item2 : al){//输出排序结果
                System.out.println(item2);
                oos2.writeObject(item2);
            }
            oos2.writeObject(null);
            oos2.flush();
        }catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if(oos != null){
                    oos.close();
                }
                if(ois != null){
                    ois.close();
                }
                if(oos2 !=null){
                    oos2.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }
}
