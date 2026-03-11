import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());

    final int ROW_COUNT = Integer.parseInt(st.nextToken());
    final int COL_COUNT = Integer.parseInt(st.nextToken());

    final int TARGET_COUNT = Integer.parseInt(br.readLine());

    TOPOGRAPHY[][] topography = new TOPOGRAPHY[ROW_COUNT + 1][COL_COUNT + 1];

    for (int i = 1; i <= ROW_COUNT; i++) {
      char[] chars = br.readLine().toCharArray();

      for (int j = 1; j <= COL_COUNT; j++) {
        topography[i][j] = TOPOGRAPHY.from(chars[j - 1]);
      }
    }

    List<Coordinate> start = new ArrayList<>();
    List<Coordinate> end = new ArrayList<>();

    for (int i = 0; i < TARGET_COUNT; i++) {
      st = new StringTokenizer(br.readLine());
      
      int startRow = Integer.parseInt(st.nextToken());
      int startCol = Integer.parseInt(st.nextToken());
      int endRow = Integer.parseInt(st.nextToken());
      int endCol = Integer.parseInt(st.nextToken());

      start.add(new Coordinate(startRow, startCol));
      end.add(new Coordinate(endRow, endCol));
    }

    Board board = new Board(ROW_COUNT, COL_COUNT, TARGET_COUNT, topography, start, end);

    System.out.print(board.answer());
  }
}

class Board {
  Count[][] dp;

  int rowCount;
  int colCount;

  int targetCount;

  TOPOGRAPHY[][] topography;

  List<Coordinate> start;
  List<Coordinate> end;

  Board(int rowCount, int colCount, int targetCount,
     TOPOGRAPHY[][] topography, List<Coordinate> start, List<Coordinate> end) {
      this.rowCount = rowCount;
      this.colCount = colCount;
      this.targetCount = targetCount;

      this.topography = topography;

      this.start = start;
      this.end = end;

      dp = new Count[rowCount + 1][colCount + 1];

      for (int i = 0; i <= rowCount; i++) {
        dp[i][0] = new Count(0, 0, 0);
      }
      for (int i = 0; i <= colCount; i++) {
        dp[0][i] = new Count(0, 0, 0);
      }

      initDP();
  }

  private void initDP() {
    for (int r = 1; r <= this.rowCount; r++) {
      for (int c = 1; c <= this.colCount; c++) {
        dp[r][c] = new Count(dp[r][c - 1].jungleCount, dp[r][c - 1].oceanCount, dp[r][c - 1].iceCount);
        
        switch (topography[r][c]) {
          case JUNGLE:
            dp[r][c].jungleCount++;
            break;
          case OCEAN:
            dp[r][c].oceanCount++;
            break;
          case ICE:
            dp[r][c].iceCount++;
        }
      }

      for (int c = 1; c <= this.colCount; c++) {
        dp[r][c].jungleCount += dp[r - 1][c].jungleCount;
        dp[r][c].oceanCount += dp[r - 1][c].oceanCount;
        dp[r][c].iceCount += dp[r - 1][c].iceCount;
      }

    }
  }
  
  public String answer() {
    StringBuilder sb = new StringBuilder();

    for (int i = 0; i < targetCount; i++) {
      Coordinate startCoor = start.get(i);
      Coordinate endCoor = end.get(i);

      Count result = calculate(startCoor, endCoor);

      sb.append(result).append("\n");
    }

    sb.deleteCharAt(sb.length() - 1);
    return sb.toString();
  }

  private Count calculate(Coordinate start, Coordinate end) {
    int j = dp[end.row][end.col].jungleCount 
    - dp[start.row - 1][end.col].jungleCount 
    - dp[end.row][start.col - 1].jungleCount 
    + dp[start.row - 1][start.col - 1].jungleCount;

    int o = dp[end.row][end.col].oceanCount 
    - dp[start.row - 1][end.col].oceanCount 
    - dp[end.row][start.col - 1].oceanCount 
    + dp[start.row - 1][start.col - 1].oceanCount;
    
    int i = dp[end.row][end.col].iceCount 
    - dp[start.row - 1][end.col].iceCount 
    - dp[end.row][start.col - 1].iceCount 
    + dp[start.row - 1][start.col - 1].iceCount;

    return new Count(j, o, i);
  }

}

class Count{
  int jungleCount;
  int oceanCount;
  int iceCount;

  @Override
  public String toString() {
    return this.jungleCount + " " + this.oceanCount + " " + iceCount;
  }

  Count(int jungleCount, int oceanCount, int iceCount) {
    this.jungleCount = jungleCount;
    this.oceanCount = oceanCount;
    this.iceCount = iceCount;
  }
}

class Coordinate {
  int row;
  int col;
  
  Coordinate(int row, int col) {
    this.row = row;
    this.col = col;
  }
}

enum TOPOGRAPHY {
  JUNGLE('J'), OCEAN('O'), ICE('I');

  char character;

  TOPOGRAPHY (char character) {
    this.character = character;
  }

  public static TOPOGRAPHY from(char character) {
    for (TOPOGRAPHY t: TOPOGRAPHY.values()) {
      if(t.character == character) {
        return t;
      }
    }

    throw new RuntimeException("WHAT???");
  }
}