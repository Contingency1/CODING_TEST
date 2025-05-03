import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    String[] firstLine = br.readLine().split(" ");

    final int START = Integer.parseInt(firstLine[0]);
    final int END = Integer.parseInt(firstLine[1]);

    int max = Math.max(START, END);
    PriorityQueue<NodeJS> queue = new PriorityQueue<>();
    boolean[] visited = new boolean[max + 3];

    queue.add(new NodeJS(START, 0));
//    System.out.println("START: " + START + ", END: " + END);

    int answer = 0;
    while (!queue.isEmpty()) {
      NodeJS polled = queue.poll();
      int polledTo = polled.to;
      int polledCost = polled.cost;

//      System.out.println("Polled -> to: " + polledTo + ", cost: " + polledCost);

      if (polledTo == END) {
        answer = polledCost;
//        System.out.println("==> END reached with cost: " + answer);
        break;
      }

      if (visited[polledTo]) {
//        System.out.println("Already visited: " + polledTo);
        continue;
      }

      visited[polledTo] = true;

      if (polledTo - 1 >= 0) {
//        System.out.println("Add: " + (polledTo - 1) + " with cost " + (polledCost + 1));
        queue.add(new NodeJS(polledTo - 1, polledCost + 1));
        if (polledTo - 1 >= END) {
          continue;
        }
      }

      if (polledTo + 1 <= END) {
//        System.out.println("Add: " + (polledTo + 1) + " with cost " + (polledCost + 1));
        queue.add(new NodeJS(polledTo + 1, polledCost + 1));
      }

      if (polledTo * 2 < END + 3) {
//        System.out.println("Add: " + (polledTo * 2) + " with cost " + polledCost);
        queue.add(new NodeJS(polledTo * 2, polledCost));
      }
    }

    sb.append(answer);
    System.out.print(sb);
  }

  static class NodeJS implements Comparable<NodeJS> {

    int to;
    int cost;

    NodeJS(int to, int cost) {
      this.to = to;
      this.cost = cost;
    }

    @Override
    public int compareTo(NodeJS other) {
      return this.cost - other.cost;
    }
  }
}
