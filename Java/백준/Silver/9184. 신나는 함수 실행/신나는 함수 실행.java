import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;

public class Main {

  private static int way(int a, int b, int c, HashMap<String, Integer> map) {

    String str = a + " " + b + " " + c;

    if (a <= 0 || b <= 0 || c <= 0) {
      return 1;
    }

    if (a > 20 || b > 20 || c > 20) {
      return way(20, 20, 20, map);
    }

    if (map.containsKey(str)) {
      return map.get(str);
    } else {

      if (a < b && b < c) {
        int key = way(a, b, c - 1, map) + way(a, b - 1, c - 1, map) - way(a, b - 1, c, map);
        map.put(str, key);
        return key;
      }

      int key = way(a - 1, b, c, map)
          + way(a - 1, b - 1, c, map)
          + way(a - 1, b, c - 1, map)
          - way(a - 1, b - 1, c - 1, map);
      map.put(str, key);
      return key;
    }

  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();
    HashMap<String, Integer> map = new HashMap<>();

    while (true) {
      int[] input = Arrays.stream(br.readLine().split(" "))
          .mapToInt(Integer::parseInt).toArray();

      if (input[0] == -1 && input[1] == -1 && input[2] == -1) {
        break;
      }

      sb.append("w(").append(input[0]).append(", ")
          .append(input[1]).append(", ")
          .append(input[2]).append(")");

      sb.append(" = ").append(way(input[0], input[1], input[2], map)).append("\n");
    }

    sb.deleteCharAt(sb.length() - 1);

    System.out.print(sb);
  }
}
