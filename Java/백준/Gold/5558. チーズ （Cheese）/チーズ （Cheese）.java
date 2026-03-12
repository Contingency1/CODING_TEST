import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayDeque;

public class Main {
  static int[][] offSet = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());

    final int ROW_COUNT = Integer.parseInt(st.nextToken());
    final int COL_COUNT = Integer.parseInt(st.nextToken());
    final int MAX_CHEESE = Integer.parseInt(st.nextToken());

    char[][] board = new char[ROW_COUNT][COL_COUNT];

    Coordinate start = null;

    for (int i = 0; i < ROW_COUNT; i++) {
      char[] input = br.readLine().toCharArray();

      for (int j = 0; j < COL_COUNT; j++) {
        board[i][j] = input[j];

        if(board[i][j] == 'S') {
          board[i][j] = '.';
          start = new Coordinate(i, j, 0);
        }
      }
    }
  
    int hp = 1;

    boolean[][] visited = new boolean[ROW_COUNT][COL_COUNT];
    visited[start.row][start.col] = true;
    ArrayDeque<Coordinate> queue = new ArrayDeque<>();
    queue.add(start);

    int answer = Integer.MAX_VALUE;

    while(hp < MAX_CHEESE + 1 && !queue.isEmpty()) {
      Coordinate polled = queue.poll();

      int row = polled.row;
      int col = polled.col;
      int cost = polled.cost;

      for (int[] o: offSet) {
        int newRow = row + o[0];
        int newCol = col + o[1];

        if(newRow < 0 || newRow >= ROW_COUNT ||
           newCol < 0 || newCol >= COL_COUNT || 
           board[newRow][newCol] == 'X' || visited[newRow][newCol]) {
          continue;
        }

        if (board[newRow][newCol] == '.') {
          queue.add(new Coordinate(newRow, newCol, cost + 1));
          visited[newRow][newCol] = true;
        } else {
          int number = (int) board[newRow][newCol] - 48;
          if (number == hp) {
            if(hp == MAX_CHEESE) {
              answer = Math.min(answer, cost + 1);
              break;
            }

            hp++;
            queue.clear();
            queue.add(new Coordinate(newRow, newCol, cost + 1));
            visited = new boolean[ROW_COUNT][COL_COUNT];
            visited[newRow][newCol] = true;
            break;
          } else {
            queue.add(new Coordinate(newRow, newCol, cost + 1));
            visited[newRow][newCol] = true;
          }
        }
      }
    }

    System.out.print(answer);
  }
}

class Coordinate{
  int row;
  int col;
  int cost;

  Coordinate(int row, int col, int cost) {
    this.row = row;
    this.col = col;
    this.cost = cost;
  }
}