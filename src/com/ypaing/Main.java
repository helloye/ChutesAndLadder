package com.ypaing;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

public class Main {
    public static final String BOARD_CONFIG = "config/board-config.csv";
    public static final String PLAYER_CONFIG = "config/player-config.csv";

    public static void main(String[] args) throws IOException {

        Board gameBoard = createBoard(BOARD_CONFIG);
        Player[] players = setPlayers(PLAYER_CONFIG);

        // TODO: Validate board/players are properly set.
        // Assume valid for now, and start the game loop.

        boolean gameOver = false;
        int spinner;
        int currPos;
        int newPos;
        int turnCounter = 1;
        Square eventSquare;
        Random r = new Random();

        while(!gameOver) {
            // While game is not over, keep playing.

            // Loop through each player's turn
            for(int i=0; i<players.length; i++) {
                // Spin for player
                spinner = r.nextInt(6)+1;
                currPos = players[i].getPosition();
                newPos =  currPos + spinner;

                // Log the current position offset from spinner
                System.out.print(
                        turnCounter + ": " + players[i].getName()
                        + ": " + currPos + " --> " + newPos);

                // If the player rolled past 100, skip this turn.
                if (newPos > 100) {
                    continue;
                }

                // Else check if the player's new position has an event.
                if(gameBoard.isAnEventSquare(newPos)) {
                    // If it's a event square, need to offset newPosition by event offset.
                    eventSquare = gameBoard.getSquareAtPos(newPos);
                    newPos += eventSquare.getOffset();

                    // Then log the event + new offset position
                    System.out.print(" --" + eventSquare.getEventTypeDescription()
                            + "--> " + newPos);
                }
                System.out.println();
                // If the new position lands the player at 100, game is over, break for loop.
                if (newPos == 100) {
                    players[i].setPosition(newPos);
                    gameOver = true;
                    System.out.println("The winner is: " + players[i].getName());
                    break;
                }

                // Else move player's position to new position.
                players[i].setPosition(newPos);

                turnCounter++;
            }

        }
    }

    // Sets the player name from a config file.
    // TODO: dynamically/interactively set players instead of reading from static config file.
    public static Player[] setPlayers(String playerConfigFile) throws IOException {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(playerConfigFile));
            String[] playerData = br.readLine().split(",");
            Player[] players = new Player[playerData.length];

            for(int i=0; i<playerData.length; i++) {
                players[i] = new Player(playerData[i].trim(), 0);
            }

            return players;
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // Creates the board with configured chutes/ladder events from config file.
    public static Board createBoard(String boardConfigFile) throws IOException {
        BufferedReader br = null;

        // 101 squares, start at 1 index to make it more similar to a game board
        Square[] squares = new Square[101];
        for(int i=0; i<squares.length; i++) {
            // Fill all squares with default value
            squares[i] = new Square(i, 'N', 0);
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
                    char eventType = data[1].toUpperCase().charAt(0);
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
