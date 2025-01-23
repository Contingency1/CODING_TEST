import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.TreeSet;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    int[] input = Arrays.stream(br.readLine().split(" "))
        .mapToInt(Integer::parseInt).toArray();

    HashMap<String, Integer> hashMap = new HashMap<>();

    for (int i = 0; i < input[0]; i++) {
      String str = br.readLine();
      if (str.length() >= input[1]) {
        hashMap.put(str, hashMap.getOrDefault(str, 0) + 1);
      }
    }

    TreeSet<Object[]> treeSet = new TreeSet<>((o1, o2) -> {
      int secondCompare = Integer.compare((int) o2[1], (int) o1[1]);
      if (secondCompare != 0) {
        return secondCompare;
      }

      int firstLengthCompare = Integer.compare(
          ((String) o2[0]).length(), ((String) o1[0]).length());
      if (firstLengthCompare != 0) {
        return firstLengthCompare;
      }

      return ((String) o1[0]).compareTo((String) o2[0]);
    });

    for (Entry<String, Integer> map : hashMap.entrySet()) {
      treeSet.add(new Object[]{map.getKey(), map.getValue()});
    }

    for (Object[] arr : treeSet) {
      sb.append(arr[0]).append("\n");
//      System.out.println(Arrays.toString(arr));
    }
    sb.deleteCharAt(sb.length() - 1);
    System.out.print(sb);
  }
}