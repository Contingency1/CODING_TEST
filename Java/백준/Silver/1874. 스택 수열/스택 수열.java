import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class Main {
  static StringBuilder sb = new StringBuilder();

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int count = Integer.parseInt(br.readLine());

    ArrayDeque<Integer> queue = new ArrayDeque<>();

    int[] input = new int[count + 1];
    boolean[] used = new boolean[count + 1];

    for (int i = 1; i <= count; i++) {
      input[i] = Integer.parseInt(br.readLine());
    }
    
    int nextNumber = 1;

    for (int i = 1; i <= count; i++) {
      int cur = input[i];

      if(used[cur]) {
        System.out.print("NO");
        return;
      }

      if(nextNumber <= cur) {
        for (int number = nextNumber; number <= count; number++) {
          queue.add(number);
          plus();

          if (queue.peekLast() == cur) {
            queue.pollLast();
            minus();
            used[cur] = true;
            nextNumber = number + 1;
            break;
          }

          nextNumber = number + 1;
        }
      } else {
        boolean flag = false;

        while(!queue.isEmpty()) {
          if(queue.peekLast() == cur) {
            queue.pollLast();
            minus();
            used[cur] = true;
            flag = true;
            break;
          } else {
            queue.pollLast();
            minus();
          }
        }

        if(flag) {
          continue;
        }

        System.out.print("NO");
        return;
      }
    }

    System.out.print(sb);
  }

  static void plus() {
    // System.out.println("PLUS==========");
    sb.append("+").append("\n");
  }

  static void minus() {
    // System.out.println("MINUS==========");
    sb.append("-").append("\n");
  }
}