import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {


  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    StringTokenizer st = new StringTokenizer(br.readLine());
    br.close();
    long answer = 0;

    for (int i = 0; i < 3; i++) {
      answer += Long.parseLong(st.nextToken());
    }

    sb.append(answer);

    System.out.print(sb);
  }


}
