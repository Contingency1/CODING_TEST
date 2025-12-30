import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int COUNT, answer;
    static int[][] arr;

    static int[][] offset = 
    {
        {-1, -1}, {-1, 0}, {-1, 1},
        {0, -1}, {0, 1},
        {1, -1}, {1, 0}, {1, 1},
    };
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        COUNT = Integer.parseInt(br.readLine());
        final int unChecked = 9;

        if(COUNT <= 2) {
            System.out.print(0);
            return;
        }

        answer = 0;

        if (COUNT > 4) {
            answer += (COUNT - 4) * (COUNT - 4);
        }

        arr = new int[COUNT][COUNT];

        for (int i = 0 ; i < COUNT; i++) {
            char[] chars = br.readLine().toCharArray();

            for (int j = 0; j < COUNT; j++) {
                if (chars[j] == '#') {
                    arr[i][j] = unChecked;
                } else {
                    arr[i][j] = Integer.parseInt("" + chars[j]);
                }
            }
        }

        checkCorner();

        checkLine();

        System.out.print(answer);
    }

    // static void printArr() {
    //     for (int i = 0; i < COUNT; i++) {
    //         System.out.println(Arrays.toString(arr[i]));
    //     }
    // }

    static void checkLine() {
        for (int i = 1; i < COUNT - 1; i++) {
            if (arr[0][i] >= 1) {
                for (int[] off: offset) {
                    int row = off[0];
                    int col = i + off[1];

                    if (row >= 1 && row <= COUNT - 2 && col >= 1 && col <= COUNT - 2) {
                        if (arr[row][col] == 9) {
                            answer++;
                            arr[row][col] = 7;

                            arr[0][col - 1]--;
                            arr[0][col]--;
                            arr[0][col + 1]--;

                            if(arr[0][i] == 0) {
                                break;
                            }
                        }
                    }
                }
            } else {
                for (int[] off: offset) {
                    int row = off[0];
                    int col = i + off[1];

                    if (row >= 1 && row <= COUNT - 2 && col >= 1 && col <= COUNT - 2) {
                        if (arr[row][col] == 9) {
                            arr[row][col] = 8;
                        }
                    }
                }
            }

            if (arr[i][0] >= 1) {
                for (int[] off: offset) {
                    int row = i + off[0];
                    int col = off[1];

                    if (row >= 1 && row <= COUNT - 2 && col >= 1 && col <= COUNT - 2) {
                        if (arr[row][col] == 9) {
                            answer++;
                            arr[row][col] = 7;

                            arr[row - 1][0]--;
                            arr[row][0]--;
                            arr[row + 1][0]--;

                            if(arr[i][0] == 0) {
                                break;
                            }
                        }
                    }
                }
            } else {
                for (int[] off: offset) {
                    int row = i + off[0];
                    int col = off[1];

                    if (row >= 1 && row <= COUNT - 2 && col >= 1 && col <= COUNT - 2) {
                        if (arr[row][col] == 9) {
                            arr[row][col] = 8;
                        }
                    }
                }
            }

            if (arr[COUNT - 1][i] >= 1) {
                for (int[] off: offset) {
                    int row = COUNT - 1 + off[0];
                    int col = i + off[1];

                    if (row >= 1 && row <= COUNT - 2 && col >= 1 && col <= COUNT - 2) {
                        if (arr[row][col] == 9) {
                            answer++;
                            arr[row][col] = 7;

                            arr[COUNT - 1][col - 1]--;
                            arr[COUNT - 1][col]--;
                            arr[COUNT - 1][col + 1]--;

                            if(arr[COUNT - 1][i] == 0) {
                                break;
                            }
                        }
                    }
                }
            } else {
                for (int[] off: offset) {
                    int row = COUNT - 1 + off[0];
                    int col = i + off[1];

                    if (row >= 1 && row <= COUNT - 2 && col >= 1 && col <= COUNT - 2) {
                        if (arr[row][col] == 9) {
                            arr[row][col] = 8;
                        }
                    }
                }
            }

            if (arr[i][COUNT - 1] >= 1) {
                for (int[] off: offset) {
                    int row = i + off[0];
                    int col = COUNT - 1 + off[1];

                    if (row >= 1 && row <= COUNT - 2 && col >= 1 && col <= COUNT - 2) {
                        if (arr[row][col] == 9) {
                            answer++;
                            arr[row][col] = 7;

                            arr[row - 1][COUNT - 1]--;
                            arr[row][COUNT - 1]--;
                            arr[row + 1][COUNT - 1]--;

                            if(arr[i][COUNT - 1] == 0) {
                                break;
                            }
                        }
                    }
                }
            } else {
                for (int[] off: offset) {
                    int row = i + off[0];
                    int col = COUNT - 1 + off[1];

                    if (row >= 1 && row <= COUNT - 2 && col >= 1 && col <= COUNT - 2) {
                        if (arr[row][col] == 9) {
                            arr[row][col] = 8;
                        }
                    }
                }
            }
        }
    }

    static void checkCorner() {
        // 7: 지뢰, 8: 없음 확정, 9: 미확인
        if (arr[0][0] == 1) {
            arr[1][1] = 7;

            for (int[] off: offset) {
                int row = 1 + off[0];
                int col = 1 + off[1];
                     
                if (row == 0 || row == COUNT - 1 || col == 0 || col == COUNT - 1) {
                    arr[row][col]--;
                }
            }
            
            answer++;
        } else {
            arr[1][1] = 8;
        }

        if (arr[0][COUNT - 1] == 1) {
            arr[1][COUNT - 2] = 7;

            for (int[] off: offset) {
                int row = 1 + off[0];
                int col = COUNT - 2 + off[1];

                if (row == 0 || row == COUNT - 1 || col == 0 || col == COUNT - 1) {
                    arr[row][col]--;
                }
            }
            
            answer++;
        } else {
            arr[1][COUNT - 2] = 8;
        }

        if (arr[COUNT - 1][0] == 1) {
            arr[COUNT - 2][1] = 7;

            for (int[] off: offset) {
                int row = COUNT - 2 + off[0];
                int col = 1 + off[1];
                
                if (row == 0 || row == COUNT - 1 || col == 0 || col == COUNT - 1) {
                    arr[row][col]--;
                }
            }
            
            answer++;
        } else {
            arr[COUNT - 2][1] = 8;
        }

        if (arr[COUNT - 1][COUNT - 1] == 1) {
            arr[COUNT - 2][COUNT - 2] = 7;

            for (int[] off: offset) {
                int row = COUNT - 2 + off[0];
                int col = COUNT - 2 + off[1];
                
                if (row == 0 || row == COUNT - 1 || col == 0 || col == COUNT - 1) {
                    arr[row][col]--;
                }
            }
            
            answer++;
        } else {
            arr[COUNT - 2][COUNT - 2] = 8;
        }
    }
}