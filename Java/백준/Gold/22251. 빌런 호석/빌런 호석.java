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
    
    int MAX_FLOOR = Integer.parseInt(st.nextToken());
    int NUMBER_COUNT = Integer.parseInt(st.nextToken());
    int MAX_REVERSE_COUNT = Integer.parseInt(st.nextToken());
    int CURRENT_FLOOR = Integer.parseInt(st.nextToken());
    
    Board board = new Board(NUMBER_COUNT, CURRENT_FLOOR);
 
    BoardCalculateService service = new BoardCalculateService(MAX_FLOOR, MAX_REVERSE_COUNT, NUMBER_COUNT, board);

    int answer = service.getPossibleCount();

    System.out.print(answer);
  }

  static class BoardCalculateService {
    int maxFloor;
    int maxReverseCount;
    int numberCount;
    Board target;

    BoardCalculateService(int maxFloor, int maxReverseCount, int numberCount, Board board) {
      this.maxFloor = maxFloor;
      this.maxReverseCount = maxReverseCount;
      this.numberCount = numberCount;
      this.target = board;
    }

    public int getPossibleCount() {
      int result = 0;

      for (int i = 1; i <= this.maxFloor; i++) {
        if (this.target.getCurFloor() == i) {
          continue;
        }

        Board cur = new Board(numberCount ,i);

        if(this.target.canMake(cur, maxReverseCount)){
          result++;
        }
      }

      return result;
    }

  }

  static class Board {
    private int curFloor;
    private List<Place> places = new ArrayList<>();

    Board(int numberCount, int currentFloor) {
      this.curFloor = currentFloor;

      for (int i = numberCount; i >= 1; i--) {
        int ten = (int) Math.pow(10, i - 1);
        int input = (currentFloor / ten) % 10;

        this.places.add(Place.fromInt(input));
      }
    }

    public boolean canMake(Board o, int max) {
      List<Place> targetPlaces = o.getPlaces();

      int result = 0;

      for (int i = 0; i < places.size(); i++) {
        int diff = places.get(i).getDiffCount(targetPlaces.get(i));
        
        result += diff;
        
        if (max < result) {
          return false;
        }
      }

      return true;
    }

    public List<Place> getPlaces() {
      return this.places;
    }

    public int getCurFloor() {
      return this.curFloor;
    }

  }

  enum Place {
    ZERO(0, true, true, true, true, true, true, false),
    ONE(1, false, true, true, false, false, false, false),
    TWO(2, true, true, false, true, true, false, true),
    THREE(3, true, true, true, true, false, false, true),
    FOUR(4, false, true, true, false, false, true, true),
    FIVE(5, true, false, true, true, false, true, true),
    SIX(6, true, false, true, true, true, true, true),
    SEVEN(7, true, true, true, false, false, false, false),
    EIGHT(8, true, true, true, true, true, true, true),
    NINE(9, true, true, true, true, false, true, true);

    int value;
    Led[] leds = new Led[7];

    static int[][] CACHE = new int[10][10];

    Place(int value, boolean... input) {
      this.value = value;

      for (int i = 0; i < 7; i++) {
        this.leds[i] = new Led(input[i]);
      }

    }

    public int getDiffCount(Place o) {
      int result = 0;

      if (this.value == o.value) {
        return 0;
      }

      if(CACHE[this.value][o.value] != 0) {
        return CACHE[this.value][o.value];
      }

      for (int i = 0; i < this.leds.length; i++) {
        if(this.leds[i].isDiff(o.leds[i])) {
          result++;
        }
      }

      CACHE[this.value][o.value] = result;
      return result;
    }

    static public Place fromInt(int input) {
      for (Place p: Place.values()) {
        if(p.value == input) {
          return p;
        }
      }

      throw new RuntimeException("NOOOO");
    }
  }

  static class Led {
    private boolean power;

    Led (boolean power) {
      this.power = power;
    }

    public boolean getPower() {
      return this.power;
    }

    public boolean isDiff(Led led) {
      if (this.power != led.getPower()) {
        return true;
      }

      return false;
    }

  }
}
