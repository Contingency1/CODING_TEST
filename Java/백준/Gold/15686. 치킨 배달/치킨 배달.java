import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

  static int answer = Integer.MAX_VALUE;
  static int[][] distance;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    String[] str = br.readLine().split(" ");

    final int CITY_COUNT = Integer.parseInt(str[0]);
    final int TARGET_COUNT = Integer.parseInt(str[1]);

    int[][] array = new int[CITY_COUNT + 1][CITY_COUNT + 1];

    for (int i = 1; i < CITY_COUNT + 1; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      for (int j = 1; j <= CITY_COUNT; j++) {
        array[i][j] = Integer.parseInt(st.nextToken());
      }
    }

//    for (int[] ints : array) {
//      System.out.println(Arrays.toString(ints));
//    }

    List<Node> house = new ArrayList<>();
    List<Node> chicken = new ArrayList<>();
    for (int i = 1; i <= CITY_COUNT; i++) {
      for (int j = 1; j <= CITY_COUNT; j++) {
        if (array[i][j] == 1) {
          house.add(new Node(i, j));
        } else if (array[i][j] == 2) {
          chicken.add(new Node(i, j));
        }
      }
    }

    distance = new int[house.size()][chicken.size()];
    for (int i = 0; i < house.size(); i++) {
      for (int j = 0; j < chicken.size(); j++) {
        distance[i][j] = Math.abs(house.get(i).row - chicken.get(j).row)
            + Math.abs(house.get(i).col - chicken.get(j).col);
      }
    }

    back(new ArrayList<>(), 0, TARGET_COUNT);

    sb.append(answer);
    System.out.print(sb);
  }

  static void back(List<Integer> buffer, int start, int targetSize) {

    if (targetSize == 0) {
      int sum = 0;
      for (int i = 0; i < distance.length; i++) {
        int min = Integer.MAX_VALUE;
        for (Integer j : buffer) {
          min = Math.min(distance[i][j], min);
        }
        sum += min;
      }
      answer = Math.min(answer, sum);
      return;
    }

    for (int i = start; i < distance[0].length; i++) {
      buffer.add(i);
      back(buffer, i + 1, targetSize - 1);
      buffer.remove(buffer.size() - 1);
    }


  }

  static class Node {

    int row;
    int col;

    public Node(int row, int col) {
      this.row = row;
      this.col = col;
    }
  }


}
