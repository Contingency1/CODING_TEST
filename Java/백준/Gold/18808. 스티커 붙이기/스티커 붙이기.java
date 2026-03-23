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
    final int STICKER_COUNT = Integer.parseInt(st.nextToken());

    List<Sticker> list = new ArrayList<>(STICKER_COUNT);
    boolean[][] used = new boolean[ROW_COUNT][COL_COUNT];

    for (int i = 0; i < STICKER_COUNT; i++) {

      st = new StringTokenizer(br.readLine());

      int row = Integer.parseInt(st.nextToken());
      int col = Integer.parseInt(st.nextToken());
      boolean[][] isOn = new boolean[row][col];

      for (int r = 0; r < row; r++) {
        st = new  StringTokenizer(br.readLine());

        for (int c = 0; c < col; c++) {
          isOn[r][c] = st.nextToken().equals("1"); 
        }
      }

      Sticker sticker = new Sticker(row, col, isOn);
      list.add(sticker);
    }
    
    for (Sticker sticker: list) {
      boolean flag = false;

      for (int i = 0; i < 4; i++) {
        for (int r = 0; r < ROW_COUNT; r++) {
          for (int c = 0; c < COL_COUNT; c++) {
            if(sticker.validate(r, c, ROW_COUNT, COL_COUNT, used)) {
              sticker.turnOn(r, c, used);
              flag = true;
            }

            if(flag) {
              break;
            }
          }

          if(flag) {
            break;
          }
        }

        if(flag) {
          break;
        }

        sticker.turnRight();
      }
    }

    System.out.print(answer(used));
  }

  private static int answer(boolean[][] used) {
    int answer = 0;

    for (boolean[] use :used) {
      for (boolean u: use) {
        if(u) {
          answer++;
        }
      }
    }

    return answer;
  }
}

class Sticker {
  int row;
  int col;
  boolean[][] isOn;

  Sticker(int row, int col, boolean[][] isOn) {
    this.row = row;
    this.col = col;
    this.isOn = isOn;
  }

  public void turnRight() {
    boolean[][] newOne = new boolean[this.col][this.row];

    for (int r = 0; r < this.row; r++) {
      for (int c = 0; c < this.col; c++) {
        newOne[c][this.row - 1 - r] = this.isOn[r][c];
      }
    }

    this.isOn = newOne;

    int buffer = this.row;
    this.row = this.col;
    this.col = buffer;
  }

  public boolean validate(int curRow, int curCol, int rowMax, int colMax, boolean[][] used) {
    int endRow = curRow + this.row - 1;
    int endCol = curCol + this.col - 1;

    if(endRow >= rowMax || endCol >= colMax) {
      return false;
    }

    for (int r = curRow; r <= endRow; r++) {
      for (int c = curCol; c <= endCol; c++) {
        if (this.isOn[r - curRow][c - curCol] && used[r][c]) {
          return false;
        }
      }
    }

    return true;
  }

  public void turnOn(int curRow, int curCol, boolean[][] used) {
    for (int r = curRow; r <= curRow + this.row - 1; r++) {
      for (int c = curCol; c <= curCol + this.col - 1; c++) {
        if (this.isOn[r - curRow][c - curCol]) {
          used[r][c] = true;
        }
      }
    }
  } 
}