import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    final int N = Integer.parseInt(br.readLine());

    int[] cost = new int[N + 1];
    int[] inDegree = new int[N + 1];

    List<List<Integer>> nextNode = new ArrayList<>();
    List<List<Integer>> prevNode = new ArrayList<>();
    for (int i = 0; i <= N; i++) {
      nextNode.add(new ArrayList<>());
      prevNode.add(new ArrayList<>());
    }

    for (int i = 1; i <= N; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());

      int curCost = Integer.parseInt(st.nextToken());
      cost[i] = curCost;

      while (true) {
        int number = Integer.parseInt(st.nextToken());

        if (number == -1) {
          break;
        }

        inDegree[i]++;
        nextNode.get(number).add(i);
        prevNode.get(i).add(number);
      }
    }

    int[] answer = new int[N + 1];
    ArrayDeque<Help> queue = new ArrayDeque<>();

    for (int i = 1; i < inDegree.length; i++) {
      if (inDegree[i] == 0) {
        inDegree[i] = -1;
        queue.add(new Help(0, i));
        answer[i] = cost[i];
      }
    }

    while (!queue.isEmpty()) {
      Help poll = queue.poll();
      int from = poll.from;
      int to = poll.to;

      if (from == 0) {
        for (Integer i : nextNode.get(to)) {
          inDegree[i]--;
        }

        for (int i = 1; i < inDegree.length; i++) {
          if (inDegree[i] == 0) {
            inDegree[i] = -1;
            queue.add(new Help(to, i));
          }
        }
        continue;
      }

      for (Integer i : nextNode.get(to)) {
        inDegree[i]--;
      }

      int buffer = 0;

      for (Integer i : prevNode.get(to)) {
        buffer = Math.max(buffer, answer[i]);
      }

      buffer += cost[to];
      answer[to] = Math.max(answer[to], buffer);

      for (int i = 1; i < inDegree.length; i++) {
        if (inDegree[i] == 0) {
          inDegree[i] = -1;
          queue.add(new Help(to, i));
        }
      }
    }

    for (int i = 1; i < answer.length; i++) {
      System.out.println(answer[i]);
    }
  }

  static class Help {

    int from;
    int to;

    public Help(int from, int to) {
      this.from = from;
      this.to = to;
    }
  }
}