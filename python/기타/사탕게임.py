N = int(input())

board = [list(input()) for _ in range(N)]
maxCnt = 0

def check():
    global maxCnt
    for i in range(N):
        cnt = 1
        for j in range (1,N):
            if board[i][j] == board[i][j-1]:
                cnt +=1
                maxCnt = max(cnt,maxCnt)
            else:
                cnt = 1
        
        cnt = 1
        for j in range(1,N):
            if board[j][i] == board[j-1][i] :
                cnt +=1
                maxCnt = max(cnt,maxCnt)
            else:
                cnt = 1

for i in range(N):
    for j in range(N):
        if j+1 < N:
            board[i][j],board[i][j+1] = board[i][j+1],board[i][j]    
            check()
            board[i][j],board[i][j+1] = board[i][j+1],board[i][j]    
            
        if i+1 < N:
            board[i][j],board[i+1][j] = board[i+1][j],board[i][j]    
            check()
            board[i][j],board[i+1][j] = board[i+1][j],board[i][j]    
            
print(maxCnt)








