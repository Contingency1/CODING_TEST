import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;

public class Main {

  static int min = Integer.MAX_VALUE;

  private static void bfs(int start, int end) {
    ArrayDeque<int[]> queue = new ArrayDeque<>();
    boolean[] visited = new boolean[100_001];

    queue.add(new int[]{start, 0});
    visited[start] = true;

    while (!queue.isEmpty()) {
      int[] element = queue.poll();
      int node = element[0];
      int cost = element[1];

      int[] cases = new int[]{node - 1, node + 1, node * 2};

      // 더하거나 곱한게 목표로 한것보다 작거나 같아야만 함??? 맞나??
      for (int i = 0; i < 3; i++) {
        if (cases[i] < 0 || cases[i] > 100_000) {
          continue;
        }

        if (cases[i] == end) {
          min = Math.min(min, cost + 1);
          return;
        }

        if (!visited[cases[i]]) {
          visited[cases[i]] = true;
          queue.add(new int[]{cases[i], cost + 1});
          if (node > end) {
            break;
          }
        }
      }
    }

  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    int[] input = Arrays.stream(br.readLine().split(" "))
        .mapToInt(Integer::parseInt).toArray();

    int start = input[0];
    int end = input[1];

    if (start == end) {
      System.out.println(0);
      return;
    }
    bfs(start, end);

    sb.append(min);

    System.out.print(sb);
  }
}