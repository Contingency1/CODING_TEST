import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int count = Integer.parseInt(st.nextToken()); 
    int skip = Integer.parseInt(st.nextToken());

    Node cur = new Node(null, 1);

    Node start = cur;

    for (int i = 2; i <= count; i++) {
      Node next =  new Node(null, i);

      cur.next = next;
      cur = next;
    }

    cur.next = start;

    List<Integer> list = new ArrayList<>();

    for (int k = 0; k < count; k++) {

      if(skip == 1) {
          list.add(start.value);
          start = start.next;
          continue;
      }

      for (int i = 1; i < skip; i++) {
        if(i == skip - 1) {
          list.add(start.next.value);
          
          start.next = start.next.next;
          start = start.next;
          break;
        } 
        
        start = start.next;
      }
    }

    StringBuilder sb = new StringBuilder();

    sb.append("<");

    for (int i = 0; i < list.size(); i++) {
      sb.append(list.get(i));
      if (i < list.size() - 1) {
        sb.append(", ");
      }
    }

    sb.append(">");

    System.out.print(sb);
  }
  
  static class Node {
    Node next;
    int value;

    Node(Node node, int value) {
      this.next = node;
      this.value = value;
  
    }
  }
}