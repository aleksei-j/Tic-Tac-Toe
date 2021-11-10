import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    private static String player1;
    private static String player2;
    private static ArrayList<ArrayList<String>> board = new ArrayList<>(Arrays.asList(
            new ArrayList<>(Arrays.asList(" "," "," ")),
            new ArrayList<>(Arrays.asList(" "," "," ")),
            new ArrayList<>(Arrays.asList(" "," "," "))
    ));

    public static void main(String[] args) {

        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("Welcome to the game of tic-tac-toe!");
            playerSelection(sc);
            System.out.println("Player1 is: " + player1);
            printBoard();
            for (int i = 0; i < 9; i++) {
                int player = i % 2 + 1;
                getMove(sc,player);
                printBoard();
                if (checkIfWon(player)) {
                    break;
                }
            }
            System.out.println("Wow, no one won!");
        }
    }

    public static boolean checkIfWon(int player) {
        int counterRow = 0;
        int counterColumn= 0;
        String symbol;
        if (player == 1) {
            symbol = player1;
        } else {
            symbol = player2;
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (subWinCheck(i, j, symbol))
                    counterRow++;
                else
                    counterRow = 0;

                if (subWinCheck(j, i, symbol))
                    counterColumn++;
                else
                    counterColumn = 0;

                if (board.get(0).get(0).equals(symbol) && board.get(1).get(1).equals(symbol) && board.get(2).get(2).equals(symbol)
                        || board.get(0).get(2).equals(symbol) && board.get(1).get(1).equals(symbol) && board.get(2).get(0).equals(symbol)) {
                    System.out.println("Player " + symbol + " wins!");
                    return true;
                }


                if (counterRow == 3 || counterColumn == 3) {
                    if (symbol.equals(player1))
                        System.out.print("Player1 wins!");
                    else
                        System.out.print("Player2 wins!");
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean subWinCheck(int row, int column, String symbol) {
        return board.get(row).get(column).equals(symbol);
    }

    public static void getMove(Scanner sc, int player) {
        int row;
        int column;

        System.out.print("Player" + player + " please input row of your move: ");
        row = validateMove(sc);
        System.out.print("Player" + player + " please input column of your move: ");
        column = validateMove(sc);

        if (validateMove(row,column)) {
            if (player == 1)
                board.get(row).set(column, player1);
            else
                board.get(row).set(column, player2);
        } else {
            getMove(sc, player);
        }
    }

    public static int validateMove(Scanner sc) {
        int index = sc.nextInt() - 1;
        while (index < 0 || index > 3) {
            System.out.print("Invalid move! This index is outside of the field boundaries! Try again: ");
            index = sc.nextInt() - 1;
        }
        return index;
    }

    public static boolean validateMove(int row, int column) {
        if (board.get(row).get(column).equals(" ")) {
            return true;
        }
        System.out.println("This cell is already played, try again!");
        return false;
    }

    public static void printBoard() {

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {

                if (board.get(i).get(j).equals(player1)) {
                    System.out.print(player1);
                } else if (board.get(i).get(j).equals(player2)) {
                    System.out.print(player2);
                } else {
                    System.out.print(board.get(i).get(j));
                }
                if (j != 2)
                    System.out.print(" | ");
            }
            System.out.println();
            if (i != 2)
                System.out.println("= = = = =");
        }
    }

    public static void playerSelection(Scanner sc) {
        String input;
        boolean flag = false;

        while (!flag) {
            System.out.print("Player1 please type 1 for 'X' or 2 for 'O': ");
            input = sc.nextLine();
            System.out.println(input);
            if (input.equals("1")) {
                player1 = "X";
                player2 = "O";
                flag = true;
            } else if (input.equals("2")) {
                player1 = "O";
                player2 = "X";
                flag = true;
            } else {
                System.out.println("Incorrect input, try again.");
            }
        }
    }
}