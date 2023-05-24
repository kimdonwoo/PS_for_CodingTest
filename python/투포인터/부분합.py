import sys
input = sys.stdin.readline

N,S = map(int,input().split())
nums = list(map(int,input().split()))

min_length =  100001
sum = 0
i,j=0,0

while True:

    if S <= sum :
        min_length = min(min_length,j-i)
        sum-=nums[i]
        i+=1
    elif j == N:
        break
    else :
        sum+=nums[j]
        j+=1
        
if min_length == 100001 : print(0)
else :
    print(min_length)
