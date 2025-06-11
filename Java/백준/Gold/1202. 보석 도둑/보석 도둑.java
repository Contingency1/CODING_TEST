import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();
    StringTokenizer st = new StringTokenizer(br.readLine());

    final int JEWEL_COUNT = Integer.parseInt(st.nextToken());
    final int BAG_COUNT = Integer.parseInt(st.nextToken());

    PriorityQueue<Jewel> jewelQueue = new PriorityQueue<>();
    for (int i = 0; i < JEWEL_COUNT; i++) {
      st = new StringTokenizer(br.readLine());
      int weight = Integer.parseInt(st.nextToken());
      int value = Integer.parseInt(st.nextToken());

      jewelQueue.add(new Jewel(weight, value));
    }

    PriorityQueue<Integer> bagQueue = new PriorityQueue<>();
    for (int i = 0; i < BAG_COUNT; i++) {
      int bag = Integer.parseInt(br.readLine());

      bagQueue.add(bag);
    }

    long answer = 0;

    PriorityQueue<Integer> value = new PriorityQueue<>(Comparator.reverseOrder());
    while (!bagQueue.isEmpty()) {
      Integer bagWeight = bagQueue.poll();

      while (!jewelQueue.isEmpty()) {
        Jewel jewel = jewelQueue.peek();
        int jewelWeight = jewel.weight;

        if (jewelWeight > bagWeight) {
          break;
        }

        value.add(jewelQueue.poll().value);
      }

      answer += value.peek() == null ? 0 : value.poll();
    }

    sb.append(answer);
    System.out.print(sb);
  }

  static class Jewel implements Comparable<Jewel> {

    int weight;
    int value;

    public Jewel(int weight, int value) {
      this.weight = weight;
      this.value = value;
    }

    @Override
    public int compareTo(Jewel other) {
      return this.weight == other.weight ?
          this.value - other.value :
          this.weight - other.weight;
    }

  }
}