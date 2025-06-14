import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

  static int[] parent;
  static boolean[] peopleKnow;

  static int find(int k) {
    if (k == parent[k]) {
      return k;
    }
    parent[k] = find(parent[k]);
    return parent[k];
  }

  static void union(int a, int b) {

    int fa = find(a);
    int fb = find(b);
    boolean flag = peopleKnow[fa] || peopleKnow[fb];

    if (fa != fb) {
      parent[fb] = fa;
      peopleKnow[fa] = flag;
    }
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    final int PEOPLE_COUNT = Integer.parseInt(st.nextToken());
    final int PARTY_COUNT = Integer.parseInt(st.nextToken());

    parent = new int[PEOPLE_COUNT + 1];
    for (int i = 1; i < PEOPLE_COUNT + 1; i++) {
      parent[i] = i;
    }

    st = new StringTokenizer(br.readLine());

    final int WHO_KNOW_REAL_COUNT = Integer.parseInt(st.nextToken());
    peopleKnow = new boolean[PEOPLE_COUNT + 1];
    for (int i = 0; i < WHO_KNOW_REAL_COUNT; i++) {
      peopleKnow[Integer.parseInt(st.nextToken())] = true;
    }
    List<List<Integer>> party = new ArrayList<>();

    for (int i = 0; i < PARTY_COUNT; i++) {
      party.add(new ArrayList<>());
    }

    for (int i = 0; i < PARTY_COUNT; i++) {
      st = new StringTokenizer(br.readLine());
      int count = Integer.parseInt(st.nextToken());
      for (int j = 0; j < count; j++) {
        party.get(i).add(Integer.parseInt(st.nextToken()));
      }
    }

    for (List<Integer> integers : party) {
      for (int i = 0; i < integers.size() - 1; i++) {
        Integer i1 = integers.get(i);
        Integer i2 = integers.get(i + 1);
        union(i1, i2);
      }
    }

    int answer = 0;
    for (List<Integer> integers : party) {
      boolean flag = false;
      for (Integer integer : integers) {
        if (peopleKnow[find(integer)]) {
          flag = true;
          break;
        }
      }
      if (flag) {
        continue;
      }
      answer++;
    }

    System.out.print(answer);

  }


}