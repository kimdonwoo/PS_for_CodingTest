
# 탑다운 방식
memo = {}

def fibo_dp(n) :
    if n == 1 or n == 2:
        return 1
    if n not in memo:
        memo[n] = fibo(n-1) + fibo(n-2)
    return memo[n]

# 바텀업 방식
memo = {1:1 , 2:1}

def fibo(n):
    for i in range(3,n+1):
        memo[i] = memo[i-1] + memo[i-2]    
    return memo[n]


def fibo_recur(n):
    if n==1 or n==2:
        return 1
    return fibo(n-1) + fibo(n-2)
