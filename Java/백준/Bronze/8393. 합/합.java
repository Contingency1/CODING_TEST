import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    int input = Integer.parseInt(br.readLine());

    int answer = 0;
    for (int i = 0; i <= input; i++) {
      answer = answer + i;
    }
    sb.append(answer);
    System.out.print(sb);
  }


}