import sys
input = sys.stdin.readline

"""
모든 경우에 대해서 다 따져보기!

"""

def cnt(x1,y1,x2,y2):
    return abs(x1-x2)+abs(y1-y2)

left =["qwert","asdfg","zxcv"]
right = ["0yuiop","0hjkl","bnm"]

left_dict = dict()
right_dict = dict()


for i in range (len(left)):
    for j in range(len(left[i])):
        temp = list(left[i])
        left_dict[temp[j]] = (i,j)

for i in range (len(right)):
    for j in range(len(right[i])):
        temp = list(right[i])
        right_dict[temp[j]] = (i,j)


x,y= input().split()
target = list(input().rstrip())

res = 0
for t in target:
    # 현재 위치 왼손은 x, 오른손은 y
    #dict
    if t in list(left[0])+list(left[1])+list(left[2]) :
        res += cnt(left_dict[x][0],left_dict[x][1],left_dict[t][0],left_dict[t][1])
        x = t
    else :
        res += cnt(right_dict[y][0],right_dict[y][1],right_dict[t][0],right_dict[t][1])
        y = t
        
print(res + len(target))



