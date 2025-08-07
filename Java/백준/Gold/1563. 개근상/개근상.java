import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {

  static int N;
  static int[][][] attendance;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    N = Integer.parseInt(br.readLine());

    attendance = new int[N + 1][2][3];

    for (int i = 0; i < N; i++) {
      for (int j = 0; j < 2; j++) {
        Arrays.fill(attendance[i][j], -1);
      }
    }

    int answer = dfs(0, 0, 0);

    System.out.print(answer);
  }

  static int dfs(int day, int late, int absence) {

    if (late == 2 || absence == 3) {
      return 0;
    }

    if (day == N) {
      return 1;
    }

    if (attendance[day][late][absence] == -1) {
      int result = dfs(day + 1, late, 0) +
          dfs(day + 1, late + 1, 0) +
          dfs(day + 1, late, absence + 1);
      result %= 1_000_000;
      
      attendance[day][late][absence] = result;
      return result;
    }

    return attendance[day][late][absence];
  }

}