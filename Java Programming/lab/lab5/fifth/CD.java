package fifth;

public class CD extends Item{
    private String artist;
    private int numberOfTracks;

    public CD(String artist, int numberOfTracks) {
        this.artist = artist;
        this.numberOfTracks = numberOfTracks;
    }

    public CD(String title, int playingTime, String comment, String artist, int numberOfTracks) {
        super(title, playingTime, comment);
        this.artist = artist;
        this.numberOfTracks = numberOfTracks;
    }

    @Override
    public void print() {
        System.out.println("CD{" +
                "artist='" + artist + '\'' +
                ", numberOfTracks=" + numberOfTracks +
                "title='" + title + '\'' +
                ", playingTime=" + playingTime +
                ", gotIt=" + gotIt +
                ", comment='" + comment + '\'' +
                '}');
    }

    @Override
    public String toString() {
        return "CD{" +
                "artist='" + artist + '\'' +
                ", numberOfTracks=" + numberOfTracks +
                "title='" + title + '\'' +
                ", playingTime=" + playingTime +
                ", gotIt=" + gotIt +
                ", comment='" + comment + '\'' +
                '}';
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public int getNumberOfTracks() {
        return numberOfTracks;
    }

    public void setNumberOfTracks(int numberOfTracks) {
        this.numberOfTracks = numberOfTracks;
    }
}
