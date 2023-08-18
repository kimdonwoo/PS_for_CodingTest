import heapq

# input
times =[[2,1,2],[2,3,5],[2,4,1],[4,3,3]]
n = 4
k = 2

def networkDelayTime(times, n, k):
    # 1. 그래프 implement O(times.length)
    graph = {}
    for time in times:
        graph[time[0]].append((time[2],time[1]))
    
    # 2. 다익스트라 알고리즘 O(ElogE)
    costs = {} # 현재 노드들의 최단거리 값
    pq = [] # 리스트로 만든 우선순위 큐!
    heapq.heappush(pq, (0,k)) # 1. 우선순위큐에 시작노드 추가
    
    while pq:
        cur_cost, cur_node = heapq.heappop(pq) # 2. 우선순위가 가장 높은 노드 추출
        if cur_node not in costs: # 3. 방문 여부 확인
            costs[cur_node] = cur_cost # 4. 비용 업데이트
            for cost, next_node in graph[cur_node]: # 5. 현재 노드와 연결된 노드 우선순위 큐에 추가
                next_cost = cur_cost+cost
                heapq.heappush(pq, (next_cost, next_node))

    # 3. 방문 못한 노드 찾기 O(n)
    for node in range(1,n+1):
        if node not in costs:
            return -1
        
    # 4. 최소값중에서 최대값 구하기 O(n)
    return max(costs.values)