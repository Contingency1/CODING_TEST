import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;
import java.util.Queue;
import java.util.PriorityQueue;
import java.util.Arrays;
import java.lang.StringBuilder;

class Main {
  static List<List<Line>> graph;
  static int NODE_COUNT;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());


    NODE_COUNT = Integer.parseInt(st.nextToken());
    final int ROUTE_COUNT = Integer.parseInt(st.nextToken());

    graph = new ArrayList<>();

    for (int i = 0; i <= NODE_COUNT; i++) {
      graph.add(new ArrayList<>());
    }
    
    for (int i = 0; i < ROUTE_COUNT; i++) {
      st = new StringTokenizer(br.readLine());

      int curFrom = Integer.parseInt(st.nextToken());
      int curTarget = Integer.parseInt(st.nextToken());
      int curScore = Integer.parseInt(st.nextToken());

      graph.get(curFrom).add(new Line(curTarget, curScore));
      graph.get(curTarget).add(new Line(curFrom, curScore));
    }

    int[][] answer = new int[NODE_COUNT + 1][NODE_COUNT + 1];

    for (int i = 1; i <= NODE_COUNT; i++) {
      answer[i][i] = -1;
    }

    for (int i = 1; i <= NODE_COUNT; i++) {
      answer[i] = dk(i);
    }

    StringBuilder sb = new StringBuilder();

    for (int i = 1; i <= NODE_COUNT; i++) {
      for (int j = 1; j <= NODE_COUNT; j++) {
        if(answer[i][j] == -1){
          sb.append("-").append(" ");
        } else {
          sb.append(answer[i][j]).append(" ");
        }
      }
      sb.deleteCharAt(sb.length() - 1).append("\n");
    }

    sb.deleteCharAt(sb.length() - 1);
    // System.out.print(Arrays.deepToString(answer));
    System.out.print(sb);
  }

  static int[] dk(int from) {
    Queue<PrLine> queue = new PriorityQueue<>();
    int[] distance = new int[NODE_COUNT + 1];
    Arrays.fill(distance, Integer.MAX_VALUE);
    distance[from] = -1;

    int[] result = new int[NODE_COUNT + 1];
    result[from] = -1;

    // 초기 queue 설정
    for(Line line : graph.get(from)) {
      int curTo = line.to;
      int curDistance = line.distance;

      distance[curTo] = curDistance;
      result[curTo] = curTo;
      queue.add(new PrLine(null, curTo, curDistance));
    }

    while (!queue.isEmpty()) {
      PrLine polled = queue.poll();
      int curNode = polled.node;
      int accDistance = polled.distance;

      
      for(Line line : graph.get(curNode)) {
        int nextTo = line.to;
        int nextDistance = line.distance;

        if(nextDistance + accDistance < distance[nextTo]) {
          distance[nextTo] = nextDistance + accDistance;
          PrLine buffer = polled;

          while(true) {
            if(buffer.prev == null) {
              result[nextTo] = buffer.node;
              break;
            }

            buffer = buffer.prev;
          }

          queue.add(new PrLine(polled, nextTo, nextDistance + accDistance));
        }
      }
    }


    // System.out.println("");
    // System.out.println("FROM~~~~" + from);
    // System.out.println("distance");
    // System.out.println(Arrays.toString(distance));
    // System.out.println("result");
    // System.out.println(Arrays.toString(result));

    return result;
  }

  static class PrLine implements Comparable<PrLine> {

    PrLine prev;
    int node;
    int distance;
  
    @Override
    public int compareTo(PrLine o){
      return distance - o.distance;
    }

    PrLine(PrLine prev, int nowNode,int distance){
      this.prev = prev;
      this.node = nowNode;
      this.distance = distance;
    }

  }

  static class Line {
    int to;
    int distance;

    Line(int to, int distance){
      this.to = to;
      this.distance = distance;
    }
  }


}