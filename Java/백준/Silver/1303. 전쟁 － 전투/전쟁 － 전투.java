import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayDeque;

public class Main {
  static int[][] OFFSET = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};

  static int ROW_COUNT, COL_COUNT;
  static char[][] board;
  static boolean[][] visited;

  static int W, B;
  
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    COL_COUNT = Integer.parseInt(st.nextToken());
    ROW_COUNT = Integer.parseInt(st.nextToken());

    board = new char[ROW_COUNT][COL_COUNT];

    for (int i = 0; i < ROW_COUNT; i++) {
      board[i] = br.readLine().toCharArray();
    }

    visited = new boolean[ROW_COUNT][COL_COUNT];

    for (int i = 0; i < ROW_COUNT; i++) {
      for (int j = 0; j < COL_COUNT; j++) {
        if (board[i][j] == 'W' && !visited[i][j]) {
          bfs(i, j, 'W');
        } else if (board[i][j] == 'B' && !visited[i][j]) {
          bfs(i, j, 'B');
        }

      }
    }

    System.out.print(W + " " + B);
  }

  static void bfs(int row, int col, char target) {
    ArrayDeque<Dot> queue = new ArrayDeque<>();
    queue.add(new Dot(row, col));
    visited[row][col] = true;
    
    int answer = 0;

    while(!queue.isEmpty()) {
      Dot polled = queue.poll();

      int curRow = polled.row;
      int curCol = polled.col;

      answer++;

      for (int[] o: OFFSET) {
        int newRow = curRow + o[0];
        int newCol = curCol + o[1];

        if (newRow < 0 || newRow >= ROW_COUNT || newCol < 0 || newCol >= COL_COUNT) {
          continue;
        }

        if (board[newRow][newCol] == target && !visited[newRow][newCol]) {
          visited[newRow][newCol] = true;
          queue.add(new Dot(newRow, newCol));
        }
      }
    }

    if(target == 'W') {
      W += answer * answer;
    } else {
      B += answer * answer;
    }
  }

  static class Dot {
    int row;
    int col;

    Dot(int row, int col) {
      this.row = row;
      this.col = col;
    }
  }
}