package fifth;

import java.io.Serializable;

public class Item implements Serializable {
    protected String title;
    protected int playingTime;
    protected boolean gotIt;
    protected String comment;

    public Item() {
    }
    public Item(String title, int playingTime, String comment) {
        this.title = title;
        this.playingTime = playingTime;
        this.gotIt = false;
        this.comment = comment;
    }

    public void print(){
        System.out.printf("Item{" +
                "title='" + title + '\'' +
                ", playingTime=" + playingTime +
                ", gotIt=" + gotIt +
                ", comment='" + comment + '\'' +
                '}');
    }
    @Override
    public String toString() {
        return "Item{" +
                "title='" + title + '\'' +
                ", playingTime=" + playingTime +
                ", gotIt=" + gotIt +
                ", comment='" + comment + '\'' +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPlayingTime() {
        return playingTime;
    }

    public void setPlayingTime(int playingTime) {
        this.playingTime = playingTime;
    }

    public boolean isGotIt() {
        return gotIt;
    }

    public void setGotIt(boolean gotIt) {
        this.gotIt = gotIt;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
