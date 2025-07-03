import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    final int PEOPLE_COUNT = Integer.parseInt(st.nextToken());
    final int WANTED_PEOPLE_COUNT = Integer.parseInt(st.nextToken());
    final int POWER_SPEED_SUM = Integer.parseInt(st.nextToken());

    List<Set<Integer>> dp = new ArrayList<>();

    for (int i = 0; i < WANTED_PEOPLE_COUNT + 1; i++) {
      dp.add(new HashSet<>());
    }

    dp.get(0).add(0);

    for (int i = 1; i < PEOPLE_COUNT + 1; i++) {
      st = new StringTokenizer(br.readLine());
      int power = Integer.parseInt(st.nextToken());
      int speed = Integer.parseInt(st.nextToken());

      for (int j = WANTED_PEOPLE_COUNT; j >= 1; j--) {
        for (int sum : dp.get(j - 1)) {
          dp.get(j).add(sum + power);
        }
      }
    }

    long maxScore = 0;
    for (Integer i : dp.get(WANTED_PEOPLE_COUNT)) {
      long score = i * ((long) WANTED_PEOPLE_COUNT * POWER_SPEED_SUM - i);
      if (score > maxScore) {
        maxScore = score;
      }
    }
    System.out.print(maxScore);


  }
}
