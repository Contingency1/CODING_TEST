import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.List;
import java.util.ArrayDeque;

  
public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());

    final int ROW_COUNT = Integer.parseInt(st.nextToken());
    final int COL_COUNT = Integer.parseInt(st.nextToken());

    boolean[][] board = new boolean[ROW_COUNT][COL_COUNT];

    for (int i = 0; i < ROW_COUNT; i++) {
      st = new StringTokenizer(br.readLine());

      for (int j = 0; j < COL_COUNT; j++) {
        boolean canPass = st.nextToken().equals("0");
        board[i][j] = canPass;
      }
    }

    st = new StringTokenizer(br.readLine());

    int startRow = Integer.parseInt(st.nextToken()) - 1;
    int startCol = Integer.parseInt(st.nextToken()) - 1;
    DIRECTION startDirection = DIRECTION.from(Integer.parseInt(st.nextToken()));

    Robot start = new Robot(new Coordinate(startRow, startCol), startDirection, 0);

    st = new StringTokenizer(br.readLine());

    int endRow = Integer.parseInt(st.nextToken()) - 1;
    int endCol = Integer.parseInt(st.nextToken()) - 1;
    DIRECTION endDirection = DIRECTION.from(Integer.parseInt(st.nextToken()));

    Robot end = new Robot(new Coordinate(endRow, endCol), endDirection, 0);
    
    boolean[][][] visited = new boolean[ROW_COUNT][COL_COUNT][5];

    ArrayDeque<Robot> queue = new ArrayDeque<>();
    visited[startRow][startCol][startDirection.getValue()] = true;
    queue.addLast(start);

    while(!queue.isEmpty()) {
      Robot cur = queue.pollFirst();
      
      if(end.equals(cur)) {
        System.out.print(cur.count);
        break;
      }

      List<Robot> next = cur.getNextRobots(ROW_COUNT - 1, COL_COUNT - 1, visited, board);

      for(Robot r: next) {
        queue.add(r);
        visited[r.coordinate.row][r.coordinate.col][r.direction.getValue()] = true;
      }
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

  @Override
  public boolean equals(Object o) {
    if(!(o instanceof Coordinate)) {
      return false;
   }

    Coordinate c = (Coordinate) o;
    return this.row == c.row && this.col == c.col;
  }
}

class Robot{
  Coordinate coordinate;
  DIRECTION direction;
  int count;

  private Robot getTurnLeftRobot() {
    return new Robot(coordinate, direction.turnLeft(), this.count + 1); 
  }

  private Robot getTurnRightRobot() {
    return new Robot(coordinate, direction.turnRight(), this.count + 1); 
  }

  private boolean isVisited(boolean[][][] visited) {
    if(visited[coordinate.row][coordinate.col][direction.getValue()]) {
      return true;
    }

    return false;
  }

  public List<Robot> getNextRobots(int maxRow, int maxCol, boolean[][][] visited, boolean[][] board) {
    List<Robot> result = new ArrayList<>();

    Robot left = this.getTurnLeftRobot();

    if (!left.isVisited(visited)) {
      result.add(left);
    }

    Robot right = this.getTurnRightRobot();

    if(!right.isVisited(visited)) {
      result.add(right);
    }
          
    Difference diff = this.direction.getDiffrence();

    int rowDiff = diff.rowDiff;
    int colDiff = diff.colDiff;

    for (int i = 1; i <= 3; i++) {
      int newRow = this.coordinate.row + i * rowDiff;
      int newCol = this.coordinate.col + i * colDiff;

      if (newRow < 0 || newRow > maxRow || newCol < 0 || newCol > maxCol || !board[newRow][newCol]) {
        break;
      }

      Robot newRobot = new Robot(new Coordinate(newRow, newCol), this.direction, this.count + 1);

      if(newRobot.isVisited(visited)) {
        continue;
      }

      result.add(newRobot);
    }

    return result;
  }

  Robot(Coordinate coordiante, DIRECTION direction, int count) {
    this.coordinate = coordiante;
    this.direction = direction;
    this.count = count;
  }

  @Override
  public boolean equals(Object o) {
    if(!(o instanceof Robot)) {
      return false;
    }

    Robot other = (Robot) o;

    return this.coordinate.equals(other.coordinate) 
    && this.direction.equals(other.direction);
  }
}

class Difference {
  final int rowDiff;
  final int colDiff;

  Difference(int row, int col) {
    this.rowDiff = row;
    this.colDiff = col;
  }
}

enum DIRECTION {
  EAST(1, 0, 1) {
    @Override
    public DIRECTION turnLeft() {
      return NORTH;
    }
    @Override
    public DIRECTION turnRight() {
      return SOUTH;
    }
  },
  WEST(2, 0, -1) {
    @Override
    public DIRECTION turnLeft() {
      return SOUTH;
    }
    @Override
    public DIRECTION turnRight() {
      return NORTH;
    }
  }, 
  SOUTH(3, 1, 0) {
    @Override
    public DIRECTION turnLeft() {
      return EAST;
    }
    @Override
    public DIRECTION turnRight() {
      return WEST;
    }
  },  
  NORTH(4, -1, 0) {
    @Override
    public DIRECTION turnLeft() {
      return WEST;
    }
    @Override
    public DIRECTION turnRight() {
      return EAST;
    }
  };

  final private int value;
  final private Difference diff;

  DIRECTION(int value, int rowDiff, int colDiff) {
    this.value = value;
    this.diff = new Difference(rowDiff, colDiff);
  }

  public int getValue() {
    return this.value;
  }

  public Difference getDiffrence() {
    return this.diff;
  }

  abstract DIRECTION turnRight();
  abstract DIRECTION turnLeft();

  static DIRECTION from(int value) {
    for (DIRECTION d: DIRECTION.values()) {
      if(d.value == value) {
        return d;
      }
    }

    throw new RuntimeException("NOOOoooo");
  }
}