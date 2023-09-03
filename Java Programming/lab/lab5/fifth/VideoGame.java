package fifth;

public class VideoGame extends Game{
    private String platform;

    public VideoGame(String platform) {
        this.platform = platform;
    }

    public VideoGame(int numberOfPlayers, String platform) {
        super(numberOfPlayers);
        this.platform = platform;
    }

    public VideoGame(String title, int playingTime, String comment, int numberOfPlayers, String platform) {
        super(title, playingTime, comment, numberOfPlayers);
        this.platform = platform;
    }
    @Override
    public void print() {
        System.out.println("VideoGame{" +
                "numberOfPlayers=" + numberOfPlayers +
                ", title='" + title + '\'' +
                ", playingTime=" + playingTime +
                ", gotIt=" + gotIt +
                ", comment='" + comment + '\'' +
                ", platform='" + platform + '\'' +
                '}');
    }
    @Override
    public String toString() {
        return "VideoGame{" +
                "numberOfPlayers=" + numberOfPlayers +
                ", title='" + title + '\'' +
                ", playingTime=" + playingTime +
                ", gotIt=" + gotIt +
                ", comment='" + comment + '\'' +
                ", platform='" + platform + '\'' +
                '}';
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }
}
