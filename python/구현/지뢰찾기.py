import sys
input = sys.stdin.readline



def check(a,b):
    count = 0
    # 만약 ground의 a,b가 *이라면
    if ground[a][b] == "*":
        return -1
    for i in range(8):
        x = a+dx[i]
        y = b+dy[i]
        
        if x >=0 and y >=0 and x <N and y <N : # x,y 가 범위 이내이면
            if ground[x][y] == "*" :
                count +=1;
    
    
    
    return count;

N = int(input())
dx =[0,0,1,1,-1,-1,1,-1]
dy =[1,-1,0,-1,1,-1,1,0]

ground = [list(input()) for _ in range(N)]
now = [list(input()) for _ in range(N)]

fail = 0

for i in range(N):
    for j in range(N):
        if now[i][j] == "x":
            if check(i,j) >= 0 : 
                now[i][j] = check(i,j)
            else : 
                fail = 1

if fail == 0 :
    for i in range(N):
        for j in range(N):
            print(now[i][j], end='')
        print()
else :
    for i in range(N):
        for j in range(N):
            if ground[i][j] == "*":
                now[i][j] = "*"
                
    for i in range(N):
            for j in range(N):
                print(now[i][j], end='')
            print()
            
            