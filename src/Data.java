import java.sql.Date;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.LinkedList;

public class Data {
    private int time;
    private String name;
    private LocalDateTime date;
    private int score;

    public Data(int time, String name, LocalDateTime date, int score) {
        this.time = time;
        this.name = name;
        this.date = date;
        this.score = score;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
