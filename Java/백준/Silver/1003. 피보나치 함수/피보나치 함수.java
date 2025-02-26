import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

  private static void fibo(int n, int[][] arr) {

    if (arr[n][0] == 0 && arr[n][1] == 0) {
      arr[n][0] = arr[n - 1][0] + arr[n - 2][0];
      arr[n][1] = arr[n - 1][1] + arr[n - 2][1];
    }

  }


  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    int T = Integer.parseInt(br.readLine());
    int[][] arr = new int[41][2];

    arr[0][0] = 1;
    arr[1][1] = 1;

    for (int i = 2; i <= 40; i++) {
      fibo(i, arr);
    }

    for (int i = 1; i <= T; i++) {
      int k = Integer.parseInt(br.readLine());
      sb.append(arr[k][0]).append(" ").append(arr[k][1]).append("\n");
    }

    System.out.print(sb);
  }
}
