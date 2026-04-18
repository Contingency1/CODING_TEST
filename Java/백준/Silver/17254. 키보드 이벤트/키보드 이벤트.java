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

    final int KEYBOARD_COUNT = Integer.parseInt(st.nextToken());
    final int CLICK_COUNT = Integer.parseInt(st.nextToken());

    List<Key> list = new ArrayList<>();

    for (int i = 0; i < CLICK_COUNT; i++) {
      st = new StringTokenizer(br.readLine());

      int number = Integer.parseInt(st.nextToken());
      int second = Integer.parseInt(st.nextToken());
      char character = st.nextToken().charAt(0);
      list.add(new Key(number, second, character));
    }

    list.sort(null);

    for (Key key : list) {
      System.out.print(key);
    }

  }


}

class Key implements Comparable<Key> {

  int number;
  int second;
  char character;

  public Key(int number, int second, char character) {
    this.number = number;
    this.second = second;
    this.character = character;
  }

  @Override
  public int compareTo(Key o) {
    if (this.second == o.second) {
      return this.number - o.number;
    }

    return this.second - o.second;
  }

  @Override
  public String toString() {
    return String.valueOf(character);
  }
}
