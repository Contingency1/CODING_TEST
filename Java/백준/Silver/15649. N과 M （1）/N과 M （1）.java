import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {


  private static void back(ArrayList<Integer> arr, int N, int M, StringBuilder sb) {

    if (arr.size() == M) {
      for (int i = 0; i < M; i++) {
        sb.append(arr.get(i)).append(" ");
      }
      sb.append("\n");
      return;
    }
    for (int i = 1; i <= N; i++) {
      if (!arr.contains(i)) {
        arr.add(i);
        back(arr, N, M, sb);
        arr.remove(arr.size() - 1);
      }

    }
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    String[] input = br.readLine().split(" ");
    int N = Integer.parseInt(input[0]);
    int M = Integer.parseInt(input[1]);

    ArrayList<Integer> list = new ArrayList<>();
    back(list, N, M, sb);

    System.out.print(sb);
  }
}