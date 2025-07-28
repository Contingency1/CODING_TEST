import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {


  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    br.readLine();

    StringTokenizer st = new StringTokenizer(br.readLine());

    StringBuilder answer = new StringBuilder();

    while (st.hasMoreTokens()) {
      String s = st.nextToken();

      answer.append(s).append("DORO").append(" ");
    }

    answer.deleteCharAt(answer.length() - 1);

    System.out.print(answer);
  }


}