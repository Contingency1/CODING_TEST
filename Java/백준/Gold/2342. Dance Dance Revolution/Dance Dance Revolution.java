import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    StringTokenizer st = new StringTokenizer(br.readLine());
    br.close();

    List<Integer> list = new ArrayList<>();
    while (st.hasMoreTokens()) {
      list.add(Integer.parseInt(st.nextToken()));
    }
    int listSize = list.size();

    int[][][] dp = new int[listSize][5][5];

    for (int i = 0; i < listSize; i++) {
      for (int j = 0; j < 5; j++) {
        Arrays.fill(dp[i][j], Integer.MAX_VALUE);
      }
    }

    dp[0][list.get(0)][0] = 2;
    dp[0][0][list.get(0)] = 2;

    for (int i = 1; i < listSize; i++) {
      int prev = list.get(i - 1);
      int cur = list.get(i);

      if (cur == 0) {
        break;
      }

      // 이전과 밟아야 할 값이 같다면
      if (prev == cur) {
        for (int left = 0; left < 5; left++) {
          for (int right = 0; right < 5; right++) {
            if (dp[i - 1][left][right] == Integer.MAX_VALUE) {
              continue;
            }
            dp[i][left][right] = Math.min(dp[i][left][right], dp[i - 1][left][right] + 1);
          }
        }
        continue;
      }

      for (int left = 0; left < 5; left++) {
        for (int right = 0; right < 5; right++) {
          if (dp[i - 1][left][right] == Integer.MAX_VALUE) {
            continue;
          }
          boolean leftNotMove = true;
          boolean rightNotMove = true;


          // 왼발 움직이는데 0에서 시작할 때
          if (left == 0) {
            int newValue = dp[i - 1][left][right] + 2;
            dp[i][cur][right] = Math.min(dp[i][cur][right], newValue);
            leftNotMove = false;
          } else if (right == 0) {
            int newValue = dp[i - 1][left][right] + 2;
            dp[i][left][cur] = Math.min(dp[i][left][cur], newValue);
            rightNotMove = false;
          }

          // 왼발이 움직임
          if (leftNotMove && cur != right) {
            int newValue = dp[i - 1][left][right];
            if (Math.abs(left - cur) % 2 == 0) {
              if (left == cur) {
                newValue += 1;
              } else {
                newValue += 4;
              }

            } else {
              if (left == cur) {
                newValue += 1;
              } else {
                newValue += 3;
              }
            }
            dp[i][cur][right] = Math.min(dp[i][cur][right], newValue);
          }

          // 오른발이 움직임
          if (rightNotMove && cur != left) {
            int newValue = dp[i - 1][left][right];
            if (Math.abs(right - cur) % 2 == 0) {
              if (right == cur) {
                newValue += 1;
              } else {
                newValue += 4;
              }

            } else {
              if (right == cur) {
                newValue += 1;
              } else {
                newValue += 3;
              }

            }
            dp[i][left][cur] = Math.min(dp[i][left][cur], newValue);
          }

        }
      }
    }


    int answer = Integer.MAX_VALUE;

    for (int i = 0; i < 5; i++) {
      for (int j = 0; j < 5; j++) {
        answer = Math.min(answer, dp[listSize - 2][i][j]);
      }
    }

    sb.append(answer);

    System.out.print(sb);
  }
}
