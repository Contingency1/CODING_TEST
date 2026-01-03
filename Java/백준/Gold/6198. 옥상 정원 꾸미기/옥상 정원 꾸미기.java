import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    final int COUNT = Integer.parseInt(br.readLine());

    int[] arr = new int[COUNT];

    for (int i = 0; i < COUNT; i++) {
      arr[i] = Integer.parseInt(br.readLine());
    }

    ArrayDeque<Integer> queue = new ArrayDeque<>();

    long answer = 0;

    for (int i = 0; i < COUNT; i++) {
      if(queue.isEmpty()) {
        queue.add(arr[i]);
        continue;
      }

      while (!queue.isEmpty() && queue.peekLast() <= arr[i]) {
        queue.pollLast();
      }
      
      answer += queue.size();
      queue.add(arr[i]);
    }

    System.out.print(answer);
  }
}