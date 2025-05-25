import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.StringTokenizer;

public class Main {

  static int[][] map;
  static int[][] answer;
  static int[][] groupId;
  static List<Integer> groupSize;
  static int start = 1;
  static int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};


  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    StringTokenizer st = new StringTokenizer(br.readLine());

    final int ROW = Integer.parseInt(st.nextToken());
    final int COL = Integer.parseInt(st.nextToken());
    map = new int[ROW][COL];
    groupId = new int[ROW][COL];
    answer = new int[ROW][COL];
    groupSize = new ArrayList<>();
    groupSize.add(0);

    for (int i = 0; i < ROW; i++) {
      String line = br.readLine();
      for (int j = 0; j < COL; j++) {
        map[i][j] = line.charAt(j) - '0';
      }
    }
    br.close();

    for (int i = 0; i < ROW; i++) {
      for (int j = 0; j < COL; j++) {
        if (map[i][j] == 0 && groupId[i][j] == 0) {
          bfs(i, j);
        }
      }
    }

    for (int i = 0; i < ROW; i++) {
      for (int j = 0; j < COL; j++) {
        if (map[i][j] == 1) {
          int buffer = 1;
          HashSet<Integer> visitedGroupId = new HashSet<>();
          for (int[] direction : directions) {
            int nextR = i + direction[0];
            int nextC = j + direction[1];

            if (nextR >= 0 && nextR < map.length && nextC >= 0 && nextC < map[0].length) {
              if (!visitedGroupId.contains(groupId[nextR][nextC])) {
                buffer += groupSize.get(groupId[nextR][nextC]);
                visitedGroupId.add(groupId[nextR][nextC]);
              }
            }
          }

          answer[i][j] = buffer % 10;
        }
      }
    }

    for (int[] ans : answer) {
      for (int i : ans) {
        sb.append(i);
      }
      sb.append("\n");
    }

    System.out.print(sb);
  }

  static void bfs(int row, int col) {
    ArrayDeque<NodeJS> queue = new ArrayDeque<>();
    HashSet<NodeJS> visited = new HashSet<>();
    queue.add(new NodeJS(row, col));
    visited.add(new NodeJS(row, col));
    groupSize.add(1);

    while (!queue.isEmpty()) {
      NodeJS polled = queue.poll();
      groupId[polled.row][polled.col] = start;

      int r = polled.row;
      int c = polled.col;

      for (int[] direction : directions) {
        int nextR = r + direction[0];
        int nextC = c + direction[1];

        if (nextR >= 0 && nextR < map.length && nextC >= 0 && nextC < map[0].length) {
          if (!visited.contains(new NodeJS(nextR, nextC)) && map[nextR][nextC] == 0
              && groupId[nextR][nextC] == 0) {
            queue.add(new NodeJS(nextR, nextC));
            visited.add(new NodeJS(nextR, nextC));
          }
        }
      }

    }

    groupSize.set(start, visited.size());
    start++;
  }

  static class NodeJS {

    int row;
    int col;

    public NodeJS(int row, int col) {
      this.row = row;
      this.col = col;
    }

    @Override
    public boolean equals(Object o) {
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      NodeJS nodeJS = (NodeJS) o;
      return row == nodeJS.row && col == nodeJS.col;
    }

    @Override
    public int hashCode() {
      return Objects.hash(row, col);
    }
  }
}
