import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.TreeMap;

class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    final int TEST_CASE = Integer.parseInt(br.readLine());

    int maxTime = 0;

    Map<Integer, PriorityQueue<Integer>> map = new TreeMap<>();
    for (int i = 0; i < TEST_CASE; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());

      int time = Integer.parseInt(st.nextToken());
      int score = Integer.parseInt(st.nextToken());

      maxTime = Math.max(maxTime, time);

      if (!map.containsKey(time)) {
        map.put(time, new PriorityQueue<>(Comparator.reverseOrder()));
      }

      map.get(time).add(score);
    }

    int answer = 0;
    for (int i = maxTime; i >= 1; i--) {

      int selectKey = 0;
      int maxValue = 0;

      for (int j = maxTime; j >= i; j--) {
        if (map.containsKey(j)) {
          int peek = map.get(j).peek();
          if (peek > maxValue) {
            maxValue = peek;
            selectKey = j;
          }
        }
      }
      if (selectKey == 0) {
        continue;
      }

      answer += maxValue;

      map.get(selectKey).poll();

      if (map.get(selectKey).isEmpty()) {
        map.remove(selectKey);
      }
    }

    System.out.print(answer);


  }


}