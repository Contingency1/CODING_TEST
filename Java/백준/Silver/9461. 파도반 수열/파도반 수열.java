import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    int input = Integer.parseInt(br.readLine());

    long[] arr = new long[102];

    arr[1] = 1;
    arr[2] = 1;
    arr[3] = 1;

    arr[4] = 2;
    arr[5] = 2;
    arr[6] = 3;

    arr[7] = 4;

    for (int i = 8; i <= 101; i++) {
      arr[i] = arr[i - 5] + arr[i - 1];
    }

    for (int i = 1; i <= input; i++) {
      int k = Integer.parseInt(br.readLine());
      sb.append(arr[k]).append("\n");
    }

    System.out.print(sb);
  }
}
