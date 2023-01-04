package barkingDog.시뮬레이션;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/* 감시 */
public class Bj15683 {
    static int n, m, min = Integer.MAX_VALUE;
    static int[][] arr;

    private static class CCTV{
        int num;
        int x;
        int y;

        public CCTV(int num, int x, int y) {
            this.num = num;
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n][m];
        ArrayList<CCTV> cctvs = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (arr[i][j] != 0 && arr[i][j] != 6) {
                    cctvs.add(new CCTV(arr[i][j],i,j));
                }
            }
        }
        simulation(0, arr, cctvs);
        System.out.println(min);
    }

    private static void simulation(int cnt, int[][] arr, ArrayList<CCTV> cctvs) {
        if (cnt == cctvs.size()) {
            min = Math.min(min, getCnt(arr));
            return;
        }
        int x = cctvs.get(cnt).x;
        int y = cctvs.get(cnt).y;
        int num = cctvs.get(cnt).num;
        int[][] tmp;
        if (num == 1) {
            tmp = cloneArr(arr);
            checkLeft(tmp, x, y);
            simulation(cnt+1, tmp, cctvs);

            tmp = cloneArr(arr);
            checkRight(tmp, x, y);
            simulation(cnt+1, tmp, cctvs);

            tmp = cloneArr(arr);
            checkUp(tmp, x, y);
            simulation(cnt+1, tmp, cctvs);

            tmp = cloneArr(arr);
            checkDown(tmp, x, y);
            simulation(cnt+1, tmp, cctvs);
        } else if (num == 2) {
            tmp = cloneArr(arr);
            checkLeft(tmp, x, y);
            checkRight(tmp, x, y);
            simulation(cnt+1,tmp,cctvs);

            tmp = cloneArr(arr);
            checkUp(tmp, x, y);
            checkDown(tmp, x, y);
            simulation(cnt+1,tmp,cctvs);
        } else if (num == 3) {
            tmp = cloneArr(arr);
            checkUp(tmp, x, y);
            checkRight(tmp, x, y);
            simulation(cnt+1,tmp,cctvs);

            tmp = cloneArr(arr);
            checkDown(tmp, x, y);
            checkRight(tmp, x, y);
            simulation(cnt+1,tmp,cctvs);

            tmp = cloneArr(arr);
            checkDown(tmp, x, y);
            checkLeft(tmp, x, y);
            simulation(cnt+1,tmp,cctvs);

            tmp = cloneArr(arr);
            checkUp(tmp, x, y);
            checkLeft(tmp, x, y);
            simulation(cnt+1,tmp,cctvs);
        } else if (num == 4) {
            tmp = cloneArr(arr);
            checkLeft(tmp, x, y);
            checkUp(tmp, x, y);
            checkRight(tmp, x, y);
            simulation(cnt+1,tmp,cctvs);

            tmp = cloneArr(arr);
            checkUp(tmp, x, y);
            checkRight(tmp, x, y);
            checkDown(tmp, x, y);
            simulation(cnt+1,tmp,cctvs);

            tmp = cloneArr(arr);
            checkRight(tmp, x, y);
            checkDown(tmp, x, y);
            checkLeft(tmp, x, y);
            simulation(cnt+1,tmp,cctvs);

            tmp = cloneArr(arr);
            checkDown(tmp, x, y);
            checkLeft(tmp, x, y);
            checkUp(tmp, x, y);
            simulation(cnt+1,tmp,cctvs);
        } else if (num == 5) {
            tmp = cloneArr(arr);
            checkLeft(tmp, x, y);
            checkUp(tmp, x, y);
            checkRight(tmp, x, y);
            checkDown(tmp, x, y);
            simulation(cnt+1,tmp,cctvs);
        }
    }

    private static int[][] cloneArr(int[][] arr) {
        int[][] tmp = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                tmp[i][j] = arr[i][j];
            }
        }
        return tmp;
    }

    private static int getCnt(int[][] arr) {
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <m; j++) {
                if(arr[i][j]==0) cnt++;
            }
        }
        return cnt;
    }

    private static void checkLeft(int[][] tmp, int x, int y) {
        for (int i = y-1; i >= 0; i--) {
            if(tmp[x][i]==6) return;
            if(tmp[x][i]!=0)continue;
            tmp[x][i] = -1;
        }
    }

    private static void checkRight(int[][] tmp, int x, int y) {
        for (int i = y+1; i < m; i++) {
            if(tmp[x][i]==6) return;
            if(tmp[x][i]!=0)continue;
            tmp[x][i] = -1;
        }
    }

    private static void checkUp(int[][] tmp, int x, int y) {
        for (int i = x-1; i >= 0; i--) {
            if(tmp[i][y]==6) return;
            if(tmp[i][y]!=0)continue;
            tmp[i][y] = -1;
        }
    }

    private static void checkDown(int[][] tmp, int x, int y) {
        for (int i = x+1; i < n; i++) {
            if(tmp[i][y]==6) return;
            if(tmp[i][y]!=0)continue;
            tmp[i][y] = -1;
        }
    }
}
