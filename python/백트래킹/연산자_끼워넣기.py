import sys
input = sys.stdin.readline

n = int(input())
num = list(map(int,input().split()))
op = list(map(int,input().split()))

max_value = -1e9
min_value = 1e9

def dfs(i,res):
    global op,max_value,min_value

    if(i == n):
        max_value = max(max_value,res)
        min_value = min(min_value,res)
    else:
        if op[0]>0:
            op[0] -= 1
            dfs(i+1,res+num[i])
            op[0] += 1
        
        if op[1]>0:
            op[1] -= 1
            dfs(i+1,res-num[i])
            op[1] += 1
        
        if op[2]>0:
            op[2] -= 1
            dfs(i+1,res*num[i])
            op[2] += 1
        
        if op[3]>0:
            op[3] -= 1
            dfs(i+1,int(res/num[i]))
            op[3] += 1
        

dfs(1,num[0])

print(max_value)
print(min_value)

"""
    백트래킹 원리 : 재귀 들어가기전 적용하고 재귀 나와서 취소하기

"""








