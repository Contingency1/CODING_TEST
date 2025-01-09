import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();
    long input = Integer.parseInt(br.readLine());

    sb.append(input * input * input);

    System.out.println(sb);
    System.out.print(3);
  }
}