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
        if (in == 'F' || in == 'B') {
          turtle.move(in);
        } else if (in == 'L') {
          turtle.turnLeft();
        } else {
          turtle.turnRight();
        }
      }

      System.out.println(turtle.getArea());
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

    public void turnLeft() {
      direction = direction.prevDirection();
    }

    public void turnRight() {
      direction = direction.nextDirection();
    }

    public void move(char word) {
      CoordinateOffset coordinateOffset = direction.getCoordinateOffset(word);

      int deltaX = coordinateOffset.deltaX;
      int deltaY = coordinateOffset.deltaY;

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

    CoordinateOffset getCoordinateOffset(char word);

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
    public CoordinateOffset getCoordinateOffset(char word) {
      if (word == 'F') {
        return new CoordinateOffset(deltaX, deltaY);
      }

      return new CoordinateOffset(-deltaX, -deltaY);
    }

    @Override
    public abstract Direction nextDirection();

    @Override
    public abstract Direction prevDirection();
  }


}