import sys
input =sys.stdin.readline

n = int(input())

ext=dict()

for _ in range(n):
    a,b =input().rstrip().split('.')
    if b in ext.keys():
        ext[b] = ext[b] + 1
    else :
        ext[b] = 1
        

sor = sorted(ext.keys())
for i in sor :
    print(i,ext[i])

