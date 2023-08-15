import heapq

min_heap = [5,3,9,4,1,2,6]
heapq.heapify(min_heap) # 리스트를 min heap 구현해줌 -> O(N)
heapq.heappop(min_heap) # O(logN)
heapq.heappush(min_heap,1) #마지막 노드에 추가하고 sift up -> O(logN)

max_heap = [5,3,9,4,1,2,6]
#max_heap은 모든 원소 - 붙이고 위랑 똑같이 하고 꺼내고 나서 - 다시 곱하면 됨

