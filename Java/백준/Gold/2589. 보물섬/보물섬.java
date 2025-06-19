import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

  static int[][] nodes;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int i1 = Integer.parseInt(st.nextToken());
    int i2 = Integer.parseInt(st.nextToken());
    char[][] chars = new char[i1][i2];

    int answer = 0;

    for (int i = 0; i < chars.length; i++) {
      chars[i] = br.readLine().toCharArray();
    }

    int[][] offset = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    for (int i = 0; i < i1; i++) {
      for (int j = 0; j < i2; j++) {
        if (chars[i][j] == 'L') {
          boolean[][] visited = new boolean[i1][i2];
          Queue<NodeJS> queue = new ArrayDeque<>();

          queue.add(new NodeJS(0, j, i));
          visited[i][j] = true;

          while (!queue.isEmpty()) {
            NodeJS node = queue.poll();

            int curCol = node.col;
            int curRow = node.row;
            int curCount = node.count;
            answer = Math.max(answer, curCount);

            for (int[] set : offset) {
              int nextCol = curCol + set[0];
              int nextRow = curRow + set[1];

              if (nextRow < 0 || nextRow >= i1 || nextCol < 0 || nextCol >= i2) {
                continue;
              }

              if (visited[nextRow][nextCol] || chars[nextRow][nextCol] != 'L') {
                continue;
              }

              visited[nextRow][nextCol] = true;
              queue.add(new NodeJS(curCount + 1, nextCol, nextRow));
            }
          }
        }
      }
    }

    System.out.print(answer);
  }

  static class NodeJS {

    int count;
    int col;
    int row;

    public NodeJS(int count, int col, int row) {
      this.count = count;
      this.col = col;
      this.row = row;
    }
  }


}