import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());

    final int ROW_COUNT = Integer.parseInt(st.nextToken());
    final int COL_COUNT = Integer.parseInt(st.nextToken());
    final int ROTATE_COUNT = Integer.parseInt(st.nextToken());

    final int LAYER_COUNT = Math.min(ROW_COUNT, COL_COUNT) / 2;
    int[][] arr = new int[ROW_COUNT][COL_COUNT];

    for (int i = 0; i < ROW_COUNT; i++) {
      st = new StringTokenizer(br.readLine());

      for (int j = 0; j < COL_COUNT; j++) {
        arr[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    List<List<Integer>> list = new ArrayList<>();
    for (int i = 0; i < LAYER_COUNT; i++) {
      List<Integer> buffer = new ArrayList<>();
      makeLayer(arr, i, buffer);
      list.add(buffer);
    }
    
    for(int j = 0 ; j < list.size(); j++) {
      List<Integer> buffer = list.get(j);

      int rotate = ROTATE_COUNT % buffer.size();
      for (int i = 0; i < rotate; i++) {
        int temp = buffer.remove(0);
        buffer.add(temp);
      }
    }

    int[][] answer = new int[ROW_COUNT][COL_COUNT];

    for (int i = 0; i < LAYER_COUNT; i++) {
      makeArr(answer, i, list.get(i));
    } 
    
    print(answer);
  }

  static void print(int[][] answer) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < answer.length; i++) {
      for (int j = 0; j < answer[0].length; j++) {
        sb.append(answer[i][j]).append(" ");
      }
      sb.deleteCharAt(sb.length() - 1).append("\n");
    }

    sb.deleteCharAt(sb.length() - 1);

    System.out.print(sb);
  }

  static void makeArr(int[][] arr, int layer, List<Integer> list) {
    int key = 0;

    for (int c = layer; c < arr[0].length - layer - 1; c++) {
      arr[layer][c] = list.get(key++);
    }

    for (int r = layer; r < arr.length - 1 - layer; r++) {
      arr[r][arr[0].length - 1 - layer] = list.get(key++);
    }

    for (int c = arr[0].length - 1 - layer; c > layer; c--) {
      arr[arr.length - 1 - layer][c] = list.get(key++);
    }

    for (int r = arr.length - 1 - layer; r > layer; r--) {
      arr[r][layer] = list.get(key++);
    }
  }

  static void makeLayer(int[][] arr, int layer, List<Integer> list) {
    for (int c = layer; c < arr[0].length - layer - 1; c++) {
      list.add(arr[layer][c]);
    }

    for (int r = layer; r < arr.length - 1 - layer; r++) {
      list.add(arr[r][arr[0].length - 1 - layer]);
    } 

    for (int c = arr[0].length - 1 - layer; c > layer; c--) {
      list.add(arr[arr.length - 1 - layer][c]);
    }

    for (int r = arr.length - 1 - layer; r > layer; r--) {
      list.add(arr[r][layer]);
    }
  }
}
