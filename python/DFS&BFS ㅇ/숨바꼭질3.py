from collections import deque

N,K = map(int,input().split())

q =deque()
q.append(N)

'''
    방문 관리
    bfs에는 visited에 의미있는 값 저장 생각
    이 문제에서는 시간을 저장
'''
visited=[-1 for _ in range(100001)]
visited[N] = 0
    
while q :
    # 현재 위치 꺼내기
    s = q.popleft()
    
    # 도착 (bfs라서 도착하면 그게 최소값임)
    if s == K :
        print(visited[s])
        break
    
    # 현재 위치가 유효한 범위고, 아직 방문안한 곳일 때
    # 뒤로 가기 (+1초)
    if 0 <= s-1 < 100001 and visited[s-1] == -1:
        visited[s-1] = visited[s] + 1
        q.append(s-1)
    
    # 현재 위치가 유효한 범위고, 아직 방문안한 곳일 때
    # 2배 앞으로 가기 / 2*s가 다른 연산보다 더 높은 우선순위를 가지기 위함
    if 0 < s*2 < 100001 and visited[s*2] == -1:
        visited[s*2] = visited[s]
        q.appendleft(s*2)
    
    # 현재 위치가 유효한 범위고, 아직 방문안한 곳일 때
    # 앞으로 가기 (+1초)
    if 0 <= s+1 < 100001 and visited[s+1] == -1:
        visited[s+1] = visited[s] + 1
        q.append(s+1)


    """
        bfs : 어차피 q로 한 바퀴 쏵 돌아야하니깐
            큐를 사용해서 우선순위를 가질 문제에 대해서는(즉 시간에 영향이 없는 것들)에 대해서
            appendleft를 통해 왼쪽에 넣어야 함  
    """





