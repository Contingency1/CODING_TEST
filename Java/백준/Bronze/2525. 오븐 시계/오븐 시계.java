import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine()); 

    int h = Integer.parseInt(st.nextToken());
    int m = Integer.parseInt(st.nextToken());
    
    int sum = h * 60 + m;

    int plus = Integer.parseInt(br.readLine());

    int answerSum = sum + plus;

    int aH = answerSum / 60 % 24;
    int aM = answerSum % 60;

    System.out.print(aH + " " + aM);
  }
}

