import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;
import java.util.ArrayDeque;
import java.util.Set;
import java.util.HashSet;

public class Main {
  static int COUNT, REVERSE;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    COUNT = Integer.parseInt(st.nextToken());
    REVERSE = Integer.parseInt(st.nextToken());

    int limit = COUNT - REVERSE;

    int[] arr = new int[COUNT];

    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < COUNT; i++) {
      arr[i] = Integer.parseInt(st.nextToken());
    }

    Set<Integer> set = new HashSet<>();
    ArrayDeque<Dot> queue = new ArrayDeque<>();

    set.add(toInt(arr));
    queue.add(new Dot(arr, 0));

    while (!queue.isEmpty()) {
      Dot polled = queue.poll();
      
      int[] curArr = polled.arr;
      int curCount = polled.count;

      if (checkArr(curArr)) {
        System.out.print(curCount);
        return;
      }

      for (int i = 0; i <= limit; i++) {
        int[] newArr = reverse(curArr, i);
        int newNumber = toInt(newArr);

        if(set.contains(newNumber)) {
          continue;
        }

        queue.add(new Dot(newArr, curCount + 1));
        set.add(newNumber);
      }
    }

    System.out.print(-1);
  }

  static int[] reverse(int[] input, int start) {
    int[] buffer = new int[COUNT];
    System.arraycopy(input, 0, buffer, 0, COUNT);

    for (int i = 0; i < REVERSE; i++) {
      int good = i + start;
      int bad = start + (REVERSE - 1) -i;
      buffer[good] = input[bad];
    }

    
    return buffer;
  }

  static int toInt(int[] input) {
    int result = 0;
    
    for (int i : input) {
        result = result * 10 + i;
    }

    return result;
  }

  static void bfs() {

  }

  static boolean checkArr(int[] arr) {
    for (int i = 0; i < COUNT; i++) {
      if (arr[i] != i + 1) {
        return false;
      }
    }

    return true;
  }

  static class Dot{
    int[] arr;
    int count;
    
    Dot(int[] arr, int count) {
      this.arr = arr;
      this.count = count;
    }
  }
}