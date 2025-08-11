import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;
import java.util.Queue;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;


class Main {
  static int[][] square;
  static int[][] offset = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
  static int inputRow;
  static int inputCol;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    inputRow = Integer.parseInt(st.nextToken());
    inputCol = Integer.parseInt(st.nextToken());
    
    square = new int[inputRow][inputCol];

    for(int i = 0; i < inputRow; i++) {
      st = new StringTokenizer(br.readLine());
      for(int j = 0; j < inputCol; j++) {
        square[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    for (int i = 0; i < inputRow; i++) {
      if (i == 0 || i == inputRow - 1) {
        Arrays.fill(square[i], -1);
        continue;
      }
      square[i][0] = -1;
      square[i][inputCol - 1] = -1;
    }

    
    for (int i = 0; i < inputRow; i++) {
      if (i == 0 || i == inputRow - 1) {
        for (int j = 0; j < inputCol; j++) {
          minusBfs(i, j);
        } 
        continue;
      }
      minusBfs(i, 0);
      minusBfs(i, inputCol - 1);
    }

    boolean isNothing;
    List<NodeJS> willBeDeleted = new ArrayList<>();
    int left = 0;
    int time = 0;

    while (true) {
      willBeDeleted.clear();
      isNothing = true;

      for (int i = 0; i < inputRow; i++) {
        for (int j = 0; j < inputCol; j++) {
          if(square[i][j] == 1) {
            for (int[] set : offset) {
              int newRow = i + set[0];
              int newCol = j + set[1];
              
              if (newRow >= 0 && newRow < inputRow  && newCol >= 0 && newCol < inputCol) {
                if(square[newRow][newCol] == -1) {
                  isNothing = false;
                  willBeDeleted.add(new NodeJS(i, j));
                  break;
                }
              }
            }
          }
        }
      }

      left = willBeDeleted.size() == 0 ? left : willBeDeleted.size();

      for(NodeJS node : willBeDeleted) {
        square[node.row][node.col] = -1;
      }
      
      for (int i = 0; i < inputRow; i++) {
        for (int j = 0; j < inputCol; j++) {
          if(square[i][j] == 0) {
            for (int[] set : offset) {
              int newRow = i + set[0];
              int newCol = j + set[1];
              
              if (newRow >= 0 && newRow < inputRow  && newCol >= 0 && newCol < inputCol) {
                if (square[newRow][newCol] == -1) {
                  holeBfs(i, j);
                }
              }
            }
          }
        }
      }
      
      if (isNothing) {
        break;
      }

      time++;
    }


    System.out.println(time);
    System.out.print(left); 
  }

  static void holeBfs(int row, int col) {
    Queue<NodeJS> queue = new ArrayDeque<>();
    queue.add(new NodeJS(row, col));

    while (!queue.isEmpty()) {
      NodeJS polled = queue.poll();
      int curRow = polled.row;
      int curCol = polled.col;
    
      for (int[] set : offset) {
        int newRow = curRow + set[0];
        int newCol = curCol + set[1];

        if (newRow >= 1 && newRow < inputRow - 1 && newCol >= 1 && newCol < inputCol - 1) {
          if (square[newRow][newCol] == 0) {
            square[newRow][newCol] = -1;
            queue.add(new NodeJS(newRow, newCol));
          }
        }
      }
    }

  }

  static void minusBfs(int row, int col) {
    Queue<NodeJS> queue = new ArrayDeque<>();
    queue.add(new NodeJS(row, col));

    while (!queue.isEmpty()) {
      NodeJS polled = queue.poll();
      int curRow = polled.row;
      int curCol = polled.col;
    
      for (int[] set : offset) {
        int newRow = curRow + set[0];
        int newCol = curCol + set[1];

        if (newRow >= 1 && newRow < inputRow - 1 && newCol >= 1 && newCol < inputCol - 1) {
          if (square[newRow][newCol] == 0) {
            square[newRow][newCol] = -1;
            queue.add(new NodeJS(newRow, newCol));
          }
        }
      }
    }

    
  }

  static class NodeJS {
    int row;
    int col;
    
    NodeJS(int row, int col){
      this.row = row;
      this.col = col;
    }
  }

}