import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    int input = Integer.parseInt(br.readLine());

    for (int i = 0; i < input; i++) {
      int[] input2 = Arrays.stream(br.readLine().split(" "))
          .mapToInt(Integer::parseInt).toArray();

      sb.append(input2[0] + input2[1]).append("\n");
    }

    System.out.print(sb);
  }
}