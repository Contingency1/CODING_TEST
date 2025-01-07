import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class Main {

  public static boolean isPrime(int n) {
    if (n == 1) {
      return false;
    }
    if (n == 2) {
      return true;
    }
    if (n % 2 == 0) {
      return false;
    }

    for (int i = 3; i * i <= n; i++) {
      if (n % i == 0) {
        return false;
      }
    }

    return true;
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int M = Integer.parseInt(br.readLine());
    int N = Integer.parseInt(br.readLine());
    List<Integer> base = new ArrayList<>();

    for (int i = M; i <= N; i++) {
      base.add(i);
    }

    for (int i = 0; i < base.size(); i++) {
      if (!isPrime(base.get(i))) {
        base.remove(i);
        i--;
      }
    }

    if (base.size() == 0) {
      bw.write("-1");
    } else {
      bw.write(base.stream().reduce(0, Integer::sum) + "\n");
      bw.write(base.stream().min(Integer::compareTo).get().toString());
    }
    bw.flush();
    br.close();
    bw.close();
  }
}
