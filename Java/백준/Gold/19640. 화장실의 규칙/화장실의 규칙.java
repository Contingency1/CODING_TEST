import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.PriorityQueue;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    final int PEOPLE_COUNT = Integer.parseInt(st.nextToken());
    final int LINE_COUNT = Integer.parseInt(st.nextToken());
    final int PREV_COUNT = Integer.parseInt(st.nextToken());

    List<List<Waiter>> list = new ArrayList<>();

    for (int i = 0; i <= LINE_COUNT; i++) {
      list.add(new LinkedList<>());
    }

    for (int i = 1; i <= PEOPLE_COUNT; i++) {
      st = new StringTokenizer(br.readLine());

      int lineNumber = i % LINE_COUNT == 0 ? LINE_COUNT : i % LINE_COUNT;
      List<Waiter> cur = list.get(lineNumber);

      int workDays = Integer.parseInt(st.nextToken());
      int hasty = Integer.parseInt(st.nextToken());

      if(PREV_COUNT + 1 == i) {
        cur.add(new Waiter(lineNumber, workDays, hasty, true));
      } else {
        cur.add(new Waiter(lineNumber, workDays, hasty, false));
      }
    }

    PriorityQueue<Waiter> queue = new PriorityQueue<>();

    for (int i = 1; i <= LINE_COUNT; i++) {
      if(!list.get(i).isEmpty()) {
        queue.add(list.get(i).remove(0));
      }
    }

    int answer = 0;
    Waiter cur = queue.poll();
       
    if(!list.get(cur.lineNumber).isEmpty()) {
      queue.add(list.get(cur.lineNumber).remove(0));
    }

    while(!cur.isDeca) {
      cur = queue.poll();
      answer++;

      int lineNumber = cur.lineNumber;
      if(!list.get(lineNumber).isEmpty()) {
        queue.add(list.get(lineNumber).remove(0));
      }

    }

    System.out.print(answer);
  }
}

class Waiter implements Comparable<Waiter>{
  final int lineNumber;
  final int workDays;
  final int hasty;
  final boolean isDeca;

  Waiter(int lineNumber, int workDays, int hasty, boolean isDeca) {
    this.lineNumber = lineNumber;
    this.workDays = workDays;
    this.hasty = hasty;
    this.isDeca = isDeca;
  }

  @Override
  public int compareTo(Waiter o) {
    if (this.workDays == o.workDays) {
      if(this.hasty == o.hasty) {
        return this.lineNumber - o.lineNumber;
      }

      return o.hasty - this.hasty;
    } 

    return o.workDays - this.workDays;
  }
}
