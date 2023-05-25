import sys
input = sys.stdin.readline


n,k = map(int,input().split())

moneys=[]

for _ in range(n) :
    moneys.append(int(input()))
    
dp = [0] * (k+1)
dp[0] = 1

for money in moneys:
    for i in range(money,k+1):
        if i - money >= 0 :
            dp[i] += dp[i-money]
            
print(dp[k])    






