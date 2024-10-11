import java.util.*;

public class B {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        int[] testCases = new int[T];
        int maxN = 0;

        for (int i = 0; i < T; i++) {
            testCases[i] = sc.nextInt();
            maxN = Math.max(maxN, testCases[i]);
        }

        boolean[] sieve = new boolean[maxN + 3];
        Arrays.fill(sieve, true);
        sieve[0] = sieve[1] = false;

        for (int i = 2; i * i <= maxN + 2; i++) {
            if (sieve[i]) {
                for (int j = i * i; j <= maxN + 2; j += i) {
                    sieve[j] = false;
                }
            }
        }

        int[] twinPrefix = new int[maxN + 1];
        for (int i = 2; i <= maxN - 2; i++) {
            twinPrefix[i] = twinPrefix[i - 1] + (sieve[i] && sieve[i + 2] ? 1 : 0);
        }

        for (int tc = 1; tc <= T; tc++) {
            int N = testCases[tc - 1];
            int twin = (N >= 5) ? twinPrefix[N - 2] : 0;
            int count = twin + (twin >= 1 ? 1 : 0);
            System.out.println("Case #" + tc + ": " + count);
        }

        sc.close();
    }
}
