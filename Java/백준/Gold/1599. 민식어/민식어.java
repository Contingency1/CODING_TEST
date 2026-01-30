import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int count = Integer.parseInt(br.readLine());

    List<STRING> list = new ArrayList<>();

    for (int i = 0; i < count; i++) {
      list.add(new STRING(br.readLine()));
    }
    
    list.sort(null);

    for (STRING l: list) {
      System.out.println(l);
    }

  }

  static enum English {
    A("a", 1), B("b", 2), K("k", 3), D("d", 4), E("e", 5),
    G("g", 6), H("h", 7), I("i", 8), L("l", 9), M("m", 10),
    N("n", 11), NG("ng", 12), O("o", 13), P("p", 14), R("r", 15), 
    S("s", 16), T("t", 17), U("u", 18), W("w", 19), Y("y", 20);

    final String s;
    final int value;

    English(String s, int input) {
        this.s = s;
        this.value = input;
    }

    static English toEnglish(String input) {
      for (English e: English.values()) {
        if(e.s.equals(input)) {
          return e;
        }
      }

      throw new RuntimeException("Nooo~~");
    }
  }

  static class STRING implements Comparable<STRING> {
    
    List<English> list = new ArrayList<>();
    
    @Override
    public int compareTo(STRING o) {
      int length = Math.min(o.list.size(), this.list.size());

      for (int i = 0; i < length; i++) {
        English thisE = this.list.get(i);
        English thisO = o.list.get(i);

        if(thisE.equals(thisO)) {
          continue;
        }

        if(thisE.value < thisO.value) {
          return -1;
        } 

        return 1;
      }

      if(o.list.size() > this.list.size()) {
        return -1;
      }
      return 1;
    }

    @Override
    public String toString() {
      StringBuilder sb = new StringBuilder();

      for (English l: list) {
        sb.append(l.s);
      }

      return sb.toString();
    }

    STRING(String input) {

      for (int i = 0; i < input.length(); i++) {
        if(i < input.length() - 1 && input.substring(i, i + 2).equals("ng")) {
          list.add(English.NG);
          i++;
          continue;
        }

        list.add(English.toEnglish(input.substring(i, i + 1)));
      }

    }
  }
}