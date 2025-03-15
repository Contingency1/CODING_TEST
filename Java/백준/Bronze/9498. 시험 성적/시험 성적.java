import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {


  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    int input = Integer.parseInt(br.readLine());

    if (input >= 90) {
      sb.append("A");
    } else if (input >= 80) {
      sb.append("B");
    } else if (input >= 70) {
      sb.append("C");
    } else if (input >= 60) {
      sb.append("D");
    } else {
      sb.append("F");
    }
    System.out.print(sb);
  }
}
