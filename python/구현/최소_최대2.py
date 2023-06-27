import sys
input = sys.stdin.readline

N = int(input())

for _ in range(N):
    i = int(input())
    nums = list(map(int,input().split()))
    
    print(min(nums),max(nums))
    
    
