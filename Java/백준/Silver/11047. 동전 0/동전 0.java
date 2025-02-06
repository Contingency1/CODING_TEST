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

    int[] arr = new int[input[0]];
    for (int i = 0; i < input[0]; i++) {
      arr[i] = Integer.parseInt(br.readLine());
    }

    int answer = 0;
    for (int i = input[0] - 1; i >= 0; i--) {
      if (input[1] / arr[i] > 0) {
        answer += input[1] / arr[i];
        input[1] = input[1] % arr[i];
      }

    }

    sb.append(answer);
    System.out.print(sb);
  }
}
