from collections import deque

def bfs(graph,start,visited):
    # 큐 구현을 위해 deque 사용
    q = deque([start])
    visited[start] = True
    
    # 큐가 빌때까지 반복
    while q :
        # 큐에서 하나의 원소를 뽑아 출력
        v = q.popleft()
        print(v, end=' ')
        # 아직 방문하지 않은 인접한 원소들을 큐에 삽입
        for i in graph[v] :
            if not visited[i] :
                q.append(i)
                visited[i] = True
                
graph =[
    [],
    [2,3,8],
    [1,7],
    [1,4,5],
    [3,5],
    [3,4],
    [7],
    [2,6,8],
    [1,7],
]

visited = [False]*9

bfs(graph,1,visited)