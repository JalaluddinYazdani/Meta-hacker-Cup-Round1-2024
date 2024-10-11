import java.util.*;

public class C {
    static final long MOD = 998244353;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for (int tc = 1; tc <= T; tc++) {
            long W = sc.nextLong();
            long G = sc.nextLong();
            long L = sc.nextLong();
            long E;

            if (L > 0) {
                long a = (W - G) % MOD;
                long b = ( (2 * (L % MOD)) + 1 ) % MOD;
                E = (a * b) % MOD;
            } else {
                E = (W - G) % MOD;
            }

            System.out.println("Case #" + tc + ": " + E);
        }

        sc.close();
    }
}
