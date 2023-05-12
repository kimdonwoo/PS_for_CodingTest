from collections import deque
            
n = int(input())
q= deque([i+1 for i in range(n)])

while len(q) > 1:
    q.popleft()
    q.append(q.popleft())

print(*q)