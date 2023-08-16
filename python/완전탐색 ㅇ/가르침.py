import sys
from itertools import combinations
input = sys.stdin.readline

N,K = map(int,input().split())

arr = [ list(set(input().rstrip())) for _ in range(N)]

for word in arr :
    word.remove('a')
    word.remove('n')
    word.remove('t')
    word.remove('i')
    word.remove('c')

if K < 5 :
    print(0)
    exit()
else :
    K = K-5

merged = list(set(sum(arr,[])))

    
max_res = 0

if(K > len(merged)):
    K=len(merged)

for comp in combinations(merged,K):
    res = 0
    for x in arr:
        if set(x).difference(comp) == set() :
            res += 1
    max_res = max(max_res,res)
    
print(max_res)