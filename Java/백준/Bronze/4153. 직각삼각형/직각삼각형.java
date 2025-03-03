import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {


  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    while (true) {
      int[] arr = Arrays.stream(br.readLine().split(" "))
          .mapToInt(Integer::parseInt).toArray();

      if (arr[0] == 0) {
        break;
      }

      Arrays.sort(arr);

      if (Math.pow(arr[2], 2) == Math.pow(arr[1], 2) + Math.pow(arr[0], 2)) {
        sb.append("right").append("\n");
      } else {
        sb.append("wrong").append("\n");
      }

    }
    System.out.print(sb);
  }
}
