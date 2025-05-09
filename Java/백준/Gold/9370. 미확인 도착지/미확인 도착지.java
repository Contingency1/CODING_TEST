import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

  static List<List<NodeJS>> graph = new ArrayList<>();

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    // 1 <= TEST_CASE <= 100
    final int TEST_CASE = Integer.parseInt(br.readLine());

    for (int i = 0; i < TEST_CASE; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      // 2 <= NODE_COUNT <= 2_000
      final int NODE_COUNT = Integer.parseInt(st.nextToken());
      // 1 <= EDGE_COUNT <= 50_000
      final int EDGE_COUNT = Integer.parseInt(st.nextToken());
      // 1 <= CANDIDATE_COUNT <= 100
      final int CANDIDATE_COUNT = Integer.parseInt(st.nextToken());

      st = new StringTokenizer(br.readLine());

      // 1 <= START, SIDE1, SIDE2 <= NODE_COUNT & SIDE != SIDE2
      final int START = Integer.parseInt(st.nextToken());
      final int SIDE1 = Integer.parseInt(st.nextToken());
      final int SIDE2 = Integer.parseInt(st.nextToken());

      makeGraph(NODE_COUNT, EDGE_COUNT, br);

      List<Integer> candidates = new ArrayList<>();

      for (int j = 0; j < CANDIDATE_COUNT; j++) {
        candidates.add(Integer.parseInt(br.readLine()));
      }

//      for (List<NodeJS> ia : graph) {
//        System.out.println(ia);
//      }

      PriorityQueue<Integer> answer = new PriorityQueue<>();

      int[] arr_start = dk(START, NODE_COUNT);
      int[] arr_side1 = dk(SIDE1, NODE_COUNT);
      int[] arr_side2 = dk(SIDE2, NODE_COUNT);
      
      for (int j = 0; j < CANDIDATE_COUNT; j++) {
        final int TARGET = candidates.get(j);

        int between = arr_side1[SIDE2];
        int key1 = arr_start[SIDE1] + between + arr_side2[TARGET];
        int key2 = arr_start[SIDE2] + between + arr_side1[TARGET];

        if (arr_start[TARGET] == key1 || arr_start[TARGET] == key2) {
          answer.add(TARGET);
        }
      }

      while (!answer.isEmpty()) {
        sb.append(answer.poll()).append(" ");
      }

      sb.deleteCharAt(sb.length() - 1).append("\n");
    }

    System.out.print(sb);
  }

  private static int[] dk(int start, int size) {
    PriorityQueue<UseState> pq = new PriorityQueue<>();
    pq.add(new UseState(start, 0));

    int[] arr = new int[size + 1];
    Arrays.fill(arr, -1);

    while (!pq.isEmpty()) {
      UseState state = pq.poll();
      int curTo = state.to;
      int curWeight = state.sumWeight;

      if (arr[curTo] != -1) {
        continue;
      }

      arr[curTo] = curWeight;

      for (NodeJS node : graph.get(curTo)) {
        int to = node.to;
        int plusWeight = node.weight + curWeight;

        pq.add(new UseState(to, plusWeight));
      }
    }

    return arr;
  }


  private static void makeGraph(int NODE_COUNT, int EDGE_COUNT,
      BufferedReader br) throws IOException {
    StringTokenizer st;
    graph = new ArrayList<>();
    for (int j = 0; j <= NODE_COUNT; j++) {
      graph.add(new ArrayList<>());
    }

    for (int j = 0; j < EDGE_COUNT; j++) {
      st = new StringTokenizer(br.readLine());

      int from = Integer.parseInt(st.nextToken());
      int to = Integer.parseInt(st.nextToken());
      int weight = Integer.parseInt(st.nextToken());

      graph.get(from).add(new NodeJS(to, weight));
      graph.get(to).add(new NodeJS(from, weight));
    }
  }

  static class UseState implements Comparable<UseState> {

    int to;
    int sumWeight;

    public UseState(int to, int sumWeight) {
      this.to = to;
      this.sumWeight = sumWeight;
    }

    @Override
    public int compareTo(UseState other) {
      return this.sumWeight - other.sumWeight;
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
