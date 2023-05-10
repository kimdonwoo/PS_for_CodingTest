# 소수찾기
import sys
input = sys.stdin.readline
import math

def check(number): # 소수라면 true 아니면 false
    if number == 1: return False
    
    for i in range(2,int(math.sqrt(number)+1)):
        # number를 i로 나누고
        if number%i ==0:
            return False
        
    return True    

n = int(input())
num = list(map(int,input().split()))
count=0

for i in num:
    if check(i): # i가 소수라면 count++
        count+=1
        
print(count)