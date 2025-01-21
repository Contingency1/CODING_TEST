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

    int answer = 0;

    boolean start = false;

    for (int i = 0; i < N; i++) {
      String str = br.readLine();
      if (str.equals("ENTER")) {
        set.clear();
        start = true;
        continue;
      }

      if (start) {
        if (set.contains(str)) {
          continue;
        }
        set.add(str);
        answer++;
      }
    }

    sb.append(answer);

    System.out.print(sb);
  }
}