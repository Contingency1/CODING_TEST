import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());
    int number = Integer.parseInt(st.nextToken());
    final int PICK = Integer.parseInt(st.nextToken());

    int answer = 0;
    while (true) {
      String binaryString = Integer.toBinaryString(number);
      int zeroCount = 0;

      for (int i = 0; i < binaryString.length(); i++) {
        if (binaryString.charAt(i) == '1') {
          zeroCount++;
        }
      }

      if (zeroCount <= PICK) {
        System.out.print(answer);
        break;
      }

      number++;
      answer++;
    }

  }


}