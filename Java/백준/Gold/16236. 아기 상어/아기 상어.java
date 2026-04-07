import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    final int SIZE = Integer.parseInt(br.readLine());
    int[][] board = new int[SIZE][SIZE];

    BabyShark baby = null;
    StringTokenizer st;
    for (int i = 0; i < SIZE; i++) {
      st = new StringTokenizer(br.readLine());

      for (int j = 0; j < SIZE; j++) {
        board[i][j] = Integer.parseInt(st.nextToken());

        if (board[i][j] == 9) {
          baby = new BabyShark(new Coordinate(i, j));
          board[i][j] = 0;
        }

      }
    }

    FishService fishService = new FishService(board, baby);

    int answer = fishService.getAnswer();
    System.out.print(answer);

  }
}

class Coordinate {

  private final int row;
  private final int col;

  public Coordinate(int row, int col) {
    this.row = row;
    this.col = col;
  }

  public int getRow() {
    return row;
  }

  public int getCol() {
    return col;
  }
}

class FishCoordinate extends Coordinate implements Comparable<FishCoordinate> {

  private final int size;

  public FishCoordinate(int row, int col, int size) {
    super(row, col);
    this.size = size;
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof FishCoordinate)) {
      return false;
    }

    FishCoordinate that = (FishCoordinate) o;
    return this.size == that.size
        && this.getRow() == that.getRow()
        && this.getCol() == that.getCol();
  }

  @Override
  public int compareTo(FishCoordinate o) {
    if (this.getRow() == o.getRow()) {
      return this.getCol() - o.getCol();
    }

    return this.getRow() - o.getRow();
  }
}

class BabyShark {

  static int[][] offSet = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
  private Coordinate location;
  private int size = 2;
  private int eatCount = 0;
  private int time = 0;

  public BabyShark(Coordinate location) {
    this.location = location;
  }

  public int getTime() {
    return time;
  }

  public void move(Coordinate target) {
    this.location = target;
  }

  public void eat(int[][] board, Coordinate target) {
    board[target.getRow()][target.getCol()] = 0;
    eatCount++;

    if (eatCount == size) {
      size++;
      eatCount = 0;
    }
  }

  public FishCoordinate getNextTarget(int[][] board) {
    List<FishCoordinate> result = bfs(board);

    result.sort(null);
    return result.isEmpty() ? null : result.get(0);
  }

  private List<FishCoordinate> bfs(int[][] board) {
    List<FishCoordinate> result = new ArrayList<>();
    ArrayDeque<Node> queue = new ArrayDeque<>();
    boolean[][] visited = new boolean[board.length][board[0].length];

    int minCost = Integer.MAX_VALUE;

    visited[location.getRow()][location.getCol()] = true;
    queue.addLast(new Node(location, 0));

    while (!queue.isEmpty()) {
      Node node = queue.pollFirst();
      Coordinate cur = node.getCoordinate();

      int curRow = cur.getRow();
      int curCol = cur.getCol();
      int cost = node.getCost();

      for (int[] ints : offSet) {
        int nR = ints[0] + curRow;
        int nC = ints[1] + curCol;

        if (nR < 0 || nR >= board.length ||
            nC < 0 || nC >= board[0].length ||
            visited[nR][nC] || this.size < board[nR][nC] ||
            minCost < cost + 1) {
          continue;
        }

        queue.addLast(new Node(new Coordinate(nR, nC), cost + 1));
        visited[nR][nC] = true;

        if (board[nR][nC] < this.size && board[nR][nC] != 0) {
          result.add(new FishCoordinate(nR, nC, cost + 1));
          if (minCost == Integer.MAX_VALUE) {
            minCost = cost + 1;
            this.time += cost + 1;
          }
        }
      }
    }
    return result;
  }
}

class FishService {

  int[][] board;
  BabyShark babyShark;

  public FishService(int[][] board, BabyShark babyShark) {
    this.board = board;
    this.babyShark = babyShark;
  }

  public int getAnswer() {
    FishCoordinate target;

    target = babyShark.getNextTarget(board);

    while (target != null) {
      babyShark.move(target);
      babyShark.eat(board, target);

      target = babyShark.getNextTarget(board);
    }

    return babyShark.getTime();
  }
}

class Node {

  private final Coordinate coordinate;
  private final int cost;

  public Node(Coordinate coordinate, int cost) {
    this.coordinate = coordinate;
    this.cost = cost;
  }

  public Coordinate getCoordinate() {
    return coordinate;
  }

  public int getCost() {
    return cost;
  }

}