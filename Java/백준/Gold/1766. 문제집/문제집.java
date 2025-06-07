import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();
    StringTokenizer st = new StringTokenizer(br.readLine());

    final int NUMBER_COUNT = Integer.parseInt(st.nextToken());
    final int COMPARE_COUNT = Integer.parseInt(st.nextToken());

    int[] inDegree = new int[NUMBER_COUNT + 1];

    List<List<Integer>> outGraph = new ArrayList<>();

    for (int i = 0; i <= NUMBER_COUNT; i++) {
      outGraph.add(new ArrayList<>());
    }

    for (int i = 0; i < COMPARE_COUNT; i++) {
      st = new StringTokenizer(br.readLine());
      int first = Integer.parseInt(st.nextToken());
      int last = Integer.parseInt(st.nextToken());

      inDegree[last]++;
      outGraph.get(first).add(last);
    }

    PriorityQueue<Integer> pq = new PriorityQueue<>();

    for (int i = 1; i < NUMBER_COUNT + 1; i++) {
      if (inDegree[i] == 0) {
        pq.add(i);
      }
    }

    while (!pq.isEmpty()) {
      Integer polled = pq.poll();

      if (!outGraph.get(polled).isEmpty()) {
        for (Integer i : outGraph.get(polled)) {
          inDegree[i]--;
          if (inDegree[i] == 0) {
            pq.add(i);
          }
        }
        sb.append(polled).append(" ");
        continue;
      }

      sb.append(polled).append(" ");
    }

    sb.deleteCharAt(sb.length() - 1);

    System.out.print(sb);
  }
}