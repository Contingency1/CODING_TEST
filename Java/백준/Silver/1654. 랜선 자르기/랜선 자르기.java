import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    int[] input = Arrays.stream(br.readLine().split(" "))
        .mapToInt(Integer::parseInt).toArray();

    int cases = input[0];
    int target = input[1];

    int[] arr = new int[cases];

    for (int i = 0; i < cases; i++) {
      arr[i] = Integer.parseInt(br.readLine());
    }

    Arrays.sort(arr);

    long sum = 0;
    for (int i : arr) {
      sum += i;
    }

    if (cases == 1) {
      System.out.println(arr[0] / target);
      return;
    }

    long maxValue = sum / target;

    if (maxValue == 1) {
      System.out.println(1);
      return;
    }

    long answer = method(arr, maxValue, target);

    sb.append(answer);

    System.out.print(sb);
  }

  private static long method(int[] arr, long maxValue, int target) {
    long L = 1;
    long R = maxValue;
    long MID = (L + R + 1) / 2;

    while (true) {

      long buffer = 0;

      if (L == MID) {

        for (int i : arr) {
          buffer += i / R;
        }

        if (buffer >= target) { // maxValue가 정답범위 안에 듬. L 이동
          return R;
        } else { // maxValue가 정답범위에 들지 않음, R 이동
          return MID;
        }

      }

      for (int i : arr) {
        buffer += i / MID;
      }

      if (buffer >= target) { // maxValue가 정답범위 안에 듬. L 이동
        L = MID;
      } else { // maxValue가 정답범위에 들지 않음, R 이동
        R = MID - 1;
      }

      MID = (L + R) / 2;
    }


  }


}
