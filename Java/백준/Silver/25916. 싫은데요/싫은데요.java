import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());

    final int HOLE_COUNT = Integer.parseInt(st.nextToken());
    final int HAMSTER = Integer.parseInt(st.nextToken());

    st = new StringTokenizer(br.readLine());
    
    int[] input = new int[HOLE_COUNT];

    for (int i = 0; i < HOLE_COUNT; i++) {
      input[i] = Integer.parseInt(st.nextToken());
    }
    
    int L = 0;
    int R = 0;

    int buffer = 0;
    int answer = 0;

    boolean flag = true;
    while(L < HOLE_COUNT) {
      flag = false;

      if(R < HOLE_COUNT) {
        buffer += input[R++];
        flag = true;
      }

      if(buffer <= HAMSTER) {
        answer = Math.max(answer, buffer);

        if (!flag) {
          break;
        }
      } else {
        while(buffer > HAMSTER) {
          buffer -= input[L++];
        }

        answer = Math.max(answer, buffer);
      }
    }

    System.out.print(answer);
  }
}