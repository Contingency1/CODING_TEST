import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int x = 1;
        int y = 1;

        List<int[]> inputList = br.lines()
                .map(line -> Arrays.stream(line.split(" ")).mapToInt(Integer::parseInt).toArray())
                .collect(Collectors.toList());
        int answer=inputList.get(0)[0];

         for (int i = 0;i < inputList.size();i++){
             for (int j = 0;j < inputList.get(i).length;j++){
                 if (answer<inputList.get(i)[j]) {
                     answer = inputList.get(i)[j];
                     x=i+1;
                     y=j+1;
                 }
             }
         }

        bw.write(answer + "\n");
        bw.write(x +" "+ y);

        bw.flush();

        br.close();
        bw.close();
    }
}