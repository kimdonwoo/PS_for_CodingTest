n = int(input())
person=[tuple(map(int,input().split())) for _ in range(n)]
    
res = []
for i in person:
    count = 1
    for j in person :
        if i[0] < j[0] and i[1] < j[1]:
            count+=1
    res.append(count)
     
print(*res)