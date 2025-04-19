import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class Main {

  static int N;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    N = Integer.parseInt(br.readLine());

    String[][] str = new String[N][N];

    for (int i = 0; i < N; i++) {
      str[i] = br.readLine().split("");
    }

    sb.append(bfs1(str)).append(" ").append(blindBFS(str));

    System.out.print(sb);
  }

  static int blindBFS(String[][] str) {
    boolean[][] booleans = new boolean[N][N];
    String buffer = "";
    int answer = 0;

    int[][] offset = new int[][]{{1, 0}, {0, 1}, {0, -1}, {-1, 0}};

    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        if (!booleans[i][j]) {
          answer++;
          buffer = str[i][j];

          ArrayDeque<int[]> deque = new ArrayDeque<>();
          deque.add(new int[]{i, j});
          booleans[i][j] = true;

          while (!deque.isEmpty()) {
            int[] arr = deque.poll();
            int x = arr[0], y = arr[1];

            for (int[] set : offset) {
              int plusX = x + set[0], plusY = y + set[1];
              if (plusX >= 0 && plusX < N && plusY >= 0 && plusY < N
                  && !booleans[plusX][plusY]) {
                if (str[plusX][plusY].equals(buffer)
                    || (buffer.equals("R") && str[plusX][plusY].equals("G"))
                    || (buffer.equals("G") && str[plusX][plusY].equals("R"))) {
                  deque.add(new int[]{plusX, plusY});
                  booleans[plusX][plusY] = true;
                }
              }
            }
          }
        }
      }
    }

    return answer;
  }

  static int bfs1(String[][] str) {
    boolean[][] booleans = new boolean[N][N];
    String buffer = "";
    int answer = 0;

    int[][] offset = new int[][]{{1, 0}, {0, 1}, {0, -1}, {-1, 0}};

    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        if (!booleans[i][j]) {
          answer++;
          buffer = str[i][j];

          ArrayDeque<int[]> deque = new ArrayDeque<>();
          deque.add(new int[]{i, j});
          booleans[i][j] = true;

          while (!deque.isEmpty()) {
            int[] arr = deque.poll();
            int x = arr[0], y = arr[1];

            for (int[] set : offset) {
              int plusX = x + set[0], plusY = y + set[1];
              if (plusX >= 0 && plusX < N && plusY >= 0 && plusY < N
                  && !booleans[plusX][plusY]) {
                if (str[plusX][plusY].equals(buffer)) {
                  deque.add(new int[]{plusX, plusY});
                  booleans[plusX][plusY] = true;
                }
              }
            }
          }
        }
      }

    }

    return answer;
  }

}
