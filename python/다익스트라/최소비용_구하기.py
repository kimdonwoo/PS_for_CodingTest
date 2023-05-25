import sys
import heapq
input = sys.stdin.readline
INF = int(1e9)

N = int(input())
M = int(input())
graph = [[] for _ in range(N+1)]

for _ in range(M):
    a,b,c = map(int, input().split())
    graph[a].append((b,c))

start,end = map(int, input().split())
distance = [INF]*(N+1)


def dijkstra(start) :
    distance[start] = 0
    q = [(0,start)]
        
    while q:
        dist, now = heapq.heappop(q)

        if distance[now] < dist:
            continue
        
        for i in graph[now]:
            cost = distance[now] + i[1]
            if cost < distance[i[0]]:
                distance[i[0]] = cost
                heapq.heappush(q, (cost,i[0]))

dijkstra(start)

print(distance[end])










