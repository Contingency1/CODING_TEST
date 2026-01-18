import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.HashMap;

public class Main {

  static Map<Integer, String> map = new HashMap<>();
  static Map<String, Boolean> used = new HashMap<>();

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int count = Integer.parseInt(br.readLine());

    for (int i = 1; i <= count; i++) {
      String cur = br.readLine();
      map.put(i, cur);
      used.put(cur, false);
    }

    int idx = 1;
    int answer = 0;

    for (int i = 1; i <= count; i++) {
      String cur = br.readLine();
      String target = map.get(idx);

      if(used.get(cur)) {
        continue;
      }

      if(target.equals(cur)) {
        if(idx + 1 <= count) {
          idx++;
        }
        
        used.put(target, true);

        String str = map.get(idx);

        while (used.get(str)) {
          if(idx + 1 > count) {
            break;
          }
          idx++;
          str = map.get(idx);
        }

        continue;
      }

      used.put(cur, true);
      answer++;
    }

    System.out.print(answer);
  }
}