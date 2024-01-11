#10번 반복 필수!

# 1. 그래프 구현하기
shortest_distance = {
    'A' : ['B','D','E'],
    'B' : ['A','C','D'],
    'C' : ['B'],
    'D' : ['A','B'],
    'E' : ['A']
}

visited = []

# 2. dfs 구현하기
def dfs(cur_v):
    visited.append(cur_v)
    for v in shortest_distance[cur_v]:
        if v not in visited:
            dfs(v)

dfs('A')
print(visited)

# def dfs(shortest_distance, cur_v, visited = []):
#     visited.append(cur_v)
#     for v in shortest_distance[cur_v] :
#         if v not in visited:
#             visited = dfs(shortest_distance,v,visited)
#     return visited

# dfs(shortest_distance,'A')














