import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());

    int row = Integer.parseInt(st.nextToken());
    int col = Integer.parseInt(st.nextToken());

    int buffer = Math.min(row, col);

    char[][] arr = new char[row][col];

    for (int i = 0; i < row; i++) {
      arr[i] = br.readLine().toCharArray();
    }

    int answer = 1;

    while(buffer != 1) {
      int limitRow = row - buffer;
      int limitCol = col - buffer;

      for (int i = 0; i <= limitRow; i++) {
        for (int j = 0; j <= limitCol; j++) {
          if(arr[i][j] == arr[i + buffer - 1][j] && arr[i][j] == arr[i][ j+ buffer - 1] && arr[i][j] == arr[i + buffer - 1][j + buffer - 1]) {
            answer = Math.max(answer, buffer * buffer);
          }
        }
      }

      buffer--;
    }

    System.out.print(answer);
  }
}