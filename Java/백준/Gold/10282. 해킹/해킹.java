import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;


public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    final int TEST_COUNT = Integer.parseInt(br.readLine());

    for (int i = 0; i < TEST_COUNT; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());

      int computerCount = Integer.parseInt(st.nextToken());
      int connectionCount = Integer.parseInt(st.nextToken());
      int hackedComputer = Integer.parseInt(st.nextToken());

      Map<Integer, List<Computer>> map = new HashMap<>();

      for (int j = 1; j <= computerCount; j++) {
        map.put(j, new ArrayList<>());
      }

      for (int j = 0; j < connectionCount; j++) {
        st = new StringTokenizer(br.readLine());

        int target = Integer.parseInt(st.nextToken());
        int start = Integer.parseInt(st.nextToken());
        int cost = Integer.parseInt(st.nextToken());

        map.get(start).add(new Computer(target, cost));
      }

      int[] arrCost = new int[computerCount + 1];

      Arrays.fill(arrCost, Integer.MAX_VALUE);

      PriorityQueue<Computer> queue = new PriorityQueue<>();

      queue.add(new Computer(hackedComputer, 0));

      while(!queue.isEmpty()) {
        Computer c = queue.poll();

        int target = c.target;
        int cost = c.cost;

        if(arrCost[target] < cost) {
          continue;
        }

        arrCost[target] = cost;
        
        for (Computer com: map.get(target)) {
          int nextTarget = com.target;
          int nextCost = com.cost + cost;
          
          if(nextCost < arrCost[nextTarget]) {
            queue.add(new Computer(nextTarget, nextCost));
          }
        }
      }

      int countAnswer = 0;
      int costAnswer = 0;

      for (int ar: arrCost) {
        if(ar >= 0 && ar != Integer.MAX_VALUE) {
          countAnswer++;
          costAnswer = Math.max(ar, costAnswer);
        }
      }

      System.out.println(String.format("%d %d", countAnswer, costAnswer));
    }
  }

  static class Computer implements Comparable<Computer> {
    int target;
    int cost;

    @Override
    public int compareTo(Computer other) {
      return this.cost - other.cost;
    }

    Computer(int target, int cost) {
      this.target = target;
      this.cost = cost;
    }
  }
}