import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    final int COUNT = Integer.parseInt(br.readLine());

    NodeJS root = new NodeJS();

    for (int i = 0; i < COUNT; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());

      NodeJS curNode = root;
      int amount = Integer.parseInt(st.nextToken());
      for (int j = 0; j < amount; j++) {
        String str = st.nextToken();

        if (curNode.children.containsKey(str)) {
          curNode.end = false;
          curNode = curNode.children.get(str);
        } else {
          curNode.end = false;
          NodeJS newNode = new NodeJS();
          curNode.children.put(str, newNode);
          curNode = newNode;
          curNode.end = true;
        }
      }
    }

    for (String str : root.children.keySet()) {
      printAnswer(0, root.children.get(str), str);
    }

  }

  private static void printAnswer(int depth, NodeJS node, String input) {

    StringBuilder sb = new StringBuilder();

    for (int i = 0; i < depth; i++) {
      sb.append("--");
    }
    sb.append(input);
    System.out.println(sb);

    if (node.end) {
      return;
    }

    for (String str : node.children.keySet()) {
      printAnswer(depth + 1, node.children.get(str), str);
    }

  }

  static class NodeJS {

    Map<String, NodeJS> children;
    boolean end;

    public NodeJS() {
      this.children = new TreeMap<>();
      this.end = false;
    }
  }

}

