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

    int[] deDP = new int[N];
    int[] inDP = new int[N];

    deDP[N - 1] = 1;

    for (int i = N - 2; i >= 0; i--) {
      for (int j = i + 1; j < N; j++) {
        if (input[i] > input[j]) {
          deDP[i] = Math.max(deDP[i], deDP[j] + 1);
          continue;
        }

        if (j == N - 1 && deDP[i] == 0) {
          deDP[i] = 1;
        }
      }
    }

//    for (int row : inDP) {
//      sb.append(row).append("\n");
//    }

    inDP[0] = 1;

    for (int i = 1; i < N; i++) {
      for (int j = 0; j < i; j++) {
        if (input[i] > input[j]) {
          inDP[i] = Math.max(inDP[i], inDP[j] + 1);
          continue;
        }

        if (j == 0 && inDP[i] == 0) {
          inDP[i] = 1;
        }
      }
    }

//    for (int row : deDP) {
//      sb.append(row).append("\n");
//    }
//    sb.append(Arrays.toString(deDP)).append('\n');
//
//    sb.append(Arrays.toString(inDP)).append('\n');
    int answer = 0;

    for (int i = 0; i < N; i++) {
      if (inDP[i] > 1 || deDP[i] > 1) {
        answer = Math.max(answer, inDP[i] + deDP[i]);
      }
    }

    sb.append(answer == 0 ? 1 : answer - 1);
    System.out.print(sb);
  }
}
