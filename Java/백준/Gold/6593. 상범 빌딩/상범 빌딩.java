import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st;

    int[][] offSet = {
        {0, 0, 1},
        {0, 0, -1},
        {0, 1, 0},
        {0, -1, 0},
        {1, 0, 0},
        {-1, 0, 0},
    };

    while (true) {
      st = new StringTokenizer(br.readLine());

      final int HEIGHT = Integer.parseInt(st.nextToken());
      final int ROW = Integer.parseInt(st.nextToken());
      final int COL = Integer.parseInt(st.nextToken());

      if (HEIGHT == 0 && ROW == 0 && COL == 0) {
        break;
      }

      char[][][] cube = new char[HEIGHT][ROW][COL];
      boolean[][][] visited = new boolean[HEIGHT][ROW][COL];
      ArrayDeque<NodeJS> queue = new ArrayDeque<>();

      NodeJS target = null;

      for (int i = 0; i < HEIGHT; i++) {
        for (int j = 0; j < ROW; j++) {
          String str = br.readLine();
          char[] chars = str.toCharArray();

          for (int k = 0; k < COL; k++) {
            cube[i][j][k] = chars[k];

            if (chars[k] == 'S') {
              queue.addLast(new NodeJS(i, j, k, 0));
              visited[i][j][k] = true;
            } else if (chars[k] == 'E') {
              target = new NodeJS(i, j, k, 0);
            }
          }
        }
        br.readLine();
      }

      boolean flag = true;
      while (!queue.isEmpty()) {
        NodeJS polled = queue.pollFirst();

        if (polled.equals(target)) {
          System.out.println("Escaped in " + polled.cost + " minute(s).");
          flag = false;
          break;
        }

        int height = polled.height;
        int row = polled.row;
        int col = polled.col;
        int cost = polled.cost;

        for (int[] ints : offSet) {
          int newHeight = height + ints[0];
          int newRow = row + ints[1];
          int newCol = col + ints[2];

          if (newRow < 0 || newRow >= ROW ||
              newCol < 0 || newCol >= COL ||
              newHeight < 0 || newHeight >= HEIGHT ||
              visited[newHeight][newRow][newCol] ||
              cube[newHeight][newRow][newCol] == '#') {
            continue;
          }

          visited[newHeight][newRow][newCol] = true;
          queue.add(new NodeJS(newHeight, newRow, newCol, cost + 1));
        }
      }

      if (flag) {
        System.out.println("Trapped!");
      }
    }
  }
}

class NodeJS {

  int height;
  int row;
  int col;
  int cost;

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    NodeJS nodeJS = (NodeJS) o;
    return height == nodeJS.height && row == nodeJS.row && col == nodeJS.col;
  }

  public NodeJS(int height, int row, int col, int cost) {
    this.height = height;
    this.row = row;
    this.col = col;
    this.cost = cost;
  }
}
