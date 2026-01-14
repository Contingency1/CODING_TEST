import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayDeque;
import java.util.Arrays;


public class Main {
  static int answer = 0;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());

    int COUNT = Integer.parseInt(st.nextToken());
    int NUMBER_COUNT = Integer.parseInt(st.nextToken());

    ArrayDeque<Integer> queue = new ArrayDeque<>();

    for (int i = 1; i <= COUNT; i++) {
      queue.add(i);
    }

    int[] arr = new int[NUMBER_COUNT];
    st = new StringTokenizer(br.readLine());

    for (int i = 0; i < NUMBER_COUNT; i++) {
      arr[i] = Integer.parseInt(st.nextToken());
    }


    for (int cur: arr) {
      if (cur == queue.peekFirst()) {
        queue.pollFirst();
        continue;
      }
      queue = cal(queue, cur);
    }

    System.out.print(answer);
  }

  public static ArrayDeque<Integer> cal(ArrayDeque<Integer> queue, int target) {
    ArrayDeque<Integer> buff1 = new ArrayDeque<>(queue);
    ArrayDeque<Integer> buff2 = new ArrayDeque<>(queue);

    int first = 0;

    int response1 = 0;
    while (first != target) {
      response1++;
      int cur = buff1.pollFirst();
      buff1.addLast(cur);

      first = buff1.peekFirst();
    }

    buff1.pollFirst();
    
    first = 0;

    int response2 = 0;
    while (first != target) {
      response2++;
      int cur = buff2.pollLast();
      buff2.addFirst(cur);

      first = cur;
    }

    buff2.pollFirst();

    if(response2 <= response1) {
      answer += response2;
      return buff2;
    }

    answer += response1;
    return buff1;
  }

}