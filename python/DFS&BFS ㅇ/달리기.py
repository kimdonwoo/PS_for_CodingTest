import sys
from collections import deque
input = sys.stdin.readline

n,m,k = map(int,input().split())

dx=[0,0,1,-1]
dy=[1,-1,0,0]

visited=[[-1]*m for _ in range(n)]
graph=[list(input()) for _ in range(n)]

start_x,start_y,end_x,end_y = [int(x)-1 for x in input().split()]

q= deque()
q.append((start_x,start_y))
visited[start_x][start_y]=0

while q:
    x,y=q.popleft()
    if x==end_x and y==end_y:
        print(visited[x][y])
        exit()
    
    # 방향을 잡고
    for i in range(4):
        # 이동할 거리수 잡기
        for num in range(1,k+1):
            nx = x+dx[i]*num
            ny = y+dy[i]*num
            # 범위 밖일 때
            if nx<0 or nx>=n or ny<0 or ny>=m: break
            # 벽일 때
            if graph[nx][ny]=='#':break
            # 처음 방문하는 곳이면
            if visited[nx][ny] == -1:
                q.append((nx,ny))
                visited[nx][ny]=visited[x][y]+1
            # 
            elif visited[nx][ny]==visited[x][y]+1:
                continue
            #
            else: break       

print(-1)
