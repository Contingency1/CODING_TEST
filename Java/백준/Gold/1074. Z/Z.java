import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

  static int number, X, Y, answer;

  private static void makeBase(int x, int y, int amount) {

    if (amount == 2) {
      if (x == X && y == Y) {
        answer = number;
        return;
      } else {
        number++;
      }

      if (x == X && y + 1 == Y) {
        answer = number;
        return;
      } else {
        number++;
      }

      if (x + 1 == X && y == Y) {
        answer = number;
        return;
      } else {
        number++;
      }

      if (x + 1 == X && y + 1 == Y) {
        answer = number;
      } else {
        number++;
      }

    } else {
      int key = amount / 2;

      if (X < x + key && Y < y + key) {
        makeBase(x, y, key);
      } else if (X < x + key && Y >= y + key) {
        number += (int) (Math.pow(key, 2));
        makeBase(x, y + key, key);
      } else if (X >= x + key && Y < y + key) {
        number += (int) (Math.pow(key, 2) * 2);
        makeBase(x + key, y, key);
      } else {
        number += (int) (Math.pow(key, 2) * 3);
        makeBase(x + key, y + key, key);
      }
    }
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    String[] input = br.readLine().split(" ");

    int N = Integer.parseInt(input[0]);
    X = Integer.parseInt(input[1]);
    Y = Integer.parseInt(input[2]);

    int param = (int) Math.pow(2, N);

    number = 0;

    makeBase(0, 0, param);

    sb.append(answer);

    System.out.print(sb);
  }
}