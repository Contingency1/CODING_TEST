import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();
    // 점화식은 나 혼자 유도하지 못했음
    // 하;; 죠낸 어렵네!!!!!!!!!!!!!!!!!!!!!!!!!!!!

    long[] input = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();

    long A = input[0];
    long B = input[1];
    long C = input[2];

    // (A, B, C) = (A, B/2, C) * (A, B/2, C) % C
    // = ( A 의 B/2 제곱 % C) * ( A 의 B/2 제곱 % C) % C
    if (A == 1) {
      System.out.println(A % C);
      return;
    }

    // 최대한 잘게 쪼개서, 쪼갠 값들의 나머지를 서로 곱하고 마지막에 C로 나눈다.

    long result = 1;
    while (B > 0) {
      if (B % 2 == 1) {
        result = (result * A) % C;
      }
      A = (A * A) % C;
      B /= 2;
    }

    sb.append(result);
    System.out.print(sb);
  }

}
