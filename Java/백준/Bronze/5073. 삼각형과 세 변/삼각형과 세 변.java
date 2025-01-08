import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    while (true) {
      int[] input = Arrays.stream(br.readLine().split(" "))
          .mapToInt(Integer::parseInt)
          .toArray();

      Arrays.sort(input);

      if (input[0] == 0 && input[1] == 0 && input[2] == 0) {
        sb.delete(sb.length() - 1, sb.length());
        System.out.print(sb);
        return;
      } else if (input[2] >= input[0] + input[1]) {
        sb.append("Invalid\n");
      } else if (input[0] == input[1] && input[1] == input[2]) { // 정삼각형
        sb.append("Equilateral\n");
      } else if (input[0] == input[1] || input[0] == input[2] || input[1] == input[2]) { // 이등변
        sb.append("Isosceles\n");
      } else {
        sb.append("Scalene\n");
      }
    }
  }
}