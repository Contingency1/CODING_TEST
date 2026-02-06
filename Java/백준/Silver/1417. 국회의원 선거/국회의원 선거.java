import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int count = Integer.parseInt(br.readLine());

    int cur = Integer.parseInt(br.readLine());

    PriorityQueue<Number> queue = new PriorityQueue<>();

    for (int i = 1; i < count; i++) {
      queue.add(new Number(Integer.parseInt(br.readLine())));
    }

    int answer = 0;

    if(count == 1) {
      System.out.print(0);
      return;
    }

    while(cur <= queue.peek().number) {
      int polled = queue.poll().number;

      answer++;
      cur++;

      queue.add(new Number(polled - 1));
    }

    System.out.print(answer);
  }
  static class Number implements Comparable<Number> {
    int number;

    @Override
    public int compareTo(Number o) {
      return o.number - this.number;
    }

    Number(int number) {
      this.number = number;
    }
  }
}