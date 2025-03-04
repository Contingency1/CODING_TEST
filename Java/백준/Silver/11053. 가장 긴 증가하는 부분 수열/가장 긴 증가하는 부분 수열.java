import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {


  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    int N = Integer.parseInt(br.readLine());
    int[] input = Arrays.stream(br.readLine().split(" "))
        .mapToInt(Integer::parseInt).toArray();

    int[] dp = new int[N];

    dp[N - 1] = 1;
    //첫 포인터, 작아지면서 비교
    for (int i = N - 2; i >= 0; i--) {
      //둘째 포인터, 커지면서 비교
      for (int j = i + 1; j < N; j++) {
        if (input[i] < input[j]) {
          dp[i] = Math.max(dp[i], dp[j] + 1);
        }
      }

      if (dp[i] == 0) {
        dp[i] = 1;
      }

    }

    int answer = 0;

    for (int i = 0; i < dp.length; i++) {
      answer = Math.max(answer, dp[i]);
    }

    sb.append(answer);
    System.out.print(sb);
  }
}
