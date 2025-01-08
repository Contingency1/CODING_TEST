import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int[] arr = Arrays.stream(br.readLine().split(" "))
        .mapToInt(Integer::parseInt)
        .toArray();
    int xToW = arr[2] - arr[0];
    int yToH = arr[3] - arr[1];
    int answerX, answerY;

    if (arr[0] >= xToW) {
      answerX = xToW;
    } else {
      answerX = arr[0];
    }

    if (arr[1] >= yToH) {
      answerY = yToH;
    } else {
      answerY = arr[1];
    }

    if (answerX >= answerY) {
      bw.write(String.valueOf(answerY));
    } else {
      bw.write(String.valueOf(answerX));
    }

    bw.flush();
    br.close();
    bw.close();
  }
}
