import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    int cityCount = Integer.parseInt(br.readLine());

    int[] distance = new int[cityCount - 1];
    int[] oilPrice = new int[cityCount];

    distance = Arrays.stream(br.readLine().split(" "))
        .mapToInt(Integer::parseInt).toArray();

    oilPrice = Arrays.stream(br.readLine().split(" "))
        .mapToInt(Integer::parseInt).toArray();

    long currentDistance = 0;
    int currentCity = 0;

    ArrayList<Integer> stop = new ArrayList<>();
    int min = oilPrice[0];
    stop.add(0);

    for (int i = 0; i < cityCount - 1; i++) {
      if (min > oilPrice[i]) {
        min = oilPrice[i];
        stop.add(i);
      }
    }

//    sb.append(Arrays.toString(stop.toArray()));

    int stopLength = stop.size();

    long sum = 0;

    for (int i = 0; i < stopLength; i++) {

      if (i + 1 <= stopLength - 1) {

        for (int j = stop.get(i); j <= stop.get(i + 1) - 1; j++) {
          sum += (long) oilPrice[stop.get(i)] * distance[j];
//          sb.append(sum).append(": 1\n");
        }
      } else {
        for (int j = stop.get(stop.size() - 1); j < distance.length; j++) {
          sum += (long) oilPrice[stop.get(stop.size() - 1)] * distance[j];
//          sb.append(sum).append(": 2\n");
        }
      }

    }

    sb.append(sum);

    System.out.print(sb);
  }
}
