import heapq


def dijkstra(graph, start, final):
    costs = {} # 현재 노드들의 최단거리 값
    pq = [] # 리스트로 만든 우선순위 큐!
    heapq.heappush(pq, (0,start)) # 1. 우선순위큐에 시작노드 추가
    
    while pq:
        cur_cost, cur_v = heapq.heappop(pq) # 2. 우선순위가 가장 높은 노드 추출
        if cur_v not in costs: # 3. 방문 여부 확인
            costs[cur_v] = cur_cost # 4. 비용 업데이트
            for cost, next_v in graph[cur_v]: # 5. 현재 노드와 연결된 노드 우선순위 큐에 추가
                next_cost = cur_cost+cost
                heapq.heappush(pq, (next_cost, next_v))
    return costs[final] # 6. 목적지에 기록된 비용 반환


