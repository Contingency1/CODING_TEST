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

    int[] numbers = Arrays.stream(br.readLine().split(" "))
        .mapToInt(Integer::parseInt).toArray();

    int slideCount = input[0] - input[1] + 1;

    int[] result = new int[slideCount];

    for(int i = 0; i < slideCount; i++) {
      int answer = 0;

      for(int k = i; k < i + input[1]; k++) {
        answer += numbers[k];
      }

      result[i] = answer;
    }

    int end = Integer.MIN_VALUE;

    for(int row: result) {
      if(row > end) {
        end = row;
      }
    }

    sb.append(end);

    System.out.print(sb);
  }
}