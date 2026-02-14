import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int count = Integer.parseInt(br.readLine());

    List<Node> list = new ArrayList<>();

    for (int i = 0; i < count; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());

      String name = st.nextToken();
      int korean = Integer.parseInt(st.nextToken());
      int english = Integer.parseInt(st.nextToken());
      int math = Integer.parseInt(st.nextToken());

      list.add(new Node(name, korean, english, math));
    }

    list.sort(null);

    for (Node l: list) {
      System.out.println(l.toString());
    }

  }

  static class Node implements Comparable<Node>{
    int korean;
    int english;
    int math;
    String name;

    public Node(String name, int korean,  int english, int math) {
      this.name = name;
      this.korean = korean;
      this.english = english;
      this.math = math;
    }

    @Override
    public int compareTo(Node o) {
      if(korean == o.korean) {
        if (english == o.english ) {
          if(math == o.math) {
            return name.compareTo(o.name);
          }
          return o.math - math;
        }

        return english - o.english;
      }

      return o.korean - korean;
    }

    @Override
    public String toString(){
      return this.name;
    }
  }
}