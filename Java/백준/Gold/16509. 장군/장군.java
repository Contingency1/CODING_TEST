import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {

  static int[][] offSet = {};

  static boolean[][] visited = new boolean[10][9];

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());

    int startRow = Integer.parseInt(st.nextToken());
    int startCol = Integer.parseInt(st.nextToken());

    st = new StringTokenizer(br.readLine());

    int targetRow = Integer.parseInt(st.nextToken());
    int targetCol = Integer.parseInt(st.nextToken());

    ArrayDeque<Node> queue = new ArrayDeque<>();
    visited[startRow][startCol] = true;
    queue.addLast(new Node(0, startRow, startCol));

    while (!queue.isEmpty()) {
      Node node = queue.pollFirst();

      int row = node.row;
      int col = node.col;
      int count = node.count;

      if (row == targetRow && col == targetCol) {
        System.out.print(count);
        return;
      }

      for (Move move : Move.values()) {
        int newRow = row + move.destination[0];
        int newCol = col + move.destination[1];

        if (newRow >= 0 && newRow < 10 && newCol >= 0 && newCol < 9 && !visited[newRow][newCol]) {
          boolean flag = false;
          for (int[] ints : move.step) {
            int r = row + ints[0];
            int c = col + ints[1];

            if (r == targetRow && c == targetCol) {
              flag = true;
              break;
            }
          }

          if (flag) {
            continue;
          }

          queue.add(new Node(count + 1, newRow, newCol));
          visited[newRow][newCol] = true;
        }
      }


    }
  }
}

enum Move {
  A(new int[]{3, 2}, new int[][]{{1, 0}, {2, 1}}),
  B(new int[]{3, -2}, new int[][]{{1, 0}, {2, -1}}),
  C(new int[]{-3, 2}, new int[][]{{-1, 0}, {-2, 1}}),
  D(new int[]{-3, -2}, new int[][]{{-1, 0}, {-2, -1}}),
  E(new int[]{2, 3}, new int[][]{{0, 1}, {1, 2}}),
  F(new int[]{-2, 3}, new int[][]{{0, 1}, {-1, 2}}),
  G(new int[]{2, -3}, new int[][]{{0, -1}, {1, -2}}),
  H(new int[]{-2, -3}, new int[][]{{0, -1}, {-1, -2}});

  final int[] destination;
  final int[][] step;

  Move(int[] destination, int[][] step) {
    this.destination = destination;
    this.step = step;
  }
}

class Node {

  int count;
  int row;
  int col;

  public Node(int count, int row, int col) {
    this.count = count;
    this.row = row;
    this.col = col;
  }
}