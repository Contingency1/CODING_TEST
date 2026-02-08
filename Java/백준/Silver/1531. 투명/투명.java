import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());

    int[][] arr = new int[101][101];

    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());

      int startX = Integer.parseInt(st.nextToken());
      int startY = Integer.parseInt(st.nextToken());
      int endX = Integer.parseInt(st.nextToken());
      int endY = Integer.parseInt(st.nextToken());

      for (int x = startX; x <= endX; x++) {
        for (int y = startY; y <= endY; y++) {
          arr[x][y]++;
        }
      }
    }

    int answer = 0;

    for (int i = 1; i < 101; i++) {
      for (int j = 1; j < 101; j++) {
        if(arr[i][j] > M) {
          answer++;
        }
      }
    }

    System.out.print(answer);
  }
}