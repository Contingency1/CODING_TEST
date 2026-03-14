import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    final int COUNT = Integer.parseInt(br.readLine());

    StringTokenizer st = new StringTokenizer(br.readLine());

    int[] input = new int[COUNT];
    for (int i = 0; i < COUNT; i++) {
      input[i] = Integer.parseInt(st.nextToken());
    }

    Arrays.sort(input);

    int answer = 2;

    if (COUNT == 1) {
      System.out.print(1);
      return;
    }

    int L = input[0];
    int R = input[1];

    for (int i = 2; i < COUNT; i++) {
      if (L < input[i]) {
        L = input[i];
        answer++;
        continue;
      }

      if (R < input[i]) {
        R = input[i];
        answer++;
      }
    }

    System.out.print(answer);
  }

}