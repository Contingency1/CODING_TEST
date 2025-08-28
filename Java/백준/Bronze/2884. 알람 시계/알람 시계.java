import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());

    int sum = Integer.parseInt(st.nextToken()) * 60 + Integer.parseInt(st.nextToken());

    if (sum - 45 < 0) {
      sum = 24 * 60 + (sum - 45);
    } else {
      sum -= 45;
    }
    
    System.out.print(sum / 60 + " " + sum % 60);
  }
}

