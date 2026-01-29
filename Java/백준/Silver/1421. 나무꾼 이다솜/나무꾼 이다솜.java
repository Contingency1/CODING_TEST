import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());

    final int TREE_COUNT = Integer.parseInt(st.nextToken());
    final int COST = Integer.parseInt(st.nextToken());
    final int PRICE = Integer.parseInt(st.nextToken());

    int[] arr = new int[TREE_COUNT];

    for (int i = 0; i < TREE_COUNT; i++) {
      arr[i] = Integer.parseInt(br.readLine());
    }

    Arrays.sort(arr);

    long answer = 0;

    for (int i = arr[TREE_COUNT - 1]; i >= 1; i--) {
      int wood = 0;
      int cut = 0;

      for (int j = TREE_COUNT - 1; j >= 0; j--) {
        int cur = arr[j];

        if (i > cur) {
          break;
        }

        int mok = cur / i;
        int left = cur % i;

        int willCut = left == 0 ? mok - 1 : mok;
        
        int result = mok * i * PRICE - willCut * COST;

        if(result < 0) {
          continue;
        }

        cut += willCut;
        wood += mok;
      }

      long b = wood * i;
      long c = cut * COST;
      long d = b * PRICE;

      long key =  d - c;

      answer = Math.max(answer, key);
    }
    
    System.out.print(answer);
  }
}