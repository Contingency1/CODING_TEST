import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    StringTokenizer st = new StringTokenizer(br.readLine());

    final int STUDENT_COUNT = Integer.parseInt(st.nextToken());
    final int COMPARE_COUNT = Integer.parseInt(st.nextToken());

    int[][] compare = new int[COMPARE_COUNT][2];

    for (int i = 0; i < COMPARE_COUNT; i++) {
      st = new StringTokenizer(br.readLine());

      compare[i][0] = Integer.parseInt(st.nextToken());
      compare[i][1] = Integer.parseInt(st.nextToken());
    }

    br.close();

    List<List<Integer>> node = new ArrayList<>();
    int[] inDegreeCount = new int[STUDENT_COUNT + 1];

    for (int i = 0; i <= STUDENT_COUNT; i++) {
      node.add(new ArrayList<>());
    }

    boolean[] isUsed = new boolean[STUDENT_COUNT + 1];

    for (int[] compareItem : compare) {
      isUsed[compareItem[0]] = true;
      isUsed[compareItem[1]] = true;
      node.get(compareItem[0]).add(compareItem[1]);
      inDegreeCount[compareItem[1]]++;
    }

    ArrayDeque<Integer> queue = new ArrayDeque<>();
    for (int i = 1; i <= STUDENT_COUNT; i++) {
      // 진입차수가 0이고
      if (inDegreeCount[i] == 0) {
        // 입력된 적이 없다면
        if (!isUsed[i]) {
          sb.append(i).append(" ");
          continue;
        }
        // 입력된 적이 있으면
        queue.offer(i);
      }
    }

    while (!queue.isEmpty()) {
      int cur = queue.poll();
      sb.append(cur).append(" ");

      for (int next : node.get(cur)) {
        inDegreeCount[next]--;
        if (inDegreeCount[next] == 0) {
          queue.offer(next);
        }
      }
    }

    System.out.print(sb);
  }


}
