import java.util.Queue;
import java.util.ArrayDeque;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Comparator;

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {

        List<Integer> truck = new LinkedList<>();
        
        for(int t: truck_weights) {
            truck.add(t);
        }
        
        
        int curWeight = 0;
        
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        
        for(int i = 0; i < bridge_length - 1; i++) {
            queue.add(0);
        }
        
        curWeight = truck.remove(0);
        queue.add(curWeight);
        
        int answer = 1;
        
        while (curWeight != 0) {
            curWeight -= queue.pollFirst();
            
            int nextTruck = 0;
            
            if(!truck.isEmpty()) {
               nextTruck = truck.get(0);
            }
        
            if (curWeight + nextTruck <= weight) {
                curWeight += nextTruck;
                queue.add(nextTruck);
                
                if(nextTruck != 0) {
                    truck.remove(0);
                }
            } else {
                queue.add(0);
            }
            
            answer++;
            // System.out.println(answer+ " "  + queue);
        }
        
        
        return answer;
    }
}