import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    PUYO[][] puyo = new PUYO[12][6];

    for (int r = 11; r >= 0; r--) {
      char[] chars = br.readLine().toCharArray();

      for (int c = 0; c < 6; c++) {
        puyo[r][c] = PUYO.from(chars[c]);
      }
    }

    Board board = new Board(puyo);
    board.check();

    System.out.print(board.answer);
  }
}

class Board {
  PUYO[][] puyo;
  
  final int ROW_COUNT = 12;
  final int COL_COUNT = 6;

  int answer = 0;

  final int[][] OFF_SET = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};

  Board(PUYO[][] puyo) {
    this.puyo = puyo;
  }

  // @Override
  // public String toString() {
  //     StringBuilder sb = new StringBuilder();
  //     sb.append("=== Puyo Board ===\n");
      
  //     for (int r = ROW_COUNT - 1; r >= 0; r--) {
  //         sb.append(String.format("%2d | ", r));
  //         for (int c = 0; c < COL_COUNT; c++) {
  //             sb.append(this.puyo[r][c].chr).append(" ");
  //         }
  //         sb.append("\n");
  //     }
      
  //     sb.append("------------------\n");
  //     sb.append("     0 1 2 3 4 5  (col)");
  //     return sb.toString();
  // }

  public void check() {
    boolean[][] visited = new boolean[this.ROW_COUNT][this.COL_COUNT];
    boolean isBoomed = true;

    List<Coordinate> target = new ArrayList<>();

    while(isBoomed) {
      isBoomed = false;
      
      for (int r = 0; r < this.ROW_COUNT; r++) {
        for (int c = 0; c < this.COL_COUNT; c++) {
          if (this.puyo[r][c] == PUYO.NULL || visited[r][c]) {
            continue;
          }

          Coordinate coordinate = new Coordinate(r, c);
          List<Coordinate> result = bfs(visited, coordinate, this.puyo[r][c]);

          if(result.size() >= 4) {
            isBoomed = true;

            for (Coordinate re: result) {
              target.add(re);
            }
          }
        }
      }

      if(isBoomed) {
        boom(target);
        target.clear();
        resetPuyo();
        this.answer++;
      }

      visited = new boolean[this.ROW_COUNT][this.COL_COUNT];
    }
  }

  private void boom(List<Coordinate> targets) {
    for (Coordinate c: targets) {
      int row = c.row;
      int col = c.col;

      this.puyo[row][col] = PUYO.NULL;
    }

  }

  private List<Coordinate> bfs(boolean[][] visited, Coordinate start, PUYO puyo) {
    ArrayDeque<Coordinate> queue = new ArrayDeque<>();
    queue.addLast(start);

    List<Coordinate> result = new ArrayList<>();
    visited[start.row][start.col] = true;

    while (!queue.isEmpty()) {
      Coordinate polled = queue.pollFirst();
      result.add(polled);

      int row = polled.row;
      int col = polled.col;

      for (int[] off: OFF_SET) {
        int newRow = row + off[0];
        int newCol = col + off[1];
        
        if(newRow >= 0 && newRow < this.ROW_COUNT &&
          newCol >= 0 && newCol < this.COL_COUNT && 
          !visited[newRow][newCol] && this.puyo[newRow][newCol] == puyo) {
            visited[newRow][newCol] = true;
            queue.addLast(new Coordinate(newRow, newCol));
          }
      }
    }

    return result;
  }

  private void resetPuyo() {
    for (int c = 0; c < COL_COUNT; c++) {
      down(c);
    }
  }

  private void down(int col) {
    ArrayDeque<Coordinate> queue = new ArrayDeque<>();
    
    for (int r = 0; r < this.ROW_COUNT; r++) {
      if(this.puyo[r][col] != PUYO.NULL) {
        queue.addLast(new Coordinate(r, col));
      }
    }

    for (int r = 0; r < 12; r++) {
      if(!queue.isEmpty()) {
        Coordinate c = queue.pollFirst();
        this.puyo[r][col] = this.puyo[c.row][c.col]; 
        continue;
      }

      this.puyo[r][col] = PUYO.NULL;
    }

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

enum PUYO {
  RED('R'), GREEN('G'), BLUE('B'), PURPLE('P'), YELLOW('Y'), NULL('.');

  char chr;

  PUYO(char chr) {
    this.chr = chr;
  }

  public static PUYO from(char chr) {
    for(PUYO p: PUYO.values()) {
      if(p.chr == chr) {
        return p;
      }
    }

    throw new RuntimeException("NOPE");
  }
}