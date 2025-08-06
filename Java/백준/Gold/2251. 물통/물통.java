import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

class Main {

  static boolean[][][] visited = new boolean[201][201][201];
  static int maxOne, maxTwo, maxThree;

  static void change(ArrayDeque<Bottle> queue, int one, int two, int three, int from, int to) {

    int[] bottle = {one, two, three};

    int buffer = to == 0 ? maxOne : to == 1 ? maxTwo : maxThree;

    int plus = Math.min(bottle[from], buffer - bottle[to]);
    bottle[from] -= plus;
    bottle[to] += plus;

    if (!visited[bottle[0]][bottle[1]][bottle[2]]) {
      visited[bottle[0]][bottle[1]][bottle[2]] = true;
      queue.add(new Bottle(bottle[0], bottle[1], bottle[2]));
    }

  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());
    maxOne = Integer.parseInt(st.nextToken());
    maxTwo = Integer.parseInt(st.nextToken());
    maxThree = Integer.parseInt(st.nextToken());

    ArrayDeque<Bottle> queue = new ArrayDeque<>();
    queue.add(new Bottle(0, 0, maxThree));
    visited[0][0][maxThree] = true;

    Set<Integer> answer = new HashSet<>();
    while (!queue.isEmpty()) {
      Bottle cur = queue.poll();
      int first = cur.first;
      int second = cur.second;
      int third = cur.third;

      if (first == 0) {
        answer.add(third);
      }

      change(queue, first, second, third, 0, 1);
      change(queue, first, second, third, 0, 2);
      change(queue, first, second, third, 1, 2);
      change(queue, first, second, third, 1, 0);
      change(queue, first, second, third, 2, 1);
      change(queue, first, second, third, 2, 0);
    }

    List<Integer> answers = new ArrayList<>(answer);

    answers.sort(Integer::compareTo);
    StringBuilder sb = new StringBuilder();
    for (Integer i : answers) {
      sb.append(i).append(" ");
    }

    sb.deleteCharAt(sb.length() - 1);

    System.out.print(sb);

  }

  static class Bottle {

    int first;
    int second;
    int third;

    public Bottle(int first, int second, int third) {
      this.first = first;
      this.second = second;
      this.third = third;
    }
  }


}