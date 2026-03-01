import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Objects;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    final int ROW_COUNT = Integer.parseInt(st.nextToken());
    final int COL_COUNT = Integer.parseInt(st.nextToken());
    final int ROUND_COUNT = Integer.parseInt(st.nextToken());

    int[][] input = new int[ROW_COUNT][COL_COUNT];

    for (int i = 0; i < ROW_COUNT; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < COL_COUNT; j++) {
        input[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    Board board = new Board(ROW_COUNT - 1, COL_COUNT - 1, input);
    List<Attack> attack = new ArrayList<>();
    List<Coordinate> defense = new ArrayList<>();

    for (int i = 0; i < ROUND_COUNT; i++) {
      st = new StringTokenizer(br.readLine());
      int rowIdx = Integer.parseInt(st.nextToken()) - 1;
      int colIdx = Integer.parseInt(st.nextToken()) - 1;
      Direction direction = Direction.fromChar(st.nextToken().charAt(0));

      attack.add(new Attack(new Coordinate(rowIdx, colIdx), direction));
      
      st = new StringTokenizer(br.readLine());
      int rowIdxForDefense = Integer.parseInt(st.nextToken()) - 1;
      int colIdxForDefense = Integer.parseInt(st.nextToken()) - 1;

      defense.add(new Coordinate(rowIdxForDefense, colIdxForDefense));
    }

    int answer = 0;

    for (int i = 0; i < ROUND_COUNT; i++) {
      Attack curAttack = attack.get(i);
      Coordinate curCoord = defense.get(i);

      board.beAttacked(curAttack.getCoordinate(), curAttack.getDirection());
      board.beRestored(curCoord);

      answer += board.getFallenCount();
      board.initFallenDomino();
      
    }

    System.out.println(answer);
    System.out.print(board);
  }
}

class Board {
  private final int maxRowIdx;
  private final int maxColIdx;
  private Domino[][] dominos;

  private Set<Coordinate> attackedSet = new HashSet<>();

  Board(int maxRowIdx, int maxColIdx, int[][] input) {
    this.maxRowIdx = maxRowIdx;
    this.maxColIdx = maxColIdx;

    this.dominos = new Domino[maxRowIdx + 1][maxColIdx + 1];

    for (int i = 0; i <= maxRowIdx; i++) {
      for (int j = 0; j <= maxColIdx; j++) {
        dominos[i][j] = new Domino(input[i][j]);
      }
    }
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    
    for (int i = 0; i <= this.maxRowIdx; i++) {
      for (int j = 0; j <= this.maxColIdx; j++) {
        sb.append(dominos[i][j]).append(" ");
      }

      sb.deleteCharAt(sb.length() - 1).append("\n");
    }

    sb.deleteCharAt(sb.length() - 1);

    return sb.toString();
  }

  public void beAttacked(Coordinate coordinate, Direction direction) {
    int curRowIdx = coordinate.getRowIdx();
    int curColIdx = coordinate.getColIdx();

    Coordinate target = getEndCoordinate(curRowIdx, curColIdx, direction);

    switch(direction) {
      case WEST:
        for (int c = curColIdx; c >= target.getColIdx(); c--) {
          if (dominos[curRowIdx][c].isFallen()) {
            continue;
          }

          Coordinate nextTarget = getEndCoordinate(curRowIdx, c, direction);

          if (nextTarget.getColIdx() < target.getColIdx()) {
            target = nextTarget;
          }

          this.attackedSet.add(new Coordinate(curRowIdx, c));
          
          dominos[curRowIdx][c].attacked();
        }
      break;
      case NORTH:
        for (int r = curRowIdx; r >= target.getRowIdx(); r--) {
          if (dominos[r][curColIdx].isFallen()) {
            continue;
          }

          Coordinate nextTarget = getEndCoordinate(r, curColIdx, direction);

          if (nextTarget.getRowIdx() < target.getRowIdx()) {
            target = nextTarget;
          }
          
          this.attackedSet.add(new Coordinate(r, curColIdx));
          dominos[r][curColIdx].attacked();
        }
      break;
      case EAST:
        for (int c = curColIdx; c <= target.getColIdx(); c++) {
          if (dominos[curRowIdx][c].isFallen()) {
            continue;
          }

          Coordinate nextTarget = getEndCoordinate(curRowIdx, c, direction);

          if (nextTarget.getColIdx() > target.getColIdx()) {
            target = nextTarget;
          }

          this.attackedSet.add(new Coordinate(curRowIdx, c));
          
          dominos[curRowIdx][c].attacked();
        }
      break;
      case SOUTH:
        for (int r = curRowIdx; r <= target.getRowIdx(); r++) {
          if (dominos[r][curColIdx].isFallen()) {
            continue;
          }

          Coordinate nextTarget = getEndCoordinate(r, curColIdx, direction);

          if (nextTarget.getRowIdx() > target.getRowIdx()) {
            target = nextTarget;
          }
        
          this.attackedSet.add(new Coordinate(r, curColIdx));
          
          dominos[r][curColIdx].attacked();
        }
    }
  }

  public void beRestored(Coordinate coordinate) {
    dominos[coordinate.getRowIdx()][coordinate.getColIdx()].restored();
  }

  public int getFallenCount() {
    return this.attackedSet.size();
  }

  public void initFallenDomino() {
    this.attackedSet.clear();
  }

  private Coordinate getEndCoordinate(int curRowIdx, int curColIdx, Direction direction) {
    Domino curDomino = dominos[curRowIdx][curColIdx];

    Coordinate coordinate = new Coordinate(curRowIdx, curColIdx);

    int rowOffset = curDomino.getNextBeFallenCount() * direction.rowOffset;
    int colOffset = curDomino.getNextBeFallenCount() * direction.colOffset;

    Coordinate targetCoordinate;
    
      switch(direction) {
        case WEST:
          if (coordinate.getColIdx() == 0) {
            return coordinate;
          }

          if(coordinate.getColIdx() + colOffset >= coordinate.getColIdx()) {
            return coordinate;
          }

          targetCoordinate = coordinate.getCoordinateWithOffset(rowOffset, colOffset, this.maxRowIdx, this.maxColIdx);
        break;
        case NORTH:
         if (coordinate.getRowIdx() == 0) {
            return coordinate;
          }

          if(coordinate.getRowIdx() + rowOffset >= coordinate.getRowIdx()) {
            return coordinate;
          }

          targetCoordinate = coordinate.getCoordinateWithOffset(rowOffset, colOffset, this.maxRowIdx, this.maxColIdx);

        break;
        case EAST:
          if (coordinate.getColIdx() == this.maxColIdx) {
            return coordinate;
          }

          if(coordinate.getColIdx() + colOffset <= coordinate.getColIdx()) {
            return coordinate;
          }

          targetCoordinate = coordinate.getCoordinateWithOffset(rowOffset, colOffset, this.maxRowIdx, this.maxColIdx);
        break;
        default:
          if (coordinate.getRowIdx() == this.maxRowIdx) {
            return coordinate;
          }

          if(coordinate.getRowIdx() + rowOffset <= coordinate.getRowIdx()) {
            return coordinate;
          }

          targetCoordinate = coordinate.getCoordinateWithOffset(rowOffset, colOffset, this.maxRowIdx, this.maxColIdx);
      }

    return targetCoordinate;
  }
}

class Domino {
  private boolean isFallen = false;
  private int height;

  Domino(int height) {
    this.height = height;
  }

  public boolean isFallen() {
    return this.isFallen;
  }

  public int getNextBeFallenCount() {
    return this.height - 1;
  }

  public void attacked() {
    this.isFallen = true;
  }

  public void restored() {
    this.isFallen = false;
  }

  @Override
  public String toString() {
    return isFallen ? "F" : "S"; 
  }
}

class Coordinate {
  private final int rowIdx;
  private final int colIdx;

  public int getRowIdx() {
    return this.rowIdx;
  }

  public int getColIdx() {
    return this.colIdx;
  }

  public Coordinate getCoordinateWithOffset(int rowOffset, int colOffset, int maxRowIdx, int maxColIdx) {
    int newRow = this.rowIdx + rowOffset;
    int newCol = this.colIdx + colOffset;

    if(newRow < 0) {
      newRow = 0;
    }
    
    if(newCol < 0) {
      newCol = 0;
    }

    if(newRow > maxRowIdx) {
      newRow = maxRowIdx;
    }

    if(newCol > maxColIdx) {
      newCol = maxColIdx;
    }

    return new Coordinate(newRow, newCol);
  }

  @Override
  public boolean equals(Object o) {
    if(this == o) {
      return true;
    }

    if(!(o instanceof Coordinate)) {
      return false;
    }

    Coordinate c = (Coordinate) o;
    return this.rowIdx == c.rowIdx && this.colIdx == c.colIdx; 
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.rowIdx, this.colIdx);
  }

  Coordinate(int row, int col) {
    this.rowIdx = row;
    this.colIdx = col;
  }
}

enum Direction {
  WEST('W', 0, -1), NORTH('N', -1, 0), SOUTH('S', 1, 0), EAST('E', 0, 1);

  char direction;
  int rowOffset;
  int colOffset;

  Direction(char direction, int rowOffset, int colOffset) {
    this.direction = direction;
    this.rowOffset = rowOffset;
    this.colOffset = colOffset;
  }

  public static Direction fromChar(char c) {
    for (Direction d: Direction.values()) {
      if(d.direction == c) {
        return d;
      }
    }

    throw new RuntimeException("Direction Not Found.");
  }
}

class Attack {
  private final Coordinate coordinate;
  private final Direction direction;

  Attack(Coordinate c, Direction d) {
    this.coordinate = c;
    this.direction = d;
  }

  public Coordinate getCoordinate() {
    return this.coordinate;
  }

  public Direction getDirection() {
    return this.direction;
  }
}