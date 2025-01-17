import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    int N = Integer.parseInt(br.readLine());

    int[] input = Arrays.stream(br.readLine().split(" "))
        .mapToInt(Integer::parseInt).toArray();
    ArrayList<Integer> List1 = new ArrayList<>();

    for (int row : input) {
      List1.add(row);
    }

    ArrayList<Integer> List2 = new ArrayList<>();
    int LastNum = 1;

    for (int i = 0; i < List1.size(); i++) {
      int key = List1.get(i);
      if (key == LastNum) { // 원본의 숫자가 맞다면
        List1.remove(i);
        LastNum++;
      } else { // 원본의 숫자가 틀리다면
        int key2 = List2.size() - 1;
        if (key2 >= 0 && List2.get(key2) == LastNum) { // List2의 마지막이 LastNum이면 실행
          List2.remove(key2);
          LastNum++;
        } else { //List2의 마지막이 LastNum이 아니면
          List2.add(key);
          List1.remove(i);
        }
      }
      i = -1;
    }

    for (int i = List2.size() - 1; i >= 0; i--) {
      if (List2.get(i) == LastNum) {
        List2.remove(i);
        LastNum++;
      }
    }

//    sb.append("List1: ");
//    for (int list : List1) {
//      sb.append(list + " ");
//    }
//
//    sb.append("\n").append("List2: ");
//    for (int list : List2) {
//      sb.append(list + " ");
//    }

    if (List1.isEmpty() && List2.isEmpty()) {
      sb.append("Nice");
    } else {
      sb.append("Sad");
    }
    System.out.print(sb);
  }
}