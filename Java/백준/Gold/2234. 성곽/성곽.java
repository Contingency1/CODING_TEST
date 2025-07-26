import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

class Main {

  static int[][] grid;
  static int[][] visited;
  static Map<Integer, Integer> map;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());

    final int COL_COUNT = Integer.parseInt(st.nextToken());
    final int ROW_COUNT = Integer.parseInt(st.nextToken());

    grid = new int[ROW_COUNT][COL_COUNT];

    for (int i = 0; i < ROW_COUNT; i++) {
      st = new StringTokenizer(br.readLine());

      for (int j = 0; j < COL_COUNT; j++) {
        grid[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    visited = new int[ROW_COUNT][COL_COUNT];

    int id = 1;

    map = new HashMap<>();

    int max = 0;
    for (int row = 0; row < ROW_COUNT; row++) {
      for (int col = 0; col < COL_COUNT; col++) {
        if (visited[row][col] == 0) {
          map.put(id, 0);
          bfs(row, col, id);
          max = Math.max(max, map.get(id));
          id++;
        }
      }
    }

    StringBuilder answer = new StringBuilder();
    answer.append(map.size()).append("\n").append(max).append("\n");

    int maxSize = 0;
    int[][] offset = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};

    for (int row = 0; row < ROW_COUNT; row++) {
      for (int col = 0; col < COL_COUNT; col++) {

        for (int[] ints : offset) {
          int newRow = row + ints[0];
          int newCol = col + ints[1];

          if (newRow >= 0 && newRow < ROW_COUNT && newCol >= 0 && newCol < COL_COUNT) {
            if (visited[newRow][newCol] != visited[row][col]) {
              maxSize = Math.max(maxSize,
                  map.get(visited[newRow][newCol]) + map.get(visited[row][col]));
            }
          }
        }
      }
    }
    answer.append(maxSize);
    System.out.print(answer);
  }


  private static void bfs(int row, int col, int id) {

    ArrayDeque<Node> queue = new ArrayDeque<>();
    queue.add(new Node(row, col));
    visited[row][col] = id;

    while (!queue.isEmpty()) {
      Node node = queue.poll();
      Integer i = map.get(id);
      map.put(id, i + 1);

      int curRow = node.row;
      int curCol = node.col;

      int location = grid[curRow][curCol];

      if ((location & 8) == 0) {
        if (visited[curRow + 1][curCol] == 0) {
          visited[curRow + 1][curCol] = id;
          queue.add(new Node(curRow + 1, curCol));
        }
      }

      if ((location & 4) == 0) {
        if (visited[curRow][curCol + 1] == 0) {
          visited[curRow][curCol + 1] = id;
          queue.add(new Node(curRow, curCol + 1));
        }
      }

      if ((location & 2) == 0) {
        if (visited[curRow - 1][curCol] == 0) {
          visited[curRow - 1][curCol] = id;
          queue.add(new Node(curRow - 1, curCol));
        }
      }

      if ((location & 1) == 0) {
        if (visited[curRow][curCol - 1] == 0) {
          visited[curRow][curCol - 1] = id;
          queue.add(new Node(curRow, curCol - 1));
        }
      }
    }


  }

  static class Node {

    int row;
    int col;

    public Node(int row, int col) {
      this.row = row;
      this.col = col;
    }
  }

}