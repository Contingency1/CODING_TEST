import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    final int COUNT = Integer.parseInt(br.readLine());

    StringTokenizer st = new StringTokenizer(br.readLine());

    int[] curArr = new int[COUNT];
    int[] targetArr = new int[COUNT];
    int[] idxArr = new int[COUNT];

    for (int i = 0; i < COUNT; i++) {
      curArr[i] = i;
      targetArr[i] = Integer.parseInt(st.nextToken());
    }

    st = new StringTokenizer(br.readLine());

    for (int i = 0; i < COUNT; i++) {
      idxArr[i] = Integer.parseInt(st.nextToken());
    }


    CustomArray cur = new CustomArray(curArr);
    CustomArray idx = new CustomArray(idxArr);
    Set<CustomArray> set = new HashSet<>();

    int answer = 0;

    while(!set.contains(cur)) {
      set.add(new CustomArray(cur.data));

      if (!cur.validate(targetArr)) {
        cur.changeWithOffSet(idx);
        answer++;
      } else {
        System.out.print(answer);
        return;
      }
    }

    System.out.print(-1);
  }
}

class CustomArray {
    int[] data;
    int[] map;

    public CustomArray(int[] data) {
      this.data = data;
      makeMap();
    }

    private void makeMap() {
      this.map = new int[this.data.length];
      for (int i = 0; i < this.data.length; i++) {
        this.map[data[i]] =  i % 3;
      }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        CustomArray other = (CustomArray) o;
        return Arrays.equals(data, other.data);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(data);
    }

    public boolean validate(int[] target) {
      for (int i = 0; i < target.length; i++) {
        int to = target[i];
        int value = map[i];

        // System.out.println("i: " + i + ", to: " + to + ", value: " + value);
        if (value != to) {
          return false;
        }
      }

      return true;
    }

    public void changeWithOffSet(CustomArray idx) {
      int[] newData = new int[this.data.length];

      for (int i = 0; i < idx.data.length; i++) {
        newData[idx.data[i]] = this.data[i];
      }

      this.data = newData;
      makeMap();
    }
}