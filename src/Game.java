import java.util.Random;
import java.util.Scanner;

class Game {
    private final String PLAYER = "P ";
    private final String BACKGROUND = "* ";
    private final String MINE = "M ";

    private final String[][] map = new String[11][11];
    private final Scanner sc = new Scanner(System.in);
    private final Random rd = new Random();

    private char dir;

    Game() {
        initMap();
        initPlayer();
        initMine();

        while (true) {
            printAll();
            getDirPlayer();
            movePlayer(dir);
        }
    }


    private void movePlayer(char dir) {
        // TODO : 배열 벗어나지 않도록 조건문 만들기
        int[] locationPlayer = retrievePlayer();
        map[locationPlayer[0]][locationPlayer[1]] = BACKGROUND;
        switch (dir) {
            case 'W':
                map[locationPlayer[0] - 1][locationPlayer[1]] = PLAYER;
                break;
            case 'A':
                map[locationPlayer[0]][locationPlayer[1] - 1] = PLAYER;
                break;
            case 'S':
                map[locationPlayer[0] + 1][locationPlayer[1]] = PLAYER;
                break;
            case 'D':
                map[locationPlayer[0]][locationPlayer[1] + 1] = PLAYER;
                break;
        }
    }

    private int[] retrievePlayer() {
        int row = 0;
        int col = 0;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                if (map[i][j].equals(PLAYER)) {
                    row = i;
                    col = j;
                }
            }
        }
        return new int[]{row, col};
    }

    private void getDirPlayer() {
        System.out.println("원하는 방향(W,A,S,D)을 입력해주세요.");
        dir = sc.next().charAt(0);
        // TODO : WASD 중 다른 방향이 들어왔을 때, 다시 입력받기
    }

    private void initMine() {
        // TODO : 나중에 마인 여러개 만들기
        int row, col;
        row = rd.nextInt(10) - 1;
        col = rd.nextInt(10) - 1;
        map[row][col] = MINE;
        while (row == 5 && col == 5) {
            row = rd.nextInt(10) - 1;
            col = rd.nextInt(10) - 1;
            map[row][col] = MINE;
        }
    }

    private void initMap() {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                map[i][j] = BACKGROUND;
            }
        }
    }

    private void initPlayer() {
        map[5][5] = PLAYER;
    }

    private void printAll() {
        for (String[] e : map) {
            for (String ee : e) {
                System.out.print(ee);
            }
            System.out.println();
        }
    }
}
