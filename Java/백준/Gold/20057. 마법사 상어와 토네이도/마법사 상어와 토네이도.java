import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;

class Main {

  static int N;
  static int[][] BOARD;
  // 0: North 1: East 2: South 3: West
  static int[][] OFFSET = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
  static Map<Integer, List<Coordinate>> COORDINATES = new HashMap<>();

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    N = Integer.parseInt(br.readLine());
    BOARD = new int[N][N];
 
    for (int i = N - 1; i >= 0; i--) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      for(int j = 0; j < N; j++) {
        BOARD[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    initCoordinate();

    int row = N / 2;
    int col = N / 2;

    TornadoStatus tornado = new TornadoStatus();

    int answer = 0;

    while (true) {
      if(row == N - 1 && col == 0) {
        break;
      }

      int direction = tornado.getDirection();
      int nextCellCount = tornado.getNextCellCount();
      // System.out.println("row: " + row + " col: " + col);
      // System.out.println("direction: " + direction + " nextCellCount: " + nextCellCount);

      for (int i = 0; i < nextCellCount; i++) {
        // System.out.println("=====================row: " + row + " col: " + col);
        int curSand = BOARD[row + OFFSET[direction][0]][col + OFFSET[direction][1]]; 
        int leftSand = curSand;

        for (Coordinate co: COORDINATES.get(direction)) {
          int percent = co.percent;
          int newRow = row + co.row;
          int newCol = col + co.col;
          // System.out.println("newRow: " + newRow + " newCol: " + newCol);
          // 남은 모래
          if (percent == -1) {
            if(newRow < 0 || newRow >= N || newCol < 0 || newCol >= N) {
              answer += leftSand;
              break;
            } 

            BOARD[newRow][newCol] += leftSand;
            break;
          }

          int targetSand = (int)((double) curSand  * (double) ((double) percent / 100));

          // 모래가 밖으로 나갔을 경우
          if(newRow < 0 || newRow >= N || newCol < 0 || newCol >= N) {
            answer += targetSand;
            leftSand -= targetSand;
            continue;
          }

          // 정상 흐름
          BOARD[newRow][newCol] += targetSand;
          leftSand -= targetSand;
        }

        BOARD[row + OFFSET[direction][0]][col + OFFSET[direction][1]] = 0;

        row += OFFSET[direction][0];
        col += OFFSET[direction][1];
      }
      tornado.pass();
    }

    System.out.print(answer);
  }
  
  static class TornadoStatus {
    // 0: North 1: East 2: South 3: West
    private int direction = 3;

    private int nextCellCount = 1;
    private int passedCount = 0;

    public void pass() {
      this.passedCount++;

      if (this.passedCount == 2) {
        passedCount = 0;
        
        if(nextCellCount != N - 1) {
          nextCellCount++;
        }
      }

      turnLeft();
    }

    private void turnLeft() {
      if (this.direction - 1 < 0) {
        this.direction = 3;
        return;
      }

      this.direction--;
    }

    int getDirection() {
      return this.direction;
    }

    int getNextCellCount() {
      return this.nextCellCount;
    }

  }

  static class Coordinate{
    int row;
    int col;
    int percent;

    Coordinate(int row, int col, int percent) {
      this.row = row;
      this.col = col;
      this.percent = percent;
    }
  }


  private static void initCoordinate() {
    // percent의 -1 은 a
    Coordinate[] north = {
    new Coordinate(3, 0, 5),
    new Coordinate(2, -1, 10),
    new Coordinate(2, 1, 10),
    new Coordinate(1, -1, 7),
    new Coordinate(1, 1, 7),
    new Coordinate(1, -2, 2),
    new Coordinate(1, 2, 2),
    new Coordinate(0, -1, 1),
    new Coordinate(0, 1, 1),
    new Coordinate(1, 0, 0),
    new Coordinate(2, 0, -1),
  };

    Coordinate[] south = {
    new Coordinate(-3, 0, 5),
    new Coordinate(-2, -1, 10),
    new Coordinate(-2, 1, 10),
    new Coordinate(-1, -1, 7),
    new Coordinate(-1, 1, 7),
    new Coordinate(-1, -2, 2),
    new Coordinate(-1, 2, 2),
    new Coordinate(0, -1, 1),
    new Coordinate(0, 1, 1),
    new Coordinate(-1, 0, 0),
    new Coordinate(-2, 0, -1),
  };

    Coordinate[] west = {
    new Coordinate(0, -3, 5),
    new Coordinate(-1, -2, 10),
    new Coordinate(1, -2, 10),
    new Coordinate(-1, -1, 7),
    new Coordinate(1, -1, 7),
    new Coordinate(-2, -1, 2),
    new Coordinate(2, -1, 2),
    new Coordinate(-1, 0, 1),
    new Coordinate(1, 0, 1),
    new Coordinate(0, -1, 0),
    new Coordinate(0, -2, -1),
  };
      
  Coordinate[] east = {
    new Coordinate(0, 3, 5),
    new Coordinate(-1, 2, 10),
    new Coordinate(1, 2, 10),
    new Coordinate(-1, 1, 7),
    new Coordinate(1, 1, 7),
    new Coordinate(-2, 1, 2),
    new Coordinate(2, 1, 2),
    new Coordinate(-1, 0, 1),
    new Coordinate(1, 0, 1),
    new Coordinate(0, 1, 0),
    new Coordinate(0, 2, -1),
  };

  // 0: North 1: East 2: South 3: West
  COORDINATES.put(0, new ArrayList<Coordinate>(Arrays.asList(north)));
  COORDINATES.put(1, new ArrayList<Coordinate>(Arrays.asList(east)));
  COORDINATES.put(2, new ArrayList<Coordinate>(Arrays.asList(south)));
  COORDINATES.put(3, new ArrayList<Coordinate>(Arrays.asList(west)));
  }
}

