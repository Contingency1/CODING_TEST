import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Main {


  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    int cities = Integer.parseInt(br.readLine());
    int buses = Integer.parseInt(br.readLine());

    int[][] input = new int[buses][3];

    for (int i = 0; i < buses; i++) {
      input[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }

    int[] question = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    boolean[] visited = new boolean[cities + 1];

    int start = question[0];
    int end = question[1];

    List<List<NodeJS>> nodes = new ArrayList<>();
    PriorityQueue<NodeJS> queue = new PriorityQueue<>();

    nodes.add(new ArrayList<>());

    queue.add(new NodeJS(start, 0));

    for (int i = 1; i <= cities; i++) {
      nodes.add(new ArrayList<>());
    }

    for (int i = 0; i < buses; i++) {
      int from = input[i][0];
      int to = input[i][1];
      int weight = input[i][2];

      nodes.get(from).add(new NodeJS(to, weight));
    }

    int[] distance = new int[cities + 1];

    Arrays.fill(distance, Integer.MAX_VALUE);
    distance[start] = 0;

    while (!queue.isEmpty()) {

      NodeJS polled = queue.poll();

      int polledTo = polled.to;
      int polledWei = polled.weight;

      if (distance[polledTo] < polledWei) {
        continue;
      }

      List<NodeJS> nodeArr = nodes.get(polledTo);

      for (NodeJS nd : nodeArr) {
        int curTo = nd.to;
        int curWei = nd.weight;

        int curPlusPolledWei = distance[polledTo] + curWei;

        if (curPlusPolledWei < distance[curTo]) {
          distance[curTo] = curPlusPolledWei;
          visited[curTo] = true;
          queue.add(new NodeJS(curTo, distance[curTo]));
        }
      }
    }

    sb.append(distance[end]);

    System.out.print(sb);
  }

  static class NodeJS implements Comparable<NodeJS> {

    int to;
    int weight;

    NodeJS(int to, int weight) {
      this.to = to;
      this.weight = weight;
    }

    @Override
    public int compareTo(NodeJS other) {
      // 오름차순 정렬 => 양수 일 경우 기존의 것을 후순위로 보내기
      return this.weight - other.weight;
    }
  }
}
