package fifth;

public class Game extends Item{
    protected int numberOfPlayers;

    public Game() {
    }
    public Game(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }

    public Game(String title, int playingTime, String comment, int numberOfPlayers) {
        super(title, playingTime, comment);
        this.numberOfPlayers = numberOfPlayers;
    }
    @Override
    public void print() {
        System.out.println("Game{" +
                "numberOfPlayers=" + numberOfPlayers +
                ", title='" + title + '\'' +
                ", playingTime=" + playingTime +
                ", gotIt=" + gotIt +
                ", comment='" + comment + '\'' +
                '}');
    }
    @Override
    public String toString() {
        return "Game{" +
                "numberOfPlayers=" + numberOfPlayers +
                ", title='" + title + '\'' +
                ", playingTime=" + playingTime +
                ", gotIt=" + gotIt +
                ", comment='" + comment + '\'' +
                '}';
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public void setNumberOfPlayers(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }
}
