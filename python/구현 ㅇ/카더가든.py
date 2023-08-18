import sys
input = sys.stdin.readline

n,m = map(int,input().split())
a,b,c = map(int,input().split())

ground = [list(map(int,input().split())) for _ in range(n)]

# 끝 : grounds[x+a][y+b+c]
def one(grounds,x,y,a,b,c,res):
    sum = 0
    for i in range(a):
        for j in range(b+c):
            if(sum > res) : return sys.maxsize
            sum+=grounds[x+i][y+j]
    return sum;

# 끝 : grounds[x+a+b][y+c+a]
def two(grounds,x,y,a,b,c,res):
    sum = 0
    for i in range(a):
        for j in range(c):
            if(sum > res) : return sys.maxsize
            sum+=grounds[x+i][y+j]
    for i in range(b):
        for j in range(a):
            if(sum > res) : return sys.maxsize
            sum+=grounds[x+a+i][y+c+j]
    return sum;

# 끝 : grounds[x+a+c][y+b+a]
def three(grounds,x,y,a,b,c,res):
    sum = 0
    for i in range(a):
        for j in range(b):
            if(sum > res) : return sys.maxsize
            sum+=grounds[x+i][y+j]
    for i in range(c):
        for j in range(a):
            if(sum > res) : return sys.maxsize
            sum+=grounds[x+a+i][y+b+j]
    return sum;


res = sys.maxsize
for i in range(n):#7
    for j in range(m): #6
        if i+a-1 < n and j+b+c-1 < m :
            res = min(res,one(ground,i,j,a,b,c,res))
        if i+a+b-1 < n and j+c+a-1 < m :
            res = min(res,two(ground,i,j,a,b,c,res))
        if i+a+c-1 < n and j+b+a-1 < m :
            res = min(res,three(ground,i,j,a,b,c,res))
        

print(res)