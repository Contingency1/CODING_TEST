import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    String[] str = br.readLine().split("-");

    if (str.length == 1) {
      int[] arr = Arrays.stream(str[0].split("\\+"))
          .mapToInt(Integer::parseInt).toArray();

      sb.append(Arrays.stream(arr).sum());

    } else {
      int subSum = 0;
      int minus = 0;

      int[] rows = Arrays.stream(str[0].split("\\+"))
          .mapToInt(Integer::parseInt).toArray();

      for (int row : rows) {
        subSum += row;
      }

      for (int i = 1; i < str.length; i++) {
        int[] sumArr = Arrays.stream(str[i].split("\\+")).mapToInt(Integer::parseInt).toArray();
        minus += Arrays.stream(sumArr).sum();
      }

      subSum -= minus;

      sb.append(subSum);
    }

    System.out.print(sb);
  }
}
