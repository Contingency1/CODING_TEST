import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

  static int MIN = Integer.MAX_VALUE;

  private static void dp(int X, int count, ArrayList<Integer> arr) {
//    System.out.println(X + " " + count);
//
//    System.out.println("--------");

    if (MIN < count) {
      return;
    }

    if (X == 1) {
      MIN = Math.min(MIN, count);
      return;
    }

    if (X % 2 == 0) {
      dp(X / 2, count + 1, arr);
    }

    if (X % 3 == 0) {
      dp(X / 3, count + 1, arr);
    }

    if (X - 1 >= 1) {
      dp(X - 1, count + 1, arr);
    }

  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    int X = Integer.parseInt(br.readLine());
    int count = 0;

    ArrayList<Integer> arr = new ArrayList<>();

    dp(X, count, arr);
    sb.append(MIN);

    System.out.print(sb);
  }
}
