import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayDeque;

public class Main {
  static int[][] OFFSET = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};

  static int ROW_COUNT, COL_COUNT;
  
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    ROW_COUNT = Integer.parseInt(st.nextToken());
    COL_COUNT = Integer.parseInt(st.nextToken());

    char[][] board = new char[ROW_COUNT][COL_COUNT];
    
    for(int i = 0; i < ROW_COUNT; i++) {
      st = new StringTokenizer(br.readLine());

      for (int j = 0; j < COL_COUNT; j++) {
        board[i][j] = st.nextToken().charAt(0);
      }
    }

    int answer = 0;

    for (int i = 1; i <= ROW_COUNT * COL_COUNT - 2; i++) {
      int iRow = (i - 1) / COL_COUNT;
      int iCol = (i - 1) % COL_COUNT;

      if (board[iRow][iCol] != '0') {
        continue;
      }

      board[iRow][iCol] = '1';

      for (int j = i + 1; j <= ROW_COUNT * COL_COUNT - 1; j++) {
        int jRow = (j - 1) / COL_COUNT;
        int jCol = (j - 1) % COL_COUNT;
        
        if (board[jRow][jCol] != '0') {
          continue;
        }

        board[jRow][jCol] = '1';

        for (int k = j + 1; k <= ROW_COUNT * COL_COUNT; k++) {
          int kRow = (k - 1) / COL_COUNT;
          int kCol = (k - 1) % COL_COUNT;
        
          if (board[kRow][kCol] != '0') {
            continue;
          }
        
          board[kRow][kCol] = '1';

          answer = Math.max(answer, check(board));

          board[kRow][kCol] = '0';
        }

        board[jRow][jCol] = '0';
      }

      board[iRow][iCol] = '0';
    }

    System.out.print(answer);
  }

  static int check(char[][] input) {
    char[][] buffer = new char[ROW_COUNT][COL_COUNT];

    for (int i = 0; i < input.length; i++) {
      System.arraycopy(input[i], 0, buffer[i], 0, COL_COUNT);
    }

    ArrayDeque<Dot> queue = new ArrayDeque<>();

    for (int i = 0; i < ROW_COUNT; i++) {
      for (int j = 0; j < COL_COUNT; j++) {
        if(buffer[i][j] == '2') {
          queue.add(new Dot(i, j));
        }
      }
    }
    
    while (!queue.isEmpty()) {
      Dot cur = queue.poll();

      int row = cur.row;
      int col = cur.col;
      
      for (int[] off: OFFSET) {
        int newRow = row + off[0];
        int newCol = col + off[1];

        
        if(newRow < 0 || newRow >= ROW_COUNT || newCol < 0 || newCol >= COL_COUNT) {
          continue;
        }

        if (buffer[newRow][newCol] == '0') {
          buffer[newRow][newCol] = '2';
          queue.add(new Dot(newRow, newCol));
        }
      }
    }

    int answer = 0; 

    for (int i = 0; i < ROW_COUNT; i++) {
      for (int j = 0; j < COL_COUNT; j++) {
        if (buffer[i][j] == '0') {
          answer++;
        }
      }
    }

    return answer;
  }

  static class Dot{
    int row;
    int col;

    Dot(int row, int col) {
      this.row = row;
      this.col = col;
    }
  }
}