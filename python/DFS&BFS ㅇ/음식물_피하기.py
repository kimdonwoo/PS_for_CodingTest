import sys
sys.setrecursionlimit(10000)
input = sys.stdin.readline

N,M,K = map(int,input().split())
dx = [1,-1,0,0]
dy = [0,0,1,-1]

ground = [[0]*(M) for _ in range(N)]
visited = [[False]*(M) for _ in range(N)]

for _ in range(K):
    x,y = map(int,input().split())
    ground[x-1][y-1] = 1

def dfs(x,y):
    global res
    if x < 0 or y < 0 or x >=N or y>=M : return
    if visited[x][y] : return
    
    visited[x][y] = True
    if ground[x][y] == 1:
        res+=1
        for dd in range(4):
            dfs(x+dx[dd], y+dy[dd])


res = 0
max_res = 0

for i in range(N):
    for j in range(M):
        res = 0
        dfs(i,j)
        max_res= max(max_res,res)


print(max_res)

    



