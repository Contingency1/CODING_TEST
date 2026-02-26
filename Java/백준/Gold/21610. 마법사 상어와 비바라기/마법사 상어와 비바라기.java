import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.Set;
import java.util.HashSet;
import java.util.Objects;

public class Main {
    
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());

    final int SIZE = Integer.parseInt(st.nextToken());
    final int MOVE_COUNT = Integer.parseInt(st.nextToken());

    int[][] input = new int[SIZE][SIZE];

    for (int i = 0; i < SIZE; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < SIZE; j++) {
        input[i][j] =  Integer.parseInt(st.nextToken());
      }
    }

    int[][] command = new int[MOVE_COUNT][2];

    for (int i = 0; i < MOVE_COUNT; i++) {
      st = new StringTokenizer(br.readLine());
      command[i][0] = Integer.parseInt(st.nextToken());
      command[i][1] = Integer.parseInt(st.nextToken());
    }

    Board board = new Board(SIZE, input);

    for (int[] c: command) {
      MOVE move = MOVE.fromInt(c[0]);
      int offset = c[1];

      DistanceDTO distance = move.getDistance(offset);

      board.execute(distance.row, distance.col);
    }

    System.out.print(board.getAnswer());
  }
}

class Board {
  int size;
  Basket[][] basket;
  List<Coordinate> rainedCoordinate = new ArrayList<>();
  Set<Cloud> cloud = new HashSet<>();

  // @Override
  // public String toString() {
  //   StringBuilder sb = new StringBuilder();
    
  //   for (int i = 0; i < this.basket.length; i++) {
  //     for (int j = 0; j < this.basket[i].length; j++) {
  //       sb.append(String.format("%3d ", this.basket[i][j].getWater()));
  //     }
  //     sb.append(System.lineSeparator());
  //   }
    
  //   return sb.toString();
  // }
  
  Board(int size, int[][] input) {
    this.size = size - 1;
    this.basket = new Basket[size][size];

    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        this.basket[i][j] = new Basket(input[i][j]);
      }
    }

    initCloud(size);
  }

  public int getAnswer() {
    int answer = 0;

    for (int i = 0; i <= size; i++) {
      for (int j = 0; j <= size; j++) {
        answer += this.basket[i][j].getWater();
      }
    }

    return answer;
  }

  public void execute(int plusRow, int plusCol) {
    moveCloud(plusRow, plusCol);
    // System.out.println(toString());
    rainsFromCloud();
    // System.out.println(toString());
    waterBug();
    // System.out.println(toString());
    makeCloud();
    // System.out.println(toString());

    // System.out.println("=============");
  }

  private void moveCloud(int plusRow, int plusCol) {
    Set<Cloud> buffer = new HashSet<>();

    for (Cloud c: this.cloud) {
      buffer.add(c.move(plusRow, plusCol, this.size));
    }

    this.cloud = buffer;
  }

  private void rainsFromCloud() {
    for (Cloud c: this.cloud) {
      int curRow = c.row;
      int curCol = c.col;

      basket[curRow][curCol].rains();
      this.rainedCoordinate.add(new Coordinate(curRow, curCol));
    }
  }

  private void waterBug() {
    for (Cloud c: this.cloud) {
      int curRow = c.row;
      int curCol = c.col;

      List<Coordinate> candidates = c.getDiagonalByMax(this.size);

      for(Coordinate can: candidates) {
        if(basket[can.row][can.col].isWaterPresent()) {
          basket[curRow][curCol].rains();
        }
      }
    }
  }

  private void makeCloud() {
    Set<Cloud> buffer = new HashSet<>();

    for (int i = 0; i <= size; i++) {
      for (int j = 0; j <= size; j++) {
        Cloud newCloud = new Cloud(i, j);

        if(this.cloud.contains(newCloud)){
          continue;
        }

        if(basket[i][j].isMoreThanOneThenMinusTwo()) {
          buffer.add(newCloud);
        }
      }
    }

    this.cloud = buffer;
  }

  private void initCloud(int size) {
    this.cloud.add(new Cloud(size - 1, 0));
    this.cloud.add(new Cloud(size - 1, 1));
    this.cloud.add(new Cloud(size - 2, 0));
    this.cloud.add(new Cloud(size - 2, 1));
  }
}

class Basket {
  int water;

  Basket(int water) {
    this.water = water;
  }

  public int getWater() {
    return this.water;
  }

  public boolean isWaterPresent() {
    return this.water > 0;
  }

  public boolean isMoreThanOneThenMinusTwo() {
    if(this.water > 1) {
      this.water -= 2;
      return true;
    }

    return false;
  }

  public void rains() {
    this.water++;
  }
}

class Cloud {
  int row, col;
  static int[][] OFF_SET = {{1, 1}, {1, -1}, {-1, -1}, {-1, 1}};

  Cloud (int row, int col) {
    this.row = row;
    this.col = col;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o){
      return true;
    }

    if (!(o instanceof Cloud)) {
      return false;
    } 

    Cloud coordinate = (Cloud) o;
    return this.row == coordinate.row && this.col == coordinate.col;
  }

  @Override
  public int hashCode() {
    return Objects.hash(row, col);
  }

  public Cloud move(int plusRow, int plusCol, int max) {
    this.row += plusRow;
    this.col += plusCol;

    int size = max + 1;
    this.row = ((this.row % size) + size) % size;
    this.col = ((this.col % size) + size) % size;

    return new Cloud(this.row, this.col);
  }

  public List<Coordinate> getDiagonalByMax(int max) {
    List<Coordinate> result = new ArrayList<>();

    for(int[] off: OFF_SET) {
      int newRow = this.row + off[0];
      int newCol = this.col + off[1];

      if(newRow >= 0 && newRow <= max && newCol >= 0 && newCol <= max) {
        result.add(new Coordinate(newRow, newCol));
      }
    }

    return result;
  }
}

class Coordinate {
  int row, col;

  Coordinate (int row, int col) {
    this.row = row;
    this.col = col;
  }
}

enum MOVE {
  W(1, 0, -1), NW(2, -1, -1), N(3, -1, 0), NE(4, -1, 1), E(5, 0, 1), SE(6, 1, 1), S(7, 1, 0), SW(8, 1, -1);

  int command;
  int plusRow;
  int plusCol;

  MOVE(int command, int row, int col) {
    this.command = command;
    this.plusRow = row;
    this.plusCol = col;
  }

  public static MOVE fromInt(int input) {
    for (MOVE m: MOVE.values()) {
      if(m.command == input) {
        return m;
      }
    }

    throw new RuntimeException("NOOOO");
  }

  public DistanceDTO getDistance(int offSet) {
    return new DistanceDTO(this.plusRow * offSet, this.plusCol * offSet);
  }
}

class DistanceDTO{
  final int row;
  final int col;

  DistanceDTO(int row, int col) {
    this.row = row;
    this.col = col;
  }
}