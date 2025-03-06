import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {


  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    int[] question = Arrays.stream(br.readLine().split(" "))
        .mapToInt(Integer::parseInt).toArray();

    // 물품의 수
    int N = question[0];
    // 버틸 수 있는 무게
    int K = question[1];

    int[][] input = new int[N][2];

    for (int i = 0; i < N; i++) {
      input[i] = Arrays.stream(br.readLine().split(" "))
          .mapToInt(Integer::parseInt).toArray();
    }

    Arrays.sort(input, (a, b) -> {
      if (a[0] == b[0]) {
        return a[1] - b[1]; // 1번째 인덱스로 정렬
      }
      return a[0] - b[0]; // 0번째 인덱스로 정렬
    });

    if (input.length == 1) {
      if (K < input[0][0]) {
        sb.append(0);
      } else {
        sb.append(input[0][1]);
      }
      System.out.print(sb);
      return;
    }

    int[] dp = new int[K + 1];

    dp[0] = 0;

//    int buffer = input[0][0];
//
//    for (int i = 1; i < input.length; i++) {
//      if (input[i][0] != buffer) {
//        dp[input[i - 1][0]] = input[i - 1][1];
//        break;
//      }
//    }

    for (int i = N - 1; i >= 0; i--) {
      for (int j = K; j >= input[i][0]; j--) {
        dp[j] = Math.max(dp[j], dp[j - input[i][0]] + input[i][1]);
      }
    }

    int answer = 0;
    for (int row : dp) {
      answer = Math.max(answer, row);
    }

    sb.append(answer);
    System.out.print(sb);
  }
}
