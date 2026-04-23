  import java.io.BufferedReader;
  import java.io.IOException;
  import java.io.InputStreamReader;
  import java.util.StringTokenizer;
  import java.util.Map;
  import java.util.HashMap;
  import java.util.List;
  import java.util.ArrayList;
  import java.util.ArrayDeque;

  public class Main {
    public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

      final int COUNT = Integer.parseInt(br.readLine());
      StringTokenizer st;

      Map<Integer, List<Integer>> map = new HashMap<>();
      int[] visited = new int[COUNT + 1];

      for (int i = 1; i <= COUNT; i++) {
        map.put(i, new ArrayList<>());
      }

      for (int i = 1; i <= COUNT; i++) {
        st = new StringTokenizer(br.readLine());
        int amount = Integer.parseInt(st.nextToken());

        for (int j = 0; j < amount; j++) {
          int hate = Integer.parseInt(st.nextToken());
          map.get(i).add(hate);
          map.get(hate).add(i);
        }

      }

      for (int i = 1; i <= COUNT; i++) {
        if(visited[i] == 0) {
          bfs(i, visited, map);
        }

      }

      List<Integer> blue = new ArrayList<>();
      List<Integer> white = new ArrayList<>();

      for (int i = 1; i <= COUNT; i++) {
        if (visited[i] == 1) {
          blue.add(i);
        } 
        else {
          white.add(i);
        }
      }

      System.out.println(blue.size());
      for (int i = 0; i < blue.size(); i++) {
        System.out.print(blue.get(i) + (i == blue.size() - 1 ? "" : " "));
      }
      System.out.println();

      System.out.println(white.size());
      for (int i = 0; i < white.size(); i++) {
        System.out.print(white.get(i) + (i == white.size() - 1 ? "" : " "));
      }
    }

    static void bfs(int start, int[] visited, Map<Integer, List<Integer>> map) {
      ArrayDeque<Integer> queue = new ArrayDeque<>();
      queue.addLast(start);
      visited[start] = 1;


      while(!queue.isEmpty()) {
        int cur = queue.pollFirst();

        for(int m: map.get(cur)) {
          if(visited[m] == 0) {
            visited[m] = visited[cur] == 1 ? 2: 1;
            queue.addLast(m);
          }
        }
      }
    } 
  }