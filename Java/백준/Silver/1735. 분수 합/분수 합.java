import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

  private static int calculation(int a, int b) {

    while (true) {
      int r = a % b;

      if (r == 0) {
        return b;
      } else {
        a = b;
        b = r;
      }
    }
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    int[] arr1 = Arrays.stream(br.readLine().split(" "))
        .mapToInt(Integer::parseInt).toArray();

    int[] arr2 = Arrays.stream(br.readLine().split(" "))
        .mapToInt(Integer::parseInt).toArray();

    int key = arr1[1] > arr2[1] ? arr1[1] * arr2[1] / calculation(arr1[1], arr2[1]) :
        arr1[1] * arr2[1] / calculation(arr2[1], arr1[1]);

    int arr1key = key / arr1[1];
    int arr2key = key / arr2[1];

    arr1[0] *= arr1key;
    arr2[0] *= arr2key;

    int[] answer = {arr1[0] + arr2[0], key};
    int answerkey = answer[0] > answer[1] ? calculation(answer[0], answer[1])
        : calculation(answer[1], answer[0]);

    if (answerkey != 1) {
      answer[0] /= answerkey;
      answer[1] /= answerkey;
    }

    sb.append(answer[0]).append(" ").append(answer[1]);

    System.out.print(sb);
  }
}