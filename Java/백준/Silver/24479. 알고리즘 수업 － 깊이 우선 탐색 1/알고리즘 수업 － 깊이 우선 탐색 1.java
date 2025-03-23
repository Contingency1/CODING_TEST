import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main {

  static Map<Integer, ArrayList<Integer>> map;
  static int[] visited;
  static int count;

  private static void dfs(int current) {
    visited[current] = count++; // 방문

    for (int i : map.get(current)) {
      if (visited[i] == 0) {
        dfs(i);
      }
    }
  }


  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    int[] input = Arrays.stream(br.readLine().split(" "))
        .mapToInt(Integer::parseInt).toArray();

    int N = input[0];
    int M = input[1];
    int R = input[2];

    map = new HashMap<>();
    visited = new int[N + 1];

    count = 1;

    for (int i = 1; i <= N; i++) {
      map.put(i, new ArrayList<>());
    }

    for (int i = 0; i < M; i++) {
      int[] line = Arrays.stream(br.readLine().split(" "))
          .mapToInt(Integer::parseInt).toArray();

      map.get(line[0]).add(line[1]);
      map.get(line[1]).add(line[0]);
    }

    for (ArrayList<Integer> list : map.values()) {
      list.sort(Integer::compareTo);
//      sb.append(list).append("\n");
    }

    dfs(R);

    for (int i = 1; i < visited.length; i++) {
      sb.append(visited[i]).append("\n");
    }

    System.out.print(sb);
  }
}