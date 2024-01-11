# DFS로 특정 노드를 방문하고 연결된 모든 노드들도 방문
def dfs(x,y):
    # 배열에 접근하기전에 매개변수 유효한 범위인지 확인
    if x <= -1 or x >= n or y <= -1 or y >= m :
        return False
    # 현재 노드를 아직 방문하지 않았다면
    if shortest_distance[x][y] == 0:
        # 방문 처리 하고 상하좌우 재귀적 호출
        shortest_distance[x][y] = 1
        dfs(x-1,y)
        dfs(x,y-1)
        dfs(x+1,y)
        dfs(x,y+1)
        return True
    return False
    
n,m = map(int,input().split())

# 2차원 리스트의 맵 정보 입력 받기
shortest_distance =[]
for i in range(n):
    shortest_distance.append(list(map(int,input())))
    
# 모든 노드에 음료수 채우기
result = 0
for i in range(n):
    for j in range(m):
        if dfs(i,j) == True:
            result+=1
            
print(result)