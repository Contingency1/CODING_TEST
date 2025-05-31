import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

  static boolean[] isCycle;
  static boolean[] visit;
  static int answer;
  static int[] graph;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    final int TEST_CASES = Integer.parseInt(br.readLine());

    for (int i = 0; i < TEST_CASES; i++) {
      int count = Integer.parseInt(br.readLine());
      StringTokenizer st = new StringTokenizer(br.readLine());

      graph = new int[count + 1];

      for (int j = 1; j < count + 1; j++) {
        graph[j] = Integer.parseInt(st.nextToken());
      }

      isCycle = new boolean[count + 1];
      visit = new boolean[count + 1];

      answer = 0;

      for (int j = 1; j < count + 1; j++) {
        if (graph[j] == j) {
          isCycle[j] = true;
          answer++;
        }
      }

      for (int j = 1; j < count + 1; j++) {
        if (isCycle[j]) {
          continue;
        }
        dfs(j);
      }

      sb.append(count - answer).append("\n");
    }

    br.close();
    System.out.print(sb);
  }

  public static void dfs(int n) {

    if (visit[n]) {
      isCycle[n] = true;
      answer++;
    } else {
      visit[n] = true;
    }

    if (!isCycle[graph[n]]) {
      dfs(graph[n]);
    }

    visit[n] = false;
    isCycle[n] = true;
  }
}
