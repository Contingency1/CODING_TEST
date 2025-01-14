import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.HashSet;
import java.util.TreeSet;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    String[] input = br.readLine().split(" ");
    int N = Integer.parseInt(input[0]);
    int M = Integer.parseInt(input[1]);

    HashSet<String> set = new HashSet<>(N);

    for (int i = 0; i < N; i++) {
      set.add(br.readLine());
    }

    TreeSet<String> treeSet = new TreeSet<>(Comparator.naturalOrder());

    for (int i = 0; i < M; i++) {
      String str = br.readLine();
      if (set.contains(str)) {
        treeSet.add(str);
      }
    }
    sb.append(treeSet.size()).append("\n");

    for (String s : treeSet) {
      sb.append(s).append("\n");
    }

    sb.deleteCharAt(sb.length() - 1);
    System.out.print(sb);
  }
}