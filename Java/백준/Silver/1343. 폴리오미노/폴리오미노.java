import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

public class Main {   
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    char[] input = br.readLine().toCharArray();

    StringBuilder sb = new StringBuilder();

    int idx = 0;

    while(idx < input.length) {
      if(idx + 3 <= input.length - 1) {
        boolean isA = true;
        for (int i = 0; i < 4; i++) {
          if(input[idx + i] != 'X') {
            isA = false;
            break;
          }
        }
        
        if(isA) {
          sb.append("AAAA");
          idx += 4;
          continue;
        }
      }

      if(idx + 1 <= input.length - 1) {
        boolean isB = true;

        for (int i = 0; i < 2; i++) {
          if(input[idx + i] != 'X') {
            isB = false;
            break;
          }
        }
        
        if(isB) {
          sb.append("BB");
          idx += 2;
          continue;
        }
      }

      if(input[idx] == '.') {
        sb.append(".");
        idx++;
        continue;
      }
      
      System.out.print("-1");
      return;
    } 

    System.out.print(sb);

  }
}

