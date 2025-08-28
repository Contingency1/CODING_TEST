import java.util.Arrays;

class Solution {
    int solution(int[][] land) {
        int[][] dp = new int[land.length][land[0].length];
        
        for(int i = 0; i < land[0].length; i++) {
            dp[0][i] = land[0][i];
        }
        
        for (int i = 1; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                for (int k = 0; k < dp[0].length; k++) {
                    if(k == j) {
                        continue;
                    }
                    
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][k] + land[i][j]);
                }
            }
        }
        
        
        int answer = 0;
        for (int i = 0; i < dp[0].length; i++) {
            answer = Math.max(answer, dp[dp.length - 1][i]);
        }

        return answer;
    }
}