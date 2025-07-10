import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {


  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    final int SENSOR_COUNT = Integer.parseInt(br.readLine());
    final int BASE_STATION_COUNT = Integer.parseInt(br.readLine());

    StringTokenizer st = new StringTokenizer(br.readLine());

    Set<Integer> sensorSet = new TreeSet<>();
    for (int i = 0; i < SENSOR_COUNT; i++) {
      int integer = Integer.parseInt(st.nextToken());
      sensorSet.add(integer);
    }
    int[] sensors = sensorSet.stream().mapToInt(x -> x).toArray();

    PriorityQueue<Integer> between = new PriorityQueue<>(Comparator.reverseOrder());
    for (int i = 0; i < sensors.length - 1; i++) {
      int buffer = Math.abs(sensors[i] - sensors[i + 1]);
      between.add(buffer);
    }
    for (int i = 0; i < BASE_STATION_COUNT - 1; i++) {
      between.poll();
    }

    int answer = 0;
    while (!between.isEmpty()) {
      answer += between.poll();
    }
    System.out.print(answer);
  }
}

