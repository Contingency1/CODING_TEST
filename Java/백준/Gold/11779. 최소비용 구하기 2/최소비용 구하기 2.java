import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    // (1 ≤ n ≤ 1,000)
    int cityCount = Integer.parseInt(br.readLine());
    // (1 ≤ m ≤ 100,000)
    int busCount = Integer.parseInt(br.readLine());
    List<List<NodeJS>> busLists = new ArrayList<>();

    boolean[] visited = new boolean[cityCount + 1];

    for (int i = 0; i <= cityCount; i++) {
      busLists.add(new ArrayList<>());
    }

    for (int i = 0; i < busCount; i++) {
      String[] str = br.readLine().split(" ");
      int from = Integer.parseInt(str[0]);
      int to = Integer.parseInt(str[1]);
      int cost = Integer.parseInt(str[2]);

      busLists.get(from).add(new NodeJS(to, cost));
    }

    String[] buffer = br.readLine().split(" ");
    final int START = Integer.parseInt(buffer[0]);
    final int END = Integer.parseInt(buffer[1]);

    PriorityQueue<Priority> pq = new PriorityQueue<>();

    for (NodeJS bus : busLists.get(START)) {
      pq.add(new Priority(bus.to, bus.cost, null));
    }

    while (!pq.isEmpty()) {
      Priority p = pq.poll();

      int curPoint = p.point;
      int curSumCost = p.sumCost;

      if (visited[curPoint]) {
        continue;
      }

      if (curPoint == END) {
        // 마무리 작업 넣을것.
        sb.append(curSumCost).append("\n");

        List<Integer> answer = new LinkedList<>();

        answer.add(0, curPoint);

        while (p.prev != null) {
          answer.add(0, p.prev.point);
          p.prev = p.prev.prev;
        }

        answer.add(0, START);

        sb.append(answer.size()).append("\n");

        for (Integer i : answer) {
          sb.append(i).append(" ");
        }

        break;
      }

      for (NodeJS bus : busLists.get(curPoint)) {
        int plusBusCost = bus.cost + curSumCost;
        int busTo = bus.to;

        pq.add(new Priority(busTo, plusBusCost, p));

        visited[curPoint] = true;
      }


    }

    System.out.print(sb);
  }

  static class Priority implements Comparable<Priority> {

    int point;
    int sumCost;
    Priority prev;

    Priority(int point, int sumCost, Priority prev) {
      this.point = point;
      this.sumCost = sumCost;
      this.prev = prev;
    }

    @Override
    public int compareTo(Priority other) {
      // 양수일 경우 this를 후순위로 넘김 (오름차순)
      return this.sumCost - other.sumCost;
    }
  }

  static class NodeJS {

    int to;
    int cost;

    NodeJS(int to, int cost) {
      this.to = to;
      this.cost = cost;
    }


  }


}