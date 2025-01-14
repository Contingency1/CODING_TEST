import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    int N = Integer.parseInt(br.readLine());
    HashSet<String> set = new HashSet<>();

    for (int i = 0; i < N; i++) {
      String word = br.readLine().split(" ")[0];

      if (set.contains(word)) {
        set.remove(word);
      } else {
        set.add(word);
      }
    }

    List<String> list = new ArrayList<>(set);

    list.sort(Collections.reverseOrder());

    for (String row : list) {
      sb.append(row + "\n");
    }

    sb.deleteCharAt(sb.length() - 1);

    System.out.print(sb);
  }
}