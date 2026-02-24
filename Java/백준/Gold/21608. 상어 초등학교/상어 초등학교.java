import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import java.util.HashMap;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int input = Integer.parseInt(br.readLine());

    int[] order = new int[input * input];
    Board board = new Board(input);

    StringTokenizer st;

    for (int i = 0; i < input * input; i++) {
      st = new StringTokenizer(br.readLine());

      int main = Integer.parseInt(st.nextToken());

      order[i] = main;

      int one = Integer.parseInt(st.nextToken());
      int two = Integer.parseInt(st.nextToken());
      int three = Integer.parseInt(st.nextToken());
      int four = Integer.parseInt(st.nextToken());

      board.addLikeRelation(main, one, two, three, four);
    }

    for (int c: order) {
      board.insertCharacterInBoard(c);
      // System.out.println(board);
      // System.out.println("======");
    }

    System.out.print(board.getWholeValue());
  }

  static class Board {
    static int[][] offSet = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
    Node[][] board;
    Map<Integer, Like> map = new HashMap<>();

    Board (int size) {
      this.board = new Node[size][size];
       
      for (int r = 0; r < board.length; r++) {
        for (int c = 0; c < board[0].length; c++) {
          board[r][c] = new Node();
        }
      }
    }

    // @Override()
    // public String toString() {
    //   StringBuilder sb = new StringBuilder();
    //   for (Node[] n: this.board) {
    //     for (Node k: n) {
    //       sb.append(k).append("\n");
    //     }
    //   }

    //   return sb.toString();
    // }

    public int getWholeValue() {
      int answer = 0;
      
      for (int r = 0; r < board.length; r++) {
        for (int c = 0; c < board[0].length; c++) {
          answer += getLikeNeighborCount(r, c, board[r][c].character);
        }
      }

      return answer;
    }

    private int getLikeNeighborCount(int row, int col, int input) {
      Like likes = this.map.get(input);
      int result = 0;

      for (int[] off: offSet) {
        int newRow = row + off[0];
        int newCol = col + off[1];

        if (newRow < 0 || newRow >= board.length || newCol < 0 || newCol >= board[0].length) {
          continue;
        }

        if (likes.isMatched(this.board[newRow][newCol].character)) {
          result++;
        }
      }

      return (int) Math.pow(10, result - 1);
    }
    
    public void addLikeRelation(int main, int one, int two, int three, int four) {
      Like like = new Like(one, two, three, four);
      
      this.map.put(main, like);
    }

    public void insertCharacterInBoard(int input) {
      List<Coordinate> candidates = getLikeCandidates(input);

      if(candidates.size() == 1) {
        Coordinate co = candidates.get(0);
        setCharacter(co.row, co.col, input);
        return;
      }

      candidates = getNullCandidates(input, candidates);
      
      if(candidates.size() == 1) {
        Coordinate co = candidates.get(0);
        setCharacter(co.row, co.col, input);
        return;
      }

      candidates.sort(null);

      Coordinate co = candidates.get(0);
      setCharacter(co.row, co.col, input);
    }

    private List<Coordinate> getLikeCandidates(int input) {
      int maxLike = 0;
      Like likes = this.map.get(input);
      List<Coordinate> candidates = new ArrayList<>();
      
      for (int r = 0; r < board.length; r++) {
        for (int c = 0; c < board[0].length; c++) {
          if(this.board[r][c].character != 0) {
            continue;
          }

          int curLikeCount = checkLikeNeighbor(r, c, likes);

          if(curLikeCount < maxLike) {
            continue;
          }

          if (curLikeCount > maxLike) {
            maxLike = curLikeCount;
            candidates.clear();
            candidates.add(new Coordinate(r, c));
            continue;
          }

          candidates.add(new Coordinate(r, c));
        }
      }

      return candidates;
    }

    private List<Coordinate> getNullCandidates(int input, List<Coordinate> candidates) {
      List<Coordinate> nullCandidates = new ArrayList<>();
      int maxNull = 0;

      for (Coordinate co: candidates) {
        int curNullCount = checkNullNeighbor(co.row, co.col);

        if(curNullCount < maxNull) {
          continue;
        }

        if (curNullCount > maxNull) {
          maxNull = curNullCount;
          nullCandidates.clear();
          nullCandidates.add(new Coordinate(co.row, co.col));
          continue;
        }

        nullCandidates.add(new Coordinate(co.row, co.col));
      }

      return nullCandidates;
    }

    private void setCharacter(int row, int col, int input) {
      this.board[row][col].character = input;
    } 

    private int checkLikeNeighbor(int row, int col, Like likes) {
      int result = 0;
      
      for (int[] off: offSet) {
        int newRow = row + off[0];
        int newCol = col + off[1];

        if (newRow < 0 || newRow >= board.length || newCol < 0 || newCol >= board[0].length) {
          continue;
        }

        if (likes.isMatched(this.board[newRow][newCol].character)) {
          result++;
        }
      }

      return result;
    }

    private int checkNullNeighbor(int row, int col) {
      int result = 0;
      
      for (int[] off: offSet) {
        int newRow = row + off[0];
        int newCol = col + off[1];

        if (newRow < 0 || newRow >= board.length || newCol < 0 || newCol >= board[0].length) {
          continue;
        }

        if(this.board[newRow][newCol].character == 0) {
          result++;
        }
      }

      return result;
    }

    class Node {
      int value = 0;
      int character;

      // @Override()
      // public String toString() {
      //   return "value: " + value + ", character: " + character;
      // }

      public void updateValue (int add) {
        this.value += add;
      }

      public void setCharacter (int input) {
        this.character = input;
      }
    }
   
    class Like {
      Set<Integer> chars;

      Like(int one, int two, int three, int four) {
        this.chars = new HashSet<>(Set.of(one, two ,three, four));
      }

      public boolean isMatched(int input) {
        return this.chars.contains(input);
      }
    }

    class Coordinate implements Comparable<Coordinate>{
      final int row;
      final int col;

      @Override()
      public int compareTo(Coordinate o) {
        if(this.row == o.row) {
          return this.col - o.col;
        }

        return this.row - o.row;
      }

      Coordinate(int row, int col) {
        this.row = row;
        this.col = col;
      }
    }
  }
}
