package SnakesandLadders.entity;

public class Jumper {
    int startpoint;
    int endpoint;

    public Jumper(int startpoint, int endpoint) {
        this.startpoint = startpoint;
        this.endpoint = endpoint;
    }

    public int getStartpoint() {
        return startpoint;
    }

    public void setStartpoint(int startpoint) {
        this.startpoint = startpoint;
    }

    public int getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(int endpoint) {
        this.endpoint = endpoint;
    }
}
