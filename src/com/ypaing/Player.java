package com.ypaing;

public class Player {
    private String name;
    private int position;

    Player(String n, int p) {
        this.name = n;
        this.position = p;
    }

    @Override
    public String toString() {
        return "Player Name: " + this.name + " @ Position: " + this.position;
    }

    public void setPosition(int p) {
        this.position = p;
    }

    public int getPosition() {
        return this.position;
    }

    public String getName() {
        return this.name;
    }
}
