import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {


  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    final int NUMBER_COUNT = Integer.parseInt(br.readLine());

    int[] numbers = new int[NUMBER_COUNT];

    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 0; i < NUMBER_COUNT; i++) {
      numbers[i] = Integer.parseInt(st.nextToken());
    }

    Number firstMin = new Number(100, 51);
    Number secondMin = new Number(100, 51);

    for (int i = NUMBER_COUNT - 1; i >= 0; i--) {

      if (firstMin.value > numbers[i]) {
        firstMin = new Number(i, numbers[i]);
      }

      if (i != 0) {
        if (secondMin.value > numbers[i]) {
          secondMin = new Number(i, numbers[i]);
        }
      }
    }

    int money = Integer.parseInt(br.readLine());

    if (secondMin.value > money) {
      System.out.print(0);
      return;
    }

    int digitCount = (money - secondMin.value) / firstMin.value;

    List<Integer> list = new ArrayList<>();
    list.add(secondMin.number);
    money -= secondMin.value;

    for (int i = 0; i < digitCount; i++) {
      list.add(firstMin.number);
      money -= firstMin.value;
    }

    for (int i = 0; i < list.size(); i++) {
      int curValue = numbers[list.get(i)];

      for (int j = numbers.length - 1; j >= 0; j--) {
        int newValue = numbers[j];
        // 이 놈은 무조건 양수임
        int diff = newValue - curValue;
        if (money - diff >= 0) {
          list.set(i, j);
          money -= diff;
          break;
        }
      }
    }

    for (Integer i : list) {
      System.out.print(i);
    }
  }

  static class Number {

    int number;
    int value;

    public Number(int number, int value) {
      this.number = number;
      this.value = value;
    }
  }


}

