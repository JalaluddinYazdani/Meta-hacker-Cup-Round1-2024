import java.util.*;

public class D {
    static final long MOD = 998244353;
    static int i=1;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int T = scanner.nextInt();

        // Precompute powers of 2 up to 20
        long[] power2 = new long[21];
        power2[0] = 1;
        for (int i = 1; i <= 20; i++) {
            power2[i] = Math.min(power2[i - 1] * 2, 1000001);
        }

        for (int t = 0; t < T; t++) {
            String E = scanner.next();
            long K = scanner.nextLong();
            int N = E.length();

            // Precompute suffix question marks
            int[] suffix_q = new int[N + 1];
            for (int i = N - 1; i >= 0; i--) {
                suffix_q[i] = suffix_q[i + 1] + (E.charAt(i) == '?' ? 1 : 0);
            }

            StringBuilder S = new StringBuilder(E);
            for (int i = 0; i < N; i++) {
                if (S.charAt(i) == '?') {
                    int rem_q = suffix_q[i + 1];
                    long num_S_with_2;
                    if (rem_q <= 20) {
                        num_S_with_2 = power2[rem_q];
                    } else {
                        num_S_with_2 = 1000001;
                    }
                    if (K <= num_S_with_2) {
                        S.setCharAt(i, '2');
                    } else {
                        S.setCharAt(i, '1');
                        K -= num_S_with_2;
                    }
                }
            }

            // Compute number of decodings
            long[] dp = new long[N + 1];
            dp[N] = 1;
            for (int i = N - 1; i >= 0; i--) {
                if (S.charAt(i) != '0') {
                    dp[i] += dp[i + 1];
                    if (i + 1 < N) {
                        if (S.charAt(i) == '1' || (S.charAt(i) == '2' && S.charAt(i + 1) <= '6')) {
                            dp[i] += dp[i + 2];
                        }
                    }
                }
                dp[i] %= MOD;
            }

            System.out.printf("Case #%d: %s %d%n", i++, S.toString(), dp[0]);
        }

        scanner.close();
    }
}
