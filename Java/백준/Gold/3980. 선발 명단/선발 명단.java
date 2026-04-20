import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int TEST_COUNT = Integer.parseInt(br.readLine());

    for (int i = 0; i < TEST_COUNT; i++) {
      int[][] arr = new int[11][11];
      boolean[] visited = new boolean[11];
      List<List<Integer>> list = new ArrayList<>();

      for (int j = 0; j < 11; j++) {
        StringTokenizer st = new StringTokenizer(br.readLine());
        list.add(new ArrayList<>());

        List<Integer> cur = list.get(j);
        for (int k = 0; k < 11; k++) {
          arr[j][k] = Integer.parseInt(st.nextToken());
          if (arr[j][k] != 0) {
            cur.add(k);
          }
        }
      }

      int answer = back(arr, list, 0, 0, visited);

      System.out.println(answer);
    }
  }

  static int back(int[][] arr, List<List<Integer>> list, int cur, int acc, boolean[] visited) {
    if (cur > 10) {
      return acc;
    }

    List<Integer> idx = list.get(cur);
    int answer = 0;

    for (Integer i : idx) {
      if (!visited[i]) {
        visited[i] = true;
        answer = Math.max(back(arr, list, cur + 1, acc + arr[cur][i], visited), answer);
        visited[i] = false;
      }
    }

    return answer;
  }
}

