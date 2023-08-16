# 외워라 !!! #10번 반복 필수!

from collections import deque

def bfs(graph,start_v):
    visited = [start_v]
    queue = deque(start_v)
    
    while queue:
        cur_v = queue.popleft()
        for v in graph[cur_v]:
            if v not in visited:
                visited.append(v)
                queue.append(v)
    return visited

bfs(graph, 'A')    



'''
요약하자면
체크하고 방문하고 큐 넣기
'''

















