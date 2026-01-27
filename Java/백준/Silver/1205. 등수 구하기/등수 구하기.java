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

    int scoreCount = Integer.parseInt(st.nextToken());
    int taesu = Integer.parseInt(st.nextToken());
    int rankCount = Integer.parseInt(st.nextToken());

    if(scoreCount == 0) {
      System.out.print(1);
      return;
    }

    List<Integer> list = new ArrayList<>();
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < scoreCount; i++) {
      list.add(Integer.parseInt(st.nextToken()));
    }

    if (scoreCount == rankCount && taesu <= list.get(list.size() - 1)) {
      System.out.println(-1);
      return;
    }

    int rank = 1;
    
    for (int i = 0; i < list.size(); i++) {
      if (list.get(i) > taesu) {
        rank++;
      } else {
        break;
      }
    }

    System.out.println(rank);
  }
}