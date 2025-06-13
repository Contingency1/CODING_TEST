import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    final int TEST_CASE = Integer.parseInt(br.readLine());

    for (int i = 0; i < TEST_CASE; i++) {
      int storeCount = Integer.parseInt(br.readLine());
      int[] house = new int[2];
      int[][] store = new int[storeCount][2];
      int[] festival = new int[2];
      StringTokenizer st = new StringTokenizer(br.readLine());
      List<NodeJS> nodes = new ArrayList<>();
      house[0] = Integer.parseInt(st.nextToken());
      house[1] = Integer.parseInt(st.nextToken());
      nodes.add(new NodeJS(house[0], house[1]));

      for (int j = 0; j < storeCount; j++) {
        st = new StringTokenizer(br.readLine());
        store[j][0] = Integer.parseInt(st.nextToken());
        store[j][1] = Integer.parseInt(st.nextToken());
        nodes.add(new NodeJS(store[j][0], store[j][1]));
      }
      st = new StringTokenizer(br.readLine());
      festival[0] = Integer.parseInt(st.nextToken());
      festival[1] = Integer.parseInt(st.nextToken());
      nodes.add(new NodeJS(festival[0], festival[1]));

      List<List<Graph>> graph = new ArrayList<>();
      for (int j = 0; j < storeCount + 2; j++) {
        graph.add(new ArrayList<>());
      }
      //정상 로직

      isImpossible(nodes, graph);

      ArrayDeque<Graph> queue = new ArrayDeque<>();
      queue.add(new Graph(0, nodes.get(0).x, nodes.get(0).y));

      boolean[] visited = new boolean[nodes.size()];
      visited[0] = true;
      boolean flag = false;
      while (!queue.isEmpty()) {
        Graph polled = queue.pollFirst();
        int index = polled.i;

        if (index == storeCount + 1) {
          flag = true;
          break;
        }

        for (Graph gr : graph.get(index)) {
          if (visited[gr.i]) {
            continue;
          }

          queue.add(gr);
          visited[gr.i] = true;
        }
      }
      if (flag) {
        System.out.println("happy");
      } else {

        System.out.println("sad");
      }


    }
  }

  private static void isImpossible(List<NodeJS> nodes, List<List<Graph>> graph) {
    for (int j = 0; j < nodes.size() - 1; j++) {
      for (int k = 1; k < nodes.size(); k++) {
        NodeJS buffer1 = nodes.get(j);
        NodeJS buffer2 = nodes.get(k);
        if (Math.abs(buffer1.x - buffer2.x) + Math.abs(buffer1.y - buffer2.y) > 1000) {
          continue;
        }
        graph.get(j).add(new Graph(k, buffer2.x, buffer2.y));
      }
    }
  }


  static class NodeJS {

    int x;
    int y;

    public NodeJS(int x, int y) {
      this.x = x;
      this.y = y;
    }
  }

  static class Graph {

    int i;
    int x;
    int y;

    public Graph(int i, int x, int y) {
      this.i = i;
      this.x = x;
      this.y = y;
    }
  }
}