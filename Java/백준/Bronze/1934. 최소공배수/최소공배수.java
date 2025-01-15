import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

  private static int calculation(int a, int b) {

    while (true) {
      int r = a % b;

      if (r == 0) {
        return b;
      } else {
        a = b;
        b = r;
      }
    }
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    int N = Integer.parseInt(br.readLine());
    int[][] arr = new int[N][2];
    for (int i = 0; i < N; i++) {
      arr[i] = Arrays.stream(br.readLine().split(" "))
          .mapToInt(Integer::parseInt).toArray();
    }

    for (int[] ints : arr) {
      sb.append(ints[0] * ints[1] / calculation(ints[0], ints[1])).append("\n");
    }

    System.out.print(sb);
  }
}