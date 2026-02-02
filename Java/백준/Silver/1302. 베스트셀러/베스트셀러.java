import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    final int COUNT = Integer.parseInt(br.readLine());

    Map<String, Integer> map = new HashMap<>();

    int max = 1;
    
    for (int i = 0; i < COUNT; i++) {
      String input = br.readLine();
      
      if (!map.containsKey(input)) {
        map.put(input, 1);
      } else {
        int count = map.get(input);
        max = Math.max(max, count + 1);
        map.put(input, count + 1);
      }
    }
    
    List<String> list = new ArrayList<>();
    
    for (Map.Entry<String, Integer> entry : map.entrySet()) {

      if(entry.getValue() == max) {
        list.add(entry.getKey());
      }
    }

    list.sort(null);

    System.out.print(list.get(0));
  }

}