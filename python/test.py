import sys
input = sys.stdin.readline

s = "159023"
N = 3
stri = list(map(int,s))
res = 0

for i in range(len(stri)+1-N):
    num = 0
    for j in range(N):
        if stri[i+j] <= N :
            num = num*10 + stri[i+j]
                    
    if len(list(map(int,str(num)))) != len(set(list(map(int,str(num))))) :
        continue
    if num//(10**(N-1)) != 0: 
        res = max(res,num)


if res == 0 : print(-1)
else : print(res)

if len(s) == 0 : print(0)