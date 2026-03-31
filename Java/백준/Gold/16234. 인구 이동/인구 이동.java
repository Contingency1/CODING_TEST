import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayDeque;
import java.util.List;
import java.util.ArrayList;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());

    final int COUNT = Integer.parseInt(st.nextToken());
    final int L = Integer.parseInt(st.nextToken());
    final int R = Integer.parseInt(st.nextToken());

    int[][] board = new int[COUNT][COUNT];

    for (int i = 0; i < COUNT; i++) {
      st = new StringTokenizer(br.readLine());

      for (int j = 0; j < COUNT; j++) {
        int num = Integer.parseInt(st.nextToken());

        board[i][j] = num;
      }
    }

    int answer = 0;
    int[][] offSet = {{1, 0}, {-1, 0}, {0, -1}, {0, 1}};

    while(true) {
      boolean isFixed = false;
      boolean[][] visited = new boolean[COUNT][COUNT];

      for (int r = 0; r < COUNT; r++) {
        for (int c = 0; c < COUNT; c++) {
          if(!visited[r][c]) {
            List<Coordinate> list = new ArrayList<>();

            ArrayDeque<Coordinate> queue = new ArrayDeque<>();
            queue.addLast(new Coordinate(r, c));
            visited[r][c] = true;

            while(!queue.isEmpty()) {
              Coordinate co = queue.pollFirst();

              int row = co.row;
              int col = co.col;

              list.add(new Coordinate(row, col));

              for (int[] off: offSet) {
                int newRow = row + off[0];
                int newCol = col + off[1];
                
                if(newRow < 0 || newRow >= COUNT ||
                   newCol < 0 || newCol >= COUNT || 
                   visited[newRow][newCol]) {
                    continue;
                }

                int diff = Math.abs(board[row][col] - board[newRow][newCol]);

                if(diff < L || diff > R) {
                  continue;
                }

                queue.addLast(new Coordinate(newRow, newCol));
                visited[newRow][newCol] = true;
              }
            }

            int listSize = list.size();

            if(listSize <= 1) {
              continue;
            }

            isFixed = true;
            int sum = 0;

            for (Coordinate coor: list) {
              sum += board[coor.row][coor.col];
            }

            int key = sum / listSize;

            for (Coordinate coor: list) {
              board[coor.row][coor.col] = key;
            }
          }
          
        }
      }

      if(!isFixed) {
        break;
      }

      answer++;
    }

    System.out.print(answer);
  }
}

class Coordinate {
  final int row;
  final int col;

  Coordinate(int row, int col) {
    this.row = row;
    this.col = col;
  }
}