import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {


  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    String[] input1 = br.readLine().split(" ");

    // 1 <= CHILD_COUNT <= 30_000
    final int CHILD_COUNT = Integer.parseInt(input1[0]);
    // 1 <= CHILD_RELATIONS <= 100_000
    final int CHILD_RELATIONS = Integer.parseInt(input1[1]);
    // 1 <= MINIMUM <= MIN(CHILD_COUNT, 3_000)
    final int MINIMUM = Integer.parseInt(input1[2]);

    int[] childCandy = new int[CHILD_COUNT + 1];

    String[] input2 = br.readLine().split(" ");

    for (int i = 1; i <= CHILD_COUNT; i++) {
      childCandy[i] = Integer.parseInt(input2[i - 1]);
    }

//    for (int c : childCandy) {
//      sb.append(c).append(" ");
//    }

    List<List<Integer>> relations = new ArrayList<>();

    for (int i = 0; i < CHILD_COUNT + 1; i++) {
      relations.add(new ArrayList<>());
    }

    for (int i = 0; i < CHILD_RELATIONS; i++) {
      int[] buf = Arrays.stream(br.readLine().split(" "))
          .mapToInt(Integer::parseInt).toArray();

      relations.get(buf[0]).add(buf[1]);
      relations.get(buf[1]).add(buf[0]);
    }

//    for (List<Integer> relation : relations) {
//      for (Integer child : relation) {
//        sb.append(child + " ");
//      }
//      sb.append("\n");
//    }

    boolean[] visited = new boolean[CHILD_COUNT + 1];
    List<int[]> groups = new ArrayList<>();

    for (int i = 1; i <= CHILD_COUNT; i++) {
      if (visited[i]) {
        continue;
      }

      visited[i] = true;

      int childBuffer = 1;
      int candyBuffer = childCandy[i];

      ArrayDeque<Integer> queue = new ArrayDeque<>();

      queue.add(i);

      while (!queue.isEmpty()) {
        int cur = queue.poll();

        for (int relation : relations.get(cur)) {
          if (!visited[relation]) {
            visited[relation] = true;
            childBuffer++;
            candyBuffer += childCandy[relation];
            queue.add(relation);
          }
        }
      }

      groups.add(new int[]{childBuffer, candyBuffer});
    }

//    for (int[] d : groups) {
//      System.out.println(d[0] + " " + d[1]);
//    }

    int[][] dp = new int[groups.size() + 1][MINIMUM];

    for (int i = 0; i < groups.size(); i++) {
      int[] g = groups.get(i);
      int childCount = g[0];
      int candyCount = g[1];

      for (int j = 0; j < MINIMUM; j++) {
        if (j >= childCount) {
          dp[i + 1][j] = Math.max(dp[i][j], dp[i][j - childCount] + candyCount);
        } else {
          dp[i + 1][j] = dp[i][j];
        }
      }
    }

    sb.append(dp[groups.size()][MINIMUM - 1]);

    System.out.print(sb);
  }


}
