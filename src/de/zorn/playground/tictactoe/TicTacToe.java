package de.zorn.playground.tictactoe;

import java.util.Scanner;

public class TicTacToe {

    private static final String[] board = new String[9];

    private static int moveCounter = 0;

    private static String turn = "X";

    private static String[] displayPlayingField(boolean setUp) {

        if (setUp) {
            for (int i = 0; i < 9; i++) {
                board[i] = String.valueOf(i + 1);
            }
        }

        String[] displayPlayingField = new String[5];
        displayPlayingField[0] = " " + board[0] + " | " + board[1] + " | " + board[2] + " ";
        displayPlayingField[1] = "---|---|---";
        displayPlayingField[2] = " " + board[3] + " | " + board[4] + " | " + board[5] + " ";
        displayPlayingField[3] = "---|---|---";
        displayPlayingField[4] = " " + board[6] + " | " + board[7] + " | " + board[8] + " ";

        return displayPlayingField;
    }

    private static void executeMove() {
        System.out.print("Enter your move: ");

        Scanner input = new Scanner(System.in);
        int move = input.nextInt();
        board[move - 1] = turn;

        setTurn();
        moveCounter++;
    }

    private static void setTurn() {
        if (moveCounter % 2 == 0) {
            turn = "O";
        } else {
            turn = "X";
        }
    }

    private static boolean isWinnerDetermined() {

        if (moveCounter >= 9) {
            System.out.println("No one won the game");
            return true;
        }

        for (int i = 0; i < 8; i++) {

            String line = switch (i) {
                case 0 -> board[0] + board[1] + board[2];
                case 1 -> board[3] + board[4] + board[5];
                case 2 -> board[6] + board[7] + board[8];
                case 3 -> board[0] + board[3] + board[6];
                case 4 -> board[1] + board[4] + board[7];
                case 5 -> board[2] + board[5] + board[8];
                case 6 -> board[0] + board[4] + board[8];
                case 7 -> board[2] + board[4] + board[6];
                default -> "";
            };

            if (line.equals("XXX")) {
                System.out.println("X is winning");
                return true;
            } else if (line.equals("OOO")) {
                System.out.println("O is winning");
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {

        for (String line : displayPlayingField(true)) {
            System.out.println(line);
        }

        while (!isWinnerDetermined()) {

            executeMove();

            for (String line : displayPlayingField(false)) {
                System.out.println(line);
            }
        }
    }
}
