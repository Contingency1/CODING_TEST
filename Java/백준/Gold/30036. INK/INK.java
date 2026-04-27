import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    final int INK_COUNT = Integer.parseInt(st.nextToken());
    final int BOARD_LENGTH = Integer.parseInt(st.nextToken());
    final int COMMAND_COUNT = Integer.parseInt(st.nextToken());

    char[] colors = br.readLine().toCharArray();

    InkColor inkColor = new InkColor(colors);

    char[][] grid = new char[BOARD_LENGTH][BOARD_LENGTH];

    Coordinate start = null;

    for (int i = 0; i < BOARD_LENGTH; i++) {
      char[] chars = br.readLine().toCharArray();
      for (int j = 0; j < BOARD_LENGTH; j++) {
        grid[i][j] = chars[j];

        if (grid[i][j] == '@') {
          start = new Coordinate(i, j);
          grid[i][j] = '.';
        }
      }
    }

    Board board = new Board(grid, BOARD_LENGTH - 1, BOARD_LENGTH - 1, inkColor);
    Rectangle rectangle = new Rectangle(0, start);
    char[] inputCommand = br.readLine().toCharArray();
    List<Command> list = makeCommand(inputCommand);

    for (Command command : list) {
      command.execute(rectangle, board);
    }

    char[][] answer = board.getGrid();

    for (int i = 0; i < answer.length; i++) {
      for (int j = 0; j < answer[0].length; j++) {
        if (i == rectangle.getCoordinate().getRow() && j == rectangle.getCoordinate().getCol()) {
          answer[i][j] = '@';
        }
      }
    }

    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < answer.length; i++) {
      for (int j = 0; j < answer[i].length; j++) {
        sb.append(answer[i][j]);
      }
      sb.append('\n');
    }

    System.out.print(sb);
  }

  private static List<Command> makeCommand(char[] inputCommand) {
    List<Command> list = new ArrayList<>();

    for (char c : inputCommand) {
      switch (c) {
        case 'U':
          list.add(new MoveCommand(DIRECTION.UP));
          break;
        case 'D':
          list.add(new MoveCommand(DIRECTION.DOWN));
          break;
        case 'L':
          list.add(new MoveCommand(DIRECTION.LEFT));
          break;
        case 'R':
          list.add(new MoveCommand(DIRECTION.RIGHT));
          break;
        case 'j':
          list.add(new ChargeCommand());
          break;
        case 'J':
          list.add(new JumpCommand());
          break;
        default:
          throw new RuntimeException("NOOOOO");
      }
    }
    return list;
  }
}

class Board {

  private final char[][] grid;
  private final int maxRow;
  private final int maxCol;
  private final InkColor inkColor;

  public char[][] getGrid() {
    return grid;
  }

  public Board(char[][] grid, int maxRow, int maxCol, InkColor inkColor) {
    this.grid = grid;
    this.maxRow = maxRow;
    this.maxCol = maxCol;
    this.inkColor = inkColor;
  }

  public void paint(List<Coordinate> coordinates) {
    char nextColor = inkColor.getNextColor();

    for (Coordinate coordinate : coordinates) {
      if (isValidCoordinateToPaint(coordinate)) {
        int row = coordinate.getRow();
        int col = coordinate.getCol();

        this.grid[row][col] = nextColor;
      }
    }
  }

  public boolean isValidCoordinateToPaint(Coordinate coordinate) {
    int row = coordinate.getRow();
    int col = coordinate.getCol();

    return row >= 0 && row <= maxRow && col >= 0 && col <= maxCol
        && (
        grid[row][col] == '#'
            || (grid[row][col] >= 65 && grid[row][col] <= 90)
    );
  }

  public boolean isValidCoordinateToMove(Coordinate coordinate) {
    int row = coordinate.getRow();
    int col = coordinate.getCol();

    return row >= 0 && row <= maxRow && col >= 0 && col <= maxCol
        && grid[row][col] == '.';
  }
}

class Coordinate {

  private final int row;
  private final int col;

  public int getRow() {
    return row;
  }

  public int getCol() {
    return col;
  }

  public Coordinate(int row, int col) {
    this.row = row;
    this.col = col;
  }
}

class InkColor {

  private int idx = 0;
  private final char[] color;

  public InkColor(char[] color) {
    this.color = color;
  }

  public char getNextColor() {
    if (this.idx > color.length - 1) {
      this.idx = 0;
    }

    return color[this.idx++];
  }
}

class Rectangle {

  private int leftInk;
  private Coordinate coordinate;

  public Coordinate getCoordinate() {
    return coordinate;
  }

  public Rectangle(int leftInk, Coordinate coordinate) {
    this.leftInk = leftInk;
    this.coordinate = coordinate;
  }

  public void chargeInk() {
    this.leftInk++;
  }

  public List<Coordinate> jump() {
    List<Coordinate> result = new ArrayList<>();

    int currentRow = this.coordinate.getRow();
    int currentCol = this.coordinate.getCol();

    for (int r = -leftInk; r <= leftInk; r++) {
      for (int c = -leftInk; c <= leftInk; c++) {
        if (Math.abs(r) + Math.abs(c) <= leftInk) {
          result.add(new Coordinate(currentRow + r, currentCol + c));
        }
      }
    }

    this.leftInk = 0;

    return result;
  }

  public Coordinate predictNextCoordinate(DIRECTION direction) {
    int newRow = this.coordinate.getRow() + direction.getRowDiff();
    int newCol = this.coordinate.getCol() + direction.getColDiff();
    return new Coordinate(newRow, newCol);
  }

  public void moveTo(Coordinate newCoordinate) {
    this.coordinate = newCoordinate;
  }

}

enum DIRECTION {
  UP(-1, 0), DOWN(1, 0),
  LEFT(0, -1), RIGHT(0, 1);

  private final int rowDiff;
  private final int colDiff;

  public int getRowDiff() {
    return rowDiff;
  }

  public int getColDiff() {
    return colDiff;
  }

  DIRECTION(int rowDiff, int colDiff) {
    this.rowDiff = rowDiff;
    this.colDiff = colDiff;
  }
}

class MoveCommand implements Command {

  private final DIRECTION direction;

  public MoveCommand(DIRECTION direction) {
    this.direction = direction;
  }

  @Override
  public void execute(Rectangle rectangle, Board board) {
    Coordinate next = rectangle.predictNextCoordinate(direction);

    if (board.isValidCoordinateToMove(next)) {
      rectangle.moveTo(next);
    }
  }
}

class JumpCommand implements Command {

  @Override
  public void execute(Rectangle rectangle, Board board) {
    List<Coordinate> coordinates = rectangle.jump();

    board.paint(coordinates);
  }
}

class ChargeCommand implements Command {

  @Override
  public void execute(Rectangle rectangle, Board board) {
    rectangle.chargeInk();
  }
}

interface Command {

  void execute(Rectangle rectangle, Board board);
}
