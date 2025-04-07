import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    int K = Integer.parseInt(br.readLine());

    for (int i = 1; i <= K; i++) {

      int[] input = Arrays.stream(br.readLine().split(" "))
          .mapToInt(Integer::parseInt).toArray();

      int nodeCount = input[0];
      int edgeCount = input[1];

      boolean flag = false;

      HashMap<Integer, Integer> node = new HashMap<>();
      HashMap<Integer, ArrayList<Integer>> nearNode = new HashMap<>();

      for (int j = 0; j < edgeCount; j++) {
        int[] row = Arrays.stream(br.readLine().split(" "))
            .mapToInt(Integer::parseInt).toArray();

        node.put(row[0], 0);
        node.put(row[1], 0);

        nearNode.putIfAbsent(row[0], new ArrayList<>());
        nearNode.get(row[0]).add(row[1]);
        nearNode.putIfAbsent(row[1], new ArrayList<>());
        nearNode.get(row[1]).add(row[0]);
      }

      for (int j = 1; j <= nodeCount; j++) {
        int color;

        if (!node.containsKey(j)) {
          continue;
        }

        int curNodeBuffer = node.get(j);
        if (curNodeBuffer != 0) {
          color = curNodeBuffer;
        } else {
          color = 1;
          node.put(j, color);
        }

        ArrayDeque<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{j, color});

        while (!queue.isEmpty()) {
          int[] buffer = queue.poll();
          int curNode = buffer[0];
          int nextColor = buffer[1] == 1 ? 2 : 1;

          ArrayList<Integer> nodes = nearNode.get(curNode);
          if (nodes != null) {
            for (Integer n : nodes) {
            }

            for (int buf : nodes) {
              int key = node.get(buf);
              if (key == 0) {
                queue.add(new int[]{buf, nextColor});
                node.put(buf, nextColor);
              } else if (key != nextColor) {
                flag = true;
                break;
              }
            }
          }

          if (flag) {
            break;
          }
        }

        if (flag) {
          break;
        }
      }

      if (flag) {
        sb.append("NO").append("\n");
      } else {
        sb.append("YES").append("\n");
      }

    }

    System.out.print(sb);
  }
}
