package Lesson4;

import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
    static final int SIZE = 5;
    static final int DOTS_TO_WIN = 4;

    static public char DOT_X = 'X';
    static public char DOT_O = 'O';
    static public char DOT_EMPTY = '•';

    static char [][]map;
    static Scanner sc = new Scanner(System.in);
    static Random random = new Random();

    public static void main(String[] args) {
        initMap();
        printMap();

        while (true) {
            humanTurn();
            printMap();
            if (checkWin(DOT_X)) {
                System.out.println("You WON!");
                break;
            }
            if (isFull()) {
                System.out.println("Draw!");
                break;
            }

            aiTurn();
            printMap();
            if (checkWin(DOT_O)) {
                System.out.println("Computer is WON!");
                break;
            }
            if (isFull()) {
                System.out.println("Draw!");
                break;
            }
        }

    }
    public static void initMap() {
        map = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                map[i][j] = DOT_EMPTY;
            }
        }
    }

    public static void printMap() {
        System.out.print("  ");
        for (int i = 1; i <= SIZE; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < SIZE; i++) {
            System.out.print(i + 1 + " ");
            for (int j = 0; j < SIZE; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }


    }

    public static void humanTurn() {
        int x, y;
        do {
            System.out.println("Turn X and Y: ");
            x = sc.nextInt()- 1;
            y = sc.nextInt() - 1;
        } while (!isCellValid(y, x));

        map[y][x] = DOT_X;
    }

    private static boolean isCellValid(int y, int x) {
        if (x < 0 || y < 0 || x >= SIZE || y >= SIZE) {
            return false;
        }
        return map[y][x] == DOT_EMPTY;
    }

    public static void aiTurn() {
        int x, y;
        do {
            x = random.nextInt(SIZE);
            y = random.nextInt(SIZE);
        } while (!isCellValid(y, x));

        map[y][x] = DOT_O;
    }

    public static boolean isFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == DOT_EMPTY) {
                    return false;
                }
            }

        }
        return true;
    }

    public static boolean checkLine(int start_x, int start_y, int dx, int dy, char с) {
        for(int i = 0; i < DOTS_TO_WIN; i++ ) {
            if (map[start_x + i * dx][start_y + i * dy] != с) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkWin(char с) {
        for (int i = 0; i < DOTS_TO_WIN; i++) {
            for (int j = 0; j < DOTS_TO_WIN; j++) {
                if (checkLine(i, j, 1, 0, с)) {
                    return true;
                    //столбцы
                } else if (checkLine(i, j, 0, 1, с)) {
                    return  true;
                    //диагонали
                } else if (checkLine(i, j, 1, 1, с)) {
                    return true;
                } else if (checkLine(i, SIZE - 1, 1, -1, с)) {
                    return true;
                }
            }

        }
        return false;
    }

}

