import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();
    int[] input = Arrays.stream(br.readLine().split(" "))
        .mapToInt(Integer::parseInt).toArray();

    int[] number = Arrays.stream(br.readLine().split(" "))
        .mapToInt(Integer::parseInt).toArray();

    int MAX = 0;

    for (int i = 0; i <= input[0] - 3; i++) {
      for (int j = i + 1; j <= input[0] - 2; j++) {
        for (int k = j + 1; k <= input[0] - 1; k++) {
          int sum = number[i] + number[j] + number[k];
          if (sum == input[1]) {
            sb.append(input[1]);
            System.out.print(sb);
            return;
          }
          if (sum > MAX && sum < input[1]) {
            MAX = sum;
          }
        }
      }
    }

    sb.append(MAX);

    System.out.print(sb);
  }
}