import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

class Main {

  static List<List<Integer>> child;
  static int[] time;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int WORKER_COUNT = Integer.parseInt(br.readLine());

    child = new ArrayList<>();

    for (int i = 0; i < WORKER_COUNT; i++) {
      child.add(new ArrayList<>());
    }

    StringTokenizer st = new StringTokenizer(br.readLine());
    st.nextToken();

    int[] parent = new int[WORKER_COUNT];
    parent[0] = -1;

    for (int i = 1; i < WORKER_COUNT; i++) {
      int boss = Integer.parseInt(st.nextToken());
      parent[i] = boss;
      child.get(boss).add(i);
    }

    time = new int[WORKER_COUNT];
    Arrays.fill(time, -1);

    dfs(0);

    System.out.print(time[0]);
  }

  static void dfs(int curNode) {
    if (child.get(curNode).isEmpty()) {
      time[curNode] = 0;
      return;
    }

    List<Integer> times = new ArrayList<>();

    for (Integer i : child.get(curNode)) {
      dfs(i);
      times.add(time[i]);
    }

    times.sort(Comparator.reverseOrder());

    int max = 0;
    for (int i = 0; i < times.size(); i++) {
      int finishTime = times.get(i) + i + 1;
      max = Math.max(max, finishTime);
    }

    time[curNode] = max;
  }


}

