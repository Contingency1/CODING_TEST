import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    final int COUNT = Integer.parseInt(br.readLine());

    List<PriorityQueue<Integer>> pq = new ArrayList<>();

    for (int i = 0; i < COUNT; i++) {
      pq.add(new PriorityQueue<>(Comparator.reverseOrder()));
    }

    for (int i = 0; i < COUNT; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      for (int j = 0; j < COUNT; j++) {
        int input = Integer.parseInt(st.nextToken());
        pq.get(j).add(input);
      }

    }

    int answer = 0;

    for (int i = 1; i <= COUNT; i++) {
      int max = Integer.MIN_VALUE;
      int buffer = 0;
      for (int j = 0; j < COUNT; j++) {
        PriorityQueue<Integer> integers = pq.get(j);
        if (!integers.isEmpty() && max < integers.peek()) {
          max = integers.peek();
          buffer = j;
        }
      }

      answer = pq.get(buffer).poll();
    }

    sb.append(answer);

    System.out.print(sb);
  }
}