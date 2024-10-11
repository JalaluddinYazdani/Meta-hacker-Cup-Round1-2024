MOD = 998244353

def main():
    import sys
    input = sys.stdin.read
    data = input().splitlines()
    
    T = int(data[0])  # Read number of test cases
    # Precompute powers of 2 up to 20
    power2 = [1] * 21
    for i in range(1, 21):
        power2[i] = min(power2[i - 1] * 2, 1000001)
    
    results = []
    
    index = 1
    for case_num in range(1, T + 1):
        E, K = data[index].split()
        K = int(K)
        index += 1
        N = len(E)
        
        # Precompute suffix question marks
        suffix_q = [0] * (N + 1)
        for i in range(N - 1, -1, -1):
            suffix_q[i] = suffix_q[i + 1] + (1 if E[i] == '?' else 0)
        
        S = list(E)
        for i in range(N):
            if S[i] == '?':
                rem_q = suffix_q[i + 1]
                if rem_q <= 20:
                    num_S_with_2 = power2[rem_q]
                else:
                    num_S_with_2 = 1000001
                
                if K <= num_S_with_2:
                    S[i] = '2'
                else:
                    S[i] = '1'
                    K -= num_S_with_2
        
        # Compute number of decodings
        dp = [0] * (N + 1)
        dp[N] = 1
        
        for i in range(N - 1, -1, -1):
            if S[i] != '0':
                dp[i] += dp[i + 1]
                if i + 1 < N:
                    if S[i] == '1' or (S[i] == '2' and S[i + 1] <= '6'):
                        dp[i] += dp[i + 2]
            dp[i] %= MOD
        
        results.append(f"Case #{case_num}: {''.join(S)} {dp[0]}")
    
    print("\n".join(results))

if __name__ == "__main__":
    main()
