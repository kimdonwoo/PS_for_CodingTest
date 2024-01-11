def bfs(x,y):
    q = deque()
    q.append((x,y))
    
    while q :
        x,y = q.popleft()
        
        for i in range(4):
            nx = x+dx[i]
            ny = y+dy[i]
            # 미로 찾기 공간을 벗어난 경우 무시
            if nx < 0 or ny < 0 or nx >= n or ny >= m :
                continue
            # 벽인 경우
            if shortest_distance[nx][ny] == 0:
                continue
            # 해당 노드를 처음 방문하는 경우 최단 거리 기록
            if shortest_distance[nx][ny] == 1:
                shortest_distance[nx][ny] = shortest_distance[x][y] +1
                q.append((nx,ny))
    
    return shortest_distance[n-1][m-1]
    

from collections import deque

n, m =map(int,input().split())

shortest_distance = []
for i in range(n) :
    shortest_distance.append(list(map(int,input())))
    
dx = [-1,1,0,0]
dy = [0,0,-1,1]

print(bfs(0,0))