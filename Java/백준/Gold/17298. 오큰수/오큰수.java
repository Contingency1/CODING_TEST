import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

  // 단조 스택 문제
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    int N = Integer.parseInt(br.readLine());
    int[] input = Arrays.stream(br.readLine().split(" "))
        .mapToInt(Integer::parseInt).toArray();
    int[] answer = new int[N];

    ArrayList<Integer> stack = new ArrayList<>();

    for (int i = N - 1; i >= 0; i--) {
      int lastValue = stack.isEmpty() ? 0 : stack.get(stack.size() - 1);

      if (stack.isEmpty()) {
        answer[i] = -1;
        stack.add(input[i]);
      } else if (lastValue > input[i]) {
        answer[i] = lastValue;
        stack.add(input[i]);
      } else if (lastValue <= input[i]) {
        stack.remove(stack.size() - 1);
        i++;
      }

    }

    for (int row : answer) {
      sb.append(row).append(" ");
    }

    System.out.print(sb);
  }
}
