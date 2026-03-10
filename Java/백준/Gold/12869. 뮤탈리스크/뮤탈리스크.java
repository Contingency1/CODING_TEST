import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayDeque;
import java.util.List;
import java.util.ArrayList;

public class Main {

  static boolean[][][] visited = new boolean[61][61][61];

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    final int COUNT = Integer.parseInt(br.readLine());

    StringTokenizer st = new StringTokenizer(br.readLine());

    int[] input = new int[3];

    for (int i = 0; i < COUNT; i++) {
      input[i] = Integer.parseInt(st.nextToken());
    }

    Node start = new Node(input[0], input[1], input[2], 0);

    ArrayDeque<Node> queue = new ArrayDeque<>();

    queue.add(start);

    int answer = Integer.MAX_VALUE;

    while(!queue.isEmpty()) {
      Node cur = queue.pollFirst();

      if(cur.isEnded()) {
        answer = Math.min(answer, cur.count);
        continue;
      }

      List<Node> next = cur.getNextValues();

      for (Node n: next) {
        if(!visited[n.one][n.two][n.three]) {
          visited[n.one][n.two][n.three] = true;
          queue.add(n);
        }
      }

    }

    System.out.print(answer);
  }
}

class Node {
  int one;
  int two;
  int three;

  int count;

  Node(int one, int two, int three, int count) {
    this.one = one;
    this.two = two;
    this.three = three;

    this.count = count;
  }

  public boolean isEnded() {
    return this.one <= 0 && this.two <= 0 && this.three <= 0;
  }

  public List<Node> getNextValues() {
    List<Node> result = new ArrayList<>();

    int firstOne = one - 9 < 0 ? 0 : one - 9;
    int secondOne = one - 3 < 0 ? 0 : one - 3;
    int thirdOne = one - 1 < 0 ? 0 : one - 1;

    int firstTwo = two - 9 < 0 ? 0 : two - 9;
    int secondTwo = two - 3 < 0 ? 0 : two - 3;
    int thirdTwo = two - 1 < 0 ? 0 : two - 1;

    int firstThree = three - 9 < 0 ? 0 : three - 9;
    int secondThree = three - 3 < 0 ? 0 : three - 3;
    int thirdThree = three - 1 < 0 ? 0 : three - 1;

    if(isEnded()) {
      result.add(new Node(0, 0, 0, this.count + 1));
    } else if(this.one == 0 && this.two == 0) {
      result.add(new Node(0, 0, firstThree, this.count + 1));
    } else if(this.two == 0 && this.three == 0) {
      result.add(new Node(firstOne, 0, 0, this.count + 1));
    } else if(this.one == 0 && this.three == 0) {
      result.add(new Node(0, firstTwo, 0, this.count + 1));
    } else if(this.one == 0) {
      result.add(new Node(0, firstTwo, secondThree, this.count + 1));
      result.add(new Node(0, secondTwo, firstThree, this.count + 1));
    } else if(this.two == 0) {
      result.add(new Node(firstOne, 0, secondThree, this.count + 1));
      result.add(new Node(secondOne, 0, firstThree, this.count + 1));
    } else if(this.three == 0) {
      result.add(new Node(secondOne, firstTwo, 0, this.count + 1));
      result.add(new Node(firstOne, secondTwo, 0, this.count + 1));      
    } else {
      result.add(new Node(firstOne, secondTwo, thirdThree, this.count + 1));
      result.add(new Node(firstOne, thirdTwo, secondThree, this.count + 1));
  
      result.add(new Node(secondOne, firstTwo, thirdThree, this.count + 1));
      result.add(new Node(secondOne, thirdTwo, firstThree, this.count + 1));
  
      result.add(new Node(thirdOne, firstTwo, secondThree, this.count + 1));
      result.add(new Node(thirdOne, secondTwo, firstThree, this.count + 1));
    }

    return result;
  }

}
