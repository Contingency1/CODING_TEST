import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Queue;
import java.util.ArrayDeque;
import java.util.List;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int count = Integer.parseInt(br.readLine());

    List<Dice> dices = new ArrayList<>();

    
    for (int i = 0; i < count; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());

      addDice(dices, st);
    }

    int answer = 0;

    for (int i = 0; i < 3; i++) {
      int buffer = 0;

      buffer += dices.get(0).getPairMax();

      for (int j = 1; j < count; j++) {
        Dice prev = dices.get(j - 1);
        Dice cur = dices.get(j);

        if(prev.bottom != cur.top) {
          cur.refixByPrev(prev.bottom);
        }

        buffer += cur.getPairMax();
      }

      answer = Math.max(answer, buffer);

      dices.get(0).reverse();
      buffer = dices.get(0).getPairMax();


      for (int j = 1; j < count; j++) {
        Dice prev = dices.get(j - 1);
        Dice cur = dices.get(j);

        if(prev.bottom != cur.top) {
          cur.refixByPrev(prev.bottom);
        }

        buffer += cur.getPairMax();
      }

      answer = Math.max(answer, buffer);

      dices.get(0).nextPair();
    }

    System.out.print(answer);
  }

  private static void addDice(List<Dice> dices, StringTokenizer st) {
    int a = Integer.parseInt(st.nextToken());
    int b = Integer.parseInt(st.nextToken());
    int c = Integer.parseInt(st.nextToken());
    int d = Integer.parseInt(st.nextToken());
    int e = Integer.parseInt(st.nextToken());
    int f = Integer.parseInt(st.nextToken());

    dices.add(new Dice(a, b, c, d, e, f));
  }

  static class Dice {
    int top;
    int bottom;

    Pairs pair;

    Dice (int a, int b, int c, int d, int e, int f) {
      top = a;
      pair = new Pairs(b, c, d, e);
      bottom = f;
    }

    @Override()
    public String toString() {
      return "[Dice]" + " top: " + this.top + ", bottom: " + this.bottom + ", pair: [" + this.pair + "]\n";
    }
  
       
    public void refixByPrev(int bottom) {
      while (true) {
        Pair buffer = nextPair();

        if(buffer.isMatched(bottom)) {
          if(bottom != this.top) {
            reverse();
          }

          break;
        }
      }
    }
    
    class Pairs {
      @Override()
      public String toString() {
        StringBuilder sb = new StringBuilder();

        for (Pair p: pair) {
          sb.append(p);
        }

        return sb.toString();
      }

      Queue<Pair> pair = new ArrayDeque<>();

      Pairs(int a, int b, int c, int d) {
        pair.add(new Pair(a, c));
        pair.add(new Pair(b, d));
      }

      public Pair pollPair() {
        return pair.poll();
      }
      
      public void addPair(Pair p) {
        pair.add(p);
      }

      public int getMax() {
        int max = 0;

        for (Pair p: pair) {
          max = Math.max(max, Math.max(p.one, p.two));
        } 

        return max;
      }
    }

    class Pair {
      int one, two;

      Pair(int one, int two) {
        this.one = one;
        this.two = two;
      }

      public boolean isMatched(int input) {
        if(input == this.one || input == this.two) {
          return true;
        }
        
        return false;
      }


      @Override() 
      public String toString() {
        return " one: " +one + ", two: " + two;
      }
    }

    public int getPairMax() {
      return pair.getMax();
    }

    public Pair nextPair() {
      Pair newTopAndBottom = pair.pollPair();

      pair.addPair(new Pair(top, bottom));

      this.top = newTopAndBottom.one;
      this.bottom = newTopAndBottom.two;

      return newTopAndBottom;
    }

    public void reverse() {
      int buffer = top;

      this.top = this.bottom;
      this.bottom = buffer;
    }

  }
}