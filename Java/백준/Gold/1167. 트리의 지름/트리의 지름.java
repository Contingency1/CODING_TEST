import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

  static List<List<NodeJS>> graph;
  static boolean[] visited;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    final int NODE_COUNT = Integer.parseInt(br.readLine());

    visited = new boolean[NODE_COUNT + 1];
    graph = new ArrayList<>();

    for (int i = 0; i <= NODE_COUNT; i++) {
      graph.add(new ArrayList<>());
    }

    for (int i = 0; i < NODE_COUNT; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int nodeNumber = Integer.parseInt(st.nextToken());

      while (true) {
        int to = Integer.parseInt(st.nextToken());

        if (to == -1) {
          break;
        }

        int value = Integer.parseInt(st.nextToken());
        graph.get(nodeNumber).add(new NodeJS(to, value));
      }
    }

    Sum sum = dfs(1, 0);
    Arrays.fill(visited, false);
    Sum answer = dfs(sum.nodeNumber, 0);

    System.out.print(answer.sum);

  }

  static Sum dfs(int start, int value) {
    if (visited[start]) {
      return null;
    }
    visited[start] = true;

    Sum answer = null;

    for (NodeJS node : graph.get(start)) {
      int curTo = node.to;
      int curValue = node.value;

      Sum buffer = dfs(curTo, curValue);
      if (buffer == null) {
        continue;
      }

      buffer.sum += value;

      if (answer == null) {
        answer = buffer;
        continue;
      }

      answer = answer.sum < buffer.sum ? buffer : answer;
    }

    if (answer == null) {
      return new Sum(start, value);
    }

    return answer;

  }

  static class Sum {

    int nodeNumber;
    int sum;

    public Sum(int nodeNumber, int sum) {
      this.nodeNumber = nodeNumber;
      this.sum = sum;
    }

  }

  static class NodeJS {

    int to;
    int value;

    public NodeJS(int to, int value) {
      this.to = to;
      this.value = value;
    }

  }

}

