import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Main {


  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    br.readLine();
    
    String[] arr = br.readLine().split(" ");
    HashMap<String, Integer> map = new HashMap<>();

    for (String row : arr) {
      if (map.containsKey(row)) {
        map.put(row, map.get(row) + 1);
      } else {
        map.put(row, 1);
      }
    }

    br.readLine();

    String[] arr2 = br.readLine().split(" ");

    for (String row : arr2) {
      if (map.containsKey(row)) {
        sb.append(map.get(row)).append(" ");
      } else {
        sb.append(0).append(" ");
      }
    }

    System.out.print(sb);
  }
}