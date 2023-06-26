import sys
input = sys.stdin.readline

def check(y):
    global res
    if y%400 == 0:
        res = 1
    elif y%4 == 0 and y%100 !=0 :
        res = 1
    else : res = 0

year = int(input())
check(year)
print(res)









