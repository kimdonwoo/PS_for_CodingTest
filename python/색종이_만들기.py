import sys
input = sys.stdin.readline

def recur(x,y,m):
    if end(x,y) : return
    if check(x,y,m) : return
    
    recur(x,y,m//2)
    recur(x+(m//2),y,m//2)
    recur(x,y+(m//2),m//2)
    recur(x+(m//2),y+(m//2),m//2)
    
    return
        
def check(x,y,m):
    global white
    global blue
    color = paper[x][y]
    
    for i in range(m):
        for j in range(m):
            if paper[x+i][y+j] != color :
                return False
    if color == 1: blue +=1
    else : white+=1
    comp(x,y,m)
    
    return True
    
def comp(x,y,m):
    for i in range(m):
        for j in range(m):
            paper[x+i][y+j] = 2

def end(x,y):
    if paper[x][y] == 2 :
        return True
    return False

n = int(input())
paper = [list(map(int,input().split())) for _ in range(n)]
white = 0
blue = 0
recur(0,0,n)

print(white)
print(blue)