import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;

public class Main {


  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    int cases = Integer.parseInt(br.readLine());

    int[][] move = {{2, 1}, {1, 2}, {-2, 1}, {-1, 2}, {-2, -1}, {-1, -2}, {1, -2}, {2, -1}};

    for (int i = 0; i < cases; i++) {
      int l = Integer.parseInt(br.readLine());

      boolean[][] visited = new boolean[l][l];

      int[] start = Arrays.stream(br.readLine().split(" "))
          .mapToInt(Integer::parseInt).toArray();

      int[] target = Arrays.stream(br.readLine().split(" "))
          .mapToInt(Integer::parseInt).toArray();

      ArrayDeque<int[]> queue = new ArrayDeque<>();

      queue.add(new int[]{start[0], start[1], 0});
      visited[start[0]][start[1]] = true;

      if (start[0] == target[0] && start[1] == target[1]) {
        sb.append("0").append('\n');
        continue;
      }

      while (!queue.isEmpty()) {
        int[] cur = queue.poll();

        int x = cur[0];
        int y = cur[1];
        int count = cur[2];

        for (int j = 0; j < move.length; j++) {
          int newX = x + move[j][0];
          int newY = y + move[j][1];

          if (newX >= 0 && newY >= 0 && newX < l && newY < l && !visited[newX][newY]) {
            queue.add(new int[]{newX, newY, count + 1});
            visited[newX][newY] = true;

            if (newX == target[0] && newY == target[1]) {
              sb.append(count + 1).append("\n");
              break;
            }
          }
        }
      }
    }

    System.out.print(sb);
  }
}