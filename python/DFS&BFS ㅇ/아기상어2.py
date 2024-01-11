import sys
from collections import deque
input = sys.stdin.readline

dx = [-1, -1, -1, 0, 1, 0, 1, 1]
dy = [-1, 0, 1, 1, 1, -1, 0, -1]

n,m = map(int, input().split())
shortest_distance=[list(map(int, input().split())) for _ in range(n)]


q =deque()

# map에서 모든 상어의 위치 큐에 넣기
for i in range(n):
    for j in range(m):
        if shortest_distance[i][j] == 1:
            q.append((i,j))
            
result = 0

while q:
    x,y = q.popleft()
    for i in range(8):
        nx = x+dx[i]
        ny = y+dy[i]
        # 범위 밖일때 배열 접근 x
        if nx < 0 or ny < 0 or nx >= n or ny >= m:
            continue
        # 이전에 간곳들 pass
        if shortest_distance[nx][ny] != 0:
            continue
        # 젤 처음 간곳이면 큐에 추가하고 거리 설정하고 result값이랑 비교
        q.append((nx,ny))
        shortest_distance[nx][ny] = shortest_distance[x][y] + 1
        result = max(result, shortest_distance[x][y]+1)
        
print(result - 1)
        
        
        
        
        
        