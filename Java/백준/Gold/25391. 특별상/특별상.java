import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());

    final int STUDENT_COUNT = Integer.parseInt(st.nextToken());
    final int SPECIAL_COUNT = Integer.parseInt(st.nextToken());
    final int PRIZE_COUNT = Integer.parseInt(st.nextToken());

    List<Student1> list1 = new ArrayList<>();
    List<Student2> list2 = new ArrayList<>();

    boolean[] used = new boolean[STUDENT_COUNT];

    for (int i = 0; i < STUDENT_COUNT; i++) {
      st = new StringTokenizer(br.readLine());
      int host = Integer.parseInt(st.nextToken());
      int judge = Integer.parseInt(st.nextToken());

      list1.add(new Student1(i, host, judge));
      list2.add(new Student2(i, host, judge));
    }

    list1.sort(null);

    long answer = 0;

    for (int i = 0; i < PRIZE_COUNT; i++) {
      Student1 student = list1.get(i);
      answer += student.host;
      used[student.id] = true;
    }

    list2.sort(null);

    int idx = 0;
    for (int i = 0; i < SPECIAL_COUNT;) {
      Student2 student = list2.get(idx++);
      if(used[student.id]) {
        continue;
      }

      answer += student.host;
      i++;
    }

    System.out.print(answer);
  }
}

class Student1 implements Comparable<Student1> {
  int id;
  int host;
  int judge;

  Student1(int id, int host, int judge) {
    this.id = id;
    this.host = host;
    this.judge = judge;
  }

  @Override
  public int compareTo(Student1 o) {
    return o.judge - this.judge;
  }
}

class Student2 implements Comparable<Student2> {
  int id;
  int host;
  int judge;

  Student2(int id, int host, int judge) {
    this.id = id;
    this.host = host;
    this.judge = judge;
  }

  @Override
  public int compareTo(Student2 o) {
    return o.host - this.host;
  }
}