import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    int N = Integer.parseInt(br.readLine());
    HashSet<String> set = new HashSet<>();

    set.add("ChongChong");

    for (int i = 1; i <= N; i++) {
      String[] str = br.readLine().split(" ");
      if (set.contains(str[0])) {
        set.add(str[1]);
      } else if (set.contains(str[1])) {
        set.add(str[0]);
      }
    }

    sb.append(set.size());
    System.out.print(sb);
  }
}