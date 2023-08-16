import sys
import copy
input = sys.stdin.readline

def BNP(m):
    bnp_m = m
    nums = 0
    
    for i in range(14):
        if bnp_m // stock[i] > 0:
            nums += (bnp_m // stock[i])
            bnp_m -= (bnp_m // stock[i])*stock[i]

    return stock[13]*nums+bnp_m
    

def TIMING(m):
    timing_m = m
    nums = 0
    count = 0
    
    for i in range(1,14):
        if stock[i-1] < stock[i] : # 증가
            if count > 0 : count +=1
            else: count = 1
        elif stock[i-1] > stock[i] : # 감소
            if count < 0 : count -=1
            else: count = -1      
        else : count = 0
        
        if count <= -3: # 구매 
            nums += (timing_m // stock[i]) # 이만큼 주식 구매
            timing_m -= (timing_m // stock[i])*stock[i]
        
        if count >= 3: # 판매
            timing_m += nums*stock[i]
            nums = 0
    
    return stock[13]*nums+timing_m
    


money = int(input())
stock = list(map(int,input().split()))

A = BNP(money)
B = TIMING(money)

if A > B :
    print("BNP")
elif A < B :
    print("TIMING")
else :
    print("SAMESAME")
