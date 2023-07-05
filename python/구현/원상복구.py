import sys
input = sys.stdin.readline

# N개의 카드를 K번 섞음
N,K = map(int,input().split())

Si = list(map(int,input().split()))
Di = list(map(int,input().split()))
temp=[0]*N

for _ in range(K):
    temp=[0]*N
    for i in range(N):
        temp[Di[i]-1] = Si[i]
    Si = temp

print(*Si)



