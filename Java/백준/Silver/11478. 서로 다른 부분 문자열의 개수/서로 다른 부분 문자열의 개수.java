import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//    StringBuilder sb = new StringBuilder();

    String str = br.readLine();

    HashSet<String> set = new HashSet<>();

    for (int i = 1; i <= str.length(); i++) { // 윈도우 크기 설정
      for (int j = 0; j <= str.length() - i; j++) { // 윈도우 크기에 따른 범위 설정
        set.add(str.substring(j, j + i));
      }
    }
    System.out.print(set.size());
  }
}