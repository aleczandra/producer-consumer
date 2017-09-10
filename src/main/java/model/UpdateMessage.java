package model;

public class UpdateMessage implements Comparable<UpdateMessage>{


    String name;
    long time;
    String matchTime;
    String text;

    public UpdateMessage (String name, long time, String text, String matchTime) {
        this.name = name;
        this.time = time;
        this.text = text;
        this.matchTime = matchTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getMatchTime() {
        return matchTime;
    }

    public void setMatchTime(String matchTime) {
        this.matchTime = matchTime;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public int compareTo(UpdateMessage o) {
        return (int)(this.time - o.time);
    }

    public String toString() {

        return matchTime + " - " + name + " - " + text;

    }
}
