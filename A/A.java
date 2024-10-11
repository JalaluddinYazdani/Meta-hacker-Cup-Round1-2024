import java.util.Scanner;

public class A {
    static final long MOD = 1000000007;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        for (int i = 1; i <= t; i++) {
            solve(sc, i);
        }

        sc.close();
    }

    static void solve(Scanner sc, long t) {
        long n = sc.nextLong();
        double slow = 0.0, sup = 1e9;

        for (long i = 1; i <= n; ++i) {
            long a = sc.nextLong();
            long b = sc.nextLong();

            double minispeed = (double) i / b;
            double maxispeed = a > 0 ? (double) i / a : 1e9;

            slow = Math.max(slow, minispeed);
            sup = Math.min(sup, maxispeed);

            if (slow > sup) {
                slow = -1;
                break;
            }
        }

        if (slow == -1) {
            System.out.println("Case #" + t + ": -1");
        } else {
            System.out.printf("Case #%d: %.6f%n", t, slow);
        }
    }
}
