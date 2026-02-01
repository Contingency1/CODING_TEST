import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    final int count = Integer.parseInt(br.readLine());
 
    
    List<Range> list = new ArrayList<>();
    
    StringTokenizer st;
    for (int i = 0; i < count; i++) {
      st = new StringTokenizer(br.readLine());

      list.add(new Range(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
    }

    list.sort(null);

    int answer = 0;
    
    for (int i = 0; i < count - 1; i++) {
      int buffer = 0;

      Range init = list.get(i);
    
      int start = init.start;
      int end = init.end;

      for (int j = i + 1; j < count; j++) {
        Range cur = list.get(j);
  
        int curStart = cur.start;
        int curEnd = cur.end;

        if (end < curStart) {
          break;
        }
        
        if (curStart > start && curEnd < end) {
          buffer++;
          answer = Math.max(answer, buffer);
        }
      }
    }

    System.out.print(answer);
  }

  static class Range implements Comparable<Range>{
    int start;
    int end;

    @Override
    public int compareTo(Range o) {
      return this.start - o.start;
    }

    Range(int start, int end) {
      this.start = start;
      this.end = end;
    }
  }

}