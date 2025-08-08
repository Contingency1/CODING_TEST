import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

class Main {


  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int N = Integer.parseInt(br.readLine());

    ArrayDeque<Status> queue = new ArrayDeque<>();
    queue.add(new Status(1, 0, 0));

    boolean[][] visited = new boolean[100_00][100_00];
    visited[1][0] = true;

    while (!queue.isEmpty()) {
      Status status = queue.poll();
      int strLength = status.strLength;
      int boardLength = status.boardLength;
      int time = status.time;

      if (strLength == N) {
        System.out.print(time);
        break;
      }

      // 클립 보드에 있는것 화면에 복사
      if (boardLength != 0) {
        if (!visited[strLength + boardLength][boardLength]) {
          queue.add(new Status(strLength + boardLength,
              boardLength, time + 1));
          visited[strLength][boardLength] = true;
        }
      }

      // 화면에 있는 것 클립 보드에 복사
      if (!visited[strLength][strLength]) {
        queue.add(new Status(strLength, strLength, time + 1));
        visited[strLength][strLength] = true;
      }

      // 화면에 있는 것 중 하나 삭제
      if (strLength != 0) {
        if (!visited[strLength - 1][boardLength]) {
          queue.add(new Status(strLength - 1,
              boardLength, time + 1));
          visited[strLength][boardLength] = true;
        }
      }
    }
  }

  static class Status {

    int strLength;
    int boardLength;
    int time;

    public Status(int strLength, int boardLength, int time) {
      this.strLength = strLength;
      this.boardLength = boardLength;
      this.time = time;
    }
  }
}