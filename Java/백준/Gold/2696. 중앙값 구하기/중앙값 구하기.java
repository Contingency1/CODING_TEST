import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    final int TEST_COUNT = Integer.parseInt(br.readLine());

    for (int i = 0; i < TEST_COUNT; i++) {
      int arrCount = Integer.parseInt(br.readLine());

      int[] arr = new int[arrCount + 1];
      // 그냥 한줄에 쭉 주지 이게 뭐하는거냐ㅡㅡ
      int iterCount = arrCount / 10 + 1;
      int index = 1;
      for (int j = 0; j < iterCount; j++) {
        StringTokenizer st = new StringTokenizer(br.readLine());
        while (st.hasMoreTokens()) {
          arr[index++] = Integer.parseInt(st.nextToken());
        }
      }

      PriorityQueue<Integer> smaller = new PriorityQueue<>(Comparator.reverseOrder());
      PriorityQueue<Integer> bigger = new PriorityQueue<>();
      List<Integer> answerList = new ArrayList<>();

      sb.append(arrCount / 2 + 1).append("\n");
      int mid = arr[1];
      answerList.add(mid);

      for (int j = 2; j < arr.length; j++) {
        if (mid < arr[j]) {
          bigger.add(arr[j]);
        } else {
          smaller.add(arr[j]);
        }

        if (j % 2 == 1) {
          if (bigger.size() > smaller.size()) {
            smaller.add(mid);
            mid = bigger.poll();
          } else if (bigger.size() < smaller.size()) {
            bigger.add(mid);
            mid = smaller.poll();
          }

          answerList.add(mid);
        }
      }

      for (int k = 0; k < answerList.size(); k++) {
        sb.append(answerList.get(k)).append(" ");
        if (k % 9 == 0 && k != 0) {
          sb.deleteCharAt(sb.length() - 1).append("\n");
        }

        if (k == answerList.size() - 1) {
          sb.deleteCharAt(sb.length() - 1);
        }
      }
      sb.append("\n");
    }

    sb.deleteCharAt(sb.length() - 1);

    System.out.print(sb);
  }
}