package com.ypaing;

public class Board {
    private Square[] squares;

    Board(Square[] s) {
        this.squares = s;
    }

    @Override
    public String toString() {
        String boardDetails = null;
        for(int i = 0; i<squares.length; i++) {
            boardDetails += "[" + i + "] " + squares[i].toString() + "\n";
        }

        return boardDetails;
    }
}
