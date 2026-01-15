import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;


public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int COUNT = Integer.parseInt(br.readLine());

    List<Dot> list = new ArrayList<>();
    for (int i = 0; i < COUNT; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      list.add(new Dot(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
    }

    list.sort(null);

    
    int answer = list.get(0).end - list.get(0).start;
    int prevEnd = list.get(0).end;

    for(int i = 1; i < COUNT; i++) {
      Dot d = list.get(i);

      int curS = d.start;
      int curE = d.end;

      if(curS > prevEnd) {
        answer += curE - curS;
        prevEnd = curE;
      } else {
        if(prevEnd < curE) {
          answer += curE - prevEnd;
          prevEnd = curE;
        }
      }
    }

    System.out.print(answer);
  }

  static class Dot implements Comparable<Dot>{
    int start;
    int end;

    @Override
    public int compareTo(Dot o) {
      return this.start - o.start;
    }

    Dot(int start, int end) {
      this.start = start;
      this.end = end;
    }
  }

}