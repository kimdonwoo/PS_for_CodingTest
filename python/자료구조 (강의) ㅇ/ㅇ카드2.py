from collections import deque

n = int(input())
q= deque([i+1 for i in range(n)]) # deque는 popleft랑 append
print(q)

while len(q) > 1:
    q.popleft()
    q.append(q.popleft())

print(*q)