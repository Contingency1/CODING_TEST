import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    final int SPRING = Integer.parseInt(st.nextToken());
    final int TREE = Integer.parseInt(st.nextToken());

    List<Location> locations = new ArrayList<>();

    int min = Integer.MAX_VALUE;
    int max = Integer.MIN_VALUE;
    for (int i = 0; i < SPRING; i++) {
      st = new StringTokenizer(br.readLine());

      int start = Integer.parseInt(st.nextToken());
      int end = Integer.parseInt(st.nextToken());

      min = Math.min(min, start);
      max = Math.max(max, end);

      locations.add(new Location(start, end));
    }

    locations.sort(null);

    int count = 0;
    int coverEnd = 0;

    for (Location loc : locations) {
      int start = Math.max(loc.start, coverEnd);
      int end = loc.end;

      if (start >= end) {
        continue;
      }

      int length = end - start;
      int need = (length + TREE - 1) / TREE;
      
      count += need;
      coverEnd = start + need * TREE;
    }
    System.out.print(count);
  }

  static class Location implements Comparable<Location> {

    int start;
    int end;

    public Location(int start, int end) {
      this.start = start;
      this.end = end;
    }

    @Override
    public int compareTo(Location o) {
      return start - o.start;
    }
  }
}