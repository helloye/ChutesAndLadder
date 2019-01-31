package com.ypaing;

public class Square {
    private int position;
    private char type;
    private int offset;

    // Empty, default constructor
    Square() {
        this.position = 0;
        this.type = 'U';
        this.offset = 0;
    }

    // Full constructor
    Square(int p, char t, int o) {
        this.position = p;
        this.type = t;
        this.offset = o;
    }

    @Override
    public String toString() {
        return "Square - Square[" + this.position
                + "] Type["
                + this.getEventType()
                + "] Offset[" + this.offset + "]";
    }

    private String getEventType() {
        switch (this.type) {
            case 'C':
                return "Chutes";
            case 'L':
                return "Ladders";
            default:
                return "Unknown";
        }
    }
}
