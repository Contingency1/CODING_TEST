import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

  static int[] parent;

  static int find(int k) {
    if (k == parent[k]) {
      return k;
    }
    parent[k] = find(parent[k]);
    return parent[k];
  }

  static void union(int a, int b) {
    int foundA = find(a);
    int foundB = find(b);

    // 찾은 루트가 같지 않다면
    if (foundA != foundB) {
      // foundB의 부모는 foundA다
      parent[foundB] = foundA;
    }
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    final int CITY_COUNT = Integer.parseInt(br.readLine());
    final int PLAN_COUNT = Integer.parseInt(br.readLine());

    initParent(CITY_COUNT);
    List<List<Integer>> graph = initGraph(CITY_COUNT, br);
    int[] plan = initPlan(PLAN_COUNT, br);
    br.close();

    for (int i = 1; i < graph.size(); i++) {
      List<Integer> integers = graph.get(i);
      for (Integer integer : integers) {
        union(i, integer);
      }
    }

    int standard = find(plan[0]);
    for (int i = 1; i < plan.length; i++) {
      if (standard != find(plan[i])) {
        System.out.print("NO");
        return;
      }
    }
    System.out.print("YES");

  }

  private static int[] initPlan(int PLAN_COUNT, BufferedReader br) throws IOException {
    int[] plan = new int[PLAN_COUNT];

    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 0; i < PLAN_COUNT; i++) {
      plan[i] = Integer.parseInt(st.nextToken());
    }
    return plan;
  }

  private static List<List<Integer>> initGraph(int CITY_COUNT, BufferedReader br)
      throws IOException {
    List<List<Integer>> graph = new ArrayList<>();

    for (int i = 0; i <= CITY_COUNT; i++) {
      graph.add(new ArrayList<>());
    }

    for (int i = 1; i <= CITY_COUNT; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());

      for (int j = 1; j <= CITY_COUNT; j++) {
        int city = Integer.parseInt(st.nextToken());
        if (city == 1) {
          graph.get(i).add(j);
        }
      }
    }
    return graph;
  }

  private static void initParent(int CITY_COUNT) {
    parent = new int[CITY_COUNT + 1];

    for (int i = 1; i <= CITY_COUNT; i++) {
      parent[i] = i;
    }
  }


}