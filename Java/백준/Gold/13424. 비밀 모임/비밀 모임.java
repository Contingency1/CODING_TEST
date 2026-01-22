import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Arrays;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    final int TEST_COUNT = Integer.parseInt(br.readLine());

    for (int i = 0; i < TEST_COUNT; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());

      int roomCount = Integer.parseInt(st.nextToken());
      int pathCount = Integer.parseInt(st.nextToken());
      
      Map<Integer, List<Node>> map = new HashMap<>();

      for (int j = 1; j <= roomCount; j++) {
        map.put(j, new ArrayList<>());
      }

      for (int j = 0; j < pathCount; j++) {
        st = new StringTokenizer(br.readLine());

        int room1 = Integer.parseInt(st.nextToken());
        int room2 = Integer.parseInt(st.nextToken());
        int cost = Integer.parseInt(st.nextToken());

        map.get(room1).add(new Node(room2, cost));
        map.get(room2).add(new Node(room1, cost));
      }

      int friendCount = Integer.parseInt(br.readLine());

      List<Integer> friend = new ArrayList<>();

      st = new StringTokenizer(br.readLine());

      for (int j = 0; j < friendCount; j++) {
        friend.add(Integer.parseInt(st.nextToken()));
      }

      int[][] dp = new int[roomCount + 1][roomCount + 1];

      for (int j = 1; j <= roomCount; j++) {
        Arrays.fill(dp[j], 1000000);
      }

      for (int j = 1; j <= roomCount; j++) {
        dp[j][j] = 0;
      }

      for (int j = 1; j <= roomCount; j++) {
        for (Node n: map.get(j)) {
          int target = n.target;
          int cost = n.cost;

          dp[j][target] = cost;
          dp[target][j] = cost;
        }
      }

      
      for (int key = 1; key <= roomCount; key++) {
        for (int row = 1; row <= roomCount; row++) {
          for (int col = 1; col <= roomCount; col++) {
            dp[row][col] = Math.min(dp[row][col], dp[row][key] + dp[key][col]);
          }
        }
      }

      int answer = 0;
      int min = Integer.MAX_VALUE;

      for (int idx = 1; idx <= roomCount; idx++) {
        int buffer = 0;

        for (int f: friend) {
          buffer += dp[f][idx];
        }

        if(min <= buffer) {
          continue;
        }

        min = buffer;
        answer = idx;
      }

      System.out.println(answer);
    }

  }

  static class Node{
    int target;
    int cost;

    Node(int target, int cost) {
      this.target = target;
      this.cost = cost;
    }
  }

}