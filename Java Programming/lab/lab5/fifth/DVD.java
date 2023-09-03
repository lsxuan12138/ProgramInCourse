package fifth;

public class DVD extends Item{
    private String director;

    public DVD(String director) {
        this.director = director;
    }

    public DVD(String title, int playingTime, String comment, String director) {
        super(title, playingTime, comment);
        this.director = director;
    }
    @Override
    public void print() {
        System.out.println("DVD{" +
                "director='" + director + '\'' +
                ", title='" + title + '\'' +
                ", playingTime=" + playingTime +
                ", gotIt=" + gotIt +
                ", comment='" + comment + '\'' +
                '}');
    }
    @Override
    public String toString() {
        return "DVD{" +
                "director='" + director + '\'' +
                ", title='" + title + '\'' +
                ", playingTime=" + playingTime +
                ", gotIt=" + gotIt +
                ", comment='" + comment + '\'' +
                '}';
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }
}
