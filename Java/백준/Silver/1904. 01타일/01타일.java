import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    int input = Integer.parseInt(br.readLine());
    int[] data = new int[1_000_001];

    data[1] = 1;
    data[2] = 2;

    for (int i = 3; i <= input; i++) {
      data[i] = (data[i - 2] + data[i - 1]) % 15746;
    }

    sb.append(data[input]);

    System.out.print(sb);
  }
}
