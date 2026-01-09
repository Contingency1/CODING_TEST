import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());

    int B = Integer.parseInt(st.nextToken());
    int T = Integer.parseInt(st.nextToken());

    char[] target = br.readLine().toCharArray();
    char[] input = br.readLine().toCharArray();

    int[] bigCount = new int[26];
    int[] smallCount = new int[26];

    for (char t: target) {
      if (t >= 'a' && t <= 'z') {
        smallCount[t - 97]++;
      } else {
        bigCount[t - 65]++;
      }
    }
    
    int[] b = new int[26];
    int[] s = new int[26];

    for (int i = 0; i < B; i++) {
      char t = input[i];
      
      if (t >= 'a' && t <= 'z') { 
        s[t - 'a']++;
      } else {
        b[t - 'A']++;
      }
    }

    int answer = 0;

    if(Arrays.equals(s, smallCount) && Arrays.equals(b, bigCount)) {
      answer++;
    }

    for (int i = 0; i < T - B; i++) {
      char prev = input[i];

      if (prev >= 'a' && prev <= 'z') {
        s[prev - 'a']--;
      } else {
        b[prev - 'A']--;
      }

      char next = input[i + B];

      if (next >= 'a' && next <= 'z') {
        s[next - 'a']++;
      } else { 
        b[next - 'A']++;
      }

      if (Arrays.equals(s, smallCount) && Arrays.equals(b, bigCount)) {
        answer++;
      }
    }

    System.out.print(answer);
  }
}