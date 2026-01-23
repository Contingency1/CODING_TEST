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
    StringTokenizer st = new StringTokenizer(br.readLine());

    final int CASE_COUNT = Integer.parseInt(st.nextToken());
    final int RELATION_COUNT = Integer.parseInt(st.nextToken());

    Map<Integer, List<Integer>> map = new HashMap<>();

    for (int j = 1; j <= CASE_COUNT; j++) {
      map.put(j, new ArrayList<>());
    }
    
    for (int j = 0; j < RELATION_COUNT; j++) {
      st = new StringTokenizer(br.readLine());
    
      int first = Integer.parseInt(st.nextToken());
      int last = Integer.parseInt(st.nextToken()); 
    
      map.get(first).add(last);
    }

    int count = Integer.parseInt(br.readLine());

    int[][] want = new int[count][2];

    for (int i = 0; i < count; i++) {
      st = new StringTokenizer(br.readLine());

      want[i][0] = Integer.parseInt(st.nextToken());
      want[i][1] = Integer.parseInt(st.nextToken());
    }

    int[][] dp = new int[CASE_COUNT + 1][CASE_COUNT + 1];

    for (int i = 1; i <= CASE_COUNT; i++) {
      Arrays.fill(dp[i], Integer.MAX_VALUE);
    }

    for (int i = 1; i <= CASE_COUNT; i++) {
      dp[i][i] = 2;
    }

    for (int i = 1; i <= CASE_COUNT; i++) {
      List<Integer> cur = map.get(i);
      
      for (int c: cur) {
        dp[i][c] = -1;
        dp[c][i] = 1;
      }
    }

    for (int key = 1; key <= CASE_COUNT; key++) {
      for (int row = 1; row <= CASE_COUNT; row++) {
        if(row == key) {
          continue;
        }

        for (int col = 1; col <= CASE_COUNT; col++) {
          if(col == key || col == row) {
            continue;
          }

          int one = dp[row][key];
          int two = dp[key][col];

          if(one == 1 && one == two) {
            dp[row][col] = 1;
          } else if (one == -1 && one == two) {
            dp[row][col] = -1;
          }
        }
      }
    }

    for (int[] w: want) {
      int start = w[0];
      int target = w[1];

      System.out.println(dp[start][target] == Integer.MAX_VALUE ? 0 : dp[start][target]);
    }

  }
}