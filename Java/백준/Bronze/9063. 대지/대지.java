import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    br.readLine();
    int[][] input = br.lines().map(x -> {
      String[] i = x.split(" ");
      return Arrays.stream(i).mapToInt(Integer::parseInt).toArray();
    }).toArray(int[][]::new);

    Arrays.sort(input, Comparator.comparing(x -> x[0]));

    int x1 = input[0][0];

    int x2 = input[input.length - 1][0];

    Arrays.sort(input, Comparator.comparing(x -> x[1]));

    int y1 = input[0][1];
    int y2 = input[input.length - 1][1];

    sb.append((x2 - x1) * (y2 - y1));
    System.out.print(sb);
  }
}