import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());
    final int ROW_COUNT = Integer.parseInt(st.nextToken());
    final int COL_COUNT = Integer.parseInt(st.nextToken());

    char[][] board = new char[ROW_COUNT][COL_COUNT];

    for (int i = 0; i < ROW_COUNT; i++) {
      st = new StringTokenizer(br.readLine());

      for (int j = 0; j < COL_COUNT; j++) {
        board[i][j] = st.nextToken().charAt(0);
      }
    }

    int answer = getAnswer(board);

    System.out.print(answer);

  }

  static int getAnswer(char[][] board) {
    boolean[][] visited = new boolean[board.length][board[0].length];

    int result = 0;

    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[0].length; j++) {
        if (visited[i][j] || board[i][j] == '1') {
          continue;
        }

        bfs(board, visited, new Coordinate(i, j));
        result++;
      }
    }

    return result;
  }

  static void bfs(char[][] board, boolean[][] visited, Coordinate coordinate) {
    int[][] offSet = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    ArrayDeque<Coordinate> queue = new ArrayDeque<>();

    queue.add(coordinate);
    visited[coordinate.row][coordinate.col] = true;

    while (!queue.isEmpty()) {
      Coordinate poll = queue.poll();
      int row = poll.row;
      int col = poll.col;

      for (int[] ints : offSet) {
        int r = ints[0] + row;
        int c = ints[1] + col;

        int newRow = r >= board.length ? 0 : r;
        if (newRow < 0) {
          newRow = board.length - 1;
        }

        int newCol = c >= board[0].length ? 0 : c;
        if (newCol < 0) {
          newCol = board[0].length - 1;
        }

        if (visited[newRow][newCol] || board[newRow][newCol] != '0') {
          continue;
        }

        queue.add(new Coordinate(newRow, newCol));
        visited[newRow][newCol] = true;
      }
    }

  }
}

class Coordinate {

  int row;
  int col;

  public Coordinate(int row, int col) {
    this.row = row;
    this.col = col;
  }
}