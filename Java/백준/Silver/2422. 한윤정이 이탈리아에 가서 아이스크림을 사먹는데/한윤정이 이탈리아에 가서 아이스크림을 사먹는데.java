import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());
    int count = Integer.parseInt(st.nextToken());
    int bad = Integer.parseInt(st.nextToken());

    boolean[][] pair = new boolean[count + 1][count + 1];

    for (int i = 0; i < bad; i++) {
      st = new StringTokenizer(br.readLine());
      int u = Integer.parseInt(st.nextToken());
      int v = Integer.parseInt(st.nextToken());

      pair[u][v] = true;
      pair[v][u] = true;
    }

    int answer = 0;
    for (int i = 1; i <= count - 2; i++) {
      for (int j = i + 1; j <= count - 1; j++) {
        if (pair[i][j]) {
          continue;
        }
        for (int k = j + 1; k <= count; k++) {
          if (pair[j][k] || pair[i][k]) {
            continue;
          }
          answer++;
        }
      }
    }

    System.out.print(answer);
  }
}