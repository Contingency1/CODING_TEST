import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    int N = Integer.parseInt(br.readLine());

    int[] input = Arrays.stream(br.readLine().split(" "))
        .mapToInt(Integer::parseInt).toArray();

    input = Arrays.stream(input).sorted().toArray();

    int buffer = 0;

    int answer = 0;
    for (int i = 0; i < N; i++) {
      buffer += input[i];
      answer += buffer;
    }

    sb.append(answer);

    System.out.print(sb);
  }
}
