package barkingDog.재귀;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* 재귀함수가 뭔가요? */
public class Bj17478 {

    static String underba = "";
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        System.out.println("어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다.");
        String content = "\"재귀함수가 뭔가요?\"\n"
            + "\"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.\n"
            + "마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.\n"
            + "그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어.\"";
        recursion(n, content);
        System.out.println(sb);
    }

    private static void recursion(int n, String content) {
        String line = underba;
        if (n == 0) {
            sb.append(line + "\"재귀함수가 뭔가요?\"" + "\n");
            sb.append(line + "\"재귀함수는 자기 자신을 호출하는 함수라네\"" + "\n");
            sb.append(line + "라고 답변하였지." + "\n");
            return;
        }
        sb.append(line + content.split("\n")[0] + "\n");
        sb.append(line + content.split("\n")[1] + "\n");
        sb.append(line + content.split("\n")[2] + "\n");
        sb.append(line + content.split("\n")[3] + "\n");
        underba += "____";
        recursion(n - 1, content);
        sb.append(line + "라고 답변하였지." + "\n");
    }
}
