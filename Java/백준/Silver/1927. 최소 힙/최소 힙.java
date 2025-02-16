import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

//보이어-무어 알고리즘 Bad 뭐시기 이용

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    int N = Integer.parseInt(br.readLine());

    PriorityQueue<Long> pq = new PriorityQueue<>();

    for (int i = 0; i < N; i++) {
      long num = Long.parseLong(br.readLine());

      if (num > 0) {
        pq.add(num);
        continue;
      }

      if (pq.isEmpty()) {
        sb.append(0).append("\n");
      } else if (num == 0) {
        sb.append(pq.poll()).append("\n");
      }
    }

    System.out.print(sb);
  }

}
