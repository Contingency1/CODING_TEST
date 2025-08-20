import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    final int K = Integer.parseInt(br.readLine());

    boolean answer = false;

    if (K % 4 == 0 && (K % 100 != 0 || K % 400 == 0)) {
      answer = true;
    }

    System.out.print(answer ? 1 : 0);
  }

}

