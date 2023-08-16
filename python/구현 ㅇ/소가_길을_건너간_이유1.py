import sys
input = sys.stdin.readline

N = int(input())
cow = dict()
count = 0

for _ in range(N):
    a, b = map(int,input().split())
    if a in cow.keys():
        if b != cow[a]:
            count +=1
            cow[a] = b
    else :
        cow[a] = b
        
print(count)
