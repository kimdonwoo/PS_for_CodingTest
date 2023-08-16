import sys
input = sys.stdin.readline

def check(a,b):
    global count
    global visited 
    
    visited[a][b] = True
    
    if visited[a][0] and visited[a][1] and visited[a][2] and visited[a][3] and visited[a][4] :
        count +=1
    if visited[0][b] and visited[1][b] and visited[2][b] and visited[3][b] and visited[4][b] :
        count +=1
    if a == b :
        if visited[0][0] and visited[1][1] and visited[2][2] and visited[3][3] and visited[4][4] :
            count +=1
    if a+b == 4:
        if visited[4][0] and visited[3][1] and visited[2][2] and visited[1][3] and visited[0][4] :
            count +=1
    
    

ground = [list(map(int,input().split())) for _ in range(5)]
visited = [[False]*5 for _ in range(5)]
count = 0

point = [list(map(int,input().split())) for _ in range(5)]


for i in range(5):
    for j in range(5):
        # 같으면 여기로
        flag = 0
        for a in range(5):
            for b in range(5):
                if flag == 0 and ground[a][b] == point[i][j] :
                    check(a,b)
                    
                    if count >= 3 :
                        print(5*i+j+1)
                        exit()
                    flag = 1




