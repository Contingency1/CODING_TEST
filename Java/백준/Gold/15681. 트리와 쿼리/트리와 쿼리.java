import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

  static List<List<Integer>> graph = new ArrayList<>();
  static int[] answer;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();
    String[] str = br.readLine().split(" ");

    final int NODE_COUNT = Integer.parseInt(str[0]);
    final int ROOT_NUMBER = Integer.parseInt(str[1]);
    final int QUERY_COUNT = Integer.parseInt(str[2]);

    answer = new int[NODE_COUNT + 1];

    for (int i = 0; i <= NODE_COUNT; i++) {
      graph.add(new ArrayList<>());
    }

    for (int i = 0; i < NODE_COUNT - 1; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int from = Integer.parseInt(st.nextToken());
      int to = Integer.parseInt(st.nextToken());

      graph.get(from).add(to);
      graph.get(to).add(from);
    }

    int[] query = new int[QUERY_COUNT];

    for (int i = 0; i < QUERY_COUNT; i++) {
      query[i] = Integer.parseInt(br.readLine());
    }

    boolean[] visited = new boolean[NODE_COUNT + 1];
    visited[ROOT_NUMBER] = true;
    dfs(visited, ROOT_NUMBER);

    for (int q : query) {
      sb.append(answer[q] + 1).append("\n");
    }

    System.out.print(sb);
  }

  static int dfs(boolean[] visited, int start) {
    for (Integer node : graph.get(start)) {
      if (visited[node]) {
        continue;
      }

      visited[node] = true;
      answer[start] += 1 + dfs(visited, node);
      visited[node] = false;
    }

    return answer[start];
  }


}
