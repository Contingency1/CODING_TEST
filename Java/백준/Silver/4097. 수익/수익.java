import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    while(true) {
      int count = Integer.parseInt(br.readLine());

      if(count == 0) {
        break;
      }

      int[] arr = new int[count + 1];

      for (int i = 1; i <= count; i++) {
        arr[i] = Integer.parseInt(br.readLine());
      }

      long answer = Long.MIN_VALUE;
      long buffer = arr[1];

      for (int i = 2; i <= count; i++) {
        buffer = Math.max(arr[i], arr[i] + buffer);
      
        if(answer < buffer) {
          answer = buffer;
        }
      }

      System.out.println(answer);
    }          
  }
}