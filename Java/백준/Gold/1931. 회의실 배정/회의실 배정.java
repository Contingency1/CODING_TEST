import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    int N = Integer.parseInt(br.readLine());

    long[][] input = new long[N][2];

    for (int i = 0; i < N; i++) {
      input[i] = Arrays.stream(br.readLine().split(" "))
          .mapToLong(Long::parseLong).toArray();
    }

    Arrays.sort(input, Comparator
        .comparingLong((long[] a) -> a[1])
        .thenComparingLong(a -> a[0]));

//    for (long[] row : input) {
//      sb.append(Arrays.toString(row)).append("\n");
//    }

    int sum = 1;

    ArrayList<long[]> arrList = new ArrayList<>(Arrays.asList(input));

    long standard = arrList.get(0)[1];

    for (int i = 1; i < N; i++) {
      if (arrList.get(i)[0] >= standard) {
        sum++;
        standard = arrList.get(i)[1];
//        sb.append(arrList.get(i)[1]).append("\n");
      }
    }

    sb.append(sum);
    System.out.print(sb);
  }
}
