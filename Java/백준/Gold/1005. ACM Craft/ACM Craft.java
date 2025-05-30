import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    final int TEST_CASE = Integer.parseInt(br.readLine());

    for (int i = 0; i < TEST_CASE; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      // 건물의 개수
      int numBuildings = Integer.parseInt(st.nextToken());
      // 건물 순서 개수
      int numSteps = Integer.parseInt(st.nextToken());

      // 각 건물의 비용
      int[] buildingCost = new int[numBuildings + 1];

      // 각 건물의 비용에 대한 값 추가
      st = new StringTokenizer(br.readLine());
      for (int j = 1; j <= numBuildings; j++) {
        buildingCost[j] = Integer.parseInt(st.nextToken());
      }

      // 순서에 따른 그래프 초기화
      List<List<Integer>> graph = new ArrayList<>();
      for (int j = 0; j <= numBuildings; j++) {
        graph.add(new ArrayList<>());
      }

      HashSet<Integer> findRoot = new HashSet<>();
      HashSet<Integer> notUsed = new HashSet<>();

      for (int j = 1; j <= numBuildings; j++) {
        findRoot.add(j);
        notUsed.add(j);
      }

      // 그래프에 값 추가
      for (int j = 0; j < numSteps; j++) {
        st = new StringTokenizer(br.readLine());

        int from = Integer.parseInt(st.nextToken());
        int to = Integer.parseInt(st.nextToken());

        notUsed.remove(from);
        notUsed.remove(to);

        findRoot.remove(to);
        graph.get(from).add(to);
      }

      // 목표 건물 번호
      int finalTarget = Integer.parseInt(br.readLine());

      ArrayDeque<NodeJS> queue = new ArrayDeque<>();
      boolean flag = false;

      for (int key : findRoot) {
        if (key == finalTarget) {
          sb.append(buildingCost[key]).append("\n");
          flag = true;
          break;
        }
      }

      if (flag) {
        continue;
      }

//      System.out.println(findRoot);
      for (int key : notUsed) {
        findRoot.remove(key);
      }
//      System.out.println(findRoot);
      // 여기까지 root 가 되는놈 전부다 넣음

      for (int key : findRoot) {
        queue.add(new NodeJS(0, key));
      }

      int[] dpCost = new int[numBuildings + 1];
      while (!queue.isEmpty()) {
        NodeJS node = queue.poll();
        int curFrom = node.from;
        int curTo = node.to;

        if (curTo == finalTarget) {
          dpCost[finalTarget] = Math.max(dpCost[finalTarget],
              dpCost[curFrom] + buildingCost[curTo]);
          continue;
        }

        dpCost[curTo] = Math.max(dpCost[curTo], dpCost[curFrom] + buildingCost[curTo]);

        for (int next : graph.get(curTo)) {
          if (dpCost[next] < dpCost[curTo] + buildingCost[next]) {
            dpCost[next] = dpCost[curTo] + buildingCost[next];
            queue.add(new NodeJS(curTo, next));
          }
        }
      }

      sb.append(dpCost[finalTarget]).append("\n");
    }

    br.close();
    System.out.print(sb);
  }

  static class NodeJS {

    int from;
    int to;

    public NodeJS(int from, int to) {
      this.from = from;
      this.to = to;
    }
  }
}
