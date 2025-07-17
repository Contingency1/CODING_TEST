import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    final int COUNT = Integer.parseInt(br.readLine());

    char[] chars1 = br.readLine().toCharArray();
    char[] chars2 = br.readLine().toCharArray();

    boolean[] firstInput = new boolean[COUNT];
    boolean[] secondInput = new boolean[COUNT];
    boolean[] target = new boolean[COUNT];

    for (int i = 0; i < COUNT; i++) {
      firstInput[i] = chars1[i] == '1';
      target[i] = chars2[i] == '1';
    }

    System.arraycopy(firstInput, 0, secondInput, 0, COUNT);

    toggle(firstInput, 0);
    int answer1 = makeAnswer(firstInput, target, 1);

    int answer2 = makeAnswer(secondInput, target, 0);

    if (answer1 == Integer.MAX_VALUE && answer2 == Integer.MAX_VALUE) {
      System.out.println(-1);
    } else {
      System.out.println(Math.min(answer1, answer2));
    }
  }

  private static void toggle(boolean[] arr, int idx) {
    arr[idx] = !arr[idx];
    if (idx + 1 < arr.length) {
      arr[idx + 1] = !arr[idx + 1];
    }
  }

  private static int makeAnswer(boolean[] input, boolean[] target, int answer) {
    for (int i = 0; i < input.length - 1; i++) {
      if (input[i] != target[i]) {
        toggle(input, i + 1);
        answer++;
      }
    }

    if (input[input.length - 1] != target[input.length - 1]) {
      return Integer.MAX_VALUE;
    }

    return answer;
  }
}