import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

class Game {
    private final String PLAYER = "P ";
    private final String BACKGROUND = "* ";
    private final String MINE = "M ";
    private final String MONSTER = "O ";

    private boolean playerMeetMonster = false;

    private final ArrayList<Integer> mine_x = new ArrayList<>();
    private final ArrayList<Integer> mine_y = new ArrayList<>();
    private int numMine = 1;

    private int monster_x;
    private int monster_y;

    private final String[][] map = new String[11][11];
    private final Scanner sc = new Scanner(System.in);
    private final Random rd = new Random();

    private char dir;

    Game() {
        createGame();

        while (true) {
            printAll();
            getDirPlayer();
            movePlayer(dir);
            retrievePlayerNMine();
            retrievePlayerNMonster();
        }
    }

    private void createGame() {
        initMap();
        initPlayer();
        initMine();
        initMonster();
    }

    private void retrievePlayerNMonster() {
        int temp1 = 0, temp2 = 1;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                if (map[i][j].equals(MONSTER)) {
                    temp1 = i;
                    temp2 = j;
                }
            }
        }
        if (temp1 == monster_x && temp2 == monster_y) {
            playerMeetMonster = true;
            createGame();
        }
    }

    private void retrievePlayerNMine() {
        int temp1 = 0, temp2 = 1;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                if (map[i][j].equals(PLAYER)) {
                    temp1 = i;
                    temp2 = j;
                }
            }
        }
        for (int i = 0; i < numMine; i++) {
            if (temp1 == mine_x.get(i) && temp2 == mine_y.get(i)) {
                System.out.println("지뢰를 밟았습니다.");
                System.exit(0);
            }
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

    private void getDirPlayer() {
        System.out.println("원하는 방향(W,A,S,D)을 입력해주세요.");
        dir = sc.next().charAt(0);
        // TODO : WASD 중 다른 방향이 들어왔을 때, 다시 입력받기
    }

    private void initMonster() {
        monster_x = rd.nextInt(11);
        monster_y = rd.nextInt(11);
        for (int i = 0; i < numMine; i++) {
            while (monster_x == mine_x.get(i) && monster_y == mine_y.get(i) || monster_x == 5 && monster_y == 5) {
                monster_x = rd.nextInt(11);
                monster_y = rd.nextInt(11);
                map[monster_x][monster_y] = MONSTER;
            }
        }
        map[monster_x][monster_y] = MONSTER;
    }

    private void initMine() {
        for (int i = 0; i < numMine; i++) {
            mine_x.add(rd.nextInt(11));
            mine_y.add(rd.nextInt(11));
            //TODO : 같은 위치의 마인 처리하기
            while (mine_x.get(i) == 5 && mine_y.get(i) == 5) {
                mine_x.add(rd.nextInt(11));
                mine_y.add(rd.nextInt(11));
                map[mine_x.get(i)][mine_y.get(i)] = MINE;
            }
            map[mine_x.get(i)][mine_y.get(i)] = MINE;
        }

        if (playerMeetMonster) {
            numMine = numMine * 2;
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
