import java.util.Scanner;

class Game {
    private final String PLAYER = "P ";
    private final String BACKGROUND = "* ";

    private final String[][] map = new String[11][11];
    private final Scanner sc = new Scanner(System.in);

    private char dir;

    Game() {
        initMap();
        initPlayer();

        while(true) {
            printAll();
            getDirPlayer();
            movePlayer(dir);
        }
    }

    private void movePlayer(char dir) {
        int[] locationPlayer = searchPlayer();
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

    private int[] searchPlayer() {
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
        return new int[] {row, col};
    }

    private void getDirPlayer() {
        System.out.println("원하는 방향(W,A,S,D)을 입력해주세요.");
        dir = sc.next().charAt(0);
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
