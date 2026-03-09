import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    int PLAYER_COUNT = Integer.parseInt(br.readLine());
    int LADDER_COUNT = Integer.parseInt(br.readLine());

    char[] target = br.readLine().toCharArray();

    LADDER[][] ladder = new LADDER[LADDER_COUNT][PLAYER_COUNT];

    for (int i = 0; i < LADDER_COUNT; i++) {
      char[] input = br.readLine().toCharArray();

      for (int j = 0; j < input.length; j++) {
        ladder[i][j] = LADDER.from(input[j]);
      }
    }

    Board board = new Board(PLAYER_COUNT, LADDER_COUNT, target, ladder);

    System.out.print(board.answer());
  }
}

class Board {
  int playerCount;
  int ladderCount;
  char[] start;
  char[] target;
  LADDER[][] ladder;

  Board(int playerCount, int ladderCount, char[] target, LADDER[][] ladder) {
    this.playerCount = playerCount;
    this.ladderCount = ladderCount;
    this.start = new char[this.playerCount];
    this.target = target;

    for (int i = 0; i < this.playerCount; i++) {
      this.start[i] = (char) (65 + i);
    }

    this.ladder = ladder;

    setStart();
    setTarget();

    // System.out.println(Arrays.toString(this.start));
    // System.out.println(Arrays.toString(this.target));
  }

  public String answer() {
    StringBuilder sb = new StringBuilder();

    for (int i = 0; i < this.playerCount - 1; i++) {
      if (this.start[i] == this.target[i]) {
        sb.append("*");
        continue;
      }

      if (this.start[i] == this.target[i + 1] && this.start[i + 1] == this.target[i]) {
        sb.append("-");

        char c = this.start[i];
        this.start[i] = this.target[i + 1];
        this.start[i + 1] = c;
        continue;
      }

      StringBuilder ex = new StringBuilder();

      for (int j = 0; j < this.playerCount - 1; j++) {
        ex.append("x");
      }

      return ex.toString();
    }

    return sb.toString();
  }

  private void setStart() {
    int ladderIdx = 0;

    while(ladder[ladderIdx][0] != LADDER.QUESTION) {
      for (int i = 0; i < this.playerCount - 1; i++) {
        if(ladder[ladderIdx][i] == LADDER.DASH) {
          char c = this.start[i];

          this.start[i] = this.start[i + 1];
          this.start[i + 1] = c;
        }
      }
      ladderIdx++;
    }
  }

  private void setTarget() {
    int ladderIdx = ladderCount - 1;

    while(ladder[ladderIdx][0] != LADDER.QUESTION) {
      for (int i = 0; i < this.playerCount - 1; i++) {
        if(ladder[ladderIdx][i] == LADDER.DASH) {
          char c = this.target[i];

          this.target[i] = this.target[i + 1];
          this.target[i + 1] = c;
        }
      }

      ladderIdx--;
    }
  }
}

enum LADDER{
  STAR('*'), DASH('-'), QUESTION('?');
  
  char character;

  LADDER(char character) {
    this.character = character;
  }

  public static LADDER from(char character) {
    for (LADDER l: LADDER.values()) {
      if(l.character  == character) {
        return l;
      }
    }

    throw new RuntimeException("NOOO");
  }
}