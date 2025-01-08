import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

    Arrays.sort(input);

    if (input[2] >= input[0] + input[1]) {
      sb.append(input[0] + input[1] + (input[0] + input[1] - 1));
    } else {
      sb.append(input[0] + input[1] + input[2]);
    }

    System.out.print(sb);
  }
}