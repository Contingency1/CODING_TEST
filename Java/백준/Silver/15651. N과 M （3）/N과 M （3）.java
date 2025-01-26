import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

  private static void back(int N, int M, ArrayList<Integer> array, StringBuilder sb) {

    if (array.size() == M) {
      for (int i = 0; i < M; i++) {
        sb.append(array.get(i)).append(" ");
      }
      sb.append("\n");
      return;
    }

    for (int i = 1; i <= N; i++) {
      array.add(i);
      back(N, M, array, sb);
      array.remove(array.size() - 1);
    }
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    String[] input = br.readLine().split(" ");
    int N = Integer.parseInt(input[0]);
    int M = Integer.parseInt(input[1]);

    ArrayList<Integer> array = new ArrayList<>();

    back(N, M, array, sb);
    
    System.out.print(sb);
  }
}