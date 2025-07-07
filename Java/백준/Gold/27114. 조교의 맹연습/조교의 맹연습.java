import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    final int LEFT = Integer.parseInt(st.nextToken());
    final int RIGHT = Integer.parseInt(st.nextToken());
    final int BACK = Integer.parseInt(st.nextToken());
    final int ENERGY = Integer.parseInt(st.nextToken());

    int[] energy = new int[3];
    energy[0] = LEFT;
    energy[1] = RIGHT;
    energy[2] = BACK;

    int[][] dp = new int[4][ENERGY + 1];
    for (int[] ints : dp) {
      Arrays.fill(ints, Integer.MAX_VALUE);
    }
    dp[0][0] = 0;

    for (int curEnergy = 0; curEnergy < ENERGY + 1; curEnergy++) {
      // 현재 방향
      for (int curDir = 0; curDir < 4; curDir++) {
        if (dp[curDir][curEnergy] == Integer.MAX_VALUE) {
          continue;
        }

        if (curEnergy + LEFT <= ENERGY) {
          int nextDir = (curDir + 3) % 4;
          dp[nextDir][curEnergy + LEFT] = Math.min(dp[nextDir][curEnergy + LEFT],
              dp[curDir][curEnergy] + 1);
        }

        if (curEnergy + RIGHT <= ENERGY) {
          int nextDir = (curDir + 1) % 4;
          dp[nextDir][curEnergy + RIGHT] = Math.min(dp[nextDir][curEnergy + RIGHT],
              dp[curDir][curEnergy] + 1);
        }

        if (curEnergy + BACK <= ENERGY) {
          int nextDir = (curDir + 2) % 4;
          dp[nextDir][curEnergy + BACK] = Math.min(dp[nextDir][curEnergy + BACK],
              dp[curDir][curEnergy] + 1);
        }

      }

    }
    System.out.print(dp[0][ENERGY] == Integer.MAX_VALUE ? -1 : dp[0][ENERGY]);

  }
}

