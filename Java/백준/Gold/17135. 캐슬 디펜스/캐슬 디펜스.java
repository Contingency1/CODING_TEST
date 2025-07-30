import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.StringTokenizer;

class Main {

  static int[][] grid;
  static int rowCount, colCount, archerRange;
  static List<NodeJS> offset;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    init(br);

    int answer = 0;

    for (int A1 = 0; A1 < colCount - 2; A1++) {
      for (int A2 = A1 + 1; A2 < colCount - 1; A2++) {
        for (int A3 = A2 + 1; A3 < colCount; A3++) {
          answer = Math.max(answer, calculate(A1, A2, A3));
        }
      }
    }

    System.out.print(answer);
  }

  private static int calculate(int A1, int A2, int A3) {
    int result = 0;
    int[][] bufferGrid = new int[rowCount][colCount];

    for (int i = 0; i < rowCount; i++) {
      System.arraycopy(grid[i], 0, bufferGrid[i], 0, colCount);
    }

    int[][] archers = {{rowCount, A1}, {rowCount, A2}, {rowCount, A3}};

    for (int i = 0; i < rowCount; i++) {
      Set<NodeJS> shootNodes = new HashSet<>();
      for (int[] archer : archers) {
        for (NodeJS off : offset) {
          int plusRow = off.row;
          int plusCol = off.col;

          int newRow = archer[0] + plusRow;
          int newCol = archer[1] + plusCol;

          if (newRow >= 0 && newRow < rowCount && newCol >= 0 && newCol < colCount) {
            if (bufferGrid[newRow][newCol] == 1) {
              shootNodes.add(new NodeJS(newRow, newCol));
              break;
            }
          }
        }
      }

      for (NodeJS shootNode : shootNodes) {
        int curRow = shootNode.row;
        int curCol = shootNode.col;

        bufferGrid[curRow][curCol] = 0;
      }

      result += shootNodes.size();
      nextRow(bufferGrid);
    }

    return result;
  }

  private static void init(BufferedReader br) throws IOException {
    StringTokenizer st = new StringTokenizer(br.readLine());

    rowCount = Integer.parseInt(st.nextToken());
    colCount = Integer.parseInt(st.nextToken());
    archerRange = Integer.parseInt(st.nextToken());

    grid = new int[rowCount][colCount];

    for (int i = 0; i < rowCount; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < colCount; j++) {
        grid[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    offset = new ArrayList<>();

    for (int i = 1; i <= archerRange; i++) {
      offset.add(new NodeJS(-i, 0));
      for (int j = 1; j <= archerRange - i; j++) {
        offset.add(new NodeJS(-i, j));
        offset.add(new NodeJS(-i, -j));
      }
    }

    offset.sort(null);

  }

  static void nextRow(int[][] inputGrid) {
    for (int i = rowCount - 1; i > 0; i--) {
      inputGrid[i] = inputGrid[i - 1];
    }

    inputGrid[0] = new int[colCount];
  }


  static class NodeJS implements Comparable<NodeJS> {

    int row;
    int col;

    public NodeJS(int row, int col) {
      this.row = row;
      this.col = col;
    }


    @Override
    public boolean equals(Object o) {
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      NodeJS nodeJS = (NodeJS) o;
      return row == nodeJS.row && col == nodeJS.col;
    }

    @Override
    public int hashCode() {
      return Objects.hash(row, col);
    }

    @Override
    public int compareTo(NodeJS o) {
      int result = (Math.abs(row) + Math.abs(col)) - (Math.abs(o.row) + Math.abs(o.col));
      if (result == 0) {
        return col - o.col;
      }

      return result;
    }
  }


}