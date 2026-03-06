import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Objects;
import java.util.ArrayDeque;
import java.util.Arrays;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    final int CAVE_COUNT = Integer.parseInt(st.nextToken());
    final int ROBOT_1 = Integer.parseInt(st.nextToken());
    final int ROBOT_2 = Integer.parseInt(st.nextToken());

    if(ROBOT_1 == ROBOT_2) {
      System.out.print("0");
      return;
    }
    
    Map<Integer, Set<Node>> route = new HashMap<>();

    for (int i = 1; i <= CAVE_COUNT; i++) {
      route.put(i, new HashSet<>());
    }

    for (int i = 0; i < CAVE_COUNT - 1; i++) {
      st = new StringTokenizer(br.readLine());

      int from = Integer.parseInt(st.nextToken());
      int to = Integer.parseInt(st.nextToken());
      int cost = Integer.parseInt(st.nextToken());

      route.get(from).add(new Node(to, cost));
      route.get(to).add(new Node(from, cost));
    }

    Robot robot1 = new Robot(ROBOT_1, CAVE_COUNT);
    Robot robot2 = new Robot(ROBOT_2, CAVE_COUNT);

    robot1.bfs(route);
    robot2.bfs(route);

    int[] dp1 = robot1.dp;
    int[] dp2 = robot2.dp;

    int answer = Integer.MAX_VALUE;

    for (int i = 1; i < CAVE_COUNT; i++) {
      
      for (Node n: route.get(i)) {
        int from = dp1[i];
        int to = dp2[n.to];

        answer = Math.min(answer, from + to);
      
        from = dp1[n.to];
        to = dp2[i];
      
        answer = Math.min(answer, from + to);
      }
    }

    System.out.print(answer);
  }
}

class Node {
  int to;
  int cost;

  Node(int to, int cost) {
    this.to = to;
    this.cost = cost;
  }

  @Override
  public boolean equals(Object o) {
    if(o == this) {
      return true;
    }

    if(!(o instanceof Node)) {
      return false;
    }

    Node n = (Node) o;
    return n.to == this.to && n.cost == this.cost;
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.to, this.cost);
  }
}

class Robot {
  int start;
  int[] dp;
  boolean[] visited;

  Robot(int start, int count) {
    this.start = start;
    this.dp = new int[count + 1];
    this.visited = new boolean[count + 1];

    this.visited[start] = true;
  }

  public void bfs(Map<Integer, Set<Node>> map)  {
    ArrayDeque<Integer> queue = new ArrayDeque<>();
    queue.addLast(start);

    while(!queue.isEmpty()) {
      int polled = queue.pollFirst();

      for (Node n: map.get(polled)) {
        int to = n.to;
        int cost = n.cost;
  
        if (visited[to]) {
          continue;
        }

        dp[to] = dp[polled] + cost;
        visited[to] = true;

        queue.add(to);
      }
    }

  }

}
