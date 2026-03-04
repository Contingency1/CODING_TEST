import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

public class Main {   
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st;

    List<Combination> list = new ArrayList<>();
    list.add(null);

    for (int i = 0; i < 9; i++) {
      st = new StringTokenizer(br.readLine());

      SHAPE shape = SHAPE.from(st.nextToken());
      COLOR color = COLOR.from(st.nextToken());
      BACK back = BACK.from(st.nextToken());

      Combination comb = new Combination(shape, color, back);
      list.add(comb);
    }

    Set<Integer> hab = new HashSet<>();

    for (int i = 1; i <= 9; i++) {
      Combination c1 = list.get(i);
      for (int j = i + 1; j <= 9; j++) {
        Combination c2 = list.get(j);
        for (int k = j + 1; k <= 9; k++) {
          Combination c3 = list.get(k);
          if(c1.isHab(c2, c3)) {
            hab.add(i * 100 + j *10 + k);
          }
        }
      }
    }

    int count = Integer.parseInt(br.readLine());

    int answer = 0;
    boolean visitedG = false;

    for (int i = 0; i < count; i++) {
      st = new StringTokenizer(br.readLine());

      String command = st.nextToken();

      if(command.equals("G") ) {
        if(hab.size() == 0 && !visitedG) {
          answer += 3;
          visitedG = true;
        } else {
          answer--;
        }
        continue;
      }
      
      int one = Integer.parseInt(st.nextToken());
      int two = Integer.parseInt(st.nextToken());
      int three = Integer.parseInt(st.nextToken());

      Combination cOne = list.get(one);
      Combination cTwo = list.get(two);
      Combination cThree = list.get(three);

      if(cOne.isHab(cTwo, cThree)) {
        List<Integer> buffer = new ArrayList<>();
        buffer.add(one);
        buffer.add(two);
        buffer.add(three);

        buffer.sort(null);

        int key = buffer.get(0) * 100 + buffer.get(1) * 10 + buffer.get(2);

        if(hab.contains(key)) {
          hab.remove(key);
          answer++;
        } else {
          answer--;
        }
      } else {
        answer--;
      }
    }

    System.out.print(answer);
  }
}

class Combination {
  SHAPE shape;
  COLOR color;
  BACK back;

  Combination(SHAPE shape, COLOR color, BACK back) {
    this.shape = shape;
    this.color = color;
    this.back = back;
  }

  // 기합이냐? 와 이놈 합인데??
  public boolean isHab(Combination one, Combination two) {
    boolean shape = checkShape(one.shape, two.shape);
    boolean color = checkColor(one.color, two.color);
    boolean back = checkBack(one.back, two.back);

    return shape && color && back;
  }

  private boolean checkShape(SHAPE one, SHAPE two) {
    if(this.shape.equals(one) && this.shape.equals(two) ) {
      return true;
    }

    if(!(this.shape.equals(one)) && !(this.shape.equals(two)) && !(one.equals(two))) {
      return true;
    }

    return false;
  }  
  
  private boolean checkColor(COLOR one, COLOR two) {
    if(this.color.equals(one) && this.color.equals(two) ) {
      return true;
    }

    if(!(this.color.equals(one)) && !(this.color.equals(two)) && !(one.equals(two))) {
      return true;
    }

    return false;
  } 
   
  private boolean checkBack(BACK one, BACK two) {
    if(this.back.equals(one) && this.back.equals(two) ) {
      return true;
    }

    if(!(this.back.equals(one)) && !(this.back.equals(two)) && !(one.equals(two))) {
      return true;
    }

    return false;
  }

  @Override
  public boolean equals(Object o) {
    if(this == o) {
      return true;
    }

    if(!(o instanceof Combination)) {
      return false;
    }

    Combination c = (Combination) o;

    return this.shape.equals(c.shape) && this.color.equals(c.color) && this.back.equals(c.back);
  }
}

enum SHAPE {
  CIRCLE("CIRCLE"), SQUARE("SQUARE"), TRIANGLE("TRIANGLE");

  String str;

  SHAPE(String str) {
    this.str = str;
  }

  public static SHAPE from(String input) {
    for (SHAPE b: SHAPE.values()) {
      if(b.str.equals(input)) {
        return b;
      }
    }

    throw new RuntimeException("NOO");
  }
}

enum COLOR {
  RED("RED"), YELLOW("YELLOW"), BLUE("BLUE");

  String str;

  COLOR(String str) {
    this.str = str;
  }
    
  public static COLOR from(String input) {
    for (COLOR b: COLOR.values()) {
      if(b.str.equals(input)) {
        return b;
      }
    }

    throw new RuntimeException("NOO");
  }
}

enum BACK {
  GRAY("GRAY"), BLACK("BLACK"), WHITE("WHITE");

  String str;

  BACK(String str) {
    this.str = str;
  }

  public static BACK from(String input) {
    for (BACK b: BACK.values()) {
      if(b.str.equals(input)) {
        return b;
      }
    }

    throw new RuntimeException("NOO");
  }
}