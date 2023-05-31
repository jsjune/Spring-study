import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        ArrayList<Party> parties = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            parties.add(new Party(start, 's'));
            parties.add(new Party(end, 'e'));
        }
        Collections.sort(parties);

        int answer = Integer.MIN_VALUE;
        int count = 0;
        for (Party party : parties) {
            if (party.state == 's') {
                count++;
            } else {
                count--;
            }
            answer = Math.max(answer, count);
        }

        System.out.println(answer);
    }

    private static class Party implements Comparable<Party> {
        int time;
        char state;

        public Party(int time, char state) {
            this.time = time;
            this.state = state;
        }

        @Override
        public int compareTo(Party o) {
            if (this.time == o.time) {
                return this.state - o.state;
            }
            return this.time - o.time;
        }

        @Override
        public String toString() {
            return "Party{" +
                    "time=" + time +
                    ", state=" + state +
                    '}';
        }
    }

}

