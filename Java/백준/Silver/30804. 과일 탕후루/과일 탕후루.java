import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Map;
import java.util.HashMap;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    int count = Integer.parseInt(br.readLine());

    int[] input = new int[count];
    StringTokenizer st = new StringTokenizer(br.readLine());

    for (int i = 0; i < count; i++) {
      input[i] = Integer.parseInt(st.nextToken());
    }

    Map<Integer, Integer> map  = new HashMap<>();

    int left = 0;
    int answer = 0;

    for (int right = 0; right < count; right++) {
      if(!map.containsKey(input[right])) {
        map.put(input[right], 1);
      } else {
        map.put(input[right], map.get(input[right]) + 1);
      }

      while(map.size() > 2) {
        if(map.containsKey(input[left]) && map.get(input[left]) > 1) {
          map.put(input[left], map.get(input[left]) - 1);
        } else {
          map.remove(input[left]);
        }

        left++;
      }

      answer = Math.max(answer, right - left + 1);
    }

    System.out.print(answer);

  }
}
