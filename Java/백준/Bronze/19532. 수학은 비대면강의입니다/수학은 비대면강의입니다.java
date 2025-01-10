import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    int[] input = Arrays.stream(br.readLine().split(" "))
        .mapToInt(Integer::parseInt)
        .toArray();

    int answerX = 0;
    int answerY = 0;

    int a = input[0];
    int b = input[1];
    int c = input[2];
    int d = input[3];
    int e = input[4];
    int f = input[5];

    answerX = (e * c - b * f) / (a * e - b * d);
    answerY = e == 0 ? (c - a * answerX) / b : (f - d * answerX) / e;

    sb.append(answerX).append(" ").append(answerY);

    System.out.print(sb);
  }
}