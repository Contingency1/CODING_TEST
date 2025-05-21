import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

  static int[] base;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    final int N = Integer.parseInt(br.readLine());

    base = new int[N + 1];

    StringTokenizer st = new StringTokenizer(br.readLine());

    final int M = Integer.parseInt(br.readLine());

    for (int i = 1; i <= N; i++) {
      base[i] = Integer.parseInt(st.nextToken());
    }

    int[][] questions = new int[M][2];

    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());

      questions[i][0] = Integer.parseInt(st.nextToken());
      questions[i][1] = Integer.parseInt(st.nextToken());
    }
    br.close();

    // 1 2 1 3 1 2 1
    boolean[][] dp = new boolean[N + 1][N + 1];

    // 1개씩
    for (int i = 1; i < N + 1; i++) {
      dp[i][i] = true;
    }

    // 2개씩
    for (int start = 1; start < N; start++) {
      if (base[start] == base[start + 1]) {
        dp[start][start + 1] = true;
      }
    }

    // 3개씩 ~ N개씩
    for (int plus = 2; plus < N; plus++) {
      for (int start = 1; start <= N - plus; start++) {
        int end = start + plus;
        if (base[start] == base[end]) {
          if (dp[start + 1][end - 1]) {
            dp[start][end] = true;
          }
        }
      }
    }
    for (int[] question : questions) {
      sb.append(dp[question[0]][question[1]] ? "1" : "0").append("\n");
    }
    sb.deleteCharAt(sb.length() - 1);
    System.out.print(sb);
  }
}
