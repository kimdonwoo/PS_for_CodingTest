import sys
input = sys.stdin.readline

N,M = map(int, input().split())

train = [[0]*20 for _ in range(N)]

for _ in range(M):
    inp = list(map(int, input().split()))
    if inp[0] == 1:
        train[inp[1]-1][inp[2]-1] = 1
    elif inp[0] == 2:
        train[inp[1]-1][inp[2]-1] = 0
    elif inp[0] == 3:
        train[inp[1]-1].insert(0,0)
        train[inp[1]-1].pop()
        # for i in range(1,19):
        #     train[inp[1]-1][20-i] = train[inp[1]-1][19-i]
        # train[inp[1]-1][0] = 0
    elif inp[0] == 4:
        train[inp[1]-1].pop(0)
        train[inp[1]-1].append(0)
        # for i in range(1,19):
        #     train[inp[1]-1][i-1] = train[inp[1]-1][i]
        # train[inp[1]-1][19] = 0
        
for i in range(N):
    train[i] = tuple(train[i])

print(len(set(train)))

