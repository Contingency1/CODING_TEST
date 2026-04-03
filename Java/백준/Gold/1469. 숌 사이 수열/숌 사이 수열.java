import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    final int COUNT = Integer.parseInt(br.readLine());

    int[] arr = new int[COUNT];

    StringTokenizer st = new StringTokenizer(br.readLine());

    for (int i = 0; i < COUNT; i++) {
      arr[i] = Integer.parseInt(st.nextToken());
    }

    Arrays.sort(arr);

    int[] answer = new int[COUNT * 2];
    Arrays.fill(answer, -1);

    boolean[] used = new boolean[COUNT];


    int[] key = back(answer, arr, used);

    if(key == null) {
      System.out.print(-1);
      return;
    }

    StringBuilder sb = new StringBuilder();

    for (int a :answer) {
      sb.append(a).append(" ");
    }

    sb.deleteCharAt(sb.length() - 1);
    System.out.print(sb);
  }

  static int[] back(int[] answer, int[] arr, boolean[] used) {
    for (int i = 0; i < answer.length; i++) {
      if(answer[i] != -1) {
        continue;
      }

      for (int j = 0; j < arr.length; j++) {
        if(used[j]) {
          continue;
        }
        
        int secondIdx = i + arr[j] + 1;
        if(secondIdx >= answer.length || answer[secondIdx] != -1) {
          continue;
        }
        
        used[j] = true;
        answer[i] = arr[j];
        answer[secondIdx] = arr[j];

        if(back(answer, arr, used) != null) {
          return answer;
        }

        if(check(answer)) {
          return answer;
        }

        answer[i] = -1;
        answer[secondIdx] = -1;
        used[j] = false;
      }
    }

    return null;
  }
   
  static boolean check(int[] target) {
    for (int t: target) {
      if(t == -1) {
        return false;
      }
    }
    return true;
  } 

}

