import java.util.Random;
import java.util.Scanner;

class Game {
    private final String PLAYER = "P ";
    private final String BACKGROUND = "* ";
    private final String MINE = "M ";
    private final String MONSTER = "O ";

    private int mine_x;
    private int mine_y;
    private int monster_x;
    private int monster_y;

    private final String[][] map = new String[11][11];
    private final Scanner sc = new Scanner(System.in);
    private final Random rd = new Random();

    private char dir;

    Game() {
        initMap();
        initPlayer();
        initMine();
        initMonster();

        while (true) {
            printAll();
            getDirPlayer();
            movePlayer(dir);
            endGame();
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

    private void initMonster() {
        monster_x = rd.nextInt(10) - 1;
        monster_y = rd.nextInt(10) - 1;
        map[monster_x][monster_y] = MONSTER;
        while (monster_x == 5 && monster_y == 5 || monster_x == mine_x && monster_y == mine_y) {
            monster_x = rd.nextInt(10) - 1;
            monster_y = rd.nextInt(10) - 1;
            map[monster_x][monster_y] = MONSTER;
        }
    }

    private void initMine() {
        // TODO : 나중에 마인 여러개 만들기
        mine_x = rd.nextInt(10) - 1;
        mine_y = rd.nextInt(10) - 1;
        map[mine_x][mine_y] = MINE;
        while (mine_x == 5 && mine_y == 5) {
            mine_x = rd.nextInt(10) - 1;
            mine_y = rd.nextInt(10) - 1;
            map[mine_x][mine_y] = MINE;
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

    private void endGame() {
        int temp1 = 0, temp2 = 1;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                if (map[i][j].equals(PLAYER)) {
                    temp1 = i;
                    temp2 = j;
                }
            }
        }
        if (temp1 == mine_x && temp2 == mine_y){
            System.out.println("지뢰를 밟았습니다.");
            System.exit(0);
        }
    }
}
