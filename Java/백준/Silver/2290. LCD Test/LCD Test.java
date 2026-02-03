import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());

    int count = Integer.parseInt(st.nextToken());
    char[] arr = st.nextToken().toCharArray();

    String dash = "-";
    String coll = "|";
    String blan = " ";

    int garo = count + 2;
    int sero = 2 * count + 3;

    int midSero = sero / 2 + 1;

    StringBuilder sb = new StringBuilder();

    for (int s = 1; s <= sero; s++) {
      for (int idx = 0; idx < arr.length; idx++) {
        switch (arr[idx]) {
          case '1':
            for (int g = 1; g <= garo; g++) {
              if(s == 1 || s == sero || s == midSero) {
                sb.append(blan);
              } else {
                if(g == garo) {
                  sb.append(coll);
                } else {
                  sb.append(blan);
                }
              }
            }
            sb.append(blan);
          break;

          case '2':
            for (int g = 1; g <= garo; g++) {
              if(s == 1 || s == sero || s == midSero) {
                if (g == 1 || g == garo) {
                  sb.append(blan);
                } else {
                  sb.append(dash);
                }
              } else if(s > midSero && s < sero) {
                if(g == 1) {
                  sb.append(coll);
                } else {
                  sb.append(blan);
                }
              } else {
                if(g == garo) {
                  sb.append(coll);
                } else {
                  sb.append(blan);
                }
              }
            }

            sb.append(blan);
          break;


          case '3':
            for (int g = 1; g <= garo; g++) {
              if(s == 1 || s == sero || s == midSero) {
                if (g == 1 || g == garo) {
                  sb.append(blan);
                } else {
                  sb.append(dash);
                }
              } else {
                if(g == garo) {
                  sb.append(coll);
                } else {
                  sb.append(blan);
                }
              }
            }
            sb.append(blan);
          break;                        

          case '4':
            for (int g = 1; g <= garo; g++) {
              if(s == 1 || s == sero) {
                sb.append(blan);
              } else if (s > 1 && s < midSero) {
                if(g == 1 || g == garo) {
                  sb.append(coll);
                } else {
                  sb.append(blan);
                }
              } else if (s == midSero) {
                if(g == 1 || g == garo) {
                  sb.append(blan);
                } else {
                  sb.append(dash);
                }
              } else {
                if(g == garo) {
                  sb.append(coll);
                } else {
                  sb.append(blan);
                }
              }
            }
            sb.append(blan);
          break;

          case '5':
            for (int g = 1; g <= garo; g++) {
              if(s == 1 || s == midSero || s == sero) {
                if(g == 1 || g == garo) {
                  sb.append(blan);
                } else {
                  sb.append(dash);
                }
              } else if (s < midSero){
                if(g == 1) {
                  sb.append(coll);
                } else {
                  sb.append(blan);
                }
              } else {
                if(g == garo) {
                  sb.append(coll);
                } else {
                  sb.append(blan);
                }
              }
            }

            sb.append(blan);
          break;          

          case '6':
            for (int g = 1; g <= garo; g++) {
              if(s == 1 || s == midSero || s == sero) {
                if(g == 1 || g == garo) {
                  sb.append(blan);
                } else {
                  sb.append(dash);
                }
              } else if(s < midSero){
                if(g == 1) {
                  sb.append(coll);
                } else {
                  sb.append(blan);
                }
              } else {
                if(g == 1 || g == garo) {
                  sb.append(coll);
                } else {
                  sb.append(blan);
                }
              }
            }
            sb.append(blan);

          break;

          case '7':
            for (int g = 1; g <= garo; g++) {
              if(s == 1) {
                if(g == 1 || g == garo) {
                  sb.append(blan);
                } else {
                  sb.append(dash);
                }
              } else if (s == midSero || s == sero) {
                sb.append(blan);
              } else {
                if(g == garo) {
                  sb.append(coll);
                } else {
                  sb.append(blan);
                }
              }
            }
            sb.append(blan);

          break;

          case '8':
            for (int g = 1; g <= garo; g++) {
              if(s == 1 || s == midSero || s == sero) {
                if(g == 1 || g == garo) {
                  sb.append(blan);
                } else {
                  sb.append(dash);
                }
              } else {
                if(g == 1 || g == garo) {
                  sb.append(coll);
                } else {
                  sb.append(blan);
                }

              }              
            }
            sb.append(blan);
          break;


          case '9':
            for (int g = 1; g <= garo; g++) {
              if(s == 1 || s == midSero || s == sero) {
                if(g == 1 || g == garo) {
                  sb.append(blan);
                } else {
                  sb.append(dash);
                }
              } else if (s > 1 && s < midSero) {
                if(g == 1 || g == garo) {
                  sb.append(coll);
                } else {
                  sb.append(blan);
                }
              } else {
                if(g == garo) {
                  sb.append(coll);
                } else {
                  sb.append(blan);
                }
              }            
            }
            sb.append(blan);
          break;

          case '0':
            for (int g = 1; g <= garo; g++) {
              if(s == 1 || s == sero) {
                if(g == 1 || g == garo) {
                  sb.append(blan);
                } else {
                  sb.append(dash);
                }
              } else if (s == midSero) {
                sb.append(blan);
              } else {
                if(g == 1 || g == garo){
                  sb.append(coll);
                } else {
                  sb.append(blan);
                }
              }
            }
            sb.append(blan);
        }
      }
      sb.append("\n");
    }
      
    sb.deleteCharAt(sb.length() - 1);
    System.out.print(sb);
  }

}