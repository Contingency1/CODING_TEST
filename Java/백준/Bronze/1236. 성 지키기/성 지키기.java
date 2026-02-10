import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());

    int row = Integer.parseInt(st.nextToken());
    int col = Integer.parseInt(st.nextToken());

    boolean[] rowArr = new boolean[row];
    boolean[] colArr = new boolean[col];

    char[][] input = new char[row][col];
    
    for (int i = 0; i < row; i++) {
      input[i] = br.readLine().toCharArray();
    }

    for (int i = 0; i < row; i++) {
      for (int j = 0; j < col; j++) {
        if (input[i][j] == 'X') {
          rowArr[i] = true;
          colArr[j] = true;
        }
      }
    }

    int answer = 0;

    for (int i = 0; i < row; i++) {
      if (!rowArr[i]) {
        answer++;
        for (int j = 0; j < col; j++) {
          if(!colArr[j]) {
            colArr[j] = true;
            break;
          }
        }
      }
    }
         
    for (int j = 0; j < col; j++) {
      if(!colArr[j]) {
        answer++;
      }
    }

    System.out.print(answer);
  }

}