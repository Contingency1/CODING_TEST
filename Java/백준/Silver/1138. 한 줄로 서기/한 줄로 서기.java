import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    final int N = Integer.parseInt(br.readLine());

    StringTokenizer st = new StringTokenizer(br.readLine());

    int[] input = new int[N];
    boolean[] used = new boolean[N];
    
    for (int i = 0; i < N; i++) {
      input[i] = Integer.parseInt(st.nextToken());
    };

    StringBuilder sb = new StringBuilder(); 
  
    for (int i = 0; i < N; i++) {
      if (!used[i] && input[i] == 0) {
        sb.append(i+1).append(" ");
        used[i] = true;
        for (int j = 0; j < i; j++) {
          input[j]--;
        }
        i=-1;
      }
    }
    sb.deleteCharAt(sb.length()-1);
    System.out.print(sb);
  }



}