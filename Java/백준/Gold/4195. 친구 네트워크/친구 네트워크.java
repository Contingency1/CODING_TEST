import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

  static Map<String, String> findRootMap;
  static Map<String, Integer> countMapping;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    final int TEST_CASE = Integer.parseInt(br.readLine());

    for (int i = 0; i < TEST_CASE; i++) {
      int count = Integer.parseInt(br.readLine());
      List<List<String>> list = initList(count);
      findRootMap = new HashMap<>();
      countMapping = new HashMap<>();

      for (int j = 0; j < count; j++) {
        StringTokenizer st = new StringTokenizer(br.readLine());
        String s1 = st.nextToken();
        String s2 = st.nextToken();

        List<String> str = list.get(j);
        str.add(s1);
        str.add(s2);

        countMapping.put(s1, 1);
        countMapping.put(s2, 1);
        findRootMap.put(s1, s1);
        findRootMap.put(s2, s2);
      }

      for (List<String> str : list) {
        String s1 = str.get(0);
        String s2 = str.get(1);
        String root = union(s1, s2);

        System.out.println(countMapping.get(root));
      }

      findRootMap.clear();
      countMapping.clear();
    }

  }

  private static List<List<String>> initList(int count) {
    List<List<String>> list = new ArrayList<>();

    for (int j = 0; j < count; j++) {
      list.add(new ArrayList<>());
    }
    return list;
  }


  static String find(String input) {
    if (findRootMap.get(input).equals(input)) {
      return input;
    }

    String root = find(findRootMap.get(input));
    findRootMap.put(input, root);
    return root;
  }

  static String union(String a, String b) {
    String rootA = find(a);
    String rootB = find(b);

    if (rootA.equals(rootB)) {
      return rootA;
    }

    findRootMap.put(rootA, rootB);
    countMapping.put(rootB, countMapping.get(rootB) + countMapping.get(rootA));

    return rootB;
  }
}