import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());

    final int ROW = Integer.parseInt(st.nextToken());
    final int COL = Integer.parseInt(st.nextToken());

    final int TIME = Integer.parseInt(st.nextToken());

    int[][] board = new int[ROW][COL];
    Coordinate purifier1 = null, purifier2 = null;

    for (int i = 0; i < ROW; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < COL; j++) {
        board[i][j] = Integer.parseInt(st.nextToken());

        if(board[i][j] == -1) {
          if(purifier1 == null) {
            purifier1 = new Coordinate(i, j);
          } else {
            purifier2 = new Coordinate(i, j);
          }
        }
      }
    }

    Board Eomjunsick = new Board(board, purifier1, purifier2);

    for (int i = 0; i < TIME; i++) {
      Eomjunsick.dustSpreads();
      Eomjunsick.purify();
    }

    System.out.print(Eomjunsick.getDust());
  }

}

class Board  {
  int[][] board;
  Coordinate purifier1;
  Coordinate purifier2;
  static int[][] offSet = {{1, 0}, {0, -1}, {-1, 0}, {0, 1}};

  public int getDust() {
    int answer = 0;

    for (int[] i: this.board){

      for (int k: i) {
        if(k != -1 && k !=0 ){
          answer += k;
        }
      }
    } 

    return answer;
  }

  public void dustSpreads() {
    int[][] newOne = new int[this.board.length][this.board[0].length];

    for (int r = 0; r < board.length; r++) {
      for (int c = 0; c < board[0].length; c++) {
        if(board[r][c] != 0 && board[r][c] != -1) {
          int value = board[r][c] / 5;

          List<Coordinate> list = new ArrayList<>();

          for (int[] off: offSet) {
            int newRow = r + off[0];
            int newCol = c + off[1];

            
            if (newRow < 0 || newRow >= this.board.length ||
              newCol < 0 || newCol >= this.board[0].length ||
              isPurifierCoordinate(newRow, newCol)
            ) {
              continue;
            }

            list.add(new Coordinate(newRow, newCol));
          }

          int original = board[r][c] - value * list.size();

          if(original > 0) {
            newOne[r][c] += original;
          }

          for (Coordinate co: list) {
            newOne[co.row][co.col] += value;
          }
        }
      }
    }

    newOne[purifier1.row][purifier1.col] = -1;
    newOne[purifier2.row][purifier2.col] = -1;

    this.board = newOne;
  }

  public void purify() {
    int p1Row = this.purifier1.row;

    for (int r = p1Row - 1; r > 0; r--) {
      this.board[r][0] = this.board[r - 1][0];
    }

    for (int c = 0; c < this.board[0].length - 1; c++) {
      this.board[0][c] = this.board[0][c + 1];
    }

    for (int r = 0; r < p1Row; r++) {
      this.board[r][this.board[0].length - 1] = this.board[r + 1][this.board[0].length - 1];
    }

    for (int c = this.board[0].length - 1; c > 1; c--) {
      this.board[p1Row][c] = this.board[p1Row][c -1];
    }

    this.board[p1Row][1] = 0;
    
    int p2Row = this.purifier2.row;

    for (int r = p2Row + 1; r < this.board.length - 1; r++) {
      this.board[r][0] = this.board[r + 1][0];
    }

    for (int c = 0; c < this.board[0].length - 1; c++) {
      this.board[this.board.length - 1][c] = this.board[this.board.length - 1][c + 1];
    }

    for (int r = this.board.length - 1; r > p2Row; r--) {
      this.board[r][this.board[0].length - 1] = this.board[r - 1][this.board[0].length - 1];
    }

    for (int c = this.board[0].length - 1; c > 1; c--) {
      this.board[p2Row][c] = this.board[p2Row][c - 1];
    }

    this.board[p2Row][1] = 0;
  }

  private boolean isPurifierCoordinate(int row, int col) {
    if(row == this.purifier1.row && col == this.purifier1.col) {
      return true;
    }
        
    if(row == this.purifier2.row && col == this.purifier2.col) {
      return true;
    }

    return false;
  }

  Board(int[][] board, Coordinate purifier1, Coordinate purifier2) {
    this.board = board;
    this.purifier1 = purifier1;
    this.purifier2 = purifier2;
  }
}

class Coordinate {
  final int row;
  final int col;
  
  Coordinate(int row, int col) {
    this.row = row;
    this.col = col;
  }
}