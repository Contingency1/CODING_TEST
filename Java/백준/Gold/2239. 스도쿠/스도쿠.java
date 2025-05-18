import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {

  static boolean solved = false;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int[][] baseArray = new int[9][9];

    for (int i = 0; i < 9; i++) {
      int[] buffer = Arrays.stream(br.readLine().split(""))
          .mapToInt(Integer::parseInt).toArray();
      System.arraycopy(buffer, 0, baseArray[i], 0, 9);
    }
    br.close();

    back(baseArray, 0, 0);

  }

  private static void back(int[][] arr, int row, int col) {
    if (solved) {
      return;
    }

    int nextRow = (col == 8) ? row + 1 : row;
    int nextCol = (col == 8) ? 0 : col + 1;
    if (row == 9) {
      for (int[] a : arr) {
        for (int k : a) {
          System.out.print(k);
        }
        System.out.println();
      }
      solved = true;
      return;
    }

    if (arr[row][col] != 0) {
      back(arr, nextRow, nextCol);
      return;
    }

    List<Integer> ableNumbers = getAbleNumbers(arr, row, col);

    for (int number : ableNumbers) {
      arr[row][col] = number;
      back(arr, nextRow, nextCol);
      arr[row][col] = 0;
    }

  }

  private static List<Integer> getAbleNumbers(int[][] arr, int row, int col) {
    List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
    Set<Integer> toRemove = new HashSet<>();

    for (int i = 0; i < 9; i++) {
      if (arr[row][i] != 0) {
        toRemove.add(arr[row][i]);
      }

      if (arr[i][col] != 0) {
        toRemove.add(arr[i][col]);
      }
    }

    int boxRow = (row / 3) * 3;
    int boxCol = (col / 3) * 3;

    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        int key = arr[boxRow + i][boxCol + j];
        if (key != 0) {
          toRemove.add(key);
        }
      }
    }

    for (int i = 0; i < list.size(); i++) {
      if (toRemove.contains(list.get(i))) {
        list.remove(i);
        i--;
      }
    }
    return list;
  }


}
