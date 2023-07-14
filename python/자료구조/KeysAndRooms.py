from collections import deque

# visited를 어떻게 정할건가?

def canVisitAllRooms(rooms):
    visited = []
    
    def dfs(v):        
        visited.append(v)
        for next_v in rooms[v]:
            if next_v not in visited:
                dfs(next_v)
    
    dfs(0)
    if len(visited) == len(rooms) :
        return True
    else :
        return False
    #return visited

def canVisitAllRooms2(rooms):
    visited = [False]*len(rooms)
    
    def dfs(v):        
        visited[v] = True
        for next_v in rooms[v]:
            if visited[next_v] == False:
                dfs(next_v)
    
    dfs(0)
    if len(visited) == len(rooms) :
        return True
    else :
        return False
    #return visited


def canVisitAllRoomsDict(rooms):
    visited = {}
    
    def dfs(v):        
        visited[v] = True
        for next_v in rooms[v]:
            if next_v not in visited:
                dfs(next_v)
    
    dfs(0)
    if len(visited) == len(rooms) :
        return True
    else :
        return False
    #return visited
    
    
def canVisitAllRoomsBFS(rooms):
    visited = [False]*len(rooms)
    
    def bfs(v):
        q = deque()
        q.append(v)
        visited[v] = True
        
        while q:
            cur_v = q.popleft()
            for next_v in rooms[cur_v]:
                if visited[next_v] == False :
                    q.append(next_v)
                    visited[next_v] = True
    
    
    bfs(0)
    print(visited)
    
    return all(visited)
    
    


rooms = [[1,3],[2,4],[0],[4],[],[3,4]]
#print(canVisitAllRooms(rooms))
print(canVisitAllRoomsBFS(rooms))