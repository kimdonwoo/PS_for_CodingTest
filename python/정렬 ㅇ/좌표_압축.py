import sys
input = sys.stdin.readline

n = int(input())

number=list(map(int,input().split()))
#print(number)

number2 = sorted(list(set(number)))
#print(number2)

dic = {number2[i] : i for i in range(len(number2))}
#print(dic)

for i in number :
    print(dic[i] , end=' ')

