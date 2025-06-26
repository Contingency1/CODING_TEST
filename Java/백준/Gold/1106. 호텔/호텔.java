import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    final int TARGET = Integer.parseInt(st.nextToken());
    final int CITY_COUNT = Integer.parseInt(st.nextToken());

    Map<Integer, Integer> map = new TreeMap<>();
    for (int i = 0; i < CITY_COUNT; i++) {
      st = new StringTokenizer(br.readLine());

      // 앞에 있는게 비용
      int cost = Integer.parseInt(st.nextToken());
      // 뒤에 있는게 인원
      int pop = Integer.parseInt(st.nextToken());

      if (map.containsKey(pop)) {
        if (map.get(pop) > cost) {
          map.put(pop, cost);
        }
        continue;
      }

      map.put(pop, cost);
    }

    int count = map.size() + 1;
    int[] pops = new int[count];
    int[] costs = new int[count];

    int x = 1;
    for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
      pops[x] = entry.getKey();
      costs[x] = entry.getValue();
      x++;
    }

    int[][] dp = new int[pops.length][TARGET + 1];

    for (int i = 1; i < dp[0].length; i++) {
      dp[0][i] = Integer.MAX_VALUE;
    }

    for (int i = 1; i < dp.length; i++) {
      for (int j = 1; j < dp[0].length; j++) {
        int multiplex = j / pops[i];

        if (multiplex * pops[i] < j) {
          multiplex++;
          dp[i][j] = Math.min(dp[i - 1][j], multiplex * costs[i]);
        } else {
          dp[i][j] = dp[i - 1][j];
        }

        if (j - pops[i] >= 0 && dp[i][j - pops[i]] != Integer.MAX_VALUE) {
          dp[i][j] = Math.min(dp[i][j], dp[i][j - pops[i]] + costs[i]);
        }
      }
    }

/*    for (int[] ints : dp) {
      System.out.println("ints = " + Arrays.toString(ints));
    }*/
    int answer = Integer.MAX_VALUE;
    for (int i = 0; i < dp.length; i++) {
      answer = Math.min(answer, dp[i][TARGET]);
    }

    System.out.print(answer);


  }


}