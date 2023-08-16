"""
    (1,1) (1,2) 시작
    
    (N,N) 도착하는 방법의 수 출력
"""
from collections import deque

pipe = [(0,0),(0,1)]
N = int(input())
ground = [list(map(int, input().split())) for _ in range(N)]

q= deque()
q.append(pipe)

N -=1
count = 0

while q :
    state = q.popleft()
    if state[1][0] == N and state[1][1] == N:
        count+=1
        continue
        
    if state[0][0] == state[1][0] : # 가로
    
        # 가로 이동
        if state[1][1] + 1 <= N and ground[state[1][0]][state[1][1] + 1] == 0:
            q.append([(state[1][0],state[1][1]),(state[1][0],state[1][1] + 1)])
            # 대각선 이동
            if state[1][0] + 1 <= N  and ground[state[1][0]][state[1][1] + 1] + ground[state[1][0]+1][state[1][1]] + ground[state[1][0]+1][state[1][1] + 1] == 0 :
                    q.append([(state[1][0],state[1][1]),(state[1][0]+1,state[1][1] + 1)])
                
    elif state[0][1] == state[1][1] : # 세로
        # 세로 이동
        if state[1][0] + 1 <= N and ground[state[1][0]+1][state[1][1]] == 0:
            q.append([(state[1][0],state[1][1]),(state[1][0]+1,state[1][1])])
            # 대각선 이동
            if state[1][1] + 1 <= N and ground[state[1][0]][state[1][1] + 1]+ ground[state[1][0]+1][state[1][1]]+ground[state[1][0]+1][state[1][1] + 1] == 0 :
                q.append([(state[1][0],state[1][1]),(state[1][0]+1,state[1][1]+1)])
        
    else : 
        # 가로 이동
        if state[1][1] + 1 <= N and ground[state[1][0]][state[1][1] + 1] == 0:
            q.append([(state[1][0],state[1][1]),(state[1][0],state[1][1] + 1)])
        # 세로 이동
        if state[1][0] + 1 <= N and ground[state[1][0]+1][state[1][1]] == 0:
            q.append([(state[1][0],state[1][1]),(state[1][0]+1,state[1][1])])
            # 대각선 이동
            if state[1][1] + 1 <= N and  ground[state[1][0]][state[1][1] + 1] + ground[state[1][0]+1][state[1][1]] + ground[state[1][0]+1][state[1][1] + 1] == 0 :
                    q.append([(state[1][0],state[1][1]),(state[1][0]+1,state[1][1]+1)])
        
print(count)
