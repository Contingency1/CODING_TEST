import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    long[] input = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();

    long start = input[0];
    long end = input[1];

    ArrayDeque<long[]> queue = new ArrayDeque<>();

    long answer = -1;
    queue.add(new long[]{start, 0});

    while (!queue.isEmpty()) {
      long[] polled = queue.poll();
      long number = polled[0];
      long count = polled[1];

      if (number == end) {
        answer = count;
        break;
      }

      long multi = number * 2;
      long plus = number * 10 + 1;

      if (multi <= end) {
        queue.add(new long[]{multi, count + 1});
      }

      if (plus <= end) {
        queue.add(new long[]{plus, count + 1});
      }

    }

    sb.append(answer == -1 ? -1 : answer + 1);

    System.out.print(sb);
  }

}
