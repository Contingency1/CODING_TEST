import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    final int PEOPLE_COUNT = Integer.parseInt(st.nextToken());
    final int RELATION_COUNT = Integer.parseInt(st.nextToken());

    Map<Integer, Set<Integer>> map = new HashMap<>();
    for (int i = 1; i <= PEOPLE_COUNT; i++) {
      map.put(i, new HashSet<>());
      map.get(i).add(i);
    }

    for (int i = 0; i < RELATION_COUNT; i++) {
      st = new StringTokenizer(br.readLine());

      int one = Integer.parseInt(st.nextToken());
      int two = Integer.parseInt(st.nextToken());

      map.get(one).add(two);
      map.get(two).add(one);
    }

    StringBuilder sb = new StringBuilder();

    int answer = 0;

    while (true) {
      int change = 0;
      Map<Integer, Set<Integer>> newMap = new HashMap<>();

      for (Map.Entry<Integer, Set<Integer>> entry : map.entrySet()) {
        newMap.put(entry.getKey(), new HashSet<>(entry.getValue()));
      }

      for (int i = 1; i <= PEOPLE_COUNT; i++) {
        for (Integer child : map.get(i)) {
          for (Integer childChild : map.get(child)) {
            if (!newMap.get(i).contains(childChild)) {
              newMap.get(i).add(childChild);
              newMap.get(childChild).add(i);
              change++;
            }
          }
        }
      }

      if (change == 0) {
        break;
      }

      sb.append(change).append("\n");

      map = newMap;
      answer++;
    }

    System.out.println(answer);
    System.out.print(sb);
  }
}