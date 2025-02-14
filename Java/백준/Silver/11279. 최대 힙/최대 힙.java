import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;


public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

    int n = Integer.parseInt(br.readLine());

    for (int i = 0; i < n; i++) {
      int num = Integer.parseInt(br.readLine());

      pq.add(num);

      if (num == 0) {
        sb.append(pq.poll()).append("\n");
      }
    }
    System.out.print(sb);
  }

}
