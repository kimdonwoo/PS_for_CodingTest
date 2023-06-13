from collections import deque

def path(s):
    arr = []
    
    for _ in range(visited[s]+2):
        arr.append(s)
        s = move[s]
        
    print(' '.join(map(str, arr[::-1])))

def bfs():
    q = deque()
    q.append(N)
    
    while q:
        s = q.popleft()
        if s == K:
            print(visited[s] + 1)
            path(s)
            return s
        
        for i in (s+1, s-1, 2*s):
            if 0<= i < 100001 and visited[i] == -1 :
                visited[i] = visited[s] + 1
                q.append(i)
                # move에 이전 위치를 넣음
                move[i] = s


N, K = map(int, input().split())
visited=[-1 for _ in range(100001)]
move = [0]*100001
bfs()