import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {


  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    int[] input = br.lines().mapToInt(Integer::parseInt).toArray();

    Arrays.sort(input);

    int sum = 0;

    for (int row : input) {
      sum += row;
    }

    sb.append(sum / 5).append("\n").append(input[2]);

    System.out.print(sb);
  }
}