import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    int N = Integer.parseInt(br.readLine());

    class Custom implements Comparable<Custom> {

      final long number;

      Custom(long n) {
        number = n;
      }

      private long getNumber() {
        return this.number;
      }

      @Override
      public int compareTo(Custom custom) {
        if (Math.abs(this.number) > Math.abs(custom.getNumber())) {
          return 1;
        } else if (Math.abs(this.number) < Math.abs(custom.getNumber())) {
          return -1;
        } else {
          if (this.number > custom.getNumber()) {
            return 1;
          } else if (this.number < custom.getNumber()) {
            return -1;
          }
          return 0;
        }
      }
    }

    PriorityQueue<Custom> pq = new PriorityQueue<>();

    for (int i = 0; i < N; i++) {
      long input = Long.parseLong(br.readLine());

      if (input != 0) {
        pq.add(new Custom(input));
      } else {
        if (pq.isEmpty()) {
          sb.append("0").append("\n");
        } else {
          sb.append(pq.poll().getNumber()).append("\n");
        }
      }
    }

    System.out.print(sb);
  }
}

