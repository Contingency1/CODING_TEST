import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    final int PEOPLE_COUNT = Integer.parseInt(br.readLine());

    int[] answer = new int[PEOPLE_COUNT + 1];
    Arrays.fill(answer, -1);
    boolean[] known = new boolean[PEOPLE_COUNT + 1];

    List<List<Integer>> neighbors = new ArrayList<List<Integer>>();

    for (int i = 0; i <= PEOPLE_COUNT; i++) {
      neighbors.add(new ArrayList<>());
    }

    StringTokenizer st;
    for (int i = 1; i <= PEOPLE_COUNT; i++) {
      st = new StringTokenizer(br.readLine());

      while (true) {
        int neighbour = Integer.parseInt(st.nextToken());
        if (neighbour == 0) {
          break;
        }
        neighbors.get(i).add(neighbour);
      }
    }

    final int RUMOR_COUNT = Integer.parseInt(br.readLine());
    st = new StringTokenizer(br.readLine());

    PriorityQueue<Rumor> queue = new PriorityQueue<>();

    int[] rumorCreator = new int[RUMOR_COUNT];
    for (int i = 0; i < RUMOR_COUNT; i++) {
      rumorCreator[i] = Integer.parseInt(st.nextToken());
      answer[rumorCreator[i]] = 0;
      known[rumorCreator[i]] = true;

      queue.add(new Rumor(1, rumorCreator[i]));
    }

    int lastTime = 1;
    List<Integer> know = new ArrayList<>();

    while (!queue.isEmpty()) {
      List<Integer> buffer = new ArrayList<>();
      Rumor rumor = queue.poll();
      int time = rumor.time;
      int from = rumor.from;

      if (lastTime != time) {
        for (Integer i : know) {
          known[i] = true;
        }

        know.clear();
        lastTime = time;
      }
      for (Integer i : neighbors.get(from)) {
        if (known[i]) {
          continue;
        }

        int neighbourSize = neighbors.get(i).size();

        int half = neighbourSize % 2 == 0 ?
            neighbourSize / 2
            : neighbourSize / 2 + 1;
        int believe = 0;

        for (Integer integer : neighbors.get(i)) {
          if (known[integer]) {
            believe++;
          }
        }

        if (half <= believe) {
          buffer.add(i);
        }
      }

      for (Integer i : buffer) {
        know.add(i);
        answer[i] = time;
        queue.add(new Rumor(time + 1, i));
      }

    }

    StringBuilder sb = new StringBuilder();

    for (int i = 1; i < answer.length; i++) {
      sb.append(answer[i]).append(" ");
    }

    sb.deleteCharAt(sb.length() - 1);
    System.out.print(sb);
  }

  static class Rumor implements Comparable<Rumor> {

    int time;
    int from;

    public Rumor(int time, int from) {
      this.time = time;
      this.from = from;
    }

    @Override
    public int compareTo(Rumor o) {
      return time - o.time;
    }
  }


}