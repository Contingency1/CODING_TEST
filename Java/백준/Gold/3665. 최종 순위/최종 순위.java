import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    final int TEST_CASE = Integer.parseInt(br.readLine());

    for (int i = 0; i < TEST_CASE; i++) {
      int teamCount = Integer.parseInt(br.readLine());
      int[] rankReturnTeam = new int[teamCount + 1];
      int[] teamReturnRank = new int[teamCount + 1];

      StringTokenizer st = new StringTokenizer(br.readLine());

      for (int j = 1; j <= teamCount; j++) {
        int number = Integer.parseInt(st.nextToken());
        rankReturnTeam[j] = number;
        teamReturnRank[number] = j;
      }

      List<List<Integer>> nodes = new ArrayList<>();
      for (int j = 0; j <= teamCount; j++) {
        nodes.add(new ArrayList<>());
      }

      int[] inDegreeCount = new int[teamCount + 1];
      for (int j = 1; j < rankReturnTeam.length - 1; j++) {
        for (int k = j + 1; k < rankReturnTeam.length; k++) {
          nodes.get(rankReturnTeam[j]).add(rankReturnTeam[k]);
          inDegreeCount[rankReturnTeam[k]]++;
        }
      }

      // 순위 변동 여부
      boolean[] isUsed = new boolean[teamCount + 1];

      int rankChangedCount = Integer.parseInt(br.readLine());
      if (rankChangedCount == 0) {
        for (int j = 1; j < rankReturnTeam.length; j++) {
          sb.append(rankReturnTeam[j]).append(" ");
        }
        sb.deleteCharAt(sb.length() - 1).append("\n");
        continue;
      }

      //0번째가 앞에 오고, 1번째가 뒤에 온다.
      int[][] rankChangedTeam = new int[rankChangedCount][2];
      for (int j = 0; j < rankChangedCount; j++) {
        st = new StringTokenizer(br.readLine());
        int first = Integer.parseInt(st.nextToken());
        int second = Integer.parseInt(st.nextToken());

        // first의 순위가 second의 순위 보다 작을 경우
        if (teamReturnRank[first] < teamReturnRank[second]) {
          rankChangedTeam[j][0] = second;
          rankChangedTeam[j][1] = first;
        } else {
          rankChangedTeam[j][0] = first;
          rankChangedTeam[j][1] = second;
        }

        isUsed[first] = true;
        isUsed[second] = true;
      }

/*      for (List<Integer> list : nodes) {
        System.out.println("list = " + list);
      }
      System.out.println("inDegreeCount = " + Arrays.toString(inDegreeCount));
      System.out.println("=====");*/
//      for (int[] rankChanged : rankChangedTeam) {
//        System.out.println("rankChanged = " + Arrays.toString(rankChanged));
//      }

      for (int[] team : rankChangedTeam) {
        int front = team[0];
        int back = team[1];

        List<Integer> backArray = nodes.get(back);
        for (int j = 0; j < backArray.size(); j++) {
          if (backArray.get(j) == front) {
            inDegreeCount[front]--;
            backArray.remove(j);
            break;
          }
        }

        nodes.get(front).add(back);
        inDegreeCount[back]++;
      }

/*      for (List<Integer> list : nodes) {
        System.out.println("list = " + list);
      }
      System.out.println("inDegreeCount = " + Arrays.toString(inDegreeCount));
      System.out.println("=====");*/

      List<Integer> result = new ArrayList<>();

      ArrayDeque<Integer> queue = new ArrayDeque<>();
      for (int j = 1; j < inDegreeCount.length; j++) {
        if (inDegreeCount[j] == 0) {
          queue.add(j);
        }
      }

      if (queue.size() != 1) {
        sb.append("IMPOSSIBLE").append("\n");
        continue;
      }

      while (!queue.isEmpty()) {
        Integer polled = queue.poll();
        result.add(polled);

        for (int next : nodes.get(polled)) {
          inDegreeCount[next]--;
          if (inDegreeCount[next] == 0) {
            queue.add(next);
          }
        }
      }

      if (result.size() != teamCount) {
        sb.append("IMPOSSIBLE").append("\n");
        continue;
      }

      for (Integer integer : result) {
        sb.append(integer).append(" ");
      }
      sb.deleteCharAt(sb.length() - 1).append("\n");
    }

    br.close();
    System.out.print(sb);
  }


}