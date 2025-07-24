import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    final int N = Integer.parseInt(br.readLine());

    StringTokenizer st = new StringTokenizer(br.readLine());
    int x = Integer.parseInt(st.nextToken());
    int y = Integer.parseInt(st.nextToken());

    int toX = Integer.parseInt(st.nextToken());
    int toY = Integer.parseInt(st.nextToken());

//    (r-2, c-1), (r-2, c+1), (r, c-2), (r, c+2), (r+2, c-1), (r+2, c+1)
    int[][] offset = {{-2, -1}, {-2, 1}, {0, -2}, {0, 2}, {2, -1}, {2, 1}};

    ArrayDeque<NodeJS> queue = new ArrayDeque<>();

    queue.add(new NodeJS(x, y, 0));

    boolean[][] visited = new boolean[N][N];

    while (!queue.isEmpty()) {

      NodeJS poll = queue.poll();
      int curX = poll.x;
      int curY = poll.y;

      if (curX == toX && curY == toY) {
        System.out.print(poll.count);
        return;
      }

      for (int[] ints : offset) {
        int plusX = curX + ints[0];
        int plusY = curY + ints[1];

        if (plusX >= 0 && plusX < N && plusY >= 0 && plusY < N) {
          if (visited[plusX][plusY]) {
            continue;
          }

          queue.add(new NodeJS(plusX, plusY, poll.count + 1));
          visited[plusX][plusY] = true;
        }
      }


    }
    System.out.print(-1);


  }

  static class NodeJS {

    int x;
    int y;
    int count;

    public NodeJS(int x, int y, int count) {
      this.x = x;
      this.y = y;
      this.count = count;
    }
  }


}