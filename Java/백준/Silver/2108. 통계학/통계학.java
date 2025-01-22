import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.TreeSet;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    int N = Integer.parseInt(br.readLine());

    int[] arr = new int[N];
    HashMap<Integer, Integer> map = new HashMap<>();

    for (int i = 0; i < N; i++) {
      arr[i] = Integer.parseInt(br.readLine());
    }

    int sum = 0;
    int MAX = -4000;
    int MIN = 4000;

    TreeSet<Integer> set = new TreeSet<>();
    int freq = 0;

    for (int i = 0; i < N; i++) {
      sum += arr[i];
      MAX = Math.max(arr[i], MAX);
      MIN = Math.min(arr[i], MIN);

      if (map.containsKey(arr[i])) {
        map.put(arr[i], map.get(arr[i]) + 1);
      } else {
        map.put(arr[i], 1);
      }

      if (freq < map.get(arr[i])) {
        set.clear();
        set.add(arr[i]);
        freq = map.get(arr[i]);
      } else if (freq == map.get(arr[i])) {
        set.add(arr[i]);
      }

    }

    int[] arr2 = new int[set.size()];
    int i = 0;
    for (int num : set) {
      arr2[i++] = num;
    }

//    for (int s : set) {
//      sb.append("set: ").append(s).append("\n");
//    }
//
//    for (int num : arr2) {
//      sb.append("num").append(num).append("\n");
//    }

    arr = Arrays.stream(arr).sorted().toArray();
//    산술평균 : N개의 수들의 합을 N으로 나눈 값
    sb.append(Math.round((float) sum / N)).append("\n");
//    중앙값 : N개의 수들을 증가하는 순서로 나열했을 경우 그 중앙에 위치하는 값
    sb.append(arr[N / 2]).append("\n");
//    최빈값 : N개의 수들 중 가장 많이 나타나는 값
    sb.append(arr2.length > 1 ? arr2[1] : arr2[0]).append("\n");
//    범위 : N개의 수들 중 최댓값과 최솟값의 차이
    sb.append(MAX - MIN);

    System.out.print(sb);
  }
}