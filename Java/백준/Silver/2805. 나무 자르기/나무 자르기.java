import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

    int N = input[0];
    int M = input[1];

    int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

    Arrays.sort(arr);

    int L = 0;
    int R = arr[N - 1];

    int MID = (L + R) / 2;

    while (true) {
      if (L == MID) {
        long buffer = 0;

        for (int i : arr) {
          if (i - R > 0) {
            buffer += i - R;
          }
        }

        if (buffer >= M) {
          sb.append(R);
        } else {
          sb.append(L);
        }
        break;
      }

      long buffer = 0;

      for (int i : arr) {
        if (i - MID > 0) {
          buffer += i - MID;
        }
      }

      if (buffer >= M) { // 정상범위 내 -> L 옮김
        L = MID;
      } else { // 정상범위 밖 -> R 옮김
        R = MID - 1;
      }

      MID = (L + R) / 2;
    }

    System.out.print(sb);
  }


}
