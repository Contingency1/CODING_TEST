import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    final int count = Integer.parseInt(br.readLine());

    StringBuilder sb = new StringBuilder();

    for (int i = 0; i < count; i++) {
      String input = br.readLine();

      if(input.charAt(0) >= '0' && input.charAt(0) <= '9') {
        int target = Integer.parseInt(input);

        while (target > 0) {
          for (RomanNumerals r: RomanNumerals.values()) {
            int curNumber = r.number;

            if (curNumber <= target) {
              sb.append(r.str);
              target -= curNumber;
              break;
            }
          }
        }
      } else {
        int result = 0;

        for (int idx = 0; idx < input.length();) {
          for (RomanNumerals r: RomanNumerals.values()) {
            int length = r.str.length();

            String sub;

            if(idx != input.length() - 1) {
              sub = input.substring(idx, idx + length);
            } else if (length == 2) {
              continue;
            } else {
              sub = input.substring(idx, idx + length);
            }

            if(sub.equals(r.str)) {
              result += r.number;
              idx += length;
              break;
            }
          }
        }

        sb.append(result);
      }

      sb.append("\n");
    }

    sb.deleteCharAt(sb.length() - 1);
    System.out.print(sb);
  }
}

enum RomanNumerals {
  M(1000, "M"),
  CM(900, "CM"),
  D(500, "D"),
  CD(400, "CD"),
  C(100, "C"), 
  XC(90, "XC"), 
  L(50, "L"),
  XL(40, "XL"),
  X(10, "X"), 
  IX(9, "IX"), 
  V(5, "V"),
  IV(4, "IV"),
  I(1, "I");

  int number;
  String str;

  RomanNumerals(int input, String str) {
    this.number = input;
    this.str = str;
  }
}
