import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

  static List<List<NodeJS>> graph = new ArrayList<>();

  static int[][] answer;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    final int NODE_COUNT = Integer.parseInt(br.readLine());
    final int EDGE_COUNT = Integer.parseInt(br.readLine());
    answer = new int[NODE_COUNT + 1][NODE_COUNT + 1];

    for (int i = 0; i < NODE_COUNT + 1; i++) {
      Arrays.fill(answer[i], 100_000_001);
    }

    for (int i = 1; i < NODE_COUNT + 1; i++) {
      answer[i][i] = 0;
    }

    makeGraph(NODE_COUNT, EDGE_COUNT, br);

    // 기초 만들기
    for (int i = 1; i <= NODE_COUNT; i++) {
      for (NodeJS node : graph.get(i)) {
        int curTo = node.to;
        int curWeight = node.weight;

        answer[i][curTo] = Math.min(curWeight, answer[i][curTo]);
      }
    }

    // 삼중 for 문 돌리기
    for (int i = 1; i < NODE_COUNT + 1; i++) {
      for (int j = 1; j < NODE_COUNT + 1; j++) {
        for (int k = 1; k < NODE_COUNT + 1; k++) {
          answer[j][k] = Math.min(answer[j][k], answer[j][i] + answer[i][k]);
        }
      }
    }

    for (int i = 1; i <= NODE_COUNT; i++) {
      for (int j = 1; j <= NODE_COUNT; j++) {
        if (answer[i][j] == 100_000_001) {
          sb.append(0).append(" ");
        } else {
          sb.append(answer[i][j]).append(" ");
        }
      }
      sb.deleteCharAt(sb.length() - 1).append("\n");
    }

    System.out.print(sb);
  }


  private static void makeGraph(int NODE_COUNT, int EDGE_COUNT, BufferedReader br)
      throws IOException {
    for (int i = 0; i <= NODE_COUNT; i++) {
      graph.add(new ArrayList<>());
    }

    for (int i = 1; i <= EDGE_COUNT; i++) {
      String[] str = br.readLine().split(" ");
      int from = Integer.parseInt(str[0]);
      int to = Integer.parseInt(str[1]);
      int weight = Integer.parseInt(str[2]);

      graph.get(from).add(new NodeJS(to, weight));
    }
  }


  static class NodeJS {

    int to;
    int weight;

    public NodeJS(int to, int weight) {
      this.to = to;
      this.weight = weight;
    }
  }
}
