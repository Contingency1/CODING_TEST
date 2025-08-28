import java.util.Queue;
import java.util.ArrayDeque;


class Solution {
    public int solution(String begin, String target, String[] words) {

        char[] chBegin = begin.toCharArray();
                
        int charLength = chBegin.length;
        
        char[][] charArr = new char[words.length][charLength];
        
        for(int i = 0; i < charArr.length; i++) {
            charArr[i] = words[i].toCharArray();
        }
        
        boolean flag = true;
        
        for (String s: words) {
            if (target.equals(s)) {
                flag = false;
                break;
            }
        }
        
        if (flag) {
            return 0;
        }
        
        Queue<Str> queue = new ArrayDeque<>();
        queue.add(new Str(begin, 0));
        
        boolean[] visited = new boolean[words.length];
        
        while (!queue.isEmpty()) {
            Str polled = queue.poll();
            String str = polled.str;
            int count = polled.count;
            
            if(str.equals(target)) {
                return count;
            }
            
            for(int i = 0; i < charArr.length; i++) {
                if (visited[i]) {
                    continue;
                }
                
                if (isOK(str.toCharArray(), charArr[i])) {
                    queue.add(new Str(new String(charArr[i], 0, charLength), count + 1));
                    visited[i] = true;
                }
            }
        }
        
        return 0;
    }
    
    static class Str {
        String str;
        int count;
        
        Str (String str, int count) {
            this.str = str;
            this.count = count;
        }
    }
    
    static boolean isOK(char[] start, char[] target) {
        boolean flag = false;
        
        for(int i = 0; i < start.length; i++) {
            if (start[i] != target[i]) {
                if (flag) {
                    return false;
                }
                
                flag = true;
            }
        }
        
        return true;
    }
    
}