import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {


  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    final int WORK_COUNT = Integer.parseInt(br.readLine());

    PriorityQueue<Factor> pq = new PriorityQueue<>();

    for (int i = 0; i < WORK_COUNT; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int spend = Integer.parseInt(st.nextToken());
      int until = Integer.parseInt(st.nextToken());

      pq.add(new Factor(spend, until));
    }

    int now = 0;
    int finalTime = Integer.MAX_VALUE;

    while (!pq.isEmpty()) {
      Factor poll = pq.poll();
      int spendTime = poll.spendTime;
      int untilTime = poll.untilTime;
      now += spendTime;

      finalTime = Math.min(finalTime, untilTime - now);

      if (now > untilTime) {
        System.out.print(-1);
        return;
      }

    }

    System.out.print(finalTime);
  }

  static class Factor implements Comparable<Factor> {

    int spendTime;
    int untilTime;

    public Factor(int spendTime, int untilTime) {
      this.spendTime = spendTime;
      this.untilTime = untilTime;
    }

    @Override
    public int compareTo(Factor o) {
      if (this.untilTime == o.untilTime) {
        return this.spendTime - o.spendTime;
      }

      return this.untilTime - o.untilTime;

    }
  }

}

