import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    final int COUNT = Integer.parseInt(br.readLine());

    for (int i = 0; i < COUNT; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int teamCount = Integer.parseInt(st.nextToken());
      int problemCount = Integer.parseInt(st.nextToken());
      int myTeamID = Integer.parseInt(st.nextToken());
      int logCount = Integer.parseInt(st.nextToken());

      List<Team> list = new ArrayList<>();

      for (int j = 0; j < teamCount; j++) {
        list.add(new Team(j + 1, problemCount));
      }

      for (int j = 0; j < logCount; j++) {
        st = new StringTokenizer(br.readLine());

        int id = Integer.parseInt(st.nextToken()) - 1;
        int problem = Integer.parseInt(st.nextToken());
        int point = Integer.parseInt(st.nextToken());

        Team team = list.get(id);

        team.updatePoint(problem, point, j);
      }

      list.sort(null);

      for (int j = 0; j < list.size(); j++) {
        if(list.get(j).getId() == myTeamID) {
          System.out.println(j + 1);
          break;
        }
      }

    }
  }

  static class Team implements Comparable<Team>{
    int id;
    int requestCount = 0;
    int point = 0;

    int lastRequest = 0;

    Map<Integer, Integer> problem = new HashMap<>();

    public int getId() {
      return this.id;
    }

    public void updatePoint(int problemId, int newPoint, int requestId) {
      int curPoint = problem.get(problemId);

      if(curPoint < newPoint) {
        problem.put(problemId, newPoint);
        point -= curPoint;
        point += newPoint;
      }

      requestCount++;
      lastRequest = requestId;
    }
    
    @Override
    public int compareTo(Team o) {
      if(o.point == this.point) {
        if(this.requestCount == o.requestCount) {
          return this.lastRequest - o.lastRequest;
        }

        return this.requestCount - o.requestCount;
      }

      return o.point - this.point;
    }

    Team(int id, int problemCount) {
      this.id = id;

      for (int i = 1; i <= problemCount; i++) {
        problem.put(i, 0);
      }
    }
  }
}