import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    final int TEST_CASE = Integer.parseInt(br.readLine());

    for (int i = 0; i < TEST_CASE; i++) {
      List<List<Integer>> graph = new ArrayList<>();
      int numberCount = Integer.parseInt(br.readLine());

      for (int j = 0; j < numberCount + 1; j++) {
        graph.add(new ArrayList<>());
      }

      boolean[] isRoot = new boolean[numberCount + 1];
      Arrays.fill(isRoot, true);

      int[] parent = new int[numberCount + 1];

      for (int j = 1; j < numberCount; j++) {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int from = Integer.parseInt(st.nextToken());
        int to = Integer.parseInt(st.nextToken());
        graph.get(from).add(to);
        isRoot[to] = false;
        parent[to] = from;
      }

      int rootNumber = 0;
      for (int j = 1; j < isRoot.length; j++) {
        if (isRoot[j]) {
          rootNumber = j;
          break;
        }
      }
      int[] depth = new int[numberCount + 1];

      ArrayDeque<Node> queue = new ArrayDeque<>();

      queue.add(new Node(rootNumber, 1));
      while (!queue.isEmpty()) {
        Node poll = queue.poll();

        int to = poll.to;
        int depthOfTo = poll.depth;
        depth[to] = depthOfTo;

        for (Integer integer : graph.get(to)) {
          queue.add(new Node(integer, depthOfTo + 1));
        }
      }

      StringTokenizer st = new StringTokenizer(br.readLine());
      int condition1 = Integer.parseInt(st.nextToken());
      int condition2 = Integer.parseInt(st.nextToken());

      if (depth[condition1] > depth[condition2]) {
        while (true) {
          if (depth[condition1] == depth[condition2]) {
            break;
          }
          condition1 = parent[condition1];
        }
      } else if (depth[condition1] < depth[condition2]) {
        while (true) {
          if (depth[condition1] == depth[condition2]) {
            break;
          }
          condition2 = parent[condition2];
        }
      }
      while (true) {
        if (condition1 == condition2) {
          System.out.println(condition1);
          break;
        }
        condition1 = parent[condition1];
        condition2 = parent[condition2];
      }

    }
  }

  static class Node {

    int to;
    int depth;

    public Node(int to, int depth) {
      this.to = to;
      this.depth = depth;
    }
  }


}