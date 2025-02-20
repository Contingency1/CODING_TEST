import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

  static int MIN = Integer.MAX_VALUE;
  static int N;
  static int[][] ints;
  static ArrayList<Integer[]> original;
  static ArrayList<Integer[]> target;

  private static void method(
      ArrayList<Integer> start, ArrayList<Integer> end, int select) {

    if (select == N / 2) {
      original.add(start.toArray(new Integer[0]));
      target.add(end.toArray(new Integer[0]));
    } else {
      for (int i = 0; i < start.size(); i++) {
        ArrayList<Integer> start2 = new ArrayList<>(start);

        if (end.isEmpty() || end.get(end.size() - 1) < start2.get(i)) {
          end.add(start2.remove(i));
          method(start2, end, ++select);
          end.remove(end.size() - 1);
          select--;
        }
      }
    }
  }

  private static int sum(Integer[] arr) {

    int sum = 0;

    for (int i = 0; i < N - (N / 2); i++) {
      for (int j = i + 1; j < arr.length; j++) {
        sum += ints[arr[i]][arr[j]] + ints[arr[j]][arr[i]];
      }
    }

    return sum;
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    N = Integer.parseInt(br.readLine());
    ints = new int[N + 1][N + 1];

    for (int i = 1; i <= N; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());

      for (int j = 1; j <= N; j++) {
        ints[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    ArrayList<Integer> start = new ArrayList<>();

    for (int i = 1; i <= N; i++) {
      start.add(i);
    }

    ArrayList<Integer> end = new ArrayList<>();

    target = new ArrayList<>();
    original = new ArrayList<>();

    method(start, end, 0);

    for (int i = 0; i < target.size(); i++) {
      MIN = Math.min(MIN, Math.abs(sum(original.get(i)) - sum(target.get(i))));
    }

    sb.append(MIN);

    System.out.print(sb);
  }
}
