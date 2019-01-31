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

    public boolean isAnEventSquare(int p) {
        char e = squares[p].getType();

        // Check if it's a chutes or ladder square.
        return e == 'C' || e == 'L';
    }

    public Square getSquareAtPos(int p) {
        return squares[p];
    }
}
