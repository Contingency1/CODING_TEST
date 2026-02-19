import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int count = Integer.parseInt(br.readLine());
    for (int i = 0; i < count; i++) {
      char[] input = br.readLine().toCharArray();

      Turtle turtle = new Turtle();

      for (char in : input) {
        Command command = Command.from(in);

        turtle.execute(command);
      }

      System.out.println(turtle.getArea());
    }
  }

  enum Command {
    FORWARD('F'),
    BACKWARD('B'),
    TURN_LEFT('L'),
    TURN_RIGHT('R');

    private final char code;

    Command(char code) {
      this.code = code;
    }

    public static Command from(char code) {
      for (Command c : Command.values()) {
        if (c.code == code) {
          return c;
        }
      }
      throw new RuntimeException("NOOOOOO~~~");
    }
  }


  static class Turtle {

    int x = 0;
    int y = 0;

    int minX = 0;
    int minY = 0;

    int maxX = 0;
    int maxY = 0;

    Direction direction = DirectionImpl.NORTH;

    public Turtle() {
    }

    public void execute(Command command) {
      switch (command) {
        case FORWARD:
          forward();
          break;
        case BACKWARD:
          backward();
          break;
        case TURN_LEFT:
          direction = direction.prevDirection();
          break;
        case TURN_RIGHT:
          direction = direction.nextDirection();
      }
    }

    public void forward() {
      move(1);
    }

    public void backward() {
      move(-1);
    }

    private void move(int multiplier) {
      CoordinateOffset coordinateOffset = direction.getCoordinateOffset();

      int deltaX = coordinateOffset.deltaX * multiplier;
      int deltaY = coordinateOffset.deltaY * multiplier;

      int newX = this.x + deltaX;
      int newY = this.y + deltaY;

      renewCoordinateRecord(newX, newY);

      this.x = newX;
      this.y = newY;
    }

    private void renewCoordinateRecord(int newX, int newY) {
      this.minX = Math.min(minX, newX);
      this.maxX = Math.max(maxX, newX);

      this.minY = Math.min(minY, newY);
      this.maxY = Math.max(maxY, newY);
    }

    public int getArea() {
      if (maxX == minX || maxY == minY) {
        return 0;
      }

      return (maxX - minX) * (maxY - minY);
    }
  }

  static class CoordinateOffset {

    int deltaX;
    int deltaY;

    public CoordinateOffset(int deltaX, int deltaY) {
      this.deltaX = deltaX;
      this.deltaY = deltaY;
    }
  }

  interface Direction {

    CoordinateOffset getCoordinateOffset();

    Direction nextDirection();

    Direction prevDirection();
  }

  enum DirectionImpl implements Direction {
    NORTH(0, 1) {
      @Override
      public Direction nextDirection() {
        return EAST;
      }

      @Override
      public Direction prevDirection() {
        return WEST;
      }
    },
    EAST(1, 0) {
      @Override
      public Direction nextDirection() {
        return SOUTH;
      }

      @Override
      public Direction prevDirection() {
        return NORTH;
      }
    },
    SOUTH(0, -1) {
      @Override
      public Direction nextDirection() {
        return WEST;
      }

      @Override
      public Direction prevDirection() {
        return EAST;
      }
    },
    WEST(-1, 0) {
      @Override
      public Direction nextDirection() {
        return NORTH;
      }

      @Override
      public Direction prevDirection() {
        return SOUTH;
      }
    };

    private final int deltaX;
    private final int deltaY;

    DirectionImpl(int deltaX, int deltaY) {
      this.deltaX = deltaX;
      this.deltaY = deltaY;
    }

    @Override
    public CoordinateOffset getCoordinateOffset() {
      return new CoordinateOffset(deltaX, deltaY);
    }

    @Override
    public abstract Direction nextDirection();

    @Override
    public abstract Direction prevDirection();
  }


}