import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

  static double[][] stars;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    final int STAR_COUNT = Integer.parseInt(br.readLine());

    stars = new double[STAR_COUNT + 1][2];

    for (int i = 1; i < STAR_COUNT + 1; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      stars[i][0] = Double.parseDouble(st.nextToken());
      stars[i][1] = Double.parseDouble(st.nextToken());
    }

    boolean[] visited = new boolean[STAR_COUNT + 1];

    PriorityQueue<Length> pq = new PriorityQueue<>();

    for (int i = 2; i < stars.length; i++) {
      double x = stars[1][0] - stars[i][0];
      double y = stars[1][1] - stars[i][1];

      double length = Math.round(Math.sqrt(x * x + y * y) * 1000) / 1000.0;

      pq.add(new Length(i, length));
    }

    visited[1] = true;

    int count = 0;
    double answer = 0;
    while (!pq.isEmpty()) {
      Length poll = pq.poll();
      int to = poll.to;
      if (visited[to]) {
        continue;
      }
      visited[to] = true;
      double length = poll.length;

      for (int i = 1; i < STAR_COUNT + 1; i++) {
        if (i == to) {
          continue;
        }
        double x = stars[to][0] - stars[i][0];
        double y = stars[to][1] - stars[i][1];

        double bufLength = Math.round(Math.sqrt(x * x + y * y) * 1000) / 1000.0;

        pq.add(new Length(i, bufLength));
      }

      count++;
      answer += length;

      if (count == STAR_COUNT - 1) {
        break;
      }
    }

    System.out.printf("%.2f", answer);
  }

  static class Length implements Comparable<Length> {

    int to;
    double length;

    public Length(int to, double length) {
      this.to = to;
      this.length = length;
    }

    @Override
    public int compareTo(Length o) {
      return this.length - o.length > 0 ? 1 : -1;
    }
  }

}