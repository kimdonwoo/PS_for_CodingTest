import sys
input = sys.stdin.readline

n,m = map(int,input().split())

#print("n : ",n," / m : ",m)

card=list(map(int,input().split()))
#print(card)

ans = 0

for i in range(n) :
    for j in range (i+1,n) :
        for k in range(j+1,n) :
            x = card[i]+card[j]+card[k]
            if x <= m and x > ans :
                ans = x
                
print(ans)

