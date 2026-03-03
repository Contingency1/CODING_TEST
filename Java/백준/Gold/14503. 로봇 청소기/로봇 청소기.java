import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;

public class Main {   
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());

    final int ROW_COUNT = Integer.parseInt(st.nextToken());
    final int COL_COUNT = Integer.parseInt(st.nextToken());

    st = new StringTokenizer(br.readLine());

    final int robotRow = Integer.parseInt(st.nextToken());
    final int robotCol = Integer.parseInt(st.nextToken());
    final Direction direction = Direction.from(Integer.parseInt(st.nextToken()));

    Robot robot = new Robot(new Coordinate(robotRow, robotCol), direction);

    boolean[][] input = new boolean[ROW_COUNT][COL_COUNT];

    for (int i = 0; i < ROW_COUNT; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < COL_COUNT; j++) {
        String str = st.nextToken();
        input[i][j] = str.equals("1");
      }
    }

    Board board = new Board(ROW_COUNT, COL_COUNT, robot, input);

    board.execute();

    System.out.print(board.getRobotScore());
  }
}

class Board {
  private int maxRow;
  private int maxCol;
  private Robot robot;
  private Cell[][] cell;

  public int getRobotScore() {
    return this.robot.getCleanCount();
  }
  Board(int maxRow, int maxCol, Robot robot, boolean[][] input) {
    this.maxRow = maxRow;
    this.maxCol = maxCol;
    this.robot = robot;

    cell = new Cell[maxRow][maxCol];

    for (int i = 0; i < maxRow; i++) {
      for (int j = 0; j < maxCol; j++) {
        this.cell[i][j] = new Cell(!input[i][j]);
      }
    }
  }

  public void execute() {
    while(robot.isOn()) {
      Coordinate coordinate = robot.getCoordinate();
      robot.firstJob(cell[coordinate.getRow()][coordinate.getCol()]);

      if (isThereUncleaned()) {
        robot.turnLeft();
        Coordinate front = robot.getFrontCoordinate();

        if (front.validate(this.maxRow, this.maxCol) &&
        cell[front.getRow()][front.getCol()].isRoom() &&
      !cell[front.getRow()][front.getCol()].isCleaned()) {
          robot.forward();
        }
      } else {
        Coordinate back = robot.getBackCoordinate();

        if(back.validate(this.maxRow, this.maxCol) && 
        cell[back.getRow()][back.getCol()].isRoom()) {
          robot.backward();
        } else {
          robot.turnOff();
        }
      }
    }
  }


  private boolean isThereUncleaned() {
    List<Coordinate> coordinates = robot.getNearCoordinate(this.maxRow, this.maxCol);

    for (Coordinate c: coordinates) {
      int row = c.getRow();
      int col = c.getCol();

      if(!cell[row][col].isRoom()){
        continue;
      }

      if (!cell[row][col].isCleaned()) {
        return true;
      }
    }

    return false;
  }
}

class Robot {
  Coordinate coordinate;
  Direction direction;
  int cleanCount;
  private static int[][] OFFSET = {{1, 0}, {0, 1}, {-1, 0}, {0 , -1}};
  boolean isOn = true;

  Robot(Coordinate coordinate, Direction direction) {
    this.coordinate = coordinate;
    this.direction = direction;
  }

  private boolean isRoomCleaned(Cell room) {
    if(room.isCleaned()) {
      return true;
    }

    return false;
  }

  public void forward() {
    this.coordinate = getFrontCoordinate();
  }

  public void backward() {
    this.coordinate = getBackCoordinate();
  }

  public Coordinate getFrontCoordinate() {
    int newRow = this.coordinate.getRow() + direction.rowOffset;
    int newCol = this.coordinate.getCol() + direction.colOffset;

    return new Coordinate(newRow, newCol);
  }

  public Coordinate getBackCoordinate() {
    int newRow = this.coordinate.getRow() - direction.rowOffset;
    int newCol = this.coordinate.getCol() - direction.colOffset;

    return new Coordinate(newRow, newCol);
  }

  public Coordinate getCoordinate() {
    return this.coordinate;
  }

  public void turnOff() {
    this.isOn = false;
  }

  public boolean isOn() {
    return this.isOn;
  }

  public List<Coordinate> getNearCoordinate(int maxRow, int maxCol) {
    List<Coordinate> result = new ArrayList<>();

    for (int[] off: OFFSET) {
      int newRow = this.coordinate.getRow() + off[0];
      int newCol = this.coordinate.getCol() + off[1];

      if(newRow >= 0 && newRow < maxRow && newCol >= 0 && newCol < maxCol) {
        result.add(new Coordinate(newRow, newCol));
      }
    }

    return result;
  }

  public void firstJob(Cell room) {
    if(isRoomCleaned(room)) {
      return;
    }

    this.cleanCount++;
    room.beCleaned();
  }

  public void turnLeft() {
    this.direction = this.direction.turnLeft();
  }

  public int getCleanCount() {
    return this.cleanCount;
  }
}

class Cell {
  private boolean isRoom;
  private boolean isCleaned = false;

  Cell(boolean isRoom) {
    this.isRoom = isRoom;
  }

  public boolean isRoom() {
    return this.isRoom;
  }

  public boolean isCleaned() {
    return this.isCleaned;
  }

  public void beCleaned() {
    this.isCleaned = true;
  }
}

class Coordinate {
  private int row;
  private int col;

  public int getRow() {
    return this.row;
  }

  public int getCol() {
    return this.col;
  }

  public boolean validate(int maxRow, int maxCol) {
    return this.row >= 0 && this.row < maxRow && this.col >= 0 && this.col < maxCol;
  }

  Coordinate(int row, int col) {
    this.row = row;
    this.col = col;
  }
}

enum Direction {
  NORTH(0, -1, 0) {
    @Override
    public Direction turnLeft() {
      return WEST;
    }

  }, 
  EAST(1, 0, 1) {
    @Override
    public Direction turnLeft() {
      return NORTH;
    }
  }, 
  SOUTH(2, 1, 0) {
    @Override
    public Direction turnLeft() {
      return EAST;
    }
  }, 
  WEST(3, 0, -1) {
    @Override
    public Direction turnLeft() {
      return SOUTH;
    }
  };
  
  int number;
  int rowOffset;
  int colOffset;

  Direction(int input, int rowOffset, int colOffset) {
    this.number = input;
    this.rowOffset = rowOffset;
    this.colOffset = colOffset;
  }

  public static Direction from(int input) {
    for (Direction d: Direction.values()) {
      if(d.number == input) {
        return d;
      }
    }

    throw new RuntimeException("NOOOO");
  }

  public abstract Direction turnLeft();
}