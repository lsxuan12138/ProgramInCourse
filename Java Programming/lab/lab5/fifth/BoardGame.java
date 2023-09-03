package fifth;

public class BoardGame extends Game{
    public BoardGame() {
    }

    public BoardGame(int numberOfPlayers) {
        super(numberOfPlayers);
    }

    public BoardGame(String title, int playingTime, String comment, int numberOfPlayers) {
        super(title, playingTime, comment, numberOfPlayers);
    }
    @Override
    public void print() {
        System.out.println("BoardGame{" +
                "numberOfPlayers=" + numberOfPlayers +
                ", title='" + title + '\'' +
                ", playingTime=" + playingTime +
                ", gotIt=" + gotIt +
                ", comment='" + comment + '\'' +
                '}');
    }
    @Override
    public String toString() {
        return "BoardGame{" +
                "numberOfPlayers=" + numberOfPlayers +
                ", title='" + title + '\'' +
                ", playingTime=" + playingTime +
                ", gotIt=" + gotIt +
                ", comment='" + comment + '\'' +
                '}';
    }
}
