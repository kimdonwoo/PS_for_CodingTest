from collections import deque


def shortestPathBinaryMatrix(grid):
    shortest_path = -1
    row = len(grid)
    col = len(grid[0])
    dx = [0,0,1,-1,1,-1,1,-1]
    dy = [1,-1,0,0,-1,1,1,-1]
    
    if grid[0][0] != 0 or grid[row-1][col-1] != 0:
        return shortest_path
    
    visited = [[False]*col for _ in range(row)]
    
    q = deque()
    q.append((0,0,1))
    visited[0][0] = True
    
    while  q:
        cur_x,cur_y, cur_len = q.popleft()
        
        # 목적지 도착
        if cur_x == row-1 and cur_y == col-1 :
            shortest_path = cur_len
            break
        
        for i in range(8):
            next_x = cur_x+dx[i]
            next_y = cur_y+dy[i] 
            
            if next_x >= 0 and next_x < row and next_y > = 0 and next_y < col:# 먼저 그리드 범위 체크
                if grid[next_x][next_y] == 0 and visited[next_x][next_y] == False:    # 0인지 확인 + visited 확인
                    q.append((next_x,next_y,cur_len+1))
                    visited[next_x][next_y] = True   
    
    return shortest_path

'''




'''












