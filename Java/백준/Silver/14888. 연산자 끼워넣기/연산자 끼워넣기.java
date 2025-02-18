import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;


public class Main {


  private static void method(ArrayList<Integer> numbers,
      int[] operators,
      ArrayList<Integer> answer, int sum) {

//    System.out.println(sum);
//    System.out.println("numbers: " + numbers);
//    System.out.println("operators: " + Arrays.toString(operators));

    if (numbers.isEmpty()) {
      answer.add(sum);
    }

    for (int i = 0; i < 4; i++) {
      ArrayList<Integer> newNumbers = new ArrayList<>(numbers);

      int[] newOperators = new int[operators.length];

      System.arraycopy(operators, 0, newOperators, 0, operators.length);

      if (operators[i] > 0) {
        newOperators[i]--;
        switch (i) {
          case 0:
            int sum1 = sum + newNumbers.remove(0);
            method(newNumbers, newOperators, answer, sum1);
            break;
          case 1:
            int sum2 = sum - newNumbers.remove(0);
            method(newNumbers, newOperators, answer, sum2);
            break;
          case 2:
            int sum3 = sum * newNumbers.remove(0);
            method(newNumbers, newOperators, answer, sum3);
            break;
          case 3:
            int sum4 = sum / newNumbers.remove(0);
            method(newNumbers, newOperators, answer, sum4);
        }
      }

    }

  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    int N = Integer.parseInt(br.readLine());

    int[] numbers = Arrays.stream(br.readLine().split(" "))
        .mapToInt(Integer::parseInt).toArray();

    int[] operators = Arrays.stream(br.readLine().split(" "))
        .mapToInt(Integer::parseInt).toArray();

    ArrayList<Integer> numbersList = new ArrayList<>();

    for (int number : numbers) {
      numbersList.add(number);
    }

    ArrayList<Integer> answersList = new ArrayList<>();

    for (int i = 0; i < 4; i++) {

      ArrayList<Integer> newNumbers = new ArrayList<>(numbersList);

      int[] newOperators = new int[operators.length];

      System.arraycopy(operators, 0, newOperators, 0, operators.length);

      if (operators[i] > 0) {
        newOperators[i]--;
        switch (i) {
          case 0:
            int sum = newNumbers.remove(0) + newNumbers.remove(0);
            method(newNumbers, newOperators, answersList, sum);
            break;
          case 1:
            int sum2 = newNumbers.remove(0) - newNumbers.remove(0);
            method(newNumbers, newOperators, answersList, sum2);
            break;
          case 2:
            int sum3 = newNumbers.remove(0) * newNumbers.remove(0);
            method(newNumbers, newOperators, answersList, sum3);
            break;
          case 3:
            int sum4 = newNumbers.remove(0) / newNumbers.remove(0);
            method(newNumbers, newOperators, answersList, sum4);
        }
      }

    }

    int MAX = Integer.MIN_VALUE;
    int MIN = Integer.MAX_VALUE;

    for (int i = 0; i < answersList.size(); i++) {

      MAX = Math.max(MAX, answersList.get(i));
      MIN = Math.min(MIN, answersList.get(i));

    }

    sb.append(MAX).append("\n").append(MIN);

    System.out.print(sb);
  }

}
