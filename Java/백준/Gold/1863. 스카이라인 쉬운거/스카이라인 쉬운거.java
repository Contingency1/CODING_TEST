import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;
import java.util.ArrayDeque;
import java.util.Arrays;

class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int COUNT = Integer.parseInt(br.readLine());

    StringTokenizer st;

    List<Coordinate> list = new ArrayList<>();

    for (int i = 0; i < COUNT; i++) {
      st = new StringTokenizer(br.readLine());

      int x = Integer.parseInt(st.nextToken());
      int y = Integer.parseInt(st.nextToken());

      list.add(new Coordinate(x, y, i));
    }

    int answer = 0;

    ArrayDeque<Coordinate> queue = new ArrayDeque<>();

    boolean[] deleted = new boolean[COUNT];

    if(COUNT == 1 && list.get(0).y == 0) {
      System.out.print(0);
      return;
    }

    if (list.get(0).y == 0) {
      deleted[0] = true;
      queue.add(new Coordinate(list.get(1).x, list.get(1).y, 1));
    } else {
      queue.add(new Coordinate(1, list.get(0).y, 0));
    }


    for (int i = 1; i < list.size(); i++) {
      Coordinate cur = list.get(i);

      if (deleted[i]) {
        continue;
      }

      int x = cur.x;
      int y = cur.y;
      
      while (!queue.isEmpty() && queue.peekLast().y > y) {
        queue.pollLast();
        answer++;
      }

      if (!queue.isEmpty() && queue.peekLast().y == y) {
        continue;
      }

      if (y > 0) {
        queue.addLast(new Coordinate(x, y, cur.idx));
      }
    }

    answer += queue.size();

    System.out.print(answer);
  }

  static class Coordinate {
    int x;
    int y;
    int idx;

    @Override
    public String toString() {
      return "\n x: " + x + "\n y: " + y + "\n idx: " + idx;
    }


    Coordinate (int x, int y, int idx) {
      this.x = x;
      this.y = y;
      this.idx = idx;
    }
  }


}

