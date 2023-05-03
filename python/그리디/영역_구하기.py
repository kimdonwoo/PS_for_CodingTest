import sys
sys.setrecursionlimit(10**7)
input = sys.stdin.readline

def dfs(i,j):
    global count
    graph[i][j] = 1
    count+=1
    
    for d in range(4):
        nx = i+dx[d]
        ny = j+dy[d]

        if nx < 0 or ny < 0 or nx >= M or ny >= N :
            continue
        if graph[nx][ny] != 0:
            continue
        dfs(nx,ny)

M,N,K = map(int,input().split())

dx = [0,0,1,-1]
dy = [1,-1,0,0]
res =[]

square = [list(map(int,input().split())) for _ in range(K)]

graph = [[0]*N for _ in range(M)]


for i in square:
    for j in range(i[0] , i[2]):
        for k in range(i[1] , i[3]):
            graph[k][j] = 1
    
for i in range(M):
    for j in range(N):
        count = 0
        if graph[i][j] == 0 :
            dfs(i,j)
        if count != 0 :
            res.append(count)
        
print(len(res))
res.sort()
for x in res:
    print(x, end=' ')
    

