import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    String[] s = br.readLine().split(" ");
    int N = Integer.parseInt(s[0]);
    int M = Integer.parseInt(s[1]);

    HashSet<String> set = new HashSet<>();

    for (int i = 0; i < N; i++) {
      set.add(br.readLine());
    }

    String[] rows = new String[M];

    for (int i = 0; i < M; i++) {
      rows[i] = br.readLine();
    }

    int answer = 0;
    for (String row : rows) {
      if (set.contains(row)) {
        answer++;
      }
    }

    sb.append(answer);

    System.out.print(sb);
  }
}