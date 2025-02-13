import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;


public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    br.readLine();

    int[] field = Arrays.stream(br.readLine().split(" "))
        .mapToInt(Integer::parseInt).toArray();

    br.readLine();

    int[] target = Arrays.stream(br.readLine().split(" "))
        .mapToInt(Integer::parseInt).toArray();

    HashSet<Integer> set = new HashSet<>();

    for (int row : field) {
      set.add(row);
    }

    for (int row : target) {
      if (set.contains(row)) {
        sb.append(1).append("\n");
      } else {
        sb.append(0).append("\n");
      }
    }
    System.out.print(sb);
  }

}
