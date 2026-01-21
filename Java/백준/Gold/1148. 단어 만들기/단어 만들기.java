import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    List<Word> words = new ArrayList<>();

    while(true) {
      String read = br.readLine();
      
      if(read.equals("-")) {
        break;
      }

      int[] cur = new int[26];

      for (char c: read.toCharArray()) {
        cur[c - 65]++;
      }

      words.add(new Word(cur));
    }

    while(true) {
      String input = br.readLine();

      if (input.equals("#")) {
        break;
      }

      int[] puzzle = new int[26];
      Set<Integer> used = new HashSet<>();
      
      for (char c: input.toCharArray()) {
        puzzle[c - 65]++;

        used.add(c - 65);
      }
      
      int[] answer = new int[26];

      for (int i = 0; i < 26; i++) {
        if(puzzle[i] == 0) {
          continue;
        }

        for (int j = 0; j < words.size(); j++) {
          Word cur = words.get(j);

          if(cur.check(puzzle, i)) {
            answer[i]++;
          }
        }
      }
      
      int min = Integer.MAX_VALUE;
      int max = 0;

      List<Integer> left = new ArrayList<>(); 

      
      for (int s: used) {
        min = Math.min(min, answer[s]);
        max = Math.max(max, answer[s]);
        
        left.add(s);
      }

      left.sort(null);

      StringBuilder sb = new StringBuilder();

      for (int l: left) {
        if(answer[l] == min) {
          sb.append((char) (l + 65));
        }
      }

      sb.append(" ").append(min).append(" ");

      for (int l: left) {
        if(answer[l] == max) {
          sb.append((char) (l + 65));
        }
      }

      sb.append(" ").append(max);
      System.out.println(sb);
    }
  }

  static class Word {
    int[] chars = new int[26];

    Word(int[] chars) {
      this.chars = chars;
    }
   
    boolean check(int[] puzzle, int charIdx) {
      if(chars[charIdx] == 0) {
        return false;
      }

      for (int i = 0; i < 26; i++) {
        if(this.chars[i] > puzzle[i]) {
          return false;
        }
      }

      return true;
    }
  }
}