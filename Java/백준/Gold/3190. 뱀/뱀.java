import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayDeque;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    final int LENGTH = Integer.parseInt(br.readLine());
    final int APPLE_COUNT = Integer.parseInt(br.readLine());

    Cell[][] cell = new Cell[LENGTH][LENGTH];

    for (int i = 0; i < LENGTH; i++) {
      for (int j = 0; j < LENGTH; j++) {
        cell[i][j] = new Cell();
      }
    }
    
    StringTokenizer st;
 
    for (int i = 0; i < APPLE_COUNT; i++) {
      st = new StringTokenizer(br.readLine());

      int x = Integer.parseInt(st.nextToken()) - 1;
      int y = Integer.parseInt(st.nextToken()) - 1;

      cell[x][y].makeApple();
    }

    cell[0][0].makeSnake();


    final int MOVE_COUNT = Integer.parseInt(br.readLine());

    ArrayDeque<Command> command = new ArrayDeque<>();

    for (int i = 0; i < MOVE_COUNT; i++) {
      st = new StringTokenizer(br.readLine());

      int sec = Integer.parseInt(st.nextToken());
      TURN turn = TURN.from(st.nextToken().charAt(0));
      
      command.addLast(new Command(sec, turn));
    }

    Snake snake = new Snake();

    int seconds = 0;

    while(true) {
      seconds++;
      Coordinate curCoordinate = snake.getNextMove();

      int nextRow = curCoordinate.row;
      int nextCol = curCoordinate.col;

      if(nextRow < 0 || nextRow >= LENGTH ||
        nextCol < 0 || nextCol >= LENGTH || cell[nextRow][nextCol].isSnake
      ) {
        break;
      }
      
      snake.move();
      Coordinate head = snake.getHead();
      cell[head.row][head.col].makeSnake();

      if(cell[nextRow][nextCol].isApple) {
        cell[nextRow][nextCol].noApple();
      } else {
        Coordinate tail = snake.getTail();
        snake.removeTail();
        cell[tail.row][tail.col].noSnake();
      }

      if(!command.isEmpty()) {
        Command c = command.peekFirst();
        
        if(c.sec == seconds) {
          command.pollFirst();
          snake.turn(c.turn);
        }
      }
    }

    System.out.print(seconds);

  }
}

class Cell {
  boolean isApple;
  boolean isSnake;

  public void makeApple() {
    this.isApple = true;
  }

  public void noApple(){
    this.isApple = false;
  }

  public void makeSnake() {
    this.isSnake = true;
  }

  public void noSnake() {
    this.isSnake = false;
  }
}

class Snake {
  DIRECTION direction = DIRECTION.RIGHT;
  ArrayDeque<Coordinate> coordinates = new ArrayDeque<>();

  Snake() {
    this.coordinates.add(new Coordinate(0, 0));
  }

  public Coordinate getHead() {
    return this.coordinates.peekLast();
  }

  public void move() {
    Coordinate head = coordinates.peekLast();

    Coordinate newCoord = 
      new Coordinate(head.row + this.direction.row, head.col + this.direction.col);
    
    this.coordinates.addLast(newCoord);
  }

  public Coordinate getNextMove() {
    Coordinate head = coordinates.peekLast();

    return new Coordinate(head.row + this.direction.row, head.col + this.direction.col);
  }
  
  public void removeTail() {
    this.coordinates.removeFirst();
  }

  public Coordinate getTail() {
    return this.coordinates.peekFirst();
  }

  public void turn(TURN turn) {
    this.direction = turn.turn(this.direction);
  }
}

enum DIRECTION {
  LEFT(0, -1) {
    @Override
    public DIRECTION next(){
      return UP;
    }

    @Override
    public DIRECTION prev(){
      return DOWN;
    }
  },
  UP(-1, 0) {
    @Override
    public DIRECTION next(){
      return RIGHT;
    }

    @Override
    public DIRECTION prev(){
      return LEFT;
    }
  }, 
  RIGHT(0, 1) {
    @Override
    public DIRECTION next(){
      return DOWN;
    }

    @Override
    public DIRECTION prev(){
      return UP;
    }
  }, 
  DOWN(1, 0) {
    @Override
    public DIRECTION next(){
      return LEFT;
    }

    @Override
    public DIRECTION prev(){
      return RIGHT;
    }
  };

  int row;
  int col;

  DIRECTION(int row, int col) {
    this.row = row;
    this.col = col;
  }

  public abstract DIRECTION next();
  public abstract DIRECTION prev();
}

enum TURN {
  LEFT('L') {
    @Override
    public DIRECTION turn(DIRECTION dir) {
      return dir.prev();
    }
  },

  RIGHT('D') {
    @Override
    public DIRECTION turn(DIRECTION dir) {
      return dir.next();
    }
  };

  char character;

  TURN(char character) {
    this.character = character;
  }

  public abstract DIRECTION turn(DIRECTION dir);

  public static TURN from(char character) {
    for (TURN t: TURN.values()) {
      if(t.character == character) {
        return t;
      }
    }

    throw new RuntimeException("NOPE");
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

class Command {
  final int sec;
  final TURN turn;

  Command(int sec, TURN turn) {
    this.sec = sec;
    this.turn = turn;
  }
}