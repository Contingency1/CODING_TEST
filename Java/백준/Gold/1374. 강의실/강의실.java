import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int LECTURE_COUNT = Integer.parseInt(br.readLine());

    PriorityQueue<Lecture> pq1 = new PriorityQueue<>();
    for (int i = 0; i < LECTURE_COUNT; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int lectureID = Integer.parseInt(st.nextToken());
      int startAt = Integer.parseInt(st.nextToken());
      int endAt = Integer.parseInt(st.nextToken());

      pq1.add(new Lecture(lectureID, startAt, endAt));
    }

    PriorityQueue<Integer> endTime = new PriorityQueue<>();

    while (!pq1.isEmpty()) {
      Lecture curLecture = pq1.poll();

      if (!endTime.isEmpty() && endTime.peek() <= curLecture.startAt) {
        endTime.poll();
      }

      endTime.add(curLecture.endAt);
    }

    System.out.print(endTime.size());
  }

  static class Lecture implements Comparable<Lecture> {

    int id;
    int startAt;
    int endAt;

    public Lecture(int id, int startAt, int endAt) {
      this.id = id;
      this.startAt = startAt;
      this.endAt = endAt;
    }

    @Override
    public int compareTo(Lecture o) {
      return startAt - o.startAt;
    }
  }

}

