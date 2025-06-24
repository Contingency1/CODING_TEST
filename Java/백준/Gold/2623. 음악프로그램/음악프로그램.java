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
    StringTokenizer st = new StringTokenizer(br.readLine());
    final int NUMBER = Integer.parseInt(st.nextToken());
    final int CASE_COUNT = Integer.parseInt(st.nextToken());

    int[] indegree = new int[NUMBER + 1];
    List<List<Integer>> input = new ArrayList<>();

    for (int i = 0; i < CASE_COUNT; i++) {
      input.add(new ArrayList<>());
    }

    boolean[] used = new boolean[NUMBER + 1];
    int useCount = 0;

    for (int i = 0; i < CASE_COUNT; i++) {
      st = new StringTokenizer(br.readLine());
      int iterate = Integer.parseInt(st.nextToken());
      List<Integer> inputI = input.get(i);

      for (int j = 0; j < iterate; j++) {
        int insert = Integer.parseInt(st.nextToken());
        inputI.add(insert);
        used[insert] = true;
      }
    }

    for (boolean use : used) {
      if (use) {
        useCount++;
      }
    }

    List<List<Integer>> graph = new ArrayList<>();

    for (int i = 0; i < NUMBER + 1; i++) {
      graph.add(new ArrayList<>());
    }

    for (List<Integer> integers : input) {
      for (int i = 0; i < integers.size() - 1; i++) {
        Integer next = integers.get(i + 1);
        graph.get(integers.get(i)).add(next);
        indegree[next]++;
      }
    }

    PriorityQueue<Integer> pq = new PriorityQueue<>();
    for (int i = 1; i < indegree.length; i++) {
      if (indegree[i] == 0 && used[i]) {
        pq.offer(i);
      }
    }

    List<Integer> result = new ArrayList<>();
    while (!pq.isEmpty()) {
      int poll = pq.poll();
      result.add(poll);
      for (int i = 0; i < graph.get(poll).size(); i++) {
        int next = graph.get(poll).get(i);
        indegree[next]--;
        if (indegree[next] == 0) {
          pq.offer(next);
        }
      }
    }

    if (result.size() != useCount) {
      System.out.print(0);
      return;
    }

    for (Integer i : result) {
      System.out.println(i);
    }

    for (int i = 1; i < used.length; i++) {
      if (!used[i]) {
        System.out.println(i);
      }
    }


  }


}