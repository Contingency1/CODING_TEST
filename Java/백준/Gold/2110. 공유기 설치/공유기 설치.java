import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {


  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    // 묶음으로 생각하지 말고 거리로 생각해라.

    int[] input = Arrays.stream(br.readLine().split(" "))
        .mapToInt(Integer::parseInt).toArray();

    int N = input[0];
    int C = input[1];

    int[] arr = new int[N];

    for (int i = 0; i < N; i++) {
      arr[i] = Integer.parseInt(br.readLine());
    }

    Arrays.sort(arr);

    int L = 1;
    int R = arr[arr.length - 1] - arr[0];

    int answer = 0;

    while (L <= R) {
      int MID = (L + R) / 2;
      int target = C - 1;

      if (isPossible(arr, MID, target)) {
        answer = MID;
        L = MID + 1;
      } else {
        R = MID - 1;
      }
    }

    sb.append(answer);

    System.out.print(sb);
  }

  static boolean isPossible(int[] arr, int MID, int target) {

    int buffer = arr[0] + MID;

    for (int i = 1; i < arr.length; i++) {
      if (buffer <= arr[i]) {
        buffer = arr[i] + MID;
        target--;
      }
    }

    return target <= 0;
  }

}
