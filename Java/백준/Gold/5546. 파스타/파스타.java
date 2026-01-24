import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    final int DAYS = Integer.parseInt(st.nextToken());
    final int TARGET_COUNT = Integer.parseInt(st.nextToken());

    int[] fixed = new int[DAYS + 1];

    for (int i = 0; i < TARGET_COUNT; i++) {
      st = new StringTokenizer(br.readLine());

      int idx = Integer.parseInt(st.nextToken());
      int value = Integer.parseInt(st.nextToken());

      fixed[idx] = value;
    }

    int[][][] dp = new int[DAYS + 1][4][4];
    for (int one = 1; one < 4; one++) {
      for (int two = 1; two < 4; two++) {
        if (fixed[1] != 0 && fixed[1] != one) {
          continue;
        }

        if (fixed[2] != 0 && fixed[2] != two) {
          continue;
        }

        dp[2][two][one] = 1;
      }
    }

    for (int day = 3; day <= DAYS; day++) {
      for (int today = 1; today <= 3; today++) {
        if(fixed[day] != 0 && fixed[day] != today) {
          continue;
        }

        for (int one = 1; one <= 3; one++) {
          for (int two = 1; two <= 3; two++) {
            if(today == one && one == two) {
              continue;
            }

            dp[day][today][one] += dp[day - 1][one][two];
            dp[day][today][one] %= 10000;
          }
        }
      }
    }

    int answer = 0;
    for (int i = 1; i < 4; i++) {
      for (int j = 1; j < 4; j++) {
        answer += dp[DAYS][i][j];
        answer %= 10000;
      }
    }

    System.out.print(answer);
  }
}