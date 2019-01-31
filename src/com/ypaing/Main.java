package com.ypaing;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static final String BOARD_CONFIG = "config/board-config.csv";

    public static void main(String[] args) throws IOException {
        Board gameBoard = createBoard(BOARD_CONFIG);

        System.out.println(gameBoard.toString());

    }

    public static Board createBoard(String boardConfigFile) throws IOException {
        BufferedReader br = null;
        // 101 squares, start at 1 index to make it more similar to a game board
        Square[] squares = new Square[101];
        for(int i=0; i<squares.length; i++) {
            // Fill all squares with default value
            squares[i] = new Square(i, 'U', 0);
        }

        // Start reading in the board config for chutes/ladders event squares
        try {
            br = new BufferedReader(new FileReader(boardConfigFile));
            String line = "";
            int lineCount = 0;
            while((line = br.readLine()) != null) {
                if (lineCount > 0) {
                    String[] data = line.split(",");
                    // data[0] = square # (String) * Convert to int
                    // data[1] = eventType char (String) * Convert to char
                    // data[2] = offset # (String) * Convert to int
                    // data[3] = endSquare # (String) * Not needed.

                    int eventSquare = Integer.parseInt(data[0]);
                    char eventType = data[1].charAt(0);
                    int eventOffset = Integer.parseInt(data[2]);
                    Square curr = new Square(eventSquare, eventType, eventOffset);

                    squares[eventSquare] = curr;
                }

                lineCount++;
            }
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return new Board(squares);
    }
}
