import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class Main {
  static int[] arr;
  static int answer = 0;
  static int N;
  static boolean[] visited;
  static List<Integer> list;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    N = Integer.parseInt(br.readLine());

    arr = new int[N];
    visited = new boolean[N];

    StringTokenizer st = new StringTokenizer(br.readLine());

    for (int i = 0; i < N; i++) {
      arr[i] = Integer.parseInt(st.nextToken());

      if(arr[i] > 50) {
        System.out.print(0);
        return;
      }
    }
    
    list = new ArrayList<>();

    eom(0);

    System.out.print(answer);
  }

  static void eom(int count) {
    if (count == N) {
      int[] sum = new int[N + 1];

      sum[0] = 0;

      boolean[] exist = new boolean[101];

      for (int i = 0; i < list.size(); i++) {
        sum[i + 1] = sum[i] + list.get(i);
        exist[sum[i + 1]] = true;
      }

      int buffer = 0;

      for (int i = 1; i < N + 1; i++) {
        int cur = sum[i];

        if (cur + 50 > 100) {
          break;
        }

        if(exist[cur + 50]) {
          buffer++;
        }
      }
      answer = Math.max(answer, buffer);

      return;
    }

    for (int i = 0; i < N; i++) {
      if(visited[i]) {
        continue;
      }

      visited[i] = true;
      list.add(arr[i]);
      
      eom(count + 1);
      
      visited[i] = false;
      list.remove(list.size() - 1);
    }

  }
}