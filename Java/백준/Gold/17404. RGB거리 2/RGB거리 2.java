import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {


  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    // 2 <= HOUSE_COUNT <= 1_000
    final int HOUSE_COUNT = Integer.parseInt(br.readLine());
    int[][] arr = new int[HOUSE_COUNT][3];

    for (int i = 0; i < HOUSE_COUNT; i++) {
      arr[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }

    if (HOUSE_COUNT == 2) {
      int one = Math.min(arr[0][0] + arr[1][1], arr[0][0] + arr[1][2]);
      int two = Math.min(arr[0][1] + arr[1][2], arr[0][1] + arr[1][0]);
      int three = Math.min(arr[0][2] + arr[1][1], arr[0][2] + arr[1][0]);

      System.out.println(Math.min(one, Math.min(two, three)));
      return;
    }

    int answer = Integer.MAX_VALUE;

    for (int i = 0; i < 3; i++) {
      int iPlusOne = (i + 1) % 3;
      int iPlusTwo = (i + 2) % 3;
      int[][] dp = new int[HOUSE_COUNT][3];
      // 첫번째 값 고정
      dp[0][i] = arr[0][i];
      dp[0][iPlusOne] = -1;
      dp[0][iPlusTwo] = -1;
      // 두번째 값 고정
      dp[1][i] = -1;
      dp[1][iPlusOne] = dp[0][i] + arr[1][iPlusOne];
      dp[1][iPlusTwo] = dp[0][i] + arr[1][iPlusTwo];
      // 마지막 값 고정
      dp[HOUSE_COUNT - 1][i] = -1;

      for (int j = 2; j < HOUSE_COUNT - 1; j++) {
        if (dp[j][i] == 0) {
          if (dp[j - 1][iPlusOne] == -1) {
            dp[j][i] = arr[j][i] + dp[j - 1][iPlusTwo];
          } else if (dp[j - 1][iPlusTwo] == -1) {
            dp[j][i] = arr[j][i] + dp[j - 1][iPlusOne];
          } else {
            dp[j][i] = arr[j][i] + Math.min(dp[j - 1][iPlusOne], dp[j - 1][iPlusTwo]);
          }

        }

        if (dp[j][iPlusOne] == 0) {
          if (dp[j - 1][i] == -1) {
            dp[j][iPlusOne] = arr[j][iPlusOne] + dp[j - 1][iPlusTwo];
          } else if (dp[j - 1][iPlusTwo] == -1) {
            dp[j][iPlusOne] = arr[j][iPlusOne] + dp[j - 1][i];
          } else {
            dp[j][iPlusOne] = arr[j][iPlusOne] + Math.min(dp[j - 1][i], dp[j - 1][iPlusTwo]);
          }
        }

        if (dp[j][iPlusTwo] == 0) {
          if (dp[j - 1][iPlusOne] == -1) {
            dp[j][iPlusTwo] = arr[j][iPlusTwo] + dp[j - 1][i];
          } else if (dp[j - 1][i] == -1) {
            dp[j][iPlusTwo] = arr[j][iPlusTwo] + dp[j - 1][iPlusOne];
          } else {
            dp[j][iPlusTwo] = arr[j][iPlusTwo] + Math.min(dp[j - 1][i], dp[j - 1][iPlusOne]);
          }
        }
      }

      dp[HOUSE_COUNT - 1][iPlusOne] =
          arr[HOUSE_COUNT - 1][iPlusOne] + Math.min(
              dp[HOUSE_COUNT - 2][iPlusTwo] == -1 ? Integer.MAX_VALUE
                  : dp[HOUSE_COUNT - 2][iPlusTwo],
              dp[HOUSE_COUNT - 2][i] == -1 ? Integer.MAX_VALUE
                  : dp[HOUSE_COUNT - 2][i]);

      dp[HOUSE_COUNT - 1][iPlusTwo] =
          arr[HOUSE_COUNT - 1][iPlusTwo] + Math.min(
              dp[HOUSE_COUNT - 2][iPlusOne] == -1 ? Integer.MAX_VALUE
                  : dp[HOUSE_COUNT - 2][iPlusOne],
              dp[HOUSE_COUNT - 2][i] == -1 ? Integer.MAX_VALUE : dp[HOUSE_COUNT - 2][i]);

//      for (int[] d : dp) {
//        System.out.println(Arrays.toString(d));
//      }
//      System.out.println("====");
      answer = Math.min(
          Math.min(dp[HOUSE_COUNT - 1][iPlusOne], dp[HOUSE_COUNT - 1][iPlusTwo]), answer);
    }

    sb.append(answer);

    System.out.print(sb);
  }


}
