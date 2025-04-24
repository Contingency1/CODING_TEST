import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

    int start = input[0];
    int end = input[1];

    ArrayDeque<int[]> queue = new ArrayDeque<>();
    int[] visited = new int[100_001];

    Arrays.fill(visited, -1);

    queue.add(new int[]{start, 0});
    visited[start] = 0;

    int fastest = Integer.MAX_VALUE;
    int answerCount = 0;
    boolean isFirst = true;

    while (!queue.isEmpty()) {
      int[] cur = queue.poll();
      int location = cur[0];
      int count = cur[1];

      if (fastest < count) {
        continue;
      }

      if (location == end) {
        if (isFirst) {
          fastest = count;
          isFirst = false;
          answerCount++;
          continue;
        }

        if (fastest == count) {
          answerCount++;
        }
      }

      int locationPlus = location + 1;
      int locationMinus = location - 1;
      int locationMultiply = location * 2;

      if (locationPlus <= 100_000) {
        if (visited[locationPlus] == -1 || visited[locationPlus] >= count + 1) {
          queue.add(new int[]{locationPlus, count + 1});
          visited[locationPlus] = count + 1;
        }
      }

      if (locationMinus >= 0) {
        if (visited[locationMinus] == -1 || visited[locationMinus] >= count + 1) {
          queue.add(new int[]{locationMinus, count + 1});
          visited[locationMinus] = count + 1;
        }
      }

      if (locationMultiply > 0 && locationMultiply <= 100_000) {
        if (visited[locationMultiply] == -1 || visited[locationMultiply] >= count + 1) {
          queue.add(new int[]{locationMultiply, count + 1});
          visited[locationMultiply] = count + 1;
        }
      }
    }

    sb.append(fastest).append("\n").append(answerCount);

    System.out.print(sb);
  }

}
