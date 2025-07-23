import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    final int COUNT = Integer.parseInt(br.readLine());

    List<Work> works = new ArrayList<>();

    for (int i = 0; i < COUNT; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int workTime = Integer.parseInt(st.nextToken());
      int expiredAt = Integer.parseInt(st.nextToken());

      works.add(new Work(expiredAt, workTime));
    }

    works.sort(null);

    int curDay = works.get(0).expiredAt;

    for (Work work : works) {
      if (curDay > work.expiredAt) {
        curDay = work.expiredAt;
      }
      curDay -= work.workTime;
    }
    System.out.print(curDay);
  }

  static class Work implements Comparable<Work> {

    int expiredAt;
    int workTime;

    public Work(int expiredAt, int workTime) {
      this.expiredAt = expiredAt;
      this.workTime = workTime;
    }


    @Override
    public int compareTo(Work o) {
      return o.expiredAt - expiredAt;
    }
  }

}