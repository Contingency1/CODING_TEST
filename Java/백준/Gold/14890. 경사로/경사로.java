import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

  static int N, L;
  static int[][] grid;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    init(br);

    int answer = 0;
    // 행 검사
    for (int i = 0; i < N; i++) {
      int[] target = grid[i];
      if (isGood(target)) {
        answer++;
      }
    }

    // 열 검사
    for (int i = 0; i < N; i++) {
      int[] target = new int[N];
      for (int j = 0; j < N; j++) {
        target[j] = grid[j][i];
      }
      if (isGood(target)) {
        answer++;
      }
    }

    System.out.print(answer);
  }

  private static void init(BufferedReader br) throws IOException {
    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    L = Integer.parseInt(st.nextToken());

    grid = new int[N][N];

    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < N; j++) {
        grid[i][j] = Integer.parseInt(st.nextToken());
      }
    }
  }

  private static boolean isGood(int[] arr) {
    boolean[] visited = new boolean[N];

    for (int i = 0; i < arr.length - 1; i++) {
      if (Math.abs(arr[i] - arr[i + 1]) > 1) {
        return false;
      }

      if (arr[i] != arr[i + 1]) {
        if (arr[i] > arr[i + 1]) { // 오른쪽에 경사로
          int end = i + L;

          if (end > arr.length - 1) {
            return false;
          }

          // 경사로가 놓일 곳이 평탄화 되어있나?
          for (int j = i + 1; j < end; j++) {
            if (arr[j] != arr[j + 1]) {
              return false;
            }
          }

          // 평탄화 되어 있으면 경사로를 놓는데, 이미 놓여져 있으면 바로 false 뱉는다.
          for (int j = i + 1; j <= end; j++) {
            if (visited[j]) {
              return false;
            }
            visited[j] = true;
          }
        } else { // 왼쪽에 경사로
          int start = i - (L - 1);
          if (start < 0) {
            return false;
          }

          // 경사로가 놓일 곳이 평탄화 되어있나?
          for (int j = start; j < i; j++) {
            if (arr[j] != arr[j + 1]) {
              return false;
            }
          }

          // 평탄화 되어 있으면 경사로를 놓는데, 이미 놓여져 있으면 바로 false 뱉는다.
          for (int j = start; j <= i; j++) {
            if (visited[j]) {
              return false;
            }
            visited[j] = true;
          }
        }
      }
    }

    return true;
  }
}