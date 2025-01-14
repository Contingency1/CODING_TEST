import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    String[] input = br.readLine().split(" ");
    int N = Integer.parseInt(input[0]);
    int M = Integer.parseInt(input[1]);

    HashSet<String> ListN = new HashSet<>(N);
    HashSet<String> ListM = new HashSet<>(M);

    for (String row : br.readLine().split(" ")) {
      ListN.add(row);
    }

    for (String row : br.readLine().split(" ")) {
      ListM.add(row);
    }
    HashSet<String> BufferN = new HashSet<>(N);

    BufferN.addAll(ListN);

    ListN.removeAll(ListM);
    ListM.removeAll(BufferN);

    sb.append(ListN.size() + ListM.size());

    System.out.print(sb);
  }
}