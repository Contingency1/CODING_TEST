import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    char[] input = br.readLine().toCharArray();

    ArrayDeque<Character> queue = new ArrayDeque<>();

    for (int i = 0; i < input.length; i++) {
      if(!queue.isEmpty() && queue.peekLast() == '(') {
        if(input[i] == ')') {
          queue.pollLast();
        } else {
          queue.add('(');
        }

        continue;
      }

      queue.add(input[i]);
    }

    System.out.print(queue.size());
  }

}