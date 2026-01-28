import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int input = Integer.parseInt(br.readLine());
      
    if(input % 5 == 0) {
      System.out.print(input / 5);
    } else {
      System.out.print(input / 5 + 1);
    }
  }
}