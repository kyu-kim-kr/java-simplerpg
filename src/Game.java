class Game {
    private final String[][] map = new String[11][11];

    Game() {
        initMap();
        initPlayer();
        printAll();
    }


    private void initMap() {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                map[i][j] = "* ";
            }
        }
    }

    private void initPlayer() {
        map[5][5] = "P ";
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
