import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    int[] input = br.lines().mapToInt(Integer::parseInt).toArray();

    if (Arrays.stream(input).reduce(0, (x, y) -> x + y) != 180) {
      sb.append("Error");
    } else if (input[0] == 60 && input[1] == 60 && input[2] == 60) {
      sb.append("Equilateral");
    } else if (input[0] == input[1] || input[1] == input[2] || input[2] == input[0]) {
      sb.append("Isosceles");
    } else {
      sb.append("Scalene");
    }

    System.out.print(sb);
  }
}