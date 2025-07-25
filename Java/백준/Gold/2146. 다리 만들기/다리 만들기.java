import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {

  static int[][] grid;
  static int[][] offset = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int N = Integer.parseInt(br.readLine());

    grid = new int[N][N];

    for (int i = 0; i < N; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      for (int j = 0; j < N; j++) {
        grid[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    int start = 2;

    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        if (grid[i][j] == 1) {
          bfs(i, j, start++);
        }
      }
    }

    Map<Integer, List<Node>> outer = new HashMap<>();

    for (int i = 2; i < start; i++) {
      outer.put(i, new ArrayList<>());
    }

    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        if (grid[i][j] >= 2 && isOuter(i, j)) {
          outer.get(grid[i][j]).add(new Node(i, j));
        }
      }
    }

    int answer = Integer.MAX_VALUE;

    for (int i : outer.keySet()) {
      int min = bfsIsland(outer.get(i), i);
      answer = Math.min(answer, min);
    }

    System.out.print(answer);
  }

  private static int bfsIsland(List<Node> nodes, int startId) {

    int min = Integer.MAX_VALUE;

    for (Node node : nodes) {
      Queue<Step> queue = new ArrayDeque<>();
      queue.add(new Step(node.x, node.y, 0));
      boolean[][] visited = new boolean[grid.length][grid.length];
      visited[node.x][node.y] = true;

      while (!queue.isEmpty()) {
        Step step = queue.poll();

        int x = step.x;
        int y = step.y;
        int count = step.count;

        for (int[] ints : offset) {
          int newX = x + ints[0];
          int newY = y + ints[1];

          if (newX >= 0 && newX < grid.length && newY >= 0 && newY < grid.length
              && !visited[newX][newY]) {
            if (grid[newX][newY] >= 2 && grid[newX][newY] != startId) {
              min = Math.min(min, count);
              break;
            }

            visited[newX][newY] = true;
            queue.add(new Step(newX, newY, count + 1));

          }

        }
      }
    }

    return min;
  }

  private static boolean isOuter(int x, int y) {
    for (int[] ints : offset) {
      int newX = x + ints[0];
      int newY = y + ints[1];

      if (newX >= 0 && newX < grid.length && newY >= 0 && newY < grid.length
          && grid[newX][newY] == 0) {
        return true;
      }
    }

    return false;
  }

  private static void bfs(int x, int y, int target) {
    ArrayDeque<Node> queue = new ArrayDeque<>();
    queue.add(new Node(x, y));
    grid[x][y] = target;

    while (!queue.isEmpty()) {
      Node node = queue.poll();

      for (int[] ints : offset) {
        int newX = node.x + ints[0];
        int newY = node.y + ints[1];

        if (newX >= 0 && newX < grid.length && newY >= 0 && newY < grid.length) {
          if (grid[newX][newY] == 1) {
            grid[newX][newY] = target;
            queue.add(new Node(newX, newY));
          }
        }
      }
    }

  }

  static class Step {

    int x;
    int y;
    int count;

    public Step(int x, int y, int count) {
      this.x = x;
      this.y = y;
      this.count = count;
    }
  }

  static class Node {

    int x, y;

    public Node(int x, int y) {
      this.x = x;
      this.y = y;
    }
  }

}