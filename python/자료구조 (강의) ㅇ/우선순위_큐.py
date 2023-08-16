# 힙을 사용하면 삽입,삭제 모두 O(logN)
# 힙 정렬시 O(NlogN)

import sys
import heapq 
# 파이썬은 기본적으로 최소힙으로 동작 -> 오른차순 정렬
# 최대힙으로 정렬하고 싶을때는 힙 삽입시 - 붙혀서 넣기

input = sys.stdin.readline

def heapsort(iterable):
    h = []
    result = []
    
    # 모든 원소를 차례대로 힙에 삽입
    for value in iterable:
        heapq.heappush(h,value)
        
    # 힙에 삽입된 모든 원소를 차례대로 꺼내어 담기
    for i in range(len(h)):
        result.append(heapq.heappop(h))
    
    return result

n = int(input())
arr = []

for i in range(n):
    arr.append(int(input()))

res = heapsort(arr)

for i in range(n):
    print(res[i])


