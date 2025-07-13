import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

  // GPT 도움을 받아서 풀었다. 4시간 동안 쉽지 않았기에...
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    final int COUNT = Integer.parseInt(st.nextToken());
    final int REGEN_HP = Integer.parseInt(st.nextToken());

    int[] score = new int[COUNT + 1];
    int[] hp = new int[COUNT + 1];

    st = new StringTokenizer(br.readLine());
    for (int i = 1; i < COUNT + 1; i++) {
      score[i] = Integer.parseInt(st.nextToken());
    }

    st = new StringTokenizer(br.readLine());
    for (int i = 1; i < COUNT + 1; i++) {
      hp[i] = Integer.parseInt(st.nextToken());
    }

    int[] dp = new int[101];
    for (int i = 0; i <= 100; i++) {
      dp[i] = -1;
    }
    dp[100] = 0;
    for (int i = 1; i < COUNT + 1; i++) {
      int curScore = score[i];
      int curHp = hp[i];

      int[] buffer = dp.clone();
      for (int hpNow = 0; hpNow <= 100; hpNow++) {
        if (buffer[hpNow] == -1) {
          continue;
        }

        // 회복
        int afterRegen = Math.min(hpNow + REGEN_HP, 100);

        // 포기
        dp[afterRegen] = Math.max(dp[afterRegen], buffer[hpNow]);

        // 플레이
        if (afterRegen >= curHp) {
          int nextHp = afterRegen - curHp;
          dp[nextHp] = Math.max(dp[nextHp], buffer[hpNow] + curScore);
        }
      }

    }

    int answer = 0;
    for (int i : dp) {
      answer = Math.max(answer, i);
    }
    System.out.print(answer);


  }
}

