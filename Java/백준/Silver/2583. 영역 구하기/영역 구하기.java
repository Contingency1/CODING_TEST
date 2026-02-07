import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;
import java.util.ArrayDeque;

public class Main {

  static int[][] offset = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());
    
    final int ROW_COUNT = Integer.parseInt(st.nextToken());
    final int COL_COUNT = Integer.parseInt(st.nextToken());
    final int CASE = Integer.parseInt(st.nextToken());

    boolean[][] board = new boolean[ROW_COUNT][COL_COUNT];

    for (int i = 0; i < CASE; i++) {
      st = new StringTokenizer(br.readLine());

      int cStart = Integer.parseInt(st.nextToken());
      int rStart = Integer.parseInt(st.nextToken());
      int cEnd = Integer.parseInt(st.nextToken()) - 1;
      int rEnd = Integer.parseInt(st.nextToken()) - 1;

      for (int r = rStart; r <= rEnd; r++) {
        for (int c = cStart; c <= cEnd; c++) {
          board[r][c] = true;
        }
      }
    }

    List<Integer> list = new ArrayList<>();

    ArrayDeque<Dot> queue = new ArrayDeque<>();

    for (int r = 0; r < ROW_COUNT; r++) {
      for (int c = 0; c < COL_COUNT; c++) {
        if(!board[r][c]) {
          queue.addLast(new Dot(r, c));
          board[r][c] = true;

          int answer = 0;

          while(!queue.isEmpty()) {
            Dot polled = queue.pollFirst();
            answer++;

            int row = polled.row;
            int col = polled.col;

            for (int[] off: offset) {
              int newRow = row + off[0];
              int newCol = col + off[1];

              if(newRow >= 0 && newRow < ROW_COUNT &&
                 newCol >= 0 && newCol < COL_COUNT && !board[newRow][newCol]) {
                queue.addLast(new Dot(newRow, newCol));
                board[newRow][newCol] = true;
              }
            }
          }

          list.add(answer);
        }
      }
    }

    list.sort(null);

    System.out.println(list.size());

    StringBuilder sb = new StringBuilder();

    for (int l: list) {
      sb.append(l).append(" ");
    }

    sb.deleteCharAt(sb.length() - 1);
    
    System.out.print(sb);
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